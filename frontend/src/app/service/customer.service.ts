import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Customers } from '../entity/customer';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  url = environment.url;
  constructor(private http: HttpClient) { }

  getCustomersByUsernamePagingAndSorting(username: string, page: number, size: number, sort: string) {
    return this.http.get(this.url + `/api/v1/admin/users/search?username=` + username + `&page=` + page + `&size=` + size + `&sort=` + sort, { observe: `response` });
  }
  getAllCustomersPagingAndSorting(page: number): Observable<any> | any {
    return this.http.get(this.url + `/api/v1/admin/users?page=` + page);
  }
}
