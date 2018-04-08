import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MovieDetailSidebarComponent } from './movie-detail-sidebar.component';

describe('MovieDetailSidebarComponent', () => {
  let component: MovieDetailSidebarComponent;
  let fixture: ComponentFixture<MovieDetailSidebarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MovieDetailSidebarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MovieDetailSidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
