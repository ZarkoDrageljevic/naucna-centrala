import { Component, OnInit } from '@angular/core';
import {Reviewer} from '../../model/reviwer';
import {PaperService} from '../../services/paper.service';
import {ScienceFieldService} from '../../services/science-field.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {Review} from '../../model/review';
import {FormField} from '../../model/form-field';
import {TaskService} from '../../services/task.service';

@Component({
  selector: 'app-editor-review',
  templateUrl: './editor-review.component.html',
  styleUrls: ['./editor-review.component.css']
})
export class EditorReviewComponent implements OnInit {

  reviews = new Array<Review>();

  formFields = new Array<FormField>();
  taskId: string;
  decisions = ["rejected", "majorFix", "smallFix", "accepted"];

  constructor(private paperService: PaperService,
              private scienceFieldService: ScienceFieldService,
              private toastrService: ToastrService,
              private taskService: TaskService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.taskId = params['taskId'];
      this.getReviews();
      this.getFormData();
    });
  }


  submitReview() {
    console.log(this.formFields)
    this.paperService.submitEditorReview(this.taskId, this.formFields).subscribe(res => {
      this.toastrService.success('Editor Review Performed');
      this.router.navigateByUrl('task');
    });
  }


  private getReviews() {
    this.paperService.getPaperReviews(this.taskId).subscribe(result => {
      this.reviews = result;
    });
  }

  private getFormData() {
    this.taskService.getFormData(this.taskId).subscribe(res => {
      console.log(res);
      this.formFields = res.formFields;
    });
  }
}
