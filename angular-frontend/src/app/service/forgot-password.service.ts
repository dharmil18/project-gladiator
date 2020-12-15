import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {

    body: Object = {}
    email: string;
    constructor(private httpClient: HttpClient) {}

    getOtp(email: string) {
        const baseUrl: string = "http://localhost:2020/gladiator/getOTP/";
        return this.httpClient.get(baseUrl + email);
    }

    verifyOtp(email_id: string, otp: string) {
      this.email = email_id;
      this.body = {
        "email_id": email_id,
        "otp": otp
      }
      return this.httpClient.post<any>("http://localhost:2020/gladiator/verifyOTP", this.body);
    }

    changePassword(newPassword: string) {
      console.log(this.email);
      this.body = {
        "email_id": this.email,
        "newPassword": newPassword
      }
      return this.httpClient.put<any>("http://localhost:2020/gladiator/changePassword", this.body);
    }

}