import { Route, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Auth } from '../entity/auth';
import { AuthService } from '../service/auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';
import { first } from 'rxjs/operators';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: [
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
    './login.component.css',
  ]
})
export class LoginComponent implements OnInit {

  auth: Auth = {
    username: "",
    password: ""
  };
  jwtHelper: JwtHelperService;
  error: string;
  constructor(private router: Router, private authService: AuthService) {
    this.jwtHelper = new JwtHelperService();
  }

  ngOnInit() {
    this.Logout();
  }

  login() {
    this.authService.Login(this.auth.username, this.auth.password)
      .pipe(first())
      .subscribe(res => {
        let author: String = res.headers.get('Authorization');
        const tokenIndex = author.lastIndexOf(' ') + 1;
        let token = author.substr(tokenIndex);
        if (token) {
          localStorage.setItem("access_token", token);
          // this.tokenHelper.SetToken(token);
          const decodedToken = this.jwtHelper.decodeToken(token);
          localStorage.setItem("sub_token", decodedToken.sub);
          for (let prop of decodedToken.authorities) {
            localStorage.setItem(prop.authority, prop.authority);
          }
          this.router.navigate(['/index']);
        }
      },
        err => {
          console.log("login fail : " + err);
          this.error = err;
        });
  }

  Logout() {
    localStorage.clear();
  }
}
