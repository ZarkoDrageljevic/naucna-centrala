import {Component, OnInit} from '@angular/core';
import {TaskService} from '../../services/task.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormField} from '../../model/form-field';
import {PaperService} from '../../services/paper.service';
import {ToastrService} from 'ngx-toastr';
import {Paper} from '../../model/paper';

@Component({
  selector: 'app-tvalidation',
  templateUrl: './tvalidation.component.html',
  styleUrls: ['./tvalidation.component.css']
})
export class TvalidationComponent implements OnInit {

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

  submitTvalidationAnswer() {
    console.log(this.formFields)
    this.paperService.submitTvalidation(this.taskId, this.formFields).subscribe(res => {
      this.toastrService.success('Thematic Validation Performed');
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
