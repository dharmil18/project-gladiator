import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductFaqComponent } from './product-faq.component';

describe('ProductFaqComponent', () => {
  let component: ProductFaqComponent;
  let fixture: ComponentFixture<ProductFaqComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductFaqComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductFaqComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
