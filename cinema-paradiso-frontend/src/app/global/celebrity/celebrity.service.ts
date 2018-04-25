import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {AppConstant} from '../../app.constant';

const CELEBRITY_SERVER = AppConstant.API_ENDPOINT + 'celebrity/';

@Injectable()
export class CelebrityService {

  constructor(private http: HttpClient) { }

  getCelebirty(celebrityId: string) {
    return this.http.get(CELEBRITY_SERVER + 'get/' + celebrityId);
  }
}
