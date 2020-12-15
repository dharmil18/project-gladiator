import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardDetailsComponentComponent } from './card-details-component.component';

describe('CardDetailsComponentComponent', () => {
  let component: CardDetailsComponentComponent;
  let fixture: ComponentFixture<CardDetailsComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardDetailsComponentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardDetailsComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
