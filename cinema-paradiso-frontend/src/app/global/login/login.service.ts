import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {User} from '../user/user.model';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {
  }

  singup(user: User) {
    // const credential = {username: username, email: email, password: password};
    return this.http.post('http://localhost:8080/user/signup', user);
  }

  login(email: string, password: string) {
    const params = new HttpParams().set('email', email).set('password', password);
    return this.http.post('http://localhost:8080/user/login', params);
  }

  checkEmailTaken(email: string) {

  }

}
