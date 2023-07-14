import { Component } from '@angular/core';
import { ServicesService } from '../services.service';
import { Router } from '@angular/router';
import { AddClaim } from '../Claim';

@Component({
  selector: 'app-add-claim',
  templateUrl: './add-claim.component.html',
  styleUrls: ['./add-claim.component.css']
})
export class AddClaimComponent {
  constructor(private service: ServicesService, private router: Router){}

  submit(data: AddClaim){
    data.date = new Date(data.date);

    data.status = "IN PROGRESS";
    data.open = true;
    console.log(data);
    this.service.addClaim(data).subscribe((result) => {
      console.log(result);
      alert("Claim added successfully");
    });

    

  }


}
