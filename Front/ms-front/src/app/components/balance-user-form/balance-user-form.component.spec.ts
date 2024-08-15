import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BalanceUserFormComponent } from './balance-user-form.component';

describe('BalanceUserFormComponent', () => {
  let component: BalanceUserFormComponent;
  let fixture: ComponentFixture<BalanceUserFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BalanceUserFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BalanceUserFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
