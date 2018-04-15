import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

const CAROUSEL_SERVER = 'http://localhost:8080/carousel/';

@Injectable()
export class HomeService {

  public selectedImdbId: string;

  constructor(private http: HttpClient) { }

  getCarousel() {
    const httpParams = new HttpParams()
    return this.http.get(CAROUSEL_SERVER + 'get');
  }

  errorHandler(error): any {
    console.log(error);
    return Observable.throw(error.json.error || 'Server error');
  }

  parseImdbId(imdbId: string) {
    return this.selectedImdbId = imdbId;
  }

}
