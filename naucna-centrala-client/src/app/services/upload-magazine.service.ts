import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UploadMagazineService {

  constructor(private http: HttpClient) { }

  download(file: any) {
    const url = `/api/download`;
    console.log(url);
    return this.http.post(url, file, { responseType: 'blob' });
  }

  uploadPaper(paper: any) {
    const url = `/api/index/add`;
    console.log(paper);
    var headers = new HttpHeaders();
    headers.append('Content-Type', undefined)
    return this.http.post<any>(url, paper, { headers });
  }
}
