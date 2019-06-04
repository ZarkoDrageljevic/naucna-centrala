import { Component, OnInit } from '@angular/core';
import {FormField} from '../../model/form-field';
import {Paper} from '../../model/paper';
import {PaperService} from '../../services/paper.service';
import {TaskService} from '../../services/task.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-format-validation',
  templateUrl: './format-validation.component.html',
  styleUrls: ['./format-validation.component.css']
})
export class FormatValidationComponent implements OnInit {

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
    console.log(this.formFields)
    this.paperService.submitFormatValidation(this.taskId, this.formFields).subscribe(res => {
      this.toastrService.success('Format Validation Performed');
      this.router.navigateByUrl('task');
    });
  }

  private getFormData() {
    this.taskService.getFormData(this.taskId).subscribe(res => {
      console.log(res);
      this.formFields = res.formFields;
    });
  }
}
