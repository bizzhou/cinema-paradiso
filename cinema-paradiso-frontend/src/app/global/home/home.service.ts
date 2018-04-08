import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Http} from '@angular/http';

@Injectable()
export class HomeService {

  constructor(private http: HttpClient) { }

  getCarousel() {
    return this.http.get('http://localhost:8080/movie/carousel')
      .toPromise();
  }

}
