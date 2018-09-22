import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUserById(id: number): Observable<object> {
    return this.http.get(`${AppComponent.API_URL}/api/v1/admin/${id}`);
  }

  getAllUsers(): Observable<any> {
    return this.http.get(`http://localhost:8080/api/v1/admin/users`);
  }
}
