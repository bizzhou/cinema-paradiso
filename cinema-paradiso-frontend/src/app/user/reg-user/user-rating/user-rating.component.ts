import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Rating} from '../../../global/models/rating.model';

@Component({
  selector: 'app-user-rating',
  templateUrl: './user-rating.component.html',
  styleUrls: ['../../../global/movie-detail/md-review/md-review.component.scss',
    '../../../global/public-portfolio/critic/critic.component.scss',
    '../../../global/public-portfolio/reg-user/reg-user.component.scss',
    './../reg-user.component.scss',
    './user-rating.component.scss']
})
export class UserRatingComponent implements OnInit {

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
