import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { AdminDashboardComponentUsers } from './components/admin-dashboard/child-components/user-action/admin-dashboard-users.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { ForgotPasswordComponent } from './components/forgot-password/forgot-password.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { OrderSuccessComponent } from './components/order-success/order-success.component';
import { ProductCompComponent } from './components/product-comp/product-comp.component';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { ProductFaqComponent } from './components/product-faq/product-faq.component';
import { RegisterComponent } from './components/register/register.component';
import { TncComponent } from './components/tnc/tnc.component';
import { MainDashboardComponent } from './components/user-dashboard-components/main-dashboard.component';


//Creating an array of route objects
//for mapping url with components
const routes: Routes = [
    //route object: JSON objects
    { path: "", redirectTo: "home", pathMatch: "full" }, //relative URL to
    { path: "home", component: HomeComponent},
    { path: "register", component: RegisterComponent},
    { path: "login", component: LoginComponent },
    { path: "admin-dashboard", component: AdminDashboardComponent },
    { path: "products", component: ProductCompComponent },
    { path: "products/:id", component: ProductDetailsComponent },
    { path: "user-dashboard", component: MainDashboardComponent },
    { path: "product-faq", component: ProductFaqComponent },
    { path: "order-success", component: OrderSuccessComponent },
    { path: "forgot-password", component: ForgotPasswordComponent },
    { path: "change-password", component: ChangePasswordComponent },
    { path: "tnc", component: TncComponent },
    { path: 'edit-delete-users', component: AdminDashboardComponentUsers }
];


@NgModule({
    imports: [
        RouterModule.forRoot(routes, {
            preloadingStrategy: PreloadAllModules
        })
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {

}