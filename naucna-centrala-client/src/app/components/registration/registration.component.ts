import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormField} from '../../model/form-field';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  formFields = new Array<FormField>();
  taskId: string;

  constructor(private userService: UserService, private toastrService: ToastrService,
              private router: Router) {
  }

  ngOnInit() {
    this.userService.getRegisterFieldForm().subscribe(res => {
      console.log(res);
      this.formFields = res.formFields;
      this.taskId = res.taskId;
    });
  }

  register() {
    this.userService.register(this.taskId, this.formFields).subscribe(res => {
      this.toastrService.success('Registration request submitted');
      this.router.navigateByUrl('login');
    });
  }
}
