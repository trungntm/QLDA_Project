import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Injectable } from '@angular/core';
import { HttpClientModule, HttpInterceptor, HttpHandler, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app.routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { UserComponent } from './user/user.component';
import * as angularJwt from '@auth0/angular-jwt';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { MenuComponent } from './menu/menu.component';
import { AuthGuard } from './helper/auth.guard';
import { AdminGuard } from './helper/admin.guard';
import { UserGuard } from './helper/user.guard';
import { AuthService } from './service/auth.service';
import { HttpAuthInterceptor } from './helper/http-auth-interceptor';
import { ErrorInterceptor } from './helper/error-interceptor';
import { IndexComponent } from './index/index.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { CommonModule } from '@angular/common';
import { UploadFileComponent } from './upload-file/upload-file.component';
import { UploadFileService } from './service/upload-file.service';
import { CustomersDetailsComponent } from './customers-details/customers-details.component';
import { CustomersListComponent } from './customers-list/customers-list.component';

@NgModule({

  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    UserComponent,
    HeaderComponent,
    FooterComponent,
    MenuComponent,
    IndexComponent,
    RegisterComponent,
    ProfileComponent,
    UploadFileComponent,
    CustomersDetailsComponent,
    CustomersListComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    angularJwt.JwtModule
  ],
  providers: [AuthGuard, AdminGuard, UserGuard, AuthService, UploadFileService,
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: HttpAuthInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
