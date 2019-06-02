import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {Paper} from '../model/paper';

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
}
