import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';


@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot): boolean {
    const token = localStorage.getItem('jwt_token');

    if (!token) {
      this.router.navigate(['/']);
      return false;
    }

    const decodedToken: any = jwtDecode(token);
    const isAdmin = decodedToken.roles && Array.isArray(decodedToken.roles) && decodedToken.roles.includes('ROLE_ADMIN');

    if (route.routeConfig?.path === 'admin' && !isAdmin) {
      this.router.navigate(['/commercial']);
      return false;
    }

    if (route.routeConfig?.path === 'commercial' && !isAdmin) {
      return true;
    }

    return true;
  }
}
