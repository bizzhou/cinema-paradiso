import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {AppConstant} from '../../app.constant';

const SEARCH_SERVER = AppConstant.API_ENDPOINT + 'search/';

@Injectable()
export class SearchService {

  constructor(private http: HttpClient) {
  }

  search(keywords: string, pageNo: string, pageSize: string) {
    console.log(keywords, pageNo, pageSize);
    const param = new HttpParams().set('keyword', keywords)
                                  .set('pageNo', pageNo)
                                  .set('pageSize', pageSize);
    return this.http.post(SEARCH_SERVER, param);
  }


  searchDetails(endpoint: string, keywords: string, pageNo: string, pageSize: string) {
    console.log(keywords, pageNo, pageSize);
    const param = new HttpParams().set('keyword', keywords)
                                  .set('pageNo', pageNo)
                                  .set('pageSize', pageSize);
    return this.http.post(SEARCH_SERVER + endpoint, param);
  }

}
