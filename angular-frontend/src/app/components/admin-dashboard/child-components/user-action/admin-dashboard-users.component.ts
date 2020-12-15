import { Component, OnInit } from '@angular/core';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AdminDashboardService } from 'src/app/service/admin-dashboard.service';
import { NavigationEnd, Router } from '@angular/router';

@Component({
  selector: 'admin-dashboard-users',
  templateUrl: './admin-dashboard-users.component.html',
  styleUrls: ['./admin-dashboard-users.component.css']
})
export class AdminDashboardComponentUsers implements OnInit{
  allUsers: any;
  modalContent: undefined;
  editform: FormGroup;
  formObj: Object = {};
  responseData: any;
  deleteStatus: any;

  ngOnInit(): void {
    this.allUsers = [];
    this.adminService.getAllUsers()
      .subscribe(
        data => {
          console.log(data);
          this.allUsers = data;
        },
        error => {
          console.log(error);
        }
      );

        
      this.editform = new FormGroup({

        first_name: new FormControl ('', [Validators.required,Validators.minLength(2), Validators.maxLength(10)]),
        last_name: new FormControl ('',[Validators.required, Validators.minLength(2), Validators.maxLength(10)]),
        phone_number: new FormControl ( '',[ Validators.required,
          Validators.pattern("^[0-9]*$"),
          Validators.minLength(10), Validators.maxLength(10)]),
        address_line1: new FormControl ( '',Validators.required),
        address_line2: new FormControl ( '',Validators.required),
        city: new FormControl ( '',Validators.required),
        zipcode: new FormControl ('',[Validators.required, Validators.minLength(6), Validators.maxLength(6)]),
        bank_name: new FormControl ('', Validators.required),
        account_no: new FormControl ('', Validators.required),
        ifsc_code: new FormControl ('', Validators.required),
        state: new FormControl ( '',Validators.required),
        card_type: new FormControl ('',Validators.required)    
        
      })    
  }

  constructor(private adminService: AdminDashboardService, config: NgbModalConfig, private modalService: NgbModal, private router: Router){
    
    this.allUsers = [];
    this.adminService.getAllUsers()
      .subscribe(
        data => {
          console.log(data);
          this.allUsers = data;
        },
        error => {
          console.log(error);
        }
      );
  }

  refresh() {
    this.allUsers = [];
    this.adminService.getAllUsers()
      .subscribe(
        data => {
          this.allUsers = data;
        },
        error => {
          console.log(error);
        }
      );
  }

  openModal(action,user) {
    this.modalContent = user;
    this.modalService.open(action,{ size: 'lg' });
  }

  deleteUser(user_name){
    this.adminService.deleteUserByUserName(user_name).subscribe(
      (data) => {
        this.deleteStatus = data;
        console.log(this.deleteStatus.status);
        console.log(this.deleteStatus.message);
        console.log(this.deleteStatus.statusCode);

        if(this.deleteStatus.status == 'Success'){
          alert("User deleted successfully!");
          
        }
        else
          alert("User deletion unsuccessful");
      },
      error => {
        console.log(error);
      }
    );
    // this.refresh();
    this.router.navigate(['admin-dashboard']);
    this.modalService.dismissAll();
  }


  editUser(user,editform){
    console.log("editForm.value");
    console.log(editform.value.first_name);
    console.log("user");
    console.log(user);
    this.adminService.editUser(editform.value,user.user_name).subscribe(
      (data) => {
        this.responseData = data;
        console.log(this.responseData.status);
        console.log(this.responseData.message);
        console.log(this.responseData.statusCode);

        if(this.responseData.status == 'Success'){
          alert("User details edit successful!");
        }
        else if(this.responseData.message == "Some exception")
          alert("User details edit unsuccessful, try again");
        else
          alert("User details edit unsuccessful, Account No. should be unique");
      },
      error => {
        console.log(error);
      }
    );
    // this.refresh();
    this.modalService.dismissAll();
    this.router.navigate(['admin-dashboard']);
  }

}