import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from '@angular/common/http';
import {Review} from '../models/review.model';
import {AppConstant} from '../../app.constant';
import {Movie} from '../models/movie.model';
import {Token} from '../models/token.model';

const MOVIE_SERVER = AppConstant.API_ENDPOINT + 'movie/';
const WISH_LIST_SERVER = AppConstant.API_ENDPOINT + 'wishlist/';
const NOT_INTERESTED_LIST_SERVER = AppConstant.API_ENDPOINT + 'not-interested/';

@Injectable()
export class MovieService {

  constructor(private http: HttpClient) {
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

  getMoviesTopRated(pageNo: string, pageSize: string) {
    const param = new HttpParams().set('pageNo', pageNo)
      .set('pageSize', pageSize);
    return this.http.post(MOVIE_SERVER + 'topRated', param);
  }

  getTvsTonight(pageNo: string, pageSize: string) {
    const param = new HttpParams().set('pageNo', pageNo)
      .set('pageSize', pageSize);
    return this.http.post(AppConstant.API_ENDPOINT + 'tv/comingSoon', param);
  }

  getTvsTopRated(pageNo: string, pageSize: string) {
    const param = new HttpParams().set('pageNo', pageNo)
      .set('pageSize', pageSize);
    return this.http.post(AppConstant.API_ENDPOINT + 'tv/topRated', param);
  }

  getMovieDetails(imdbId: string): any {
    return this.http.get(MOVIE_SERVER + `get/${imdbId}`);
  }

  getCustomMovieDetails(imdbId: string): any {
    return this.http.get(MOVIE_SERVER + `getCustomMovie/${imdbId}`);
  }

  getTVDetails(imdbId: string): any {
    return this.http.get(AppConstant.API_ENDPOINT + `tv/get/${imdbId}`);
  }

  getCustomTVDetails(imdbId: string): any {
    return this.http.get(AppConstant.API_ENDPOINT + `tv/getCustomTV/${imdbId}`);
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

  getTVReviews(selectedMovieId: string) {
    return this.http.get(AppConstant.API_ENDPOINT + `review/get/tv/${selectedMovieId}`);
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


  getAllMovies() {
    return this.http.get(AppConstant.API_ENDPOINT + `movie/get/all`);
  }

  deleteMovie(filmId: string) {
    return this.http.delete(AppConstant.API_ENDPOINT + `movie/delete/${filmId}`);
  }

  addMovie(movie: Movie) {
    return this.http.post(AppConstant.API_ENDPOINT + `movie/add`, movie);
  }

  updateMovie(movie: Movie) {
    return this.http.post(AppConstant.API_ENDPOINT + `movie/update`, movie);
  }

  getAllReviews() {
    return this.http.get(AppConstant.API_ENDPOINT + `review/get/all`);
  }

  reportReview(reviewId: number, reportReason: string) {
    const param = new HttpParams().set('reportReason', reportReason);
    return this.http.post(AppConstant.API_ENDPOINT + `review/report/${reviewId}`, param);
  }

  uploadImages(fileList: FileList, id: string) {
    const formData: FormData = new FormData();

    for (let i = 0; i < fileList.length; i++) {
      formData.append('files', fileList[i], fileList[i].name);
    }

    formData.append('movie', id);

    const req = new HttpRequest('POST', AppConstant.API_ENDPOINT + 'admin/upload/images', formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);

  }

  uploadPoster(file: FileList, id: string) {
    const formData: FormData = new FormData();
    formData.append('file', file[0], file[0].name);
    formData.append('movie', id);

    const req = new HttpRequest('POST', AppConstant.API_ENDPOINT + 'admin/upload/poster', formData, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);
  }

  getFilmId() {
    return this.http.get(AppConstant.API_ENDPOINT + 'movie/get/new_film_id', {responseType: 'text'});
  }

}
