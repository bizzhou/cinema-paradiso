import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Review} from '../models/review.model';
import {Observable} from 'rxjs/Observable';
import {AppConstant} from '../../app.constant';

const MOVIE_SERVER = AppConstant.API_ENDPOINT + 'movie/';
const WISH_LIST_SERVER = AppConstant.API_ENDPOINT + 'wishlist/';

@Injectable()
export class MovieService {

  private movieIdSource = new BehaviorSubject<string>('');
  movieIdObservable = this.movieIdSource.asObservable();

  constructor(private http: HttpClient) {
  }

  setSelectedMovieId(movieId: string) {
    this.movieIdSource.next(movieId);
  }

  getSelectedMovieId(): any {
    return this.movieIdObservable;
  }

  getMoviesPlaying() {
    return this.http.get(MOVIE_SERVER + 'get/playing');
  }

  getMoviesTrending() {
    return this.http.get(MOVIE_SERVER + 'get/trending');
  }

  getMoviesComingSoon() {
    return this.http.get(MOVIE_SERVER + 'get/comingSoon');
  }

  getTopBoxOffice() {
    return this.http.get(MOVIE_SERVER + 'get/topBoxOffice');
  }

  getMovie(imdbId: string): any {
    return this.http.get(MOVIE_SERVER + imdbId);
  }

  addToWishList(imdbId: string) {
    const params = new HttpParams().set('filmId', imdbId);
    return this.http.post(WISH_LIST_SERVER + 'add', params);
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

  getMovieDetails(imdbId: string): any {
    return this.http.get(MOVIE_SERVER + `get/${imdbId}`);
  }

  addRatingToMovie(movieId: string, rating: number) {
    return this.http.post(MOVIE_SERVER + `add/rating/${movieId}/${rating}`, null);
  }

  editRatingForMovie(movieId: string, rating: number) {
    return this.http.post(MOVIE_SERVER + `edit/rating/${movieId}/${rating}`, null);
  }

  addReview(review: Review) {
    return this.http.post(AppConstant.API_ENDPOINT + `review/add/${review.imdbId}`, review);
  }

  getMovieReviews(selectedMovieId: string): Observable<any> {
    return this.http.get(AppConstant.API_ENDPOINT + `review/get/${selectedMovieId}`);
  }
}
