import { Component, OnInit } from '@angular/core';
import { UploadMagazineService } from 'src/app/services/upload-magazine.service';
import { FormGroup, Validators, FormBuilder, FormArray } from '@angular/forms';

@Component({
  selector: 'app-upload-magazine',
  templateUrl: './upload-magazine.component.html',
  styleUrls: ['./upload-magazine.component.css']
})
export class UploadMagazineComponent implements OnInit {
  form: FormGroup;
  loading: boolean = false;
  uploadFile: UploadFile;

  constructor(private uploadMagazineService: UploadMagazineService, private fb: FormBuilder) {
    this.createForm();
  }

  ngOnInit() {
    this.createForm();
  }

  createForm() {
    this.form = this.fb.group({
      magazine: ['', Validators.required],
      scientificField: ['', Validators.required],
      apstrakt: ['', Validators.required],
      keywords: ['', Validators.required],
      authors: this.fb.array([]),
      files: null
    });
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      let file = event.target.files[0];
      this.form.get('files').setValue(file);
    }
  }

  private prepareSave(): any {
    let input = new FormData();
    input.append('magazine', this.form.get('magazine').value);
    input.append('scientificField', this.form.get('scientificField').value);
    input.append('keywords', this.form.get('keywords').value);
    input.append('apstrakt', this.form.get('apstrakt').value);
    input.append('files', this.form.get('files').value);
    input.append('authors', JSON.stringify(this.form.get('authors').value));
    return input;
  }

  onSubmit() {
    this.uploadFile = this.prepareSave();
    console.log(this.uploadFile);
    this.loading = true;
    setTimeout(() => {
      this.loading = false;
    }, 5000);
    this.uploadMagazineService.uploadPaper(this.uploadFile).subscribe(
      data => {
        console.log(data)
      },
      error => {
        console.log(error);
      }
    );

  }

  initItemRows() {
    return this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required]
    });
  }

  addNewRow() {
    const control = <FormArray>this.form.controls['authors'];
    control.push(this.initItemRows());
  }

  deleteRow(index: number) {
    const control = <FormArray>this.form.controls['authors'];
    control.removeAt(index);
  }


}