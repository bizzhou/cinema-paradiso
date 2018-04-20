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

  movieNumberOfPages: number;
  peopleNumberOfPages: number;
  tvNumberOfPages: number;

  moviesResults: Movie[];
  celebrityResults: Celebrity[];
  keyword: string;


  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      if (params['keyword']) {
        this.keyword = params['keyword'];
        this.searchService.search(this.keyword, '0', '20').subscribe(results => {
          this.moviesResults = results['movie'] as Movie[];
          this.celebrityResults = results['celebrity'] as Celebrity[];

          this.movieNumberOfPages = results['movie_page'] * 10 - 10;
          this.peoplePage = results['celebrities_page'] * 10 - 10;
          console.log(this.peoplePage);
          this.tvPage = results['tv_page'] * 10 - 10;
        });
      }
    });
  }

  getNextPage() {
    this.searchService.search(this.keyword, this.moviePage.toString(), '20').subscribe(results => {
      this.moviesResults = results['movie'] as Movie[];
      this.celebrityResults = results['celebrity'] as Celebrity[];
      window.scroll(0, 0);
    });
  }


}
