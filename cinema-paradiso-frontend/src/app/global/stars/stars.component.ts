import {Component, OnInit} from '@angular/core';
import {MovieDetailService} from '../movie-detail/movie-detail.service';
import {MovieService} from '../movie/movie.service';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {ActivatedRoute} from '@angular/router';


@Component({
  selector: 'app-stars',
  templateUrl: './stars.component.html',
  styleUrls: ['./stars.component.scss']
})
export class StarsComponent {
  selected = 0;
  hovered = 0;
  readonly = false;

  constructor(config: NgbRatingConfig) {
    config.max = 5;
    config.readonly = true;
  }

  test() {
    console.log(this.hovered);
    console.log(this.selected);

  }

}
