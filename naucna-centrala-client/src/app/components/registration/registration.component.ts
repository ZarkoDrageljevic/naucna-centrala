import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { RegistrationService } from 'src/app/services/registration.service';
import { RegistrationDetailsDto } from 'src/app/model/registration-details-dto';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  private formFields: any[];
  private processInstanceId: string;
  private task: any;
  private start: boolean;

  constructor(private router: Router, private route: ActivatedRoute,
    private registrationService: RegistrationService) { }

  ngOnInit() {
    this.start = false;
  }

  startRegisterProcess() {
    this.registrationService.startProcess().subscribe(response => {
      this.formFields = response.formFields;
      this.processInstanceId = response.processInstanceId;
      console.log(this.processInstanceId);
      this.formFields.forEach(element => {
        console.log(element.id);
        this.start = true;
      });
    });
  }

  register(registration: RegistrationDetailsDto) {
    registration.processInstanceId = this.processInstanceId;

    this.registrationService.register(registration).subscribe(response => {
      this.registrationService.getNextTask(registration.username).subscribe(response => {
        if (response !== null) {
          alert("UNSUCCESSFULL REGISTRATION")
        }
        else {
          alert("SUCCESSFULL REGISTERED")
          this.router.navigate([`/login`]);
        }
      });
    },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          alert("Error")
          console.log(err.error.message + '\nError Status ' + err.status);
        } else {
          alert("Error")
          console.log(err.error.message + '\nError Status ' + err.status);

        }
      }
    )
  }

}
