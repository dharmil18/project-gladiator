import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AdminDashboardService {

  private allUsers: string = "http://localhost:2020/gladiator/GetAllUsers";
  private pendingUsers: string = "http://localhost:2020/gladiator/GetAllUsersWithName";
  private changeStatus: string = "http://localhost:2020/gladiator/changeStatus";
  private deleteUser: string = "http://localhost:2020/gladiator/deleteUser";
  private editUserURL: string = "http://localhost:2020/gladiator/editUser";

 

  private body: Object = {};

  constructor(private httpClient: HttpClient) { }

  getAllUsers() {
    return this.httpClient.get(this.allUsers);
  }

  getPendingUsers() {
    return this.httpClient.get(this.pendingUsers);
  }

  changeStatusOfCard(id: string, action: string) {
    this.body = {
      "user_id": id,
      "action": action
    }
    return this.httpClient.put(this.changeStatus, this.body);
  }

  deleteUserByUserName(user_name: string) {
    this.body = {
      "user_name": user_name
    }
    return this.httpClient.put<any>(this.deleteUser, this.body);
  }

  editUser(user,user_name) {
    this.body = {
      
        "first_name": user.first_name,
        "last_name": user.last_name,
        "email_id": user.email_id,
        "phone_number": user.phone_number,
        "user_name": user.user_name,
        "password_hash": user.password_hash,
        "bank_name": user.bank_name,
        "account_no": user.account_no,
        "ifsc_code": user.ifsc_code,
        "address_line1": user.address_line1,
        "address_line2": user.address_line2,
        "city": user.city,
        "state": user.state,
        "zipcode": user.zipcode,
        "card_type": user.card_type
    
    }
    return this.httpClient.put<any>(this.editUserURL+"/"+user_name, this.body);
  }
}

export class Registration {

  first_name:string;
  last_name:string;
  email_id: string;
  phone_number: string;
  user_name: string;
  password_hash: string;
  bank_name: string;
  account_no: string;
  ifsc_code: string;
  address_line1: string;
  address_line2: string;
  city: string;
  state: string;
  zipcode: string;
  card_type: string;
 }
