import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ClaimsComponent } from './claims/claims.component';
import { AddClaimComponent } from './add-claim/add-claim.component';
import { AdminClaimComponent } from './admin-claim/admin-claim.component';

const routes: Routes = [
  { path: '', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'claim', component: ClaimsComponent},
  { path: 'addClaim', component: AddClaimComponent},
  { path: 'admin', component: AdminClaimComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
