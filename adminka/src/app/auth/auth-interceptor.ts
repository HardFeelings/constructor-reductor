import {HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log('interceptor',localStorage);
    const token = localStorage.getItem('jwt_token');


    if (token) {
        const cloned = req.clone({
            setHeaders: {
                Authorization: `Bearer ${token}`
            }
        });

        console.log('Отправляемый запрос:', cloned);

        return next.handle(cloned);
    } else {
        console.log('Отправляемый запрос без токена:', req);
        return next.handle(req);
    }
}
}
