import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';

const MOVIE_SERVER = 'http://localhost:8080/movie/';
const WISH_LIST_SERVER = 'http://localhost:8080/wishlist/';
@Injectable()
export class MovieService {

  private movieIdSource = new BehaviorSubject<string>('');
  movieIdObservable = this.movieIdSource.asObservable();

  constructor(private http: HttpClient) { }

  setSelectedMovieId(movieId: string) {
    this.movieIdSource.next(movieId);
  }

  getSelectedMovieId(): any {
    return this.movieIdObservable;
  }

  getMoviesPlaying() {
    return this.http.get(MOVIE_SERVER + 'playing');
  }

  getMoviesTrending() {
    return this.http.get(MOVIE_SERVER + 'trending');
  }

  getMoviesComingSoon() {
    return this.http.get(MOVIE_SERVER + 'comingSoon');
  }

  getTopBoxOffice() {
    return this.http.get(MOVIE_SERVER + 'topBoxOffice');
  }

  getMovie(imdbId: string): any {
    return this.http.get(MOVIE_SERVER + imdbId);
  }

  addToWishList(imdbId: string) {
    const params = new HttpParams().set('filmId', imdbId);
    return this.http.post('http://localhost:8080/wishlist/add', params);
  }

  removeFromWishList(imdbId: string) {
    // const params = new HttpParams().set('filmId', imdbId);
    return this.http.delete(WISH_LIST_SERVER + 'delete/' + imdbId);
  }

  isMovieInWishList(imdbId: string) {
    const params = new HttpParams().set('filmId', imdbId);
    console.log('Check movie existence: ' + WISH_LIST_SERVER + 'exist');
    return this.http.post(WISH_LIST_SERVER + 'exist', params);
  }

}
