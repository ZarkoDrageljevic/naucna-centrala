import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {PaperService} from '../../services/paper.service';
import {TaskService} from '../../services/task.service';
import {Paper} from '../../model/paper';
import {FormField} from '../../model/form-field';
import * as FileSaver from "file-saver"


@Component({
  selector: 'app-editor-revision-review',
  templateUrl: './editor-revision-review.component.html',
  styleUrls: ['./editor-revision-review.component.css']
})
export class EditorRevisionReviewComponent implements OnInit {

  formFields = new Array<FormField>();
  taskId: string;
  paper: Paper;

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

  submitFormatAnswer() {
    console.log(this.formFields);
    this.paperService.submitReviewOfRevision(this.taskId, this.formFields).subscribe(res => {
      this.toastrService.success('Review of Revision performed');
      this.router.navigateByUrl('task');
    });
  }

  private getFormData() {
    this.taskService.getFormData(this.taskId).subscribe(res => {
      console.log(res);
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
