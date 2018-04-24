import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Review} from '../models/review.model';
import {Observable} from 'rxjs/Observable';

const MOVIE_SERVER = 'http://localhost:8080/movie/';
const WISH_LIST_SERVER = 'http://localhost:8080/wishlist/';

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

  getMoviesPlaying(pageNo: string, pageSize: string) {
    const param = new HttpParams().set('pageNo', pageNo)
                                  .set('pageSize', pageSize);
    return this.http.post(MOVIE_SERVER + 'playing', param);
  }

  getMoviesTrending(pageNo: string, pageSize: string) {
    const param = new HttpParams().set('pageNo', pageNo)
      .set('pageSize', pageSize);
    return this.http.post(MOVIE_SERVER + 'trending', param);
  }

  getMoviesComingSoon(pageNo: string, pageSize: string) {
    const param = new HttpParams().set('pageNo', pageNo)
      .set('pageSize', pageSize);
    return this.http.post(MOVIE_SERVER + 'comingSoon', param);
  }

  getTopBoxOffice(pageNo: string, pageSize: string) {
    const param = new HttpParams().set('pageNo', pageNo)
      .set('pageSize', pageSize);
    return this.http.post(MOVIE_SERVER + 'topBoxOffice', param);
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
    return this.http.post(`http://localhost:8080/review/add/${review.imdbId}`, review);
  }

  getMovieReviews(selectedMovieId: string): Observable<any> {
    return this.http.get(`http://localhost:8080/review/get/${selectedMovieId}`);
  }
}
