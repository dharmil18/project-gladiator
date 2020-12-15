import { Component, OnInit } from '@angular/core';
import { Transactions } from 'src/app/models/Transactions';
import { UserDashboardService } from 'src/app/service/user-dashboard-service.service';

@Component({
  selector: 'transactions-component',
  templateUrl: './transactions-component.component.html',
  styleUrls: ['./transactions-component.component.css']
})
export class TransactionsComponent implements OnInit {
  transactions: any;
  display: boolean = false;
  userId: string = sessionStorage.getItem("user_id");
  constructor(private userService: UserDashboardService) { 
    this.getTransactionDetails();
  }

  ngOnInit(): void {
    this.getTransactionDetails();
  }

  getTransactionDetails() {
    this.userService.getTransactionDetails(this.userId).subscribe(
      data => {
        this.transactions = data;
        if (this.transactions.length > 0)
          this.display = true;
        else 
          this.display = false;
      }
    );
  }

}
