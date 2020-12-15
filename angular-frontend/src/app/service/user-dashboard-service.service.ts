import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Cards } from '../models/Cards';
import { Transactions } from '../models/Transactions';
import { MonthlyPaidTransactions } from '../models/MonthlyPaidTransactions';

@Injectable({
  providedIn: 'root'
})
export class UserDashboardService {
    constructor(private httpClient: HttpClient) { }

    getCardDetails(id: string) {
        const baseUrl = "http://localhost:2020/gladiator/getCardDetails/" + id;
        return this.httpClient.get<Cards>(baseUrl);
    }

    getTransactionDetails(id: string) {
      const baseUrl = "http://localhost:2020/gladiator/getAllTransactions/" + id;
      return this.httpClient.get<Transactions>(baseUrl);
    }

    getBalanceCredit(id: string) {
      const baseUrl = "http://localhost:2020/gladiator/getBalanceCredit/" + id;
      return this.httpClient.get<number>(baseUrl);
    }

    getMonthlyPaidTransactions(id: string) {
      const baseUrl = "http://localhost:2020/gladiator/getAllMonthlyPaidTransactions/" + id;
      return this.httpClient.get<MonthlyPaidTransactions>(baseUrl);
    }

    getUpcomingTransactions(id: string) {
      const baseUrl = "http://localhost:2020/gladiator/getAllUpcomingTransactions/" + id;
      return this.httpClient.get<MonthlyPaidTransactions>(baseUrl);
    }

    getNextMonthAmount(id: string) {
      const baseUrl = "http://localhost:2020/gladiator/getNextMonthAmount/" + id;
      return this.httpClient.get<number>(baseUrl);
    }
}