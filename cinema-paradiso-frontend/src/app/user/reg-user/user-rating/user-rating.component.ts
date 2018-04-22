import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-rating',
  templateUrl: './user-rating.component.html',
  styleUrls: ['./../reg-user.component.scss', './user-rating.component.scss']
})
export class UserRatingComponent implements OnInit {

  @Input() profile;

  constructor() { }

  ngOnInit() {
  }

}
