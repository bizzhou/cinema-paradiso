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

  constructor(private http: HttpClient) { }

  getCarousel() {
    return this.http.get(AppConstant.API_ENDPOINT + `carousel/get`);
  }

  getCustomCarousel() {
    return this.http.get(AppConstant.API_ENDPOINT + `carousel/getCustomCarousel`);
  }

  errorHandler(error): any {
    console.log(error);
    return Observable.throw(error.json.error || 'Server error');
  }

  parseImdbId(imdbId: string) {
    return this.selectedImdbId = imdbId;
  }

}
