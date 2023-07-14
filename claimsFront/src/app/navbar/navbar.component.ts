import { Component } from '@angular/core';
import { ServicesService } from '../services.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private service: ServicesService, private router: Router){}

  isLoggedIn():boolean {
    return this.service.isLoggedIn;
  }

  isAdmin():boolean {
    return this.service.role=='ADMIN';
  }

  logout() {
    this.service.logout();
    
  }

}
