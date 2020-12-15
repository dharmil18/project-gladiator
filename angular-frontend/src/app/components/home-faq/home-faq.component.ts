import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';

@Component({
  selector: 'home-faq',
  templateUrl: './home-faq.component.html',
  styleUrls: ['./home-faq.component.css']
})
export class HomeFAQComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit(): void {
  }

  previousPage(){
    this.location.back()
  }
}
