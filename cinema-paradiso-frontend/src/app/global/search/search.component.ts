import { Component, OnInit } from '@angular/core';
import {SearchService} from './search.service';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  constructor(private searchService: SearchService) { }

  ngOnInit() {

    this.searchService.currentResult.subscribe(results => {
      console.log('hello world');
      console.log(results);
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
