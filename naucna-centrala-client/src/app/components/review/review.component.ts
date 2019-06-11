import { Component, OnInit } from '@angular/core';
import {FormField} from '../../model/form-field';
import {Paper} from '../../model/paper';
import {PaperService} from '../../services/paper.service';
import {TaskService} from '../../services/task.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import * as FileSaver from "file-saver"


@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  formFields = new Array<FormField>();
  taskId: string;
  paper: Paper;
  decisions = ["rejected", "majorFix", "smallFix", "accepted"];


  constructor(
    private paperService: PaperService,
    private taskService: TaskService,
    private toastrService: ToastrService,
    private router: Router,
    private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.taskId = params['taskId'];

      this.paperService.getPaperByTaskId(this.taskId).subscribe(res =>
        this.paper = res);
      this.getFormData();

    });
  }

  submitReview() {
    console.log(this.formFields)
    this.paperService.submitReview(this.taskId, this.formFields).subscribe(res => {
      this.toastrService.success('Format Validation Performed');
      this.router.navigateByUrl('task');
    });
  }

  private getFormData() {
    this.taskService.getFormData(this.taskId).subscribe(res => {
      console.log(res);
      res.formFields.forEach(function (formField) {
        if (formField.name == "comment" || formField.name == "commentToEditor" || formField.name == "mark"){
          formField.value.value = "";
        }
      });
      this.formFields = res.formFields;
    });
  }

  download() {
    this.paperService.downloadPaper(this.taskId).subscribe(res => {this.downloadFile(res)}, error1 => {
      this.toastrService.error(error1.error.message);
    });
  }

  downloadFile(data) {
    const blob = new Blob([data], { type: 'application/octet-stream' });
    FileSaver.saveAs(blob, `${this.paper.title}.pdf`);
  }


}
