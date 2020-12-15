import { BrowserModule } from '@angular/platform-browser';
import { NgModule, Pipe } from '@angular/core';
import  { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { CardDetailsComponent } from './components/user-dashboard-components/child-components/card-details-component/card-details-component.component';
import { MainDashboardComponent } from './components/user-dashboard-components/main-dashboard.component';
import { TransactionsComponent } from './components/user-dashboard-components/child-components/transactions-component/transactions-component.component';
// import { MenuComponent } from './components/user-dashboard-components/child-components/menu-component/menu-component.component';
import { MonthlyInstallmentsComponent } from './components/user-dashboard-components/child-components/monthly-installments/monthly-installments.component';
import { UpcomingInstallmentsComponent } from './components/user-dashboard-components/child-components/upcoming-installments/upcoming-installments.component';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './components/home/home.component';
// import { NavbarComponent } from './components/navbar/navbar.component';
import { RegisterComponent } from './components/register/register.component';
import { ProductCompComponent } from './components/product-comp/product-comp.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { HomeFAQComponent } from './components/home-faq/home-faq.component';
import { ProductFaqComponent } from './components/product-faq/product-faq.component';
import { OrderSuccessComponent } from './components/order-success/order-success.component';
import { FooterComponent } from './components/footer/footer.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { TncComponent } from './components/tnc/tnc.component';
import { AdminDashboardComponentCards } from './components/admin-dashboard/child-components/card-action/admin-dashboard-cards.component';
import { AdminDashboardComponentUsers } from './components/admin-dashboard/child-components/user-action/admin-dashboard-users.component';
import { HomeCardsComponent } from './components/home-cards/home-cards.component';

@NgModule({
  declarations: [
    AppComponent,
    CardDetailsComponent,
    MainDashboardComponent,
    TransactionsComponent,
    // MenuComponent,
    MonthlyInstallmentsComponent,
    UpcomingInstallmentsComponent,
    LoginComponent,
    HomeComponent,
    // NavbarComponent,
    HomeFAQComponent,
    RegisterComponent,
    ProductCompComponent,
    ProductDetailsComponent,
    AdminDashboardComponent,
    ProductFaqComponent,
    OrderSuccessComponent,
    FooterComponent,
    ForgotPasswordComponent,
    ChangePasswordComponent,
    TncComponent,
    AdminDashboardComponentCards,
    AdminDashboardComponentUsers,
    HomeCardsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
