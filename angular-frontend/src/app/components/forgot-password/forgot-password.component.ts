import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ForgotPasswordService } from 'src/app/service/forgot-password.service';

@Component({
  selector: 'forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {

  forgotPasswordFormObj: Object = {};
  OtpMessage: any;
  showVerifyOtp: boolean = false;
  email: any;
  verifyOtpResponse: any;

  constructor(private forgotPasswordService: ForgotPasswordService, private router: Router) { }

  ngOnInit(): void {
  }

  getOtp(forgotPasswordForm) {
    const email: string = forgotPasswordForm.value.email;
    this.email=email;
    console.log(this.email);
    console.log(email);

    this.forgotPasswordService.getOtp(email).subscribe(
      (data) => {
        this.showVerifyOtp = false;
        this.OtpMessage = data;
        console.log(this.OtpMessage.status);
        console.log(this.OtpMessage.message);
        console.log(this.OtpMessage.statusCode);

        if(this.OtpMessage.status == 'Success'){
          this.showVerifyOtp = true;
        }
        else
          alert("Invalid Email entered");
      },
      error => {
        console.log(error);
      }
    );

    this.showVerifyOtp = true;

  }

  verifyOtp(verifyOtpForm){
    const otp: string = verifyOtpForm.value.otp;
    console.log(otp);
    this.forgotPasswordService.verifyOtp(this.email,otp).subscribe(
      (data) => {
        this.verifyOtpResponse = data;

        if(this.verifyOtpResponse == true){
          this.router.navigate(['/change-password']);
        }
        else
          alert("OTP verification failed");
      },
      error => {
        console.log(error);
      }
    );
  }

}
