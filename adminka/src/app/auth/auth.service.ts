import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ABaseServiceService } from '../services/abase-service.service';
import { LoginDto } from './loginDto';
import { LoginResponseDto } from './loginResponseDto';
import { Observable } from 'rxjs';
import { ResponseInfo } from '../models/responesInfo';
import { tap } from 'rxjs/operators';
import { CookieService } from 'ngx-cookie-service';
import { RefreshTokenDto } from './refreshTokenDto';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends ABaseServiceService {
  private authUrl = 'auth';
  private refreshUrl = 'refresh';

  constructor(http: HttpClient, private cookieService: CookieService) {
    super(http, 'api/v1');
  }

login(loginDto: LoginDto): Observable<ResponseInfo<LoginResponseDto>> {
  return this.postwp<ResponseInfo<LoginResponseDto>>(this.authUrl, loginDto).pipe(
    tap(response => {
      if (response.data && response.data.token) {
        this.setSession(response.data.token);
      }
      if (response.data && response.data.refreshToken) {
        this.setRefresh(response.data.refreshToken);
      }
    })
  );
}

private setSession(token: string): void {
  localStorage.removeItem('jwt_token');
  localStorage.setItem('jwt_token', token);
}

private setRefresh(token: string): void {
  this.cookieService.delete('refresh_token');
  this.cookieService.set('refresh_token', token);
}


logout(): void {
  localStorage.removeItem('jwt_token');
  this.cookieService.delete('refresh_token');
}

getRefreshToken(): string | null {
  return this.cookieService.get('refresh_token');
}

geleteRefreshToken(): void {
  this.cookieService.delete('refresh_token');
}


getNewJwt(refreshToken: RefreshTokenDto): Observable<ResponseInfo<LoginResponseDto>> {
  return this.postwp<ResponseInfo<LoginResponseDto>>(this.refreshUrl, refreshToken).pipe(
    tap(response => {
      if (response.data && response.data.token) {
        this.setSession(response.data.token);
      }
      if (response.data && response.data.refreshToken) {
        this.setRefresh(response.data.refreshToken);
      }
    })
  );
}

updateLogin(newToken : string) {
  localStorage.removeItem('jwt_token');
  localStorage.setItem('jwt_token', newToken);
}

}
