import { Component, OnInit } from '@angular/core';
import { Cards } from 'src/app/models/Cards';
import { UserDashboardService } from 'src/app/service/user-dashboard-service.service';

@Component({
  selector: 'card-details-component',
  templateUrl: './card-details-component.component.html',
  styleUrls: ['./card-details-component.component.css']
})
export class CardDetailsComponent implements OnInit {

  cardData: any;
  cardDetails: Cards;
  balanceCredit: number;
  userId: string = sessionStorage.getItem("user_id");
  cardHolder: string = sessionStorage.getItem("fName") + " " + sessionStorage.getItem("lName");
  cssClass: string;

  constructor(private userService: UserDashboardService) { 
    this.getCardDetails();
  }

  ngOnInit(): void {
    this.getCardDetails();
  }

    getCardDetails() {
      this.userService.getCardDetails(this.userId).subscribe(
        data => {
          this.cardData = data;
          this.cardDetails = new Cards(this.cardData.card_id, this.cardData.card_no, this.cardData.card_type, this.cardData.expiry_date, this.cardData.limit, this.cardData.status, this.cardData.issue_date, this.cardData.joining_fee, this.cardData.user_id);
          sessionStorage.setItem("limit", data.limit.toFixed(2));
          sessionStorage.setItem("cardStatus", data.status);
          if (this.cardDetails.card_type == 'Gold')
            this.cssClass = "card-gold";
          else 
            this.cssClass = "card-titanium";
        }
      );

      this.userService.getBalanceCredit(this.userId).subscribe(
        data => {
          this.balanceCredit = data;
        }
      );

      this.userService.getNextMonthAmount(this.userId).subscribe(
        data => {
          sessionStorage.setItem("nextMonthAmount", data.toFixed(2));
          console.log(data);
        }
      );
    }
}
