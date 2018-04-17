import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

const MOVIE_SERVER = 'http://localhost:8080/movie/';

@Injectable()
export class MovieDetailService {

  constructor(private http: HttpClient) {
  }

  getMovieDetails(imdbId: string): any {
    console.log(MOVIE_SERVER + `get/${imdbId}`);
    return this.http.get(MOVIE_SERVER + `get/${imdbId}`);
  }

  rateMovie(hovered: number, imdbId: string) {
    return this.http.post(MOVIE_SERVER + `rate/${imdbId}/${hovered}`, null);
  }

}
