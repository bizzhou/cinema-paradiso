import {Router, RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './global/home/home.component';
import {NgModule} from '@angular/core';
import {CategoriesComponent} from './global/categories/categories.component';
import {MovieDetailComponent} from './global/movie-detail/movie-detail.component';
import {CelebrityComponent} from './global/celebrity/celebrity.component';
import {RegUserComponent} from './user/reg-user/reg-user.component';
import {SearchComponent} from './global/search/search.component';
import {AdminComponent} from './user/admin/admin.component';
import {StarsComponent} from './global/stars/stars.component';

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'movies', component: CategoriesComponent},
  // {path: ':id', component: MovieDetailComponent, data: {imdb_id: this.}},
  {path: ':id', component: MovieDetailComponent},
  {path: 'celebrity', component: CelebrityComponent},
  {path: 'user', component: RegUserComponent},
  {path: 'search', component: SearchComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'star', component: StarsComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
