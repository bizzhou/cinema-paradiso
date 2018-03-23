import { Component, OnInit } from '@angular/core';
import {UserService} from './user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
  providers: [UserService]
})
export class UserComponent implements OnInit {

  constructor(private userService: UserService) { }

  ngOnInit() {
    // this.userService.singup('a', 'binz@gmail.com', 'c').subscribe(ans => {
    //   console.log(ans);
    // });
    this.userService.login('binz@gmail.com', 'c').subscribe(ans => {
      console.log(ans);
    });
  }

  // form slider

}
