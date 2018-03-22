import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  singup(username: string, email: string, password: string) {
    const credential = {username: username, email: email, password: password};
    return this.http.post('http://localhost:8080/user/signup', credential);
  }

  login(email: string, password: string) {
    const params = new HttpParams().set('email', email).set('password', password);
    return this.http.post('http://localhost:8080/user/login', params);
  }

  checkEmailTaken(email: string) {

  }

}
