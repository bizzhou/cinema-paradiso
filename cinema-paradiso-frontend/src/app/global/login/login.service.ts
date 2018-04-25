import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../../user/user/user.model';
import {Router} from '@angular/router';
import {AppConstant} from '../../app.constant';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient, private router: Router) {
  }

  singup(user: User) {
    // const credential = {username: username, email: email, password: password};
    return this.http.post(AppConstant.API_ENDPOINT + 'user/signup', user);
  }

  login(email: string, password: string) {
    const params = new HttpParams().set('email', email).set('password', password);
    return this.http.post(AppConstant.API_ENDPOINT + 'user/login', params);
  }

  checkEmailTaken(email: string) {
    return this.http.get(AppConstant.API_ENDPOINT + 'check/email/' + email)
      .toPromise();
  }

  checkUserName(username: string) {
    return this.http.get(AppConstant.API_ENDPOINT + 'user/check/username/' + username)
      .toPromise();
  }

  logout() {
    localStorage.clear();
    this.router.navigateByUrl('/home');
    window.location.reload(true);
  }

}
