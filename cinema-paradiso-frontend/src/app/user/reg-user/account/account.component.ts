import {Component, Input, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {RegUserService} from '../reg-user.service';
import {ToastrService} from 'ngx-toastr';
import {LoginStatusService} from '../../../global/login/login.status.service';
import {LoginService} from '../../../global/login/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./../reg-user.component.scss', './account.component.scss']
})
export class AccountComponent implements OnInit {

  @Input() profile;
  oldPassword: string;
  newPassword: string;
  changePasswordSuccess: boolean;
  changePasswordFailure: boolean;

  constructor(private regUserService: RegUserService,
              private loginService: LoginService,
              private toastr: ToastrService,
              private router: Router) { }

  ngOnInit() {}

  changePassword(form: NgForm) {
    this.regUserService.changePassword(this.oldPassword, this.newPassword).subscribe(result => {
      if (result['success'] === true) {
        form.resetForm();
        this.changePasswordSuccess = true;
        this.toastr.success('Success');
        this.loginService.logout();
        this.router.navigateByUrl('/home');
      } else {
        this.changePasswordFailure = true;
        console.log('fail to cahnge the password');
        this.toastr.error('Failed to change the password, make sure you passwords are correct');
        form.resetForm();
      }
    });
  }

}
