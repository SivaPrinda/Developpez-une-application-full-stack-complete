import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfilComponent } from './userProfil.component';

describe('SignInComponent', () => {
  let component: UserProfilComponent;
  let fixture: ComponentFixture<UserProfilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UserProfilComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(UserProfilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
