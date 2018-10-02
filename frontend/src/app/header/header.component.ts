import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css',
              '../../assets/global/plugins/font-awesome/css/font-awesome.min.css',
              '../../assets/global/plugins/simple-line-icons/simple-line-icons.min.css',
              '../../assets/global/plugins/bootstrap/css/bootstrap.min.css',
              '../../assets/global/plugins/uniform/css/uniform.default.css',
              '../../assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css',
              '../../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css',
              '../../assets/global/plugins/fullcalendar/fullcalendar.min.css',
              '../../assets/global/plugins/jqvmap/jqvmap/jqvmap.css',
              '../../assets/admin/pages/css/tasks.css',
              '../../assets/global/css/components.css',
              '../../assets/global/css/plugins.css',
              '../../assets/admin/layout/css/layout.css',
              '../../assets/admin/layout/css/themes/darkblue.css',
              '../../assets/admin/layout/css/custom.css'
              ]
})
export class HeaderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
