import { Component, OnInit } from '@angular/core';
import { Claim } from '../Claim';
import { Router } from '@angular/router';
import { ServicesService } from '../services.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-claims',
  templateUrl: './claims.component.html',
  styleUrls: ['./claims.component.css']
})
export class ClaimsComponent implements OnInit{
  public claims: Claim[] = [];
  public editClaim: Claim | undefined;
  public deleteClaim: Claim | undefined;

  constructor (private router: Router, private service: ServicesService ){}

  ngOnInit() {
    this.getClaims();
      
  }

  public getClaims(): void {
    this.service.getClaimsById().subscribe(
      (response: Claim[]) => {
        this.claims = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onOpenModal(claim: Claim|null): void {
    this.editClaim = claim || undefined;
  }
  public onCloseModal(): void {
    const modelDiv = document.getElementById('myModal');
    if(modelDiv != null)
    {
      modelDiv.style.display = 'none';
    }
  }
}

// public onUpdateRestaurant(restaurant: Restaurant): void {
//   this.restaurantService.updateRestaurant(restaurant).subscribe(
//     (response: Restaurant) => {
//       console.log(response);
//       this.getRestaurants();
//     },
//     (error: HttpErrorResponse) => {
//       alert(error.message);
//     }
//   );
// }
