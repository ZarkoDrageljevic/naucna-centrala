import { Injectable, Injector } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import {LoggedUtils} from '../utils/loggedUtils';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorServiceService implements HttpInterceptor {

  constructor(private inj: Injector) { }


  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      setHeaders: {
        // 'JWToken': "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJaYXJrbyIsImNyZWF0ZWQiOjE1NTkyNTQ3MTI0ODgsImV4cCI6MTU1OTg1OTUxMn0.qew-0cPX1gGq319YIelnKIrjiha4x7_uroiJjvRdHrMtBpY2w050Q9WxJfZ0C1JRp8wOULS5Oiy6TNK8u6AZbw"
        'JWToken': LoggedUtils.getToken()
      }
    });

    return next.handle(request);
  }
}
