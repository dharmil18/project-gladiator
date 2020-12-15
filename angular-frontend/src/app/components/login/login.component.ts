import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm : FormGroup;
  users: any = [];
  loginMessage: any;

  constructor(private fb:FormBuilder, private loginService: LoginService, private router: Router) {
    this.loginForm = this.fb.group({
      "user_name": [null,Validators.required],
      "password": [null,Validators.required]
    })
    this.users = this.loginForm.value;
   }

  ngOnInit(): void {
    console.log(this.loginMessage);
  }

  login() {
    const user_name: string = this.loginForm.controls["user_name"].value;
    const password: string = this.loginForm.controls["password"].value;
    this.loginService.login(user_name, password).subscribe(
      (data) => {
        this.loginMessage = data;
        console.log(this.loginMessage.status);
        console.log(this.loginMessage.message);
        console.log(this.loginMessage.statusCode);
        console.log(this.loginMessage.loginUserData);

        sessionStorage.clear();
        if(this.loginMessage.status == 'Success'){
          if (this.loginMessage.loginUserData.role == 'User') {
            sessionStorage.setItem("user_name", this.loginMessage.loginUserData.user_name);
            sessionStorage.setItem("user_id", this.loginMessage.loginUserData.user_id);
            sessionStorage.setItem("role", this.loginMessage.loginUserData.role);
            sessionStorage.setItem("fName", this.loginMessage.loginUserData.firstName);
            sessionStorage.setItem("lName", this.loginMessage.loginUserData.lastName);
            this.router.navigate(['/user-dashboard']);
          }
          else {
            sessionStorage.setItem("user_name", this.loginMessage.loginUserData.user_name);
            sessionStorage.setItem("userId", this.loginMessage.loginUserData.user_id);
            sessionStorage.setItem("role", this.loginMessage.loginUserData.role);
            sessionStorage.setItem("fName", this.loginMessage.loginUserData.firstName);
            sessionStorage.setItem("lname", this.loginMessage.loginUserData.lastName);
            this.router.navigate(['/admin-dashboard']);
          }
        }
        else
          alert("Invalid Username or Password");
      },
      error => {
        console.log(error);
        if (error) {
          alert("User Not Found. Register First");
        }
      }
    );
  }

}



