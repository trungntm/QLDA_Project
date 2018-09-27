import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component'
import { environment } from '../../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}

@Injectable({
  providedIn: 'root'
})

export class UserService {

  url = environment.url;

  constructor(private http: HttpClient) {
  }

  getUserById(id: number): Observable<object> {
    return this.http.get(`${AppComponent.API_URL}/api/v1/admin/${id}`);
  }

  getAllUsers(): Observable<any> {
    return this.http.get(this.url + `/login?username=admin&password=admin`, { observe: `response` });
  }
}
