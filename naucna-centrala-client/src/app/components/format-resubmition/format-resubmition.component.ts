import {Component, OnInit} from '@angular/core';
import {Paper} from '../../model/paper';
import {CoAuthor} from '../../model/co-author';
import {PaperService} from '../../services/paper.service';
import {ScienceFieldService} from '../../services/science-field.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-format-resubmition',
  templateUrl: './format-resubmition.component.html',
  styleUrls: ['./format-resubmition.component.css']
})
export class FormatResubmitionComponent implements OnInit {

  paper: Paper = new Paper();
  file: File;

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
      this.paperService.getPaperByTaskId(this.taskId).subscribe(res => {
        this.paper = res;
        console.log(this.paper)
      });
    });
  }

  fileChange(event) {
    this.file = event.target.files[0];
  }

  create() {
    let formData: FormData = this.prepareFormData();
    this.paperService.resubmitFormat(this.taskId, formData).subscribe(result => {
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


}
