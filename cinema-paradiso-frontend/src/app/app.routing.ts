import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './global/home/home.component';
import {NgModule} from '@angular/core';
import {CategoriesComponent} from './global/categories/categories.component';
import {MovieDetailComponent} from './global/movie-detail/movie-detail.component';
import {CelebrityComponent} from './global/celebrity/celebrity.component';
import {RegUserComponent} from './user/reg-user/reg-user.component';
import {SearchComponent} from './global/search/search.component';
import {AdminComponent} from './user/admin/admin.component';
import {AboutComponent} from './global/about/about.component';
import {CriticReviewsComponent} from './global/review-collection/critic-reviews/critic-reviews.component';
import {RegUserReviewsComponent} from './global/review-collection/reg-user-reviews/reg-user-reviews.component';
import {RegUserPortfolioComponent} from './global/public-portfolio/reg-user/reg-user.component';
import {ContactComponent} from "./global/contact/contact.component";
import {TosComponent} from "./global/tos/tos.component";

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'movies', component: CategoriesComponent},
  {path: 'movie/:id', component: MovieDetailComponent},
  {path: 'tv/:id', component: MovieDetailComponent},
  {path: 'celebrity/:id', component: CelebrityComponent},
  {path: 'user', component: RegUserComponent},
  {path: 'search', component: SearchComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'about', component: AboutComponent},
  {path: 'portfolio/:username', component: RegUserPortfolioComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'tos', component: TosComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
