import { Component, OnInit } from '@angular/core';
import {Movie} from '../models/movie.model';
import {MovieService} from '../movie-detail/movie.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  page = 1;
  moviesPlaying: Movie[];
  isListView: boolean;

  constructor(private movieService: MovieService) { }

  ngOnInit() {

    this.getMoviesPlaying();
  }

  getMoviesPlaying(): any {
    this.movieService.getMoviesPlaying()
      .subscribe(
        data => {
          this.moviesPlaying = data as Movie[];
          console.log(this.moviesPlaying);
          localStorage.setItem('nowPlaying', JSON.stringify(this.moviesPlaying));
        },
        error => console.log('Failed to fetch movies playing')
      );
  }

  setListView(isListView: boolean) {
    this.isListView = isListView;
  }


}
