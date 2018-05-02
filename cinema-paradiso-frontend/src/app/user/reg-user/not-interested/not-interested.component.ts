import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-not-interested',
  templateUrl: './not-interested.component.html',
  styleUrls: ['./../reg-user.component.scss', './not-interested.component.scss']
})
export class NotInterestedComponent implements OnInit {

  @Input() profile;

  constructor() { }

  ngOnInit() {
  }



}
