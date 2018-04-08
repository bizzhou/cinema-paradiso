import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Http} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class HomeService {

  public selectedImdbId: string;

  constructor(private http: HttpClient) { }

  getCarousel() {
    return this.http.get('http://localhost:8080/movie/carousel');
  }

  errorHandler(error): any {
    console.log(error);
    return Observable.throw(error.json.error || 'Server error');
  }

  parseImdbId(imdbId: string) {
    return this.selectedImdbId = imdbId;
  }


}
