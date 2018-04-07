import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpRequest} from '@angular/common/http';

@Injectable()
export class RegUserService {

  constructor(private http: HttpClient) {
  }

  upload(formData: FormData) {
    const req = new HttpRequest('POST', 'http://localhost:8080/user/upload', formData, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req).subscribe(data => {
      console.log(data);
    });
  }


  getProfile() {
    return this.http.get('http://localhost:8080/user/get/profile');
  }

  update(profile: any) {
    return this.http.post('http://localhost:8080/user/update/profile', profile);

  }
}
