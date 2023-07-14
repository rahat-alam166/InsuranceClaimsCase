import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/User';
import { Observable } from 'rxjs';
import { LoginInfo } from './LoginInfo';
import { AddClaim, Claim } from './Claim';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  private apiUrl = 'http://localhost:8080'
  id: undefined | number;
  name: undefined | string;
  role: undefined | string;
  isLoggedIn!: boolean;

  constructor(private http: HttpClient, private router: Router) { }
  getRegistered(user: User): Observable<any> {
    console.log(user);
    return this.http.post(`${this.apiUrl}/user/add`, user, { responseType: 'text' });
  }

  addClaim(data:AddClaim): Observable<any> {
    console.log(this.id)
    return this.http.post(`${this.apiUrl}/user/add/${this.id}`,data, {responseType: 'text'});
  }

  getLoginStatus(loginData: LoginInfo) {
    console.log(loginData);

    this.http.post(`${this.apiUrl}/user/login`, loginData).subscribe((resultData: any) => {


      if (resultData.message == "Login Success") {
        this.id = resultData.id;
        this.name = resultData.fullName;
        this.role = resultData.role;
        this.isLoggedIn = true;
        console.log(resultData);
        console.log(this.id);
        if (resultData.role == "ADMIN")
        {
          this.router.navigateByUrl('/admin');
        }
        else
        {
          this.router.navigateByUrl('/claim');
        }
        
      }
      else {
        alert("Invalid Login"); 
      }
    });
  }

  public getClaims(): Observable<any>{
    return this.http.get<any>(`${this.apiUrl}/claims/all`);
  }

  public getClaimsById(): Observable<any>{
    return this.http.get<any>(`${this.apiUrl}/user/all/claims/${this.id}`);
  }

  public updateClaim(claim: Claim): Observable<any>{
    return this.http.put<Claim>(`${this.apiUrl}/claims/update`, claim);
  }


  logout() {
    this.id = undefined;
    this.name = undefined;
    this.role = undefined;
    this.isLoggedIn = false;
    this.router.navigateByUrl('/login');
  }


}
