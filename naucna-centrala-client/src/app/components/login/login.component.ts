import {Component, OnInit} from '@angular/core';
import {User} from '../../model/user';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = new User();


  constructor(private userService: UserService,
              private router: Router,
              private toastr: ToastrService) {
  }

  ngOnInit() {
  }

  login() {
    this.userService.login(this.user)
      .subscribe(
        response => {
          console.log(JSON.stringify(response));
          localStorage.setItem('loggedUser', JSON.stringify(response.body));
          localStorage.setItem('token', response.headers.get('JWToken'));
          this.toastr.success(`Welcome ${this.user.username}`);
          this.router.navigateByUrl('/home');
        },
        err => {
        });
  }
}
