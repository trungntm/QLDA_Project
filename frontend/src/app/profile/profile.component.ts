import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router, ActivatedRouteSnapshot } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { Observable } from 'rxjs';
import { first } from 'rxjs/operators';
import { Person } from '../entity/person';
import { PersonService } from '../service/person.service';
import * as toastr from "../../assets/global/plugins/bootstrap-toastr/toastr.min.js";
import { UploadFileComponent } from '../upload-file/upload-file.component';
@Component({
  selector: 'profile',
  templateUrl: './profile.component.html',
  styleUrls: [
    '../../assets/global/plugins/font-awesome/css/font-awesome.min.css',
    '../../assets/global/plugins/simple-line-icons/simple-line-icons.min.css',
    '../../assets/global/plugins/bootstrap/css/bootstrap.min.css',
    '../../assets/global/plugins/uniform/css/uniform.default.css',
    '../../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css',
    '../../assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css',
    '../../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css',
    '../../assets/global/plugins/fullcalendar/fullcalendar.min.css',
    '../../assets/global/plugins/jqvmap/jqvmap/jqvmap.css',
    '../../assets/admin/pages/css/profile.css',
    '../../assets/admin/pages/css/tasks.css',
    '../../assets/global/css/components.css',
    '../../assets/global/css/plugins.css',
    '../../assets/admin/layout/css/layout.css',
    '../../assets/admin/layout/css/themes/darkblue.css',
    '../../assets/admin/layout/css/custom.css',
    "../../assets/global/plugins/icheck/skins/all.css",
    './profile.component.css']
})
export class ProfileComponent implements OnInit {

  token: string;
  sub_token: string;
  isAuthenticated: boolean;
  returnUrl: string;
  isAdminRole: boolean = false;
  isUserRole: boolean = false;
  error: string;
  principal: Person;
  selectedFile: File;
  constructor(private authService: AuthService,
    private personService: PersonService) {
    this.principal = new Person;
  }

  ngOnInit() {
    this.GetPrincipal();
    this.CheckRoleAdmin();
  }

  IsAuthenticated(): Observable<boolean> | boolean {
    if (this.authService.IsAuthenticated()) {
      this.token = localStorage.getItem("access_token");
      this.sub_token = localStorage.getItem("sub_token");
      if (localStorage.getItem("ROLE_ADMIN") == "ROLE_ADMIN") {
        this.isAdminRole = true;
      }
      if (localStorage.getItem("ROLE_USER") == "ROLE_USER") {
        this.isUserRole = true;
      }
      return this.isAuthenticated = true;
    }
    return this.isAuthenticated = false;
  }

  GetPrincipal(): Observable<Person> | Person {
    return this.authService.GetPrincipal(localStorage.sub_token.toString())
      .pipe(first())
      .subscribe(res => {
        if (res.success == "true") {
          this.principal = res.data;
        }
        else if (res.success == "false") {
          this.error = res.message;
        }
      },
        err => {
          console.log(err);
        });
  }
  CheckRoleAdmin(): Observable<boolean> | boolean {
    if (localStorage.getItem("ROLE_ADMIN")) {
      return this.isAdminRole = true;
    }
    return this.isAdminRole = false;
  }

  editProfile() {
    this.personService.UpdateProfile(this.principal)
      .pipe(first())
      .subscribe(
        res => {
          if (res.ok) {
            console.log(res);
            toastr.success("Cập nhật thông tin thành công!");
          }
        },
        err => {
          console.log(err);
        }
      )
  }

  onChangedImage(e) {
    this.selectedFile = e.target.files[0];
  }

  uploadImage() {
    this.personService.UploadAvatar(this.selectedFile);
  }
}
