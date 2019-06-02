import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {PaperService} from '../../services/paper.service';
import {Paper} from '../../model/paper';
import {ScienceField} from '../../model/science-field';
import {CoAuthor} from '../../model/co-author';
import {ScienceFieldService} from '../../services/science-field.service';

@Component({
  selector: 'app-paper-submission',
  templateUrl: './paper-submission.component.html',
  styleUrls: ['./paper-submission.component.css']
})
export class PaperSubmissionComponent implements OnInit {

  paper: Paper = new Paper();
  file: File;
  scienceFields = new Array<ScienceField>();
  coauthor = new CoAuthor();

  taskId: string;

  constructor(private paperService: PaperService,
              private scienceFieldService: ScienceFieldService,
              private toastr: ToastrService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.paper.coauthors = new Array<CoAuthor>();
    this.route.params.subscribe(params => {
      this.taskId = params['taskId'];
      this.getScienceFields();
    });
  }

  fileChange(event) {
    this.file = event.target.files[0];
  }

  create() {
    let formData: FormData = this.prepareFormData();
    this.paperService.createPaper(this.taskId, formData).subscribe(result => {
      this.toastr.success('Paper is successfuly submited');
      this.router.navigate(['task']);
    });
  }

  prepareFormData() {
    let formData = new FormData();
    formData.append('file', this.file);
    formData.append('data', new Blob([JSON.stringify(this.paper)], {type: 'application/json'}));
    return formData;
  }

  insertNewCoAuthor() {
    this.paper.coauthors.push({...this.coauthor});
  }

  removeCoauthor(coauthor: CoAuthor) {
    const index: number = this.paper.coauthors.indexOf(coauthor);
    if (index !== -1) {
      this.paper.coauthors.splice(index, 1);
    }
  }

  private getScienceFields() {
    this.scienceFieldService.findAll().subscribe(result => {
      this.scienceFields = result;
    });
  }

}
