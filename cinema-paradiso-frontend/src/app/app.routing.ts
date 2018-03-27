import {Router, RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './movies/home/home.component';
import {NgModule} from '@angular/core';
import {CategoriesComponent} from './global/categories/categories.component';
import {MovieDetailComponent} from './global/movie-detail/movie-detail.component';

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'movies', component: CategoriesComponent},
  {path: 'movie-detail', component: MovieDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
