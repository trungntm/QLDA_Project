import { Component, OnInit, Optional } from '@angular/core';
import { Customers } from '../entity/customer';
import { User } from '../entity/user';
import { Observable } from 'rxjs';
import { ProfileComponent } from '../profile/profile.component';
import { CustomerService } from '../service/customer.service';
import { Route, Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-customers-list',
  templateUrl: './customers-list.component.html',
  styleUrls: [
    "../../assets/global/plugins/font-awesome/css/font-awesome.min.css",
    "../../assets/global/plugins/simple-line-icons/simple-line-icons.min.css",
    "../../assets/global/plugins/bootstrap/css/bootstrap.min.css",
    "../../assets/global/plugins/uniform/css/uniform.default.css",
    "../../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css",
    '../../assets/global/plugins/select2/select2.css',
    "../../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css",
    "../../assets/global/css/components.css",
    "../../assets/global/css/plugins.css",
    "../../assets/admin/layout/css/layout.css",
    "../../assets/admin/layout/css/themes/darkblue.css",
    "../../assets/admin/layout/css/custom.css",
    './customers-list.component.css']
})
export class CustomersListComponent implements OnInit {

  customers: Observable<User[]>;
  isRoleAdmin: Observable<boolean> | boolean;
  sub;
  page = 0;
  constructor(private router: Router, private activatedRoute: ActivatedRoute, private customerService: CustomerService) { }

  ngOnInit() {
    this.GetAllUsers();
  }

  GetAllUsers() {
    this.customerService.getAllCustomersPagingAndSorting(this.page)
      .subscribe(res => {
        this.customers = res;
        console.log(this.customers);
      }, err => {
        console.log('err : ' + err)
      });
    this.sub = this.activatedRoute.queryParams
      .subscribe(params => {
        this.page = +params['page'] || 0;
        console.log('query params ', this.page);
      });
    console.log(this.customers);
  }

  onNextCustomers() {
    this.router.navigate(['./'], { queryParams: { page: this.page + 1 }, relativeTo: this.activatedRoute });
    this.GetAllUsers();
  }

  // GotoPage(pageNum,sizeNum){
  //   this.router.navigate(['/admin/customers'],{queryParams:{page:pageNum,size:sizeNum}});
  // }

}
