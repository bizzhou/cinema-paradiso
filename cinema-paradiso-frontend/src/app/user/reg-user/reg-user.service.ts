import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from '@angular/common/http';
import {Movie} from '../../global/models/movie.model';

@Injectable()
export class RegUserService {

  constructor(private http: HttpClient) {
  }

  upload(formData: FormData) {
    const req = new HttpRequest('POST', 'http://localhost:8080/user/update/avatar', formData, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);
  }

  getProfile() {
    return this.http.get('http://localhost:8080/user/get/profile');
  }

  update(profile: any) {
    return this.http.post('http://localhost:8080/user/update/profile', profile);
  }

  changePassword(oldPassword: string, newPassword: string) {
    const params = new HttpParams().set('old_password', oldPassword).set('new_password', newPassword);
    return this.http.post('http://localhost:8080/user/change/password', params);
  }

  deleteUserRating(imdbId: string) {
    return this.http.delete(`http://localhost:8080/movie/delete/rating/${imdbId}`);
  }

  removeFromWishList(imdbId: string) {
    return this.http.delete(`http://localhost:8080/wishlist/delete/${imdbId}`);
  }

  deleteUser(userId: number) {
    return this.http.delete(`http://localhost:8080/admin/suspend/${userId}`);
  }
}
