import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';
import { AuthService } from 'src/app/auth/auth.service';
import { LoginDto } from 'src/app/auth/loginDto';
import { LoginResponseDto } from 'src/app/auth/loginResponseDto';
import { RegistrationDto } from 'src/app/auth/registrationDto';
import { ResponseInfo } from 'src/app/models/responesInfo';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent{
  loginDto: LoginDto = { username: '', password: '' };
  registrationDto: RegistrationDto = { username: '', password: '', confirmPassword: '', isAdmin: false };
  isPasswordVisible = false;

  constructor(private authService: AuthService) {}


  ngOnInit(){

  }

  togglePasswordVisibility() {
    this.isPasswordVisible = !this.isPasswordVisible;
  }

  onLogin() {
    this.authService.login(this.loginDto)
      .subscribe({
        next: (response) => {
          console.log('Login successful:', response);
          const token = response.data.token;
          const decodedToken = jwtDecode(token);
          console.log('Decoded Token:', decodedToken);
          // window.location.href = 'http://localhost:8000/comm';
        },
        error: (err) => {
          console.error('Login failed:', err);
        }
      });
  }

  // onRegister() {
  //   this.authService.createNewUser(this.registrationDto)
  //     .subscribe({
  //       next: (response) => {
  //         console.log('Registration successful:', response);
  //       },
  //       error: (err) => {
  //         console.error('Registration failed:', err);
  //       }
  //     });
  // }
}
