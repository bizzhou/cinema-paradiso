import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
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

  getMoviesPlaying() {
    return this.http.get('http://localhost:8080/movie/playing');
  }

  parseImdbId(imdbId: string) {
    return this.selectedImdbId = imdbId;
  }

}
