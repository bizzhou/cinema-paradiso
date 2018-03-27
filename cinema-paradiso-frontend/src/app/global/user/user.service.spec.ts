import {TestBed, getTestBed, inject} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {HttpParams} from '@angular/common/http';

import {UserService} from './user.service';


describe('UserService', () => {
  let injector;
  let service: UserService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });

    injector = getTestBed();
    service = injector.get(UserService);
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
