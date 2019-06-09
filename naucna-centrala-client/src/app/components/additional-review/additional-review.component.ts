import { Component, OnInit } from '@angular/core';
import {Reviewer} from '../../model/reviwer';
import {PaperService} from '../../services/paper.service';
import {ScienceFieldService} from '../../services/science-field.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-additional-review',
  templateUrl: './additional-review.component.html',
  styleUrls: ['./additional-review.component.css']
})
export class AdditionalReviewComponent implements OnInit {

  reviewers = new Array<Reviewer>();
  reviewer = new Reviewer();
  chosenReviewers = new Array<Reviewer>();

  taskId: string;

  constructor(private paperService: PaperService,
              private scienceFieldService: ScienceFieldService,
              private toastr: ToastrService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.taskId = params['taskId'];
      this.getReviewers();
    });
  }


  submit() {
    let formData: FormData = this.prepareFormData();
    this.paperService.chooseReviewers(this.taskId, formData).subscribe(result => {
      this.toastr.success('Reviewers Chosen');
      this.router.navigate(['task']);
    });
  }

  prepareFormData() {
    let formData = new FormData();
    formData.append('reviewers', new Blob([JSON.stringify(this.chosenReviewers)], {type: 'application/json'}));
    return formData;
  }

  insertReviewer(reviewer) {
    this.chosenReviewers.push(reviewer);
    console.log(this.chosenReviewers)
  }

  removeReviewer(reviewer: Reviewer) {
    const index: number = this.chosenReviewers.indexOf(reviewer);
    if (index !== -1) {
      this.chosenReviewers.splice(index, 1);
    }
  }

  private getReviewers() {
    this.paperService.getReviewers(this.taskId).subscribe(result => {
      this.reviewers = result;
    });
  }
}
