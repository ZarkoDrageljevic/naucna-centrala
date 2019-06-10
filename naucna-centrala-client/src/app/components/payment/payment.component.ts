import {Component, OnInit} from '@angular/core';
import {FormField} from '../../model/form-field';
import {Paper} from '../../model/paper';
import {PaperService} from '../../services/paper.service';
import {TaskService} from '../../services/task.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

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

      this.getFormData();

    });
  }

  submitPayment() {
    this.paperService.submitPayment(this.taskId,  new Array<FormField>()).subscribe(res => {
      this.toastrService.success('Payment Successful');
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
