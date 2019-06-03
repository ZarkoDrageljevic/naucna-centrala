import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {Paper} from '../model/paper';
import {FormField} from '../model/form-field';

@Injectable({
  providedIn: 'root'
})
export class PaperService {

  private baseUrl = '/api/paper';

  constructor(private http: HttpClient, toastr: ToastrService) {
  }

  startPaperProcess() {
    return this.http.post(`${this.baseUrl}/start-paper-process`, {});
  }

  createPaper(taskId: string, formData: FormData) {

    return this.http.post<Paper>(`${this.baseUrl}/new/${taskId}`, formData)
  }

  getPaperByTaskId(taskId: string) {
    return this.http.get<Paper>(`${this.baseUrl}/task/${taskId}`);

  }

  submitTvalidation(taskId: string, formFields: FormField[]) {
    return this.http.post(`${this.baseUrl}/tvalidation/${taskId}`, formFields);
  }

  rejectPaperThematic(taskId: string, formData: FormData) {
    return this.http.post<Paper>(`${this.baseUrl}/reject/thematic/${taskId}`, formData)
  }
}
