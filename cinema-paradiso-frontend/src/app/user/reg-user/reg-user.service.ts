import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from '@angular/common/http';

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
}
