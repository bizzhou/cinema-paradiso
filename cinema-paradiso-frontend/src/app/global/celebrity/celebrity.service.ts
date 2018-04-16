import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const CELEBRITY_SERVER = 'http://localhost:8080/celebrity/';

@Injectable()
export class CelebrityService {

  constructor(private http: HttpClient) { }

  getCelebirty(celebrityId: string) {
    return this.http.get(CELEBRITY_SERVER + 'get/' + celebrityId);
  }
}
