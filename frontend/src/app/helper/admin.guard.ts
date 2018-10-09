import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, } from '@angular/common/http';
import { Auth } from '../entity/auth';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { AuthService } from '../service/auth.service';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AdminGuard implements CanActivate {
  private jwtHelper: JwtHelperService;
  constructor(public authService: AuthService, public router: Router) {
    this.jwtHelper = new JwtHelperService;
  }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    // this will be passed from the route config
    // on the data property
    const expectedRole = route.data.expectedRole;
    console.log(expectedRole);
    const token = localStorage.getItem('access_token');
    // decode the token to get its payload
    const tokenPayload = this.jwtHelper.decodeToken(token);
    if (this.authService.IsAuthenticated() && tokenPayload.authorities.some(item => item.authority === expectedRole)) {
      console.log("AdminGuard");
      return true;
    }
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    return this.canActivate(route, state);
  }
}
