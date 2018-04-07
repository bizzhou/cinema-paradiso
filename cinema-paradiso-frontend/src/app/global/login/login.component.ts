import {Component, OnInit} from '@angular/core';
import * as $ from 'jquery';
import {User} from '../../user/user/user.model';
import {LoginService} from './login.service';
import {LoginStatusService} from './login.status.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {
  constructor(private loginService: LoginService, private loginStatusService: LoginStatusService) {
  }

  status: boolean;
  user = new User();
  isSamePassword: boolean;
  email: string;
  password: string;

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
  }

  signup() {

    // if (this.isSamePassword && this.user !== undefined) {
    //   this.loginService.singup(this.user).subscribe(data => {
    //     console.log(data);
    //   });
    // }

  }

  login() {

    console.log('loggin in');

    console.log(this.email);
    console.log(this.password);
    if (this.email !== null && this.password !== null) {
      this.loginService.login(this.email, this.password).subscribe(data => {
        console.log(data);
        localStorage.setItem('credential', JSON.stringify(data));
        // Set user loggedIn status to global. So header can subscribe to the event.
        this.loginStatusService.changeStatus(true);
      });

    }

  }


}
