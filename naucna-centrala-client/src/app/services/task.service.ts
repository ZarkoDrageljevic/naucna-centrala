import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {Observable} from 'rxjs';
import {TaskModel} from '../model/task';

@Injectable({
  providedIn: 'root'
})
export class TaskService {
  private baseUrl = '/api/task';

  constructor(private http: HttpClient, toastr: ToastrService) {
  }

  getTasks(): Observable<TaskModel[]> {
    return this.http.get<TaskModel[]>(this.baseUrl);
  }

}
