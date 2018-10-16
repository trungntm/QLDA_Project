import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Person } from '../entity/person';


@Injectable({
  providedIn: 'root'
})
export class PersonService {
  url = environment.url;
  constructor(private http: HttpClient) { }

  UpdateProfile(person: Person) {
    return this.http.put(this.url + `/api/v1/persons/update/profile`, person, { observe: `response` });
  }

  UploadAvatar(file: File) {
    console.log(file);
    return this.http.put(this.url + `/api/v1/persons/update/avatar`, file, { observe: `response` });
  }
}
