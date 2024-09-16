import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ABaseServiceService } from '../services/abase-service.service';
import { LoginDto } from './loginDto';
import { LoginResponseDto } from './loginResponseDto';
import { Observable } from 'rxjs';
import { ResponseInfo } from '../models/responesInfo';
import { RegistrationDto } from './registrationDto';
import * as moment from "moment";
import { tap, shareReplay } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class AuthService extends ABaseServiceService {
  private authUrl = 'auth';
  private regUrl = 'registration';

  constructor(http: HttpClient) {
    super(http, 'api/v1');
  }

login(loginDto: LoginDto): Observable<ResponseInfo<LoginResponseDto>> {
  return this.postwp<ResponseInfo<LoginResponseDto>>(this.authUrl, loginDto).pipe(
    tap(response => {
      if (response.data && response.data.token) {
        this.setSession(response.data.token);
      }
    })
  );
}

// createNewUser(registration: RegistrationDto): Observable<ResponseInfo<Boolean>> {
//   return this.postwp<ResponseInfo<Boolean>>(this.regUrl, registration);
// }

private setSession(token: string): void {
  console.log(localStorage);
  localStorage.removeItem('jwt_token');
  console.log(localStorage);
  localStorage.setItem('jwt_token', token);
  console.log(localStorage);
}

// logout(): void {
//   localStorage.removeItem('jwt_token');
// }

// isLoggedIn(): boolean {
//   localStorage.removeItem('jwt_token');
//   const token = localStorage.getItem('jwt_token');
//   return !!token;
// }

}
