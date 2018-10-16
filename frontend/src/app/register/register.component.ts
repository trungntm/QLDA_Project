import { Component, OnInit } from '@angular/core';
import { Register } from '../entity/register';
import { UserService } from '../service/user.service';
import { Observable } from 'rxjs';
import { first } from 'rxjs/operators';
import { AuthService } from '../service/auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: [

    '../../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css',
    '../../assets/global/plugins/font-awesome/css/font-awesome.min.css',
    '../../assets/global/plugins/simple-line-icons/simple-line-icons.min.css',
    '../../assets/global/plugins/bootstrap/css/bootstrap.min.css',
    '../../assets/global/plugins/uniform/css/uniform.default.css',
    '../../assets/global/plugins/select2/select2.css',
    '../../assets/admin/pages/css/login3.css',
    '../../assets/global/css/components.css',
    '../../assets/global/css/plugins.css',
    '../../assets/admin/layout/css/layout.css',
    '../../assets/admin/layout/css/themes/darkblue.css',
    '../../assets/admin/layout/css/custom.css',
    './register.component.css',
  ]
})
export class RegisterComponent implements OnInit {

  // userRegister: Register;
  userRegister: Register = {
    username: "",
    password: "",
    confirmPassword: "",
    fullName: "",
    email: "",
    phone: "",
    dob: new Date,
    sex: 1,
    address: ""
  };
  jwtHelper: JwtHelperService;
  error: string;

  constructor(private router: Router, private userService: UserService, private authService: AuthService) {
    this.jwtHelper = new JwtHelperService();
  }

  ngOnInit() {
  }

  register() {
    this.userService.Register(this.userRegister)
      .pipe(first())
      .subscribe(res => {
        this.authService.Login(this.userRegister.username, this.userRegister.password)
          .pipe(first())
          .subscribe(res => {
            if (this.authService.IsAuthenticated()) {
              localStorage.removeItem("access_token");
              localStorage.removeItem("sub_token");
            }
            let author: String = res.headers.get('Authorization');
            const tokenIndex = author.lastIndexOf(' ') + 1;
            let token = author.substr(tokenIndex);
            if (token) {
              localStorage.setItem("access_token", token);
              // this.tokenHelper.SetToken(token);
              const decodedToken = this.jwtHelper.decodeToken(token);
              localStorage.setItem("sub_token", decodedToken.sub);

              this.router.navigate(['/index']);
            }
          },
            err => {
              console.log("login fail : " + err);
              this.error = err;
            });
      },
        err => {
          console.log(err);
          this.error = err;

        })
  }
}
