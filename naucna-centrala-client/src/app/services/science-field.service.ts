import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {Magazine} from '../model/magazine';
import {ScienceField} from '../model/science-field';
import {HttpClient} from '@angular/common/http';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ScienceFieldService {
  private baseUrl = '/api/scientific-field';

  constructor(private http: HttpClient, toastr: ToastrService) {
  }

  findAll(): Observable<ScienceField[]> {
    return this.http.get<ScienceField[]>(this.baseUrl);
  }
}
