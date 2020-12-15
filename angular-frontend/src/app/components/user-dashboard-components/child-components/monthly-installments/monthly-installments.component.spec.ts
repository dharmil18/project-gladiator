import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlyInstallmentsComponent } from './monthly-installments.component';

describe('MonthlyInstallmentsComponent', () => {
  let component: MonthlyInstallmentsComponent;
  let fixture: ComponentFixture<MonthlyInstallmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MonthlyInstallmentsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MonthlyInstallmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
