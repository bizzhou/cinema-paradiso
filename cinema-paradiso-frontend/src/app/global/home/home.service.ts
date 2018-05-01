import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {AppConstant} from '../../app.constant';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {BehaviorSubject} from "rxjs/BehaviorSubject";

// const CAROUSEL_SERVER = 'http://localhost:8080/carousel/';



@Injectable()
export class HomeService {

  public selectedImdbId: string;
  public loginStatus = new BehaviorSubject(false);
  // currentLoginStatus = this.loginStatus.asObservable();

  constructor(private http: HttpClient) { }

  getCarousel(loginStatus: boolean) {
    return this.http.get(AppConstant.API_ENDPOINT + `carousel/get/${loginStatus}`);
  }

  errorHandler(error): any {
    console.log(error);
    return Observable.throw(error.json.error || 'Server error');
  }

  parseImdbId(imdbId: string) {
    return this.selectedImdbId = imdbId;
  }

  // getLoginStatus(status: boolean) {
  //   console.log(status);
  //   this.loginStatus.next(status);
  // }

}
