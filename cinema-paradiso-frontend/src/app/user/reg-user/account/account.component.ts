import {Component, Input, OnInit} from '@angular/core';
import {NgForm} from '@angular/forms';
import {RegUserService} from '../reg-user.service';
import {ToastrService} from 'ngx-toastr';
import {LoginService} from '../../../global/login/login.service';

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

  constructor(private userService: RegUserService,
              private loginService: LoginService,
              private toastr: ToastrService) { }

  ngOnInit() {}

  changePassword(form: NgForm) {

    this.userService.changePassword(this.oldPassword, this.newPassword).subscribe(result => {
      if (result === true) {
        form.resetForm();
        this.changePasswordSuccess = true;
        this.toastr.success('Success');
        this.loginService.logout();
      } else {
        this.changePasswordFailure = true;
        this.toastr.error('Failed to change the password, make sure you passwords are correct');
        form.resetForm();
      }
    });
  }

  deleteUser() {
    const user = JSON.parse(localStorage.getItem('credential'));
    this.userService.deleteUser(parseInt(user['id'])).subscribe(result => {
      if (result === true) {
        this.toastr.success('Successfully Deleted Your Account');
        this.loginService.logout();
      }
    });
  }
}
