
import { Component, OnInit } from '@angular/core';
import { Claim } from '../Claim';
import { Router } from '@angular/router';
import { ServicesService } from '../services.service';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-admin-claim',
  templateUrl: './admin-claim.component.html',
  styleUrls: ['./admin-claim.component.css']
})
export class AdminClaimComponent {
  public claims: Claim[] = [];
  public editClaim: Claim | undefined;
  public deleteClaim: Claim | undefined;

  constructor (private router: Router, private service: ServicesService ){}

  ngOnInit() {
    this.getClaims();
      
  }

  public getClaims(): void {
    this.service.getClaims().subscribe(
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
  public onCheck(check: string): void {
    if (this.editClaim !== undefined)
    {
      this.editClaim.status = check;
      this.editClaim.open = false;
      this.service.updateClaim(this.editClaim).subscribe(
        (response: Claim) => {
          console.log(response);
          this.getClaims();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
    
  }
  public onCloseModal(): void {
    const modelDiv = document.getElementById('myModal');
    if(modelDiv != null)
    {
      modelDiv.style.display = 'none';
    }
  }

}
