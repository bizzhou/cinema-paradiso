import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

const SEARCH_SERVER = 'http://localhost:8080/search/';

@Injectable()
export class SearchService {
  private searchResult = new BehaviorSubject<Object>(null);
  currentResult = this.searchResult.asObservable();

  nextResult(result: Object) {
    this.searchResult.next(result);
  }

  constructor(private http: HttpClient) {
  }

  search(keywords: string) {
    console.log(keywords);
    const param = new HttpParams().set('keyword', keywords);
    return this.http.post(SEARCH_SERVER, param);
  }
}
