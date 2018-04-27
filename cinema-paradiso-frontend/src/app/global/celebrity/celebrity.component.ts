import { Component, OnInit } from '@angular/core';
import { CelebrityService } from './celebrity.service';
import { ActivatedRoute } from '@angular/router';
import { Celebrity } from '../models/celebrity.model';
import {Movie} from '../models/movie.model';
// import {Celebrity} from ''

@Component({
  selector: 'app-celebrity',
  templateUrl: './celebrity.component.html',
  styleUrls: ['./celebrity.component.scss'],
  providers: [CelebrityService]
})
export class CelebrityComponent implements OnInit {

  celebrityId: string;
  celebrity: Celebrity;

  constructor(private celebrityService: CelebrityService, private route: ActivatedRoute) {
    this.celebrityId = route.snapshot.params['id'];
  }

  ngOnInit() {

    this.celebrityService.getCelebirty(this.celebrityId).subscribe(result => {
      this.celebrity = result as Celebrity;

      this.celebrityService.getCelebrityFilmography(this.celebrityId).subscribe(data => {
        this.celebrity.filmography = data as Movie[];
      });

    });

  }

}
