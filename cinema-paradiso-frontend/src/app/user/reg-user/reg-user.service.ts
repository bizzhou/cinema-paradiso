import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from '@angular/common/http';
import {Movie} from '../../global/models/movie.model';
import {AppConstant} from '../../app.constant';

@Injectable()
export class RegUserService {

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

  removeFromWishList(imdbId: string) {
    return this.http.delete(AppConstant.API_ENDPOINT + `wishlist/delete/${imdbId}`);
  }

  deleteUser(userId: number) {
    return this.http.delete(AppConstant.API_ENDPOINT + `admin/suspend/${userId}`);
  }
}
