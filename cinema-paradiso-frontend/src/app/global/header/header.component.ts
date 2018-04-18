import {Component, OnInit} from '@angular/core';
import {LoginStatusService} from '../login/login.status.service';
import {Token} from '../models/token.model';
import {LoginService} from '../login/login.service';
import {ToastrService} from 'ngx-toastr';
import {SearchService} from '../search/search.service';
import {Router} from '@angular/router';

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
  keywords: string;

  constructor(private loginStatusService: LoginStatusService,
              private loginService: LoginService,
              private toastrService: ToastrService,
              private searchService: SearchService,
              private router: Router) {
  }

  keywordSubmit() {
    this.searchService.search(this.keywords, '0', '20').subscribe(result => {
      this.searchService.nextResult(result);
      console.log('current result  ', this.searchService.currentResult);
      this.router.navigateByUrl('search');
    });
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
