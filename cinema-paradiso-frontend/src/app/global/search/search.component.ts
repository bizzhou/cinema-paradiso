import {Component, OnInit} from '@angular/core';
import {SearchService} from './search.service';
import {Movie} from '../models/movie.model';
import {Celebrity} from '../models/celebrity.model';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  constructor(private searchService: SearchService) {
  }

  moviesResults: Movie[];
  celebrityResults: Celebrity[];

  ngOnInit() {
    this.searchService.currentResult.subscribe(results => {
      this.moviesResults = results['movie'] as Movie[];
      this.celebrityResults = results['celebrity'] as Celebrity[];

      console.log('celebrity results ', this.celebrityResults);
      console.log('movie results ', this.moviesResults);

    });

    $('.show_movies').click(function (e) {
      e.preventDefault();
      $('.movie_results').show();
      $('.people_results').hide();
      $('.tv_results').hide();
    });

    $('.show_tv').click(function (e) {
      e.preventDefault();
      $('.movie_results').hide();
      $('.people_results').hide();
      $('.tv_results').show();
    });

    $('.show_people').click(function (e) {
      e.preventDefault();
      $('.movie_results').hide();
      $('.people_results').show();
      $('.tv_results').hide();
    });
  }

}
