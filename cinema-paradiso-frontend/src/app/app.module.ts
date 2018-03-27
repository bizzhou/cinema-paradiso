import { HttpClientModule } from '@angular/common/http';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';

import {HomeComponent} from './global/home/home.component';
import {FooterComponent} from './global/footer/footer.component';
import {HeaderComponent} from './global/header/header.component';
// import {UserComponent} from './global/user/user.component';
import {AppRoutingModule} from './app.routing';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './global/login/login.component';
import { CategoriesComponent } from './global/categories/categories.component';
import { MovieDetailComponent } from './global/movie-detail/movie-detail.component';
import { CelebrityComponent } from './global/celebrity/celebrity.component';
import { RegUserComponent } from './user/reg-user/reg-user.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    // UserComponent,
    LoginComponent,
    CategoriesComponent,
    MovieDetailComponent,
    CelebrityComponent,
    RegUserComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule,
    NgbModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
