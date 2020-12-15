import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'tnc',
  templateUrl: './tnc.component.html',
  styleUrls: ['./tnc.component.css']
})
export class TncComponent implements OnInit {

  constructor(private location: Location) { }

  ngOnInit(): void {
  }

  previousPage(){
    this.location.back()
  }
}
