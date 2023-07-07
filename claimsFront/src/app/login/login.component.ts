import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ServicesService } from '../services.service';
import { LoginInfo } from '../LoginInfo';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email!: string;
  password!: string;

  constructor(private http: HttpClient, private router: Router, private service: ServicesService){}

  onSubmit()
  {
    let bodyData: LoginInfo = {
      email: this.email,
      password: this.password,
    };
    
    this.service.getLoginStatus(bodyData);
  }

}
