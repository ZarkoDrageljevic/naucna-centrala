import {Component, OnInit} from '@angular/core';
import {PaperService} from '../../services/paper.service';
import {ScienceFieldService} from '../../services/science-field.service';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-reject-explanation',
  templateUrl: './reject-explanation.component.html',
  styleUrls: ['./reject-explanation.component.css']
})
export class RejectExplanationComponent implements OnInit {
  reason: String;

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
    });
  }

  submitRejection() {
    let formData: FormData = this.prepareFormData();
    this.paperService.rejectPaperThematic(this.taskId, formData).subscribe(result => {
      this.toastr.success('Paper is rejected');
      this.router.navigate(['task']);
    });
  }

  prepareFormData() {
    let formData = new FormData();
    formData.append('rejectionReason', new Blob([JSON.stringify(this.reason)], {type: 'application/json'}));
    return formData;
  }
}
