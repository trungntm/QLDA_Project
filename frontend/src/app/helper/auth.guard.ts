import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {


  constructor(private router: Router, private authService: AuthService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    // if (localStorage.getItem('access_token')) {
    //   console.log("auth guard : " + localStorage.getItem('access_token'));
    //   // logged in so return true
    //   return true;
    // }
    if (this.authService.IsAuthenticated()) {
      console.log("auth guard");
      return true;
    }
    // not logged in so redirect to login page with the return url
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    return this.canActivate(route, state);
  }
}
