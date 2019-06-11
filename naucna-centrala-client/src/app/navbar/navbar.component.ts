import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {PaperService} from '../services/paper.service';
import {LoggedUtils} from '../utils/loggedUtils';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isCollapsed = true;


  constructor(private router: Router,
              private paperService: PaperService) {
  }

  ngOnInit() {
  }

  isAuthenticated(): boolean {
    return LoggedUtils.getToken() != "";
  }

  isAuthor(): boolean {
    return LoggedUtils.getRole() == 'USER';
  }

  submitPaper() {
    this.router.navigate(['paper']);
  }

  startSubmissionProcess() {
    this.paperService.startPaperProcess().subscribe(res => {
      this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
        this.router.navigate(['task']));
      this.ngOnInit();
    });
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('login');
  }

  redirectToTask() {
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(() =>
      this.router.navigate(['task']));
  }
}
