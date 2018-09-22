import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { UserService } from '../../service/user.service';
import { User } from '../../entity/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  users: Observable<User[]>;
  constructor(private userService: UserService) { }

  ngOnInit() {
    console.log('zo roi');
    this.retrieveAllUsers();
  }

  // implement methods form services
  retrieveById() {
    // this.userService.getUserById(this.id)
    //   .subscribe(user => this.users = user);
  }

  retrieveAllUsers() {
    this.users = this.userService.getAllUsers();
  }

}
