import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Review} from '../../models/review.model';

@Component({
  selector: 'app-md-review',
  templateUrl: './md-review.component.html',
  styleUrls: ['../movie-detail.component.scss', './md-review.component.scss']
})
export class MdReviewComponent implements OnInit, OnChanges {

  @Input()
  userReviews: Review[];

  @Input()
  criticReviews: Review[];

  constructor() {
  }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.userReviews);
  }

}
