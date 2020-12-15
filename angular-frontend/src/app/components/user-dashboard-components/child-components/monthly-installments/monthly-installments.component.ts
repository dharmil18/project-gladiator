import { Component, OnInit } from '@angular/core';
import { MonthlyPaidTransactions } from 'src/app/models/MonthlyPaidTransactions';
import { UserDashboardService } from 'src/app/service/user-dashboard-service.service';

@Component({
  selector: 'monthly-installments',
  templateUrl: './monthly-installments.component.html',
  styleUrls: ['./monthly-installments.component.css']
})
export class MonthlyInstallmentsComponent implements OnInit {

  monthlyPaid: any;
  flag: boolean = false;
  buttonFlag: boolean = true;
  userId: string = sessionStorage.getItem("user_id");
  display: boolean = false;

  constructor(private userService: UserDashboardService) { }

  ngOnInit(): void {
    this.getMonthlyPaidTransactions();
  }

  getMonthlyPaidTransactions() {
    this.userService.getMonthlyPaidTransactions(this.userId).subscribe(
      data => {
        console.log(data);
        this.monthlyPaid = data;
        if (this.monthlyPaid.length > 0) {
          this.display = true;
        }
        else {
          this.display = false;
        }
      }
    );
  }

  displayAll() {
    this.flag = true;
    this.buttonFlag = false;
  }

}
