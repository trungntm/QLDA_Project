import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { UserComponent } from './user/user.component';
import { AuthGuard } from './helper/auth.guard';
import { AdminGuard } from './helper/admin.guard';
import { IndexComponent } from './index/index.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { UserGuard } from './helper/user.guard';
import { CustomersListComponent } from './customers-list/customers-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/index', pathMatch: 'full' },
  { path: 'index', component: IndexComponent },
  {
    // path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard],
    path: 'dashboard', component: DashboardComponent, canActivate: [AdminGuard], data: {
      expectedRole: 'ROLE_ADMIN',
    },
    children: [
      {
        path: "users",
        component: UserComponent,
        canActivateChild: [AdminGuard],
      },
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [UserGuard], data: { expectedRole: 'ROLE_USER' } },
  { path: 'admin/customers', component: CustomersListComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
