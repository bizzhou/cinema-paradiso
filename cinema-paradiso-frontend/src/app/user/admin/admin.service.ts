import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppConstant} from '../../app.constant';

@Injectable()
export class AdminService {

  constructor(private http: HttpClient) { }

  getReportedReviews(): any {
    return this.http.get(AppConstant.API_ENDPOINT + 'review/report/get/all');
  }

}
