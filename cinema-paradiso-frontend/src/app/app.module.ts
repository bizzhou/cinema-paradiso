import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {CommonModule} from '@angular/common';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {HomeComponent} from './global/home/home.component';
import {FooterComponent} from './global/footer/footer.component';
import {HeaderComponent} from './global/header/header.component';
import {AppRoutingModule} from './app.routing';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {LoginComponent} from './global/login/login.component';
import {CategoriesComponent} from './global/categories/categories.component';
import {MovieDetailComponent} from './global/movie-detail/movie-detail.component';
import {CelebrityComponent} from './global/celebrity/celebrity.component';
import {RegUserComponent} from './user/reg-user/reg-user.component';
import {SearchComponent} from './global/search/search.component';
import {AdminComponent} from './user/admin/admin.component';
import {LoginStatusService} from './global/login/login.status.service';
import {AuthInterceptor} from './http.interceptor';
import {HomeService} from './global/home/home.service';
import {MovieService} from './global/movie-detail/movie.service';
import {ToastrModule} from 'ngx-toastr';
import {SearchService} from './global/search/search.service';
import {LazyLoadImageModule} from 'ng-lazyload-image';

import {VgCoreModule} from 'videogular2/core';
import {VgControlsModule} from 'videogular2/controls';
import {VgOverlayPlayModule} from 'videogular2/overlay-play';
import {VgBufferingModule} from 'videogular2/buffering';


import {StarsComponent} from './global/stars/stars.component';
import {AccountComponent} from './user/reg-user/account/account.component';
import {WishlistComponent} from './user/reg-user/wishlist/wishlist.component';
import {AboutComponent} from './global/about/about.component';
import {NotInterestedComponent} from './user/reg-user/not-interested/not-interested.component';
import {UserReviewComponent} from './user/reg-user/user-review/user-review.component';
import {UserRatingComponent} from './user/reg-user/user-rating/user-rating.component';
import {MdReviewComponent} from './global/movie-detail/md-review/md-review.component';
import {CriticReviewsComponent} from './global/review-collection/critic-reviews/critic-reviews.component';
import {RegUserReviewsComponent} from './global/review-collection/reg-user-reviews/reg-user-reviews.component';
import {CriticPortfolioComponent} from './global/public-portfolio/critic/critic.component';
import {RegUserPortfolioComponent} from './global/public-portfolio/reg-user/reg-user.component';
import {CategoriesService} from './global/categories/categories.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    CategoriesComponent,
    MovieDetailComponent,
    CelebrityComponent,
    RegUserComponent,
    SearchComponent,
    AdminComponent,
    AccountComponent,
    WishlistComponent,
    AboutComponent,
    StarsComponent,
    NotInterestedComponent,
    UserReviewComponent,
    UserRatingComponent,
    MdReviewComponent,
    CriticReviewsComponent,
    RegUserReviewsComponent,
    CriticPortfolioComponent,
    RegUserPortfolioComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    CommonModule,
    BrowserAnimationsModule,
    NgbModule.forRoot(),
    ToastrModule.forRoot(),
    LazyLoadImageModule,
    VgCoreModule,
    VgControlsModule,
    VgOverlayPlayModule,
    VgBufferingModule
  ],
  providers: [
    LoginStatusService,
    SearchService,
    HomeService,
    MovieService,
    CategoriesService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
