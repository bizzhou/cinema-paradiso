import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegUserReviewsComponent } from './reg-user-reviews.component';

describe('RegUserReviewsComponent', () => {
  let component: RegUserReviewsComponent;
  let fixture: ComponentFixture<RegUserReviewsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegUserReviewsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegUserReviewsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
