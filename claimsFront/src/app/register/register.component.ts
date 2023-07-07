import { Component } from '@angular/core';
import { ServicesService } from '../services.service';
import { Router } from '@angular/router';
import { User } from 'src/app/User';
import { isEmpty } from 'rxjs';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  name!: string;
  email!: string;
  address!: string; 
  phone!: string;  
  password!: string;
  role: string = "NORMAL";
  registrationMessage:string|undefined;

  constructor(private services: ServicesService, private router: Router){}

  save()
  {
    const accountRegistration: User = {
      name: this.name,
      email: this.email,
      address: this.address,
      phone: this.phone,
      password: this.password,
      role: this.role
    };
    let resultData: any =
    this.services.getRegistered(accountRegistration).subscribe((accountRegistration) => resultData);
    alert("Registered Succesfully");
    this.router.navigateByUrl('/login');
    console.log(resultData);
    //Navigate to login

  }

}

