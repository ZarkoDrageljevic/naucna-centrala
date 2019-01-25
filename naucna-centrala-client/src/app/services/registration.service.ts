import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistrationDetailsDto } from '../model/registration-details-dto';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  private baseUrl = '/api/registration';

  constructor(private http: HttpClient) { }

  startProcess() : Observable<any>{
      return this.http.get<any[]>(`${this.baseUrl}`);
  }

  register(registration: RegistrationDetailsDto): Observable<any> {
      return this.http.post<any>(`${this.baseUrl}`, registration);
  }

  getNextTask(username: string) {
      return this.http.get<any>(`${this.baseUrl}/task?username=${username}`);
  }

  getTaskData(taskId: number) {
      return this.http.get<any>(`${this.baseUrl}/task/${taskId}`);
  }


  confirm(formData, taskId) {
      return this.http.put<any>(`${this.baseUrl}/task/execute/${taskId}`, formData);
  }
}