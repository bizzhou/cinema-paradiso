import {Component, OnInit} from '@angular/core';
import {LoginStatusService} from '../login/login.status.service';
import {Location} from '@angular/common';
import {Token} from '../login/token.model';
import {LoginService} from '../login/login.service';
import {connectableObservableDescriptor} from 'rxjs/observable/ConnectableObservable';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
  providers: [LoginService]
})
export class HeaderComponent implements OnInit {
  status: boolean;
  user: Token;
  is_admin: boolean;

  constructor(private loginStatusService: LoginStatusService, private loginService: LoginService, private toastrService: ToastrService) {
  }

  ngOnInit() {
    $(document).ready(function () {
      $('.trigger').click(function () {
        $('.modal-wrapper').toggleClass('open');
        $('.page-wrapper').toggleClass('blur');
        return false;
      });
    });

    // listen to the loggin state
    this.loginStatusService.currentStatus.subscribe(state => {
      this.status = state;
      console.log('current login state ', this.status);


      if (this.status) {
        $('.modal-wrapper').hide();
        $('.page-wrapper').hide();
        this.user = this.loginStatusService.getTokenDetails() as Token;

        if (this.user.role === 'ROLE_USER' || this.user.role === 'ROLE_CRITIC') {
          this.is_admin = false;
          console.log(this.is_admin);
        } else {
          this.is_admin = true;
          console.log(this.is_admin);
        }
      }
    }, error => {
      console.log('error');
      this.toastrService.error('Logged in failed');
    });
  }

  logout() {
    this.loginService.logout();
  }

}
