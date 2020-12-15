import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'product-faq',
  templateUrl: './product-faq.component.html',
  styleUrls: ['./product-faq.component.css']
})
export class ProductFaqComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit(): void {
  }

  previousPage(){
    this.location.back()
  }
}
