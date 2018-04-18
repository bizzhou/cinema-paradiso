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

  private searchKeyword = new BehaviorSubject<string>('');
  currentKeyword = this.searchKeyword.asObservable();

  nextResult(result: Object) {
    this.searchResult.next(result);
  }

  nextKeyword(keyword: string) {
    this.searchKeyword.next(keyword);
  }

  constructor(private http: HttpClient) {
  }

  search(keywords: string, pageNo: string, pageSize: string) {
    console.log(keywords, pageNo, pageSize);
    const param = new HttpParams().set('keyword', keywords)
                                  .set('pageNo', pageNo)
                                  .set('pageSize', pageSize);
    return this.http.post(SEARCH_SERVER, param);
  }
}
