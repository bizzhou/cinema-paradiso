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
  isListView: boolean;

  moviesPlaying: Movie[];
  numOfMoviesPlayingPages: number;
  moviePage = 1;

  constructor(private movieService: MovieService) { }

  ngOnInit() {

    this.getMoviesPlaying();

  }

  getMoviesPlaying(): any {
    this.movieService.getMoviesPlaying('0', '20')
      .subscribe(
        data => {
          this.moviesPlaying = data['movie'] as Movie[];
          this.numOfMoviesPlayingPages = data['movie_page'] * 10;
          console.log(this.moviesPlaying);
          console.log(this.numOfMoviesPlayingPages)
          localStorage.setItem('nowPlaying', JSON.stringify(this.moviesPlaying));
        },
        error => console.log('Failed to fetch movies playing')
      );
  }

  getMoviesPlayingNextPage(currentPage) {
    const actualPage = currentPage - 1;
    this.movieService.getMoviesPlaying(actualPage.toString(), '20')
      .subscribe(results => {
        this.moviesPlaying = results['movie'] as Movie[];
        window.scroll(0, 0);
      });
  }

  setListView(isListView: boolean) {
    this.isListView = isListView;
  }


}
