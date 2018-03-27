import {Component, OnInit} from '@angular/core';
import * as $ from 'jquery';
import {User} from './user.model';
import {LoginService} from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {

  constructor(private loginService: LoginService) {
  }

  private user = new User();
  private isSamePassword: boolean;
  private email: string;
  private password: string;

  ngOnInit() {

    const signupButton = document.getElementById('signup-button'),
      loginButton = document.getElementById('login-button'),
      userForms = document.getElementById('user_options-forms');

    /**
     * Add event listener to the "Sign Up" button
     */
    signupButton.addEventListener('click', () => {
      userForms.classList.remove('bounceRight');
      userForms.classList.add('bounceLeft');
    }, false);


    /**
     * Add event listener to the "Login" button
     */
    loginButton.addEventListener('click', () => {
      userForms.classList.remove('bounceLeft');
      userForms.classList.add('bounceRight');
    }, false);

    const parentCtx = this;

    $('.second_password').on('keyup', function () {

      if ($('.first_password').val() === $('.second_password').val()) {
        console.log('same password');
        parentCtx.isSamePassword = true;
      } else {
        console.log('different password');
        parentCtx.isSamePassword = false;
      }
    });

  }

  signup() {

    if (this.isSamePassword && this.user !== undefined) {
      this.loginService.singup(this.user).subscribe(data => {
        console.log(data);
      });
    }

  }

  login() {

    if (this.email !== null && this.password !== null) {
      this.loginService.login(this.email, this.password).subscribe(data => {
        console.log(data);
      });
    }


  }


}
