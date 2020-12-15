import { Component, OnInit, Output } from '@angular/core';
import { ProductsService } from 'src/app/service/products.service';

@Component({
  selector: 'product-comp',
  templateUrl: './product-comp.component.html',
  styleUrls: ['./product-comp.component.css']
})
export class ProductCompComponent implements OnInit{



  obtainedResults : any;
  constructor(private productsService: ProductsService) {

      this.obtainedResults = [];
      this.productsService.getProducts()
      .subscribe(
        data => {
          console.log(data);
          this.obtainedResults = data;
       
        },
        error => {
          console.log(error);
        }
      );

   }
  
  ngOnInit() : void{
    this.obtainedResults = [];
    this.productsService.getProducts()
    .subscribe(
      data => {
        this.obtainedResults = data;
       
      },
      error => {
        console.log(error);
      }
    );
  }
}
