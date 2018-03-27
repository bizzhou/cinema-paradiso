import {Router, RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './movies/home/home.component';
import {UserComponent} from './global/user/user.component';
import {NgModule} from '@angular/core';
import {LoginComponent} from './global/login/login.component';
import {CategoriesComponent} from "./global/categories/categories.component";

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'movies', component: CategoriesComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
