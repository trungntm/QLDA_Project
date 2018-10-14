import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { Observable } from 'rxjs';
import { first } from 'rxjs/operators';
import { Person } from '../entity/person';
import { UserComponent } from '../user/user.component';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: [
    '../../assets/global/plugins/font-awesome/css/font-awesome.min.css',
    '../../assets/global/plugins/bootstrap/css/bootstrap.min.css',
    '../../assets/global/plugins/fancybox/source/jquery.fancybox.css',
    '../../assets/global/plugins/carousel-owl-carousel/owl-carousel/owl.carousel.css',
    '../../assets/global/plugins/slider-layer-slider/css/layerslider.css',
    '../../assets/global/css/components.css',
    '../../assets/frontend/layout/css/style.css',
    '../../assets/frontend/pages/css/style-shop.css',
    '../../assets/frontend/pages/css/style-layer-slider.css',
    '../../assets/frontend/layout/css/style-responsive.css',
    '../../assets/frontend/layout/css/themes/red.css',
    '../../assets/frontend/layout/css/custom.css',
    './index.component.css']
})
export class IndexComponent implements OnInit {
  token: string;
  sub_token: string;
  isAuthenticated: boolean;
  returnUrl: string;
  isAdminRole: boolean;
  constructor(private route: Router, private authService: AuthService) {
  }

  ngOnInit() {
    this.IsAuthenticated();
  }

  IsAuthenticated(): Observable<boolean> | boolean {
    if (this.authService.IsAuthenticated()) {
      this.token = localStorage.getItem("access_token");
      this.sub_token = localStorage.getItem("sub_token");
      if (localStorage.getItem("ROLE_ADMIN") == "ROLE_ADMIN") {
        this.isAdminRole = true;
      }
      return this.isAuthenticated = true;
    }
    return this.isAuthenticated = false;
  }

}
