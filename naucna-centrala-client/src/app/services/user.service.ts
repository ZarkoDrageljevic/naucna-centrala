import {Injectable} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {User} from '../model/user';
import {catchError, map} from 'rxjs/operators';
import {throwError} from 'rxjs';
import {FormField} from '../model/form-field';
import {Magazine} from '../model/magazine';
import {CamundaTaskForm} from '../model/camunda-task-form';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = '/api/user';

  constructor(protected http: HttpClient,
              toastr: ToastrService) {
  }

  login(user: User) {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json');
    console.log("ovde sam");
    return this.http.post(`${this.baseUrl}/login`, user, {observe: 'response'});
  }

  register(taskId: string, formFields: FormField[]) {
    return this.http.post(`${this.baseUrl}/registration/${taskId}`, formFields);
  }

  getRegisterFieldForm() {
    return this.http.get<CamundaTaskForm>(`${this.baseUrl}/registration/form`);
  }
}
