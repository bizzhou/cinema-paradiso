import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Review} from '../models/review.model';
import {AppConstant} from '../../app.constant';

const MOVIE_SERVER = AppConstant.API_ENDPOINT + 'movie/';
const WISH_LIST_SERVER = AppConstant.API_ENDPOINT + 'wishlist/';

@Injectable()
export class MovieService {

  constructor(private http: HttpClient) {
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

  addToWishList(imdbId: string) {
    const params = new HttpParams().set('filmId', imdbId);
    return this.http.post(WISH_LIST_SERVER + 'add', params);
  }

  removeFromWishList(imdbId: string) {
    return this.http.delete(WISH_LIST_SERVER + `delete/${imdbId}`);
  }

  isMovieInWishList(imdbId: string) {
    const params = new HttpParams().set('filmId', imdbId);
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

  getMovieReviews(selectedMovieId: string) {
    return this.http.get(AppConstant.API_ENDPOINT + `review/get/${selectedMovieId}`);
  }

  editReviewForMovie(clickedReview: Review) {
    console.log('clicked review ', clickedReview);
    clickedReview.imdbId = clickedReview['movie']['imdbId'];
    return this.http.post(AppConstant.API_ENDPOINT + `review/edit/${clickedReview.imdbId}`, clickedReview);
  }

  deleteReviewForMovie(reviewId: number) {
    return this.http.delete(AppConstant.API_ENDPOINT + `review/delete/${reviewId}`);
  }

  getUserReviews() {
    return this.http.get(AppConstant.API_ENDPOINT + `review/gets/user_reviews`);
  }


}
