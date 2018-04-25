import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-user-review',
  templateUrl: './user-review.component.html',
  styleUrls: ['./../reg-user.component.scss', './user-review.component.scss']
})
export class UserReviewComponent implements OnInit {

  @Input() reviews;

  constructor() { }

  ngOnInit() {
    console.log(this.reviews);
  }

}
