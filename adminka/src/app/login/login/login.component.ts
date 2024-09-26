import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { AuthService } from 'src/app/auth/auth.service';
import { LoginDto } from 'src/app/auth/loginDto';
import { LoginResponseDto } from 'src/app/auth/loginResponseDto';
import { RefreshTokenDto } from 'src/app/auth/refreshTokenDto';
import { RegistrationDto } from 'src/app/auth/registrationDto';
import { ResponseInfo } from 'src/app/models/responesInfo';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent{
  loginDto: LoginDto;
  isPasswordVisible = false;
  errorMsg: string | null = null;

  constructor(private authService: AuthService, private router: Router) {
    this.loginDto = new LoginDto();
  }

  ngOnInit(){
    if(localStorage.getItem('jwt_token')){
      const refreshToken = this.authService.getRefreshToken();
      const newRefreshDto = new RefreshTokenDto;
      newRefreshDto.token = refreshToken;
      this.authService.getNewJwt(newRefreshDto).subscribe({
        next: (response) => {
          this.authService.updateLogin(response.data.token);
          const decodedToken: any = jwtDecode(response.data.token);
          const roles: string[] = decodedToken.roles || [];
          if(roles.includes('ROLE_ADMIN')){
            this.router.navigate(['/comm']);
          }
          else{
            this.router.navigate(['/comm']);
          }

        },
        error: (err) => {
          this.authService.geleteRefreshToken();
          localStorage.removeItem('jwt_token');
          this.router.navigate(['/']);
        }
      });
    }
  }

  togglePasswordVisibility() {
    this.isPasswordVisible = !this.isPasswordVisible;
  }

  onLogin() {
    this.authService.login(this.loginDto).subscribe({
        next: (response) => {
          const decodedToken: any = jwtDecode(response.data.token);
          const roles: string[] = decodedToken.roles || [];
          if(roles.includes('ROLE_ADMIN')){
            this.router.navigate(['/comm']);
          }
          else{
            this.router.navigate(['/admin']);
          }
        },
        error: (err) => {
          this.errorMsg = err.error?.errorMsg || 'Login failed. Please try again.';
        }
      });
  }



}
