import {Observable} from 'rxjs/Observable';
import {Injectable} from '@angular/core';
import {HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders} from '@angular/common/http';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (localStorage.getItem('credential') !== null) {

      console.log('INTERCEPTING REQ');

      const token = JSON.parse(localStorage.getItem('credential')).token;

      return next.handle(req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      }));

    } else {
      return next.handle(req);
    }
  }

}
