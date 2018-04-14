import { Component, OnInit } from '@angular/core';
import {SearchService} from './search.service';

class Celebrity {
  biography: string;
  birthCity: string;
  birthCountry: string;
  birthDate: string;
  celebrityId: string;
  filmgraphy: string[];
  photos: string[];
  profileImage: string;
  name: string;
}

class Movie {
  awards: string[];
  boxOffice: number;
  casts: Celebrity[];
  director: Celebrity;
  genres: string[];
  imdbId: string;
  language: string;
  movieInfo: string;
  numberOfRatings: number;
  photos: string[];
  plot: string;
  poster: string;
  production: string;
  rating: string;
  releaseDate: string;
  reivew: string[];
  runtime: number;
  title: string;
  trailers: string[];
  year: string;
}

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {
  constructor(private searchService: SearchService) { }

  moviesResults: Movie[];


  ngOnInit() {

    this.searchService.currentResult.subscribe(results => {
      this.moviesResults = results as Movie[];
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
