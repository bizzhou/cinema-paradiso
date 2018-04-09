import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

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
import {MovieDetailService} from './global/movie-detail/movie-detail.service';
import {MovieService} from './global/movie/movie.service';
import {ToastrModule} from 'ngx-toastr';
import { StarsComponent } from './global/stars/stars.component';

@NgModule({
  // entryComponents: [MessageComponent],
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
    StarsComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    NgbModule.forRoot(),
    CommonModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
  ],
  providers: [
    LoginStatusService,
    HomeService,
    MovieService,
    MovieDetailService,
    {
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
