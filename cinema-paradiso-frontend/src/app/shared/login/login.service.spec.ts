import {TestBed, getTestBed, inject} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {HttpParams} from '@angular/common/http';

import {LoginService} from './login.service';


describe('LoginService', () => {
  let injector;
  let service: LoginService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LoginService]
    });

    injector = getTestBed();
    service = injector.get(LoginService);
    httpMock = injector.get(HttpTestingController);
  });

  describe('#signup', () => {
    it('should return a jwt string', () => {
      service.singup('Bin Zhou', '123@gmail.com', 'test').subscribe(
        resp => {
          // expect(resp.length > 10);
          console.log(resp);
        }
      );
    });
  });

});
