import { Component, OnInit } from '@angular/core';
import {Movie} from '../models/movie.model';
import {MovieService} from '../movie-detail/movie.service';


export enum Sidebar {
  moviesPlaying = 'now playing',
  moviesTopBoxOffice = 'top box office',
  moviesComingSoon = 'coming soon',
  moviesTrending = 'trending now'
}
@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  public sidebarEnum = Sidebar;

  page = 1;
  moviePage = 1;
  isListView: boolean;

  currentTab = Sidebar.moviesPlaying;
  currentMovies: Movie[];
  numOfCurrentMovies: number;

  moviesPlaying: Movie[];
  numOfMoviesPlayingPages: number;

  moviesTopBoxOffice: Movie[];
  numOfMoviesTopBoxOffice: number;

  moviesComingSoon: Movie[];
  numOfMoviesComingSoon: number;

  moviesTrending: Movie[];
  numOfMoviesTrending: number;

  constructor(private movieService: MovieService) { }

  ngOnInit() {

    this.getMoviesPlaying();

  }

  // click on tab, get corresponding movies and page header
  setCurrentTab(tab: string) {

    console.log('fetching' + tab);

    switch (tab) {
      case this.sidebarEnum.moviesPlaying: {
        this.getMoviesPlaying();
        this.currentTab = this.sidebarEnum.moviesPlaying;
        break;
      }
      case this.sidebarEnum.moviesTopBoxOffice: {
        this.getMoviesTopBoxOffice();
        this.currentTab = this.sidebarEnum.moviesTopBoxOffice;
        break;
      }
      case this.sidebarEnum.moviesComingSoon: {
        this.getMoviesComingSoon();
        this.currentTab = this.sidebarEnum.moviesComingSoon;
        break;
      }
      case this.sidebarEnum.moviesTrending: {
        this.getMoviesTrending();
        this.currentTab = this.sidebarEnum.moviesTrending;
        break;
      }
    }

  }

  // TODO: add to local storage
  // get next page according to current tab
  getMoviesNextPage(currentPage) {
    const actualPage = currentPage - 1;

    console.log('sending request for: ' + this.currentTab + ' page ' + actualPage);
    switch (this.currentTab) {
      case this.sidebarEnum.moviesPlaying: {
        this.movieService.getMoviesPlaying(actualPage.toString(), '20')
          .subscribe(results => {
            this.moviesPlaying = results['movie'] as Movie[];
            this.currentMovies = this.moviesPlaying;
          });
        break;
      }
      case this.sidebarEnum.moviesTopBoxOffice: {
        this.movieService.getTopBoxOffice(actualPage.toString(), '20')
          .subscribe(results => {
            this.moviesTopBoxOffice = results['movie'] as Movie[];
            this.currentMovies = this.moviesTopBoxOffice;
          });
        break;
      }
      case this.sidebarEnum.moviesComingSoon: {
        this.movieService.getMoviesComingSoon(actualPage.toString(), '20')
          .subscribe(results => {
              this.moviesComingSoon = results['movie'] as Movie[];
              this.currentMovies = this.moviesComingSoon;

            });
        break;
      }
      case this.sidebarEnum.moviesTrending: {
        this.movieService.getMoviesTrending(actualPage.toString(), '20')
          .subscribe(results => {
              this.moviesTrending = results['movie'] as Movie[];
              this.currentMovies = this.moviesTrending;
            }
          );
      }

    }

    window.scroll(0, 0);

  }

  getMoviesPlaying(): any {
    if (this.moviesPlaying == null) {
      this.movieService.getMoviesPlaying('0', '20')
        .subscribe(
          data => {
            this.moviesPlaying = data['movie'] as Movie[];
            this.numOfMoviesPlayingPages = data['movie_page'] * 10;

            // set for current tab
            this.currentMovies = this.moviesPlaying;
            this.numOfCurrentMovies = this.numOfMoviesPlayingPages;

            console.log(this.moviesPlaying);
            console.log(this.numOfMoviesPlayingPages);
            localStorage.setItem('nowPlaying', JSON.stringify(this.moviesPlaying));
          },
          error => console.log('Failed to fetch movies playing')
        );
    } else {
      // set for current tab
      this.currentMovies = this.moviesPlaying;
      this.numOfCurrentMovies = this.numOfMoviesPlayingPages;
    }


  }

  getMoviesTopBoxOffice(): any {
    if (this.moviesTopBoxOffice == null) {
      this.movieService.getTopBoxOffice('0', '20')
        .subscribe(
          data => {
            this.moviesTopBoxOffice = data['movie'] as Movie[];
            this.numOfMoviesTopBoxOffice = data['movie_page'] * 10;

            this.currentMovies = this.moviesTopBoxOffice;
            this.numOfCurrentMovies = this.numOfMoviesPlayingPages;

            console.log(this.moviesTopBoxOffice);
            console.log(this.numOfMoviesTopBoxOffice);
            localStorage.setItem('topBoxOffice', JSON.stringify(this.moviesTopBoxOffice));
          },
          error => console.log('Failed to fetch movies with top box office')
        );
    } else {
      this.currentMovies = this.moviesTopBoxOffice;
      this.numOfCurrentMovies = this.numOfMoviesPlayingPages;
    }

  }

  getMoviesComingSoon(): any {
    if (this.moviesComingSoon == null) {
      this.movieService.getMoviesComingSoon('0', '20')
        .subscribe(
          data => {
            this.moviesComingSoon = data['movie'] as Movie[];
            this.numOfMoviesComingSoon = data['movie_page'] * 10;

            this.currentMovies = this.moviesComingSoon;
            this.numOfCurrentMovies = this.numOfMoviesComingSoon;

            console.log(this.moviesComingSoon);
            localStorage.setItem('comingSoon', JSON.stringify(this.moviesComingSoon));
          },
          error => console.log('Failed to fetch movies coming soon')
        );
    } else {
      this.currentMovies = this.moviesComingSoon;
      this.numOfCurrentMovies = this.numOfMoviesComingSoon;
    }

  }

  getMoviesTrending(): any {
    if (this.moviesTrending == null) {
      this.movieService.getMoviesTrending('0', '20')
        .subscribe(
          data => {
            this.moviesTrending = data['movie'] as Movie[];
            this.numOfMoviesTrending = data['movie_page'] * 10;

            this.currentMovies = this.moviesTrending;
            this.numOfCurrentMovies = this.numOfMoviesTrending;

            console.log(this.moviesTrending);
            localStorage.setItem('movieTrending', JSON.stringify(this.moviesTrending));
          },
          error => console.log('Failed to fetch movies trending')
        );
    } else {
      this.currentMovies = this.moviesTrending;
      this.numOfCurrentMovies = this.numOfMoviesTrending;
    }

  }

  setListView(isListView: boolean) {
    this.isListView = isListView;
  }


}
