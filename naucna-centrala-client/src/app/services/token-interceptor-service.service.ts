import { Injectable, Injector } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorServiceService implements HttpInterceptor {

  constructor(private inj: Injector) { }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      setHeaders: {
        'Auth': "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ6YXJrbyIsIkFVVEgiOiJNSUNST1NFUlZJQ0VTIiwiaWF0IjoxNTQ4MjgwOTU5LCJleHAiOjE1Nzk4Mzc5MTF9.O-06aCFZUDkLi9LqUEaUusyWOwP0SbjVjm2xSZNI0cY"
      }
    });

    return next.handle(request);
  }
}
