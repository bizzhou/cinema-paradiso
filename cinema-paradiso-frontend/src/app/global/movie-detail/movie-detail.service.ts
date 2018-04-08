import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

const MOVIE_SERVER = 'http://localhost:8080/movie/';

@Injectable()
export class MovieDetailService {

  constructor(private http: HttpClient) { }

  getMovie(imdbId: string): any {
    return this.http.get(MOVIE_SERVER + imdbId);
  }

}
