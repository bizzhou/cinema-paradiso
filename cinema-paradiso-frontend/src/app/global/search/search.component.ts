import {Component, OnInit} from '@angular/core';
import {SearchService} from './search.service';
import {Movie} from '../models/movie.model';
import {Celebrity} from '../models/celebrity.model';
import {ActivatedRoute, Params} from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  constructor(private searchService: SearchService, private route: ActivatedRoute) {
  }

  currentJustify = 'center';
  moviePage = 1;
  peoplePage = 1;
  tvPage = 1;

  moviesResults: Movie[];
  celebrityResults: Celebrity[];


  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      if (params['keyword']) {

        this.searchService.search(params['keyword'], '0', '20').subscribe(results => {
          this.moviesResults = results['movie'] as Movie[];
          this.celebrityResults = results['celebrity'] as Celebrity[];
        });
      }
    });
  }

  getNextPage() {
    this.searchService.currentKeyword.subscribe(currentKeyword => {
      console.log(this.moviePage);
      console.log(currentKeyword);

      this.searchService.search(currentKeyword, this.moviePage.toString(), '20').subscribe(results => {
        console.log(results['movie']);
        this.moviesResults = results['movie'] as Movie[];
        window.scroll(0, 0);
      });

    });
  }

}
