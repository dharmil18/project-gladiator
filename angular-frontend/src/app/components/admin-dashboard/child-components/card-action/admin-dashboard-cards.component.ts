import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import { AdminDashboardService } from 'src/app/service/admin-dashboard.service';



@Component({
  selector: 'admin-dashboard-cards',
  templateUrl: './admin-dashboard-cards.component.html',
  styleUrls: ['./admin-dashboard-cards.component.css']
})
export class AdminDashboardComponentCards {
  title = 'gladiator';
  adminName;
  users: any;
  modalContent:undefined;
  actionStatus: any;

  ngOnInit(): void {
    this.users = [];
    this.adminService.getPendingUsers()
      .subscribe(
        data => {
          console.log(data);
          this.users = data;
        },
        error => {
          console.log(error);
        }
      );
  }

  constructor(private adminService: AdminDashboardService, config: NgbModalConfig, private modalService: NgbModal, private router: Router){

    // this.users = [];
    // this.adminService.getPendingUsers()
    //   .subscribe(
    //     data => {
    //       console.log(data);
    //       this.users = data;
    //     },
    //     error => {
    //       console.log(error);
    //     }
    //   );
  }

  refresh() {
    this.users = [];
    this.adminService.getPendingUsers()
      .subscribe(
        data => {
          this.users = data;
        },
        error => {
          console.log(error);
        }
      );
  }

  confirmModal(action,user) {
    this.modalContent = user;
    this.modalService.open(action);
  }


  activateCard(user_id){
    this.adminService.changeStatusOfCard(user_id,"Activated").subscribe(
      (data) => {
        this.actionStatus = data;
        console.log(this.actionStatus.status);
        console.log(this.actionStatus.message);
        console.log(this.actionStatus.statusCode);

        if(this.actionStatus.status == 'Success'){
          alert("Card activated!");
          this.refresh();
        }
        else
          alert("Card actiation failed");
      },
      error => {
        console.log(error);
      }
    );
    this.refresh();
    this.modalService.dismissAll();
    this.router.navigate(['admin-dashboard']);
  }

  rejectCard(user_id){
    this.adminService.changeStatusOfCard(user_id,"Rejected").subscribe(
      (data) => {
        this.actionStatus = data;
        console.log(this.actionStatus.status);
        console.log(this.actionStatus.message);
        console.log(this.actionStatus.statusCode);

        if(this.actionStatus.status == 'Success'){
          alert("Card rejected!");
        }
        else
          alert("Card rejection failed");
      },
      error => {
        console.log(error);
      }
    );
    this.refresh();
    this.modalService.dismissAll();
    this.router.navigate(['admin-dashboard']);
  }

}