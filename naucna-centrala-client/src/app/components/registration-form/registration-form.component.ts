import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { RegistrationDetailsDto } from 'src/app/model/registration-details-dto';

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {

  @Input()
  formFields: any[];

  @Output()
  registrationExecuted: EventEmitter<RegistrationDetailsDto> = new EventEmitter();

  registrationDetails: RegistrationDetailsDto;

  constructor() {
    this.registrationDetails = new RegistrationDetailsDto();
  }

  ngOnInit() {
  }

  registration() {
    this.registrationExecuted.emit(this.registrationDetails);
  }

}
