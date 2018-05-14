import {Injectable} from '@angular/core';
import {CanActivate} from '@angular/router';
import {JwtHelperService} from '@auth0/angular-jwt';


@Injectable()
export class AdminCanActive implements CanActivate {
  tokenHelper = new JwtHelperService();

  constructor() {
  }

  canActivate() {
    const decodedToken = this.tokenHelper.decodeToken(localStorage.getItem('token'));
    console.log('decoded role is ', decodedToken['role']);
    return decodedToken['role'] === 'ROLE_ADMIN';
  }

}
