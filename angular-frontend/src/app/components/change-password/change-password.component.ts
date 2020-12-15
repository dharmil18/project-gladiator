import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ForgotPasswordService } from 'src/app/service/forgot-password.service';

@Component({
  selector: 'change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  email: any;    
  constructor(private forgotPasswordService: ForgotPasswordService, private router: Router) { }

  ngOnInit(): void {
  }
  changePassword(changePasswordForm){

    const password1: string = changePasswordForm.value.password1;
    const password2: string = changePasswordForm.value.password2;
    console.log(password1);
    console.log(password2);

    if(password1 == password2){
        this.forgotPasswordService.changePassword(password1).subscribe(
          data => {
            if(data.status == 'Success') {
              alert(data.message);
              this.router.navigate(['/login']);
            }
            else {
              alert(data.message);
            }
          }
        );
    }
    else{
        alert("Entered passwords don't match, try again");
    }
  }
}
