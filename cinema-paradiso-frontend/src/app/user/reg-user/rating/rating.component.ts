import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Rating} from '../../../global/models/rating.model';
import {Profile} from 'selenium-webdriver/firefox';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./../reg-user.component.scss', './rating.component.scss']
})
export class RatingComponent implements OnInit {

  @Input() profile;

  @Output()
  eventEmitter: EventEmitter<Rating> = new EventEmitter();

  constructor() {
  }

  ngOnInit() {
  }

  deleteRating(rating: Rating) {
    this.eventEmitter.emit(rating);
  }

  range(n: number) {
    return Array(n).fill(0).map((x, i) => i);
  }

}
