import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./../reg-user.component.scss', './review.component.scss']
})
export class ReviewComponent implements OnInit {

  @Input() profile;

  constructor() { }

  ngOnInit() {
  }

}
