import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-reg-user-portfolio',
  templateUrl: './reg-user.component.html',
  styleUrls: ['../../movie-detail/md-review/md-review.component.scss', '../critic/critic.component.scss', './reg-user.component.scss']
})
export class RegUserPortfolioComponent implements OnInit {

  selectedUsername: string;

  constructor(private route: ActivatedRoute) {
    this.selectedUsername = route.snapshot.params['username'];
  }

  ngOnInit() {
    window.scroll(0, 0);
  }

}
