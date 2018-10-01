import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Auth } from '../entity/auth';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  url = environment.url;

  constructor(private http: HttpClient) { }

  Login(auth: Auth): Observable<any> {
    return this.http.get(this.url + `/login?username=` + auth.username + `&password=` + auth.password, { observe: `response` });
  }
}
