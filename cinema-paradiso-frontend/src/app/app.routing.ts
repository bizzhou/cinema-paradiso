import {Router, RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './movies/home/home.component';
import {UserComponent} from './shared/user/user.component';
import {NgModule} from '@angular/core';

const appRoutes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'login', component: UserComponent},
  {path: 'home', component: HomeComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
