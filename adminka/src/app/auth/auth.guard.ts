import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {

    const token = localStorage.getItem('jwt_token');

    if (token) {
      const decodedToken: any = jwtDecode(token);

      if (decodedToken.roles && Array.isArray(decodedToken.roles)) {
        if (decodedToken.roles.includes('ROLE_ADMIN')) {
          return true;
        }
      }
    }
    this.router.navigate(['/']);
    return false;
  }
}
