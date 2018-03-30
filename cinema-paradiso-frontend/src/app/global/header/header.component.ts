import { Component, OnInit } from '@angular/core';
import { LoginStatusService } from '../login/login.status.service';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {


  status: boolean;

  constructor(private loginStatusService: LoginStatusService) { }

  ngOnInit() {
    $( document ).ready(function() {
      $('.trigger').click(function() {
        $('.modal-wrapper').toggleClass('open');
        $('.page-wrapper').toggleClass('blur');
        return false;
      });
    });

    // listen to the loggin state
    this.loginStatusService.currentStatus.subscribe(state => {
      console.log(state);
      this.status = state;

      if (this.status) {
        console.log('Profile Image should be shown');


        console.log('Header has received the data');
      } else {

      }

    });



  }



}
