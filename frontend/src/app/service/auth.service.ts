import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, } from '@angular/common/http';
import { Auth } from '../entity/auth';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url = environment.url;
  jwtHelper: JwtHelperService;
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http: HttpClient) {
    this.jwtHelper = new JwtHelperService;
  }

  // service call api login
  Login(username: string, password: string): Observable<any> {
    // return this.http.get(this.url + `/login?username=` + auth.username + `&password=` + auth.password, { observe: `response` });
    return this.http.post(this.url + `/login?username=` + username + `&password=` + password, { headers: this.headers }, { observe: `response` })
  }

  // service call api logout
  Logout() {
    localStorage.removeItem("access_token");
  }

  IsAuthenticated() {
    const token = localStorage.getItem("access_token");
    if (!token) {
      return false;
    }
    return !this.jwtHelper.isTokenExpired(token);
  }
}
