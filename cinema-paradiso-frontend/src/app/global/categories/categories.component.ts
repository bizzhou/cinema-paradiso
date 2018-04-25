import { Component, OnInit } from '@angular/core';
import {Movie} from '../models/movie.model';
import {MovieService} from '../movie-detail/movie.service';


export enum Sidebar {
  MoviesPlaying = 'moviesPlaying',
  moviesTopBoxOffice = 'moviesTopBoxOffice',

}
@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  sidebar = Sidebar;

  page = 1;
  isListView: boolean;

  currentTab = Sidebar.MoviesPlaying;
  currentMovies: Movie[];
  numOfCurrentMovies: number;

  moviesPlaying: Movie[];
  numOfMoviesPlayingPages: number;
  moviesTopBoxOffice: Movie[];
  numOfMoviesTopBoxOffice: number;
  moviePage = 1;

  constructor(private movieService: MovieService) { }

  ngOnInit() {

    this.getMoviesPlaying();

  }

  setCurrentPage() {

  }

  getMoviesPlaying(): any {
    if (this.moviesPlaying != null) {
      this.currentMovies = this.moviesPlaying;
    } else {
      this.movieService.getMoviesPlaying('0', '20')
        .subscribe(
          data => {
            this.moviesPlaying = data['movie'] as Movie[];
            this.numOfMoviesPlayingPages = data['movie_page'] * 10;

            // for current tab
            this.currentMovies = this.moviesPlaying;
            this.numOfCurrentMovies = this.numOfMoviesPlayingPages;

            console.log(this.moviesPlaying);
            console.log(this.numOfMoviesPlayingPages);
            localStorage.setItem('nowPlaying', JSON.stringify(this.moviesPlaying));
          },
          error => console.log('Failed to fetch movies playing')
        );
    }

  }

  getMoviesPlayingNextPage(currentPage) {
    const actualPage = currentPage - 1;
    this.movieService.getMoviesPlaying(actualPage.toString(), '20')
      .subscribe(results => {
        this.moviesPlaying = results['movie'] as Movie[];
        window.scroll(0, 0);
      });
  }

  getMoviesTopBoxOffice(): any {
    if (this.moviesTopBoxOffice != null) {
      this.currentMovies = this.moviesTopBoxOffice;
    } else {
      this.movieService.getTopBoxOffice('0', '20')
        .subscribe(
          data => {
            this.moviesTopBoxOffice = data['movie'] as Movie[];
            this.numOfMoviesTopBoxOffice = data['movie_page'] * 10;

            this.currentMovies = this.moviesTopBoxOffice;
            this.numOfCurrentMovies = this.numOfMoviesTopBoxOffice;

            console.log(this.moviesTopBoxOffice);
            console.log(this.numOfMoviesTopBoxOffice);
            localStorage.setItem('topBoxOffice', JSON.stringify(this.moviesTopBoxOffice));
          },
          error => console.log('Failed to fetch movies with top box office')
        );
    }
  }

  setListView(isListView: boolean) {
    this.isListView = isListView;
  }


}
