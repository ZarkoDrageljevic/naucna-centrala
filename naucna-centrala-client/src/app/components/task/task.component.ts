import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {TaskModel} from '../../model/task';
import {TaskService} from '../../services/task.service';

@Component({
  selector: 'app-task',
  templateUrl: './task.component.html',
  styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

  tasks = new Array<TaskModel>();

  constructor(private taskService: TaskService,
              private router: Router) {
  }

  ngOnInit() {
    this.getTasks();
  }

  getTasks(): any {
    this.taskService.getTasks().subscribe(res => {
      this.tasks = res;
    });
  }

  goToTask(task: TaskModel) {
    console.log(task.taskDefinitionKey);
    switch (task.taskDefinitionKey) {
      case 'ChooseMagazine': {
        this.router.navigate(['/magazine/choose', task.id]);
        break;
      }
      case 'PaperSubmissionTask': {
        this.router.navigate(['/paper/submit', task.id]);
        break;
      }
      case 'Resubmission': {
        this.router.navigate(['/paper/resubmit', task.id]);
        break;
      }
      case 'ThematicValidationOfPaper': {
        this.router.navigate(['/paper/tvalidation', task.id]);
        break;
      }
      case 'WhyIsRejected': {
        this.router.navigate(['/paper/reject-explanation', task.id]);
        break;
      }
      case 'FormatCheck': {
        this.router.navigate(['/paper/format-check', task.id]);
        break;
      }
      case 'FormatCorrection': {
        this.router.navigate(['/paper/format-correction', task.id]);
        break;
      }
      case 'ChooseReviewers': {
        this.router.navigate(['/paper/choose-reviewers', task.id]);
        break;
      }
      case 'ReviewPaper': {
        this.router.navigate(['/paper/review', task.id]);
        break;
      }
      case 'ChoseReviewer': {
        this.router.navigate(['/paper/additional-reviewer', task.id]);
        break;
      }
      case 'EditorReview': {
        this.router.navigate(['/paper/editor-review', task.id]);
        break;
      }
      case 'EditorReviewOfRevision': {
        this.router.navigate(['/paper/editor-reviews-review', task.id]);
        break;

      }
      case 'FixPaper': {
        this.router.navigate(['/paper/fix-paper', task.id]);
        break;
      }
      default: {
        console.log('Invalid choice');
        break;
      }
    }
  }

}

