import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isUserLoggedIn: boolean = false;
  isAdmin: boolean = false;
  showRegisterLogin: boolean = false;
  showLogout: boolean = false;
  showProduct: boolean = true;
  userName = null;
  userRole = null;

  constructor(private router: Router) { }
  ngDoCheck(){
    this.userName=sessionStorage.getItem("user_name");
    this.userRole=sessionStorage.getItem("role");
    if(this.userName!=null && this.userRole == 'User'){
      this.isUserLoggedIn=true;
      this.showLogout = true;
      this.showRegisterLogin = false;
      this.showProduct = true;
    }
    else if (this.userName!=null && this.userRole == 'Admin') {
      this.isAdmin = true;
      this.showLogout = true;
      this.showRegisterLogin = false;
      this.showProduct = false;
    }
    else{
      ;this.showRegisterLogin = true
    }
  }

  ngOnInit(): void {
    this.userName=sessionStorage.getItem("user_name");
    if(this.userName!=null) {
      this.userRole=sessionStorage.getItem("role");
      if(this.userRole=="User") 
        this.router.navigate(['user-dashboard']);
      else if(this.userRole=="Admin") 
        this.router.navigate(['admin-dashboard']);
      }
      else 
        this.router.navigate(['home']);
    }

  logout(){
    sessionStorage.clear();
    this.isUserLoggedIn=false;
    this.isAdmin = false
    this.showLogout = false;
    this.showProduct = true;
    alert("Logging Out")
    this.router.navigate(['home']);
  }
}
