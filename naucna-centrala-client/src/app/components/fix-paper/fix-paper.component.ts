import {Component, OnInit} from '@angular/core';
import {Paper} from '../../model/paper';
import {PaperService} from '../../services/paper.service';
import {ScienceFieldService} from '../../services/science-field.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {CoAuthor} from '../../model/co-author';
import {Review} from '../../model/review';

@Component({
  selector: 'app-fix-paper',
  templateUrl: './fix-paper.component.html',
  styleUrls: ['./fix-paper.component.css']
})
export class FixPaperComponent implements OnInit {

  paper: Paper = new Paper();
  file: File;
  reviews = new Array<Review>();

  taskId: string;
  commentAnswer: string;

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
      this.paperService.getPaperByTaskId(this.taskId).subscribe(res => {
        this.paper = res;
        console.log(this.paper);
      });
      this.getReviews();
    });
  }

  fileChange(event) {
    this.file = event.target.files[0];
  }

  create() {
    let formData: FormData = this.prepareFormData();
    this.paperService.submitRevision(this.taskId, formData).subscribe(result => {
      this.toastr.success('Paper Revision submitted ');
      this.router.navigate(['task']);
    });
  }

  prepareFormData() {
    let formData = new FormData();
    formData.append('file', this.file);
    formData.append('data', new Blob([JSON.stringify(this.paper)], {type: 'application/json'}));
    formData.append('commentAnswer', new Blob([JSON.stringify(this.commentAnswer)], {type: 'application/json'}));
    return formData;
  }

  private getReviews() {
    this.paperService.getPaperReviews(this.taskId).subscribe(result => {
      this.reviews = result;
    });
  }


}


