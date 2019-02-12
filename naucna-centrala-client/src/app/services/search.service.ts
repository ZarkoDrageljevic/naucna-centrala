import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SearchService {



  constructor(private http: HttpClient) {

  }

  searchMatch(query: SimpleQuery) {
    const url = `/api/search/match`;
    console.log(query)
    console.log(url);
    return this.http.post<any>(url, query);
  }

  searchPhraze(query: SimpleQuery) {
    const url = `/api/search/phrase`;
    console.log(query)
    console.log(url);
    return this.http.post<any>(url, query);
  }

  searchBoolean(query: AdvancedQuery) {
    const url = `/api/search/boolean`;
    console.log(query)

    console.log(url);
    return this.http.post<any>(url, query);
  }

}
