import { Component, OnInit, Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpInterceptor, HttpRequest, HttpHandler } from '@angular/common/http';
import { UserService } from '../service/user.service';
import { User } from '../entity/user';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: Observable<User[]>;
  constructor(private http: HttpClient, private userService: UserService) {
    // console.log(http.options("/api/v1/admin/users"));
  }

  ngOnInit() {
    this.userService.getAllUsers().subscribe(res => {
      let author: String = res.headers.get('authorization');
      const tokenIndex = author.lastIndexOf(' ') + 1;
      let token = author.substr(tokenIndex);
      console.log(token);
    });
  }

}
