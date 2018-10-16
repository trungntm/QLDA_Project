import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component'
import { environment } from '../../environments/environment';
import { Register } from '../entity/register';

@Injectable({
  providedIn: 'root'
})

export class UserService {

  // get url from environment
  url = environment.url;

  constructor(private http: HttpClient) {
  }

  Register(userRegister: Register) {
    return this.http.post(this.url + `/api/v1/register`, userRegister, { observe: `response` });
  }
}
