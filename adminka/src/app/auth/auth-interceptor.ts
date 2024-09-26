import {HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // console.log('interceptor',localStorage);
    const token = localStorage.getItem('jwt_token');

    const excludeUrl = '/api/v1/refresh';
    // console.log('excludeUrl', req.url.includes(excludeUrl));

    if (req.url.includes(excludeUrl)) {
        // console.log('Исключаемый запрос без токена:', req);
        return next.handle(req);
    }

    if (token) {
        const cloned = req.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });

        // console.log('Отправляемый запрос с токеном:', cloned);
        return next.handle(cloned);
    } else {
        // console.log('Отправляемый запрос без токена:', req);
        return next.handle(req);
    }
  }

}
