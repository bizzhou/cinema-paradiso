///<reference path="reg-user.component.ts"/>
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegUserPortfolioComponent } from './reg-user.component';

describe('RegUserComponent', () => {
  let component: RegUserPortfolioComponent;
  let fixture: ComponentFixture<RegUserPortfolioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegUserPortfolioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegUserPortfolioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
