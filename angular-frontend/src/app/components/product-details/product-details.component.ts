import { APP_ID, Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Products } from 'src/app/models/Products';
import { ProductsService } from 'src/app/service/products.service';

@Component({
  selector: 'product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit{

  public product: Products;
  public productId : string;
  public productDetails : any;
  currentUrl: string;
  selected : string;

  nextMonthAmount: number = +sessionStorage.getItem("nextMonthAmount");
  limit: number = +sessionStorage.getItem("limit");

  flag1: boolean = false;
  flag2: boolean = false;
  flag3: boolean = false;
  flag4: boolean = false;

  buyNowFlag: boolean = false;
  isUserLoggedIn: boolean = false;
  cardPending: boolean = false;
  rejected: boolean = false;
  userName: string;
  status: string;


  constructor(private productService : ProductsService, private _Activatedroute:ActivatedRoute, private router: Router) {
    this._Activatedroute.paramMap.subscribe(params => { 
      this.productId = params.get('id'); 
    });
  }

  ngDoCheck() {
    
  }

  ngOnInit() {
    this.getProductById(this.productId);
    this.status = sessionStorage.getItem("cardStatus");
    this.userName = sessionStorage.getItem("user_name");
    if (this.status.match("Activated"))
      this.buyNowFlag = true;
    else 
      this.buyNowFlag = false;
    if (this.status.match("Pending")) {
      this.cardPending = true;
      this.buyNowFlag = false;
    }
    else {
      this.cardPending = false;
      this.buyNowFlag = true;
    }
    if (this.status.match("Rejected")) {
      this.buyNowFlag = false;
      this.rejected = true;
    }
    else  {
      this.buyNowFlag = true;
      this.rejected = false;
    }
    if (this.userName != null)
      this.isUserLoggedIn = true;
    else 
      this.isUserLoggedIn = false;
  }

  getProductById(id: string) {
    this.productService.getProductById(id)
      .subscribe(
        data => {
          this.productDetails = data;
          console.log(data);
            if (this.status.match("Activated")) {
            if (this.limit > ((this.productDetails.product_cost / 3) + this.nextMonthAmount)) 
              this.flag1 = true;
            if (this.limit > ((this.productDetails.product_cost / 6) + this.nextMonthAmount))
              this.flag2 = true;
            if (this.limit > ((this.productDetails.product_cost / 9) + this.nextMonthAmount))
              this.flag3 = true;
            if (this.limit > ((this.productDetails.product_cost / 12) + this.nextMonthAmount))
              this.flag4 = true;
          }
          else if (this.status.match("Pending") || this.status.match("Rejected")) {
            this.flag1 = false;
            this.flag2 = false;
            this.flag3 = false;
            this.flag4 = false;
            this.buyNowFlag = false;
          }
        },
        error => {
          console.log(error);
        }
      );
  }

  radioChange(event : any){
    this.selected = event.target.value;
    console.log(this.selected);
  }

  buyProduct() {
      const user_id = sessionStorage.getItem("user_id");
      const tenure = +this.selected;
      console.log(user_id, tenure, this.productId);
      this.productService.buyProduct(user_id, this.productId, tenure).subscribe(
        data => {
          console.log(data);
          if(data.status == 'Success')
            this.router.navigate(['/order-success']);
          else 
            alert(data.message);
        },
        error => {
          console.log(error);
        }      
      );
  }
}
