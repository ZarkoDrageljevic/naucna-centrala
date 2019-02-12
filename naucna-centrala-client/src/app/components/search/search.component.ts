import { Component, OnInit } from '@angular/core';
import { SearchService } from 'src/app/services/search.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { saveAs } from "file-saver";
import { UploadMagazineService } from 'src/app/services/upload-magazine.service';
import { DownloadFile } from 'src/app/model/download-file';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  simpleQueryForm: FormGroup;
  advancedQueryForm: FormGroup;
  simpleQuery: SimpleQuery;
  advancedQuery: AdvancedQuery;
  showData: Array<any>;
  searchType: String;

  searchIn: any[] = [
    { id: 0, value: "text" },
    { id: 1, value: "title" },
    { id: 2, value: "apstract" },
    { id: 3, value: "magazine" },
    { id: 4, value: "keywords" },
    { id: 5, value: "scienceField" },
    { id: 6, value: "authors" }
    
  ]

  operation: any[] = [
    { id: 0, value: "AND" },
    { id: 1, value: "OR" },
    { id: 2, value: "NOT" },
  ]

  constructor(private searchService: SearchService, private uploadMagazineService: UploadMagazineService,
    private fb: FormBuilder) { }

  ngOnInit() {
    this.searchType = "simple";
    this.createSimpleForm();
    this.createAdvancedForm();
  }

  searchTypeSimple() {
    this.searchType = "simple"
    this.createSimpleForm();
  }
  searchTypePhraze() {
    this.searchType = "phraze"
    this.createSimpleForm();
  }
  searchTypeBoolean() {
    this.searchType = "boolean"
    this.createAdvancedForm();
  }

  createSimpleForm() {
    this.simpleQueryForm = this.fb.group({
      field: new FormControl('', [Validators.required]),
      value: new FormControl('', [Validators.required]),
    });
  }

  createAdvancedForm() {
    this.advancedQueryForm = this.fb.group({
      field1: new FormControl('', [Validators.required]),
      value1: new FormControl('', [Validators.required]),
      field2: new FormControl('', [Validators.required]),
      value2: new FormControl('', [Validators.required]),
      operation: new FormControl('', [Validators.required]),
    });
  }

  simpleForm() {
    if (this.searchType == 'simple') {
      this.searchSimple();
    } else {
      this.searchPhraze();
    }
  }

  searchSimple() {
    this.simpleQuery = this.simpleQueryForm.value;
    this.simpleQuery.field = this.searchIn[this.simpleQueryForm.value.field.toString()].value
    console.log(this.simpleQueryForm)
    console.log(this.simpleQuery)
    this.searchService.searchMatch(this.simpleQuery).subscribe(data => {
      this.showData = data;
      console.log(data);
    })
  }

  searchPhraze() {
    this.simpleQuery = this.simpleQueryForm.value;
    this.simpleQuery.field = this.searchIn[this.simpleQueryForm.value.field.toString()].value
    this.searchService.searchPhraze(this.simpleQuery).subscribe(data => {
      this.showData = data;

      console.log(data);
    })
  }

  searchBoolean() {
    this.advancedQuery = this.advancedQueryForm.value;
    this.advancedQuery.field1 = this.searchIn[this.advancedQueryForm.value.field1.toString()].value
    this.advancedQuery.field2 = this.searchIn[this.advancedQueryForm.value.field2.toString()].value
    this.advancedQuery.operation = this.operation[this.advancedQueryForm.value.operation.toString()].value


    this.searchService.searchBoolean(this.advancedQuery).subscribe(data => {
      this.showData = data;

      console.log(data);
    })
  }

  download(filename: String) {
    console.log(filename)
    var downloadFile = new DownloadFile();
    downloadFile.fileName = filename;

    this.uploadMagazineService.download(downloadFile)
      .subscribe(
        data => {
          this.downloadFiles(data, filename);
        },
        error => {
          console.log(error);
        }
      );
  }

  downloadFiles(data, fileName) {
    var blob = new Blob([data], { type: 'application/pdf' });
    saveAs(blob, fileName);
  }


}
