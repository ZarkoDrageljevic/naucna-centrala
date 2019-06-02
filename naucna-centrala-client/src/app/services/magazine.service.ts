import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';
import {Observable} from 'rxjs';
import {Magazine} from '../model/magazine';
import {FormField} from '../model/form-field';

@Injectable({
  providedIn: 'root'
})
export class MagazineService {
  private baseUrl = '/api/magazine';

  constructor(private http: HttpClient, toastr: ToastrService) {
  }

  getMagazines(): Observable<Magazine[]> {
    return this.http.get<Magazine[]>(this.baseUrl);
  }

  chooseMagazine(taskId: string, formFields: Array<FormField>) {
    return this.http.post<Magazine[]>(`${this.baseUrl}/${taskId}`, formFields);

  }
}
