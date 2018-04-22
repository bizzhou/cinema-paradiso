import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./../reg-user.component.scss', './rating.component.scss']
})
export class RatingComponent implements OnInit {

  @Input() profile;

  constructor() { }

  ngOnInit() {
  }

}
