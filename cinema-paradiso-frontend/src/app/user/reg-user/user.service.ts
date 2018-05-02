import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from '@angular/common/http';
import {AppConstant} from '../../app.constant';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  upload(formData: FormData) {
    const req = new HttpRequest('POST', AppConstant.API_ENDPOINT + 'user/update/avatar', formData, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);
  }

  getProfile() {
    return this.http.get(AppConstant.API_ENDPOINT + 'user/get/profile');
  }

  update(profile: any) {
    return this.http.post(AppConstant.API_ENDPOINT + 'user/update/profile', profile);
  }

  changePassword(oldPassword: string, newPassword: string) {
    const params = new HttpParams().set('old_password', oldPassword).set('new_password', newPassword);
    return this.http.post(AppConstant.API_ENDPOINT + 'user/change/password', params);
  }

  deleteUserRating(imdbId: string) {
    return this.http.delete(AppConstant.API_ENDPOINT + `movie/delete/rating/${imdbId}`);
  }

  addToWishList(imdbId: string) {
    const params = new HttpParams().set('filmId', imdbId);
    return this.http.post(AppConstant.API_ENDPOINT + 'wishlist/add', params);
  }

  removeFromWishList(imdbId: string) {
    return this.http.delete(AppConstant.API_ENDPOINT + `wishlist/delete/${imdbId}`);
  }

  addToNotInterestedList(imdbId: string) {
    const params = new HttpParams().set('filmId', imdbId);
    return this.http.post(AppConstant.API_ENDPOINT + 'not-interested/add', params);
  }

  removeFromNotInterestedList(imdbId: string) {
    return this.http.delete(AppConstant.API_ENDPOINT + `not-interested/delete/${imdbId}`);
  }

  deleteUser(userId: number) {
    return this.http.delete(AppConstant.API_ENDPOINT + `admin/suspend/${userId}`);
  }

  getUserReviews() {
    return this.http.get(AppConstant.API_ENDPOINT + `review/get/user_reviews`);
  }

  getAllUsers() {
    return this.http.get(AppConstant.API_ENDPOINT + `admin/get/users`);
  }
}
