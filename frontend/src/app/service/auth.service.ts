import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, } from '@angular/common/http';
import { Auth } from '../entity/auth';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url = environment.url;
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  constructor(private http: HttpClient) { }

  // service call api login
  Login(auth: Auth): Observable<any> {
    // return this.http.get(this.url + `/login?username=` + auth.username + `&password=` + auth.password, { observe: `response` });
    return this.http.post(this.url + `/login?username=` + auth.username + `&password=` + auth.password, { headers: this.headers }, { observe: `response` })
  }

  // service call api logout
  Logout() {
    localStorage.removeItem("access_token");
  }



}
