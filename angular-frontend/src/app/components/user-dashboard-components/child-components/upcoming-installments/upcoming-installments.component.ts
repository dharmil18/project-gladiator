import { Component, OnInit } from '@angular/core';
import { UserDashboardService } from 'src/app/service/user-dashboard-service.service';

@Component({
  selector: 'upcoming-installments',
  templateUrl: './upcoming-installments.component.html',
  styleUrls: ['./upcoming-installments.component.css']
})
export class UpcomingInstallmentsComponent implements OnInit {

  upcoming: any;
  flag: boolean = false;
  buttonFlag: boolean = true;
  userId: string = sessionStorage.getItem("user_id");
  display: boolean = false;

  constructor(private userService: UserDashboardService) { 
    this.getUpcomingTransactions();
  }

  ngOnInit(): void {
    this.getUpcomingTransactions();
  }

  getUpcomingTransactions() {
    this.userService.getUpcomingTransactions(this.userId).subscribe(
      data => {
        this.upcoming = data;
        if (this.upcoming.length > 0)
          this.display = true;
        else 
          this.display = false;
      }
    );
  }

  displayAll() {
    this.flag = true;
    this.buttonFlag = false;
  }
}
