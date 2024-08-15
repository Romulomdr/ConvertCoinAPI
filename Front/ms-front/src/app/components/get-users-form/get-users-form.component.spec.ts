import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetUsersFormComponent } from './get-users-form.component';

describe('GetUsersFormComponent', () => {
  let component: GetUsersFormComponent;
  let fixture: ComponentFixture<GetUsersFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetUsersFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetUsersFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
