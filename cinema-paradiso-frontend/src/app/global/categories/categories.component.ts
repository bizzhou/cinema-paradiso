import { Component, OnInit } from '@angular/core';
import {Movie} from '../models/movie.model';
import {MovieService} from '../movie-detail/movie.service';
import {CategoriesService} from './categories.service';
import {Sidebar} from '../models/sidebar.model';


@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss'],
})
export class CategoriesComponent implements OnInit {

  public sidebarEnum = Sidebar;
  selectedTab: any;

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

  moviesTopRated: Movie[];
  numofMoviesTopRated: number;

  constructor(private movieService: MovieService,
              private categoriesService: CategoriesService) { }

  ngOnInit() {

    this.setCurrentTab(this.categoriesService.getCurrentTab().getValue());

  }

  // click on tab, get corresponding movies and page header
  setCurrentTab(tab: string) {

    console.log('fetching ' + tab);

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
      case this.sidebarEnum.moviesTopRated: {
        this.getMoviesTopRated();
        this.currentTab = this.sidebarEnum.moviesTopRated;
        break;
      }
      default: {
        this.getMoviesPlaying();
        this.currentTab = this.sidebarEnum.moviesPlaying;
        break;
      }
    }
    this.moviePage = 1;
    window.scroll(0, 0);

  }

  // TODO: add to local storage， with page number
  // get next page according to current tab
  getMoviesNextPage(currentPage) {
    const actualPage = currentPage - 1;

    console.log('sending request for: ' + this.currentTab + ' page ' + actualPage);
    switch (this.currentTab) {
      case this.sidebarEnum.moviesPlaying: {
        // this.movieService.getMoviesPlaying(actualPage.toString(), '20')
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
        break;
      }
      case this.sidebarEnum.moviesTopRated: {
        this.movieService.getMoviesTopRated(actualPage.toString(), '20')
          .subscribe(results => {
              this.moviesTopRated = results['movie'] as Movie[];
              this.currentMovies = this.moviesTopRated;
            }
          );
        break;
      }
    }

    window.scroll(0, 0);

  }

  getMoviesPlaying(): any {
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


  }

  getMoviesTopBoxOffice(): any {
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


  }

  getMoviesComingSoon(): any {
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

  }

  getMoviesTopRated(): any {
    this.movieService.getMoviesTopRated('0', '20')
      .subscribe(
        data => {
          this.moviesTopRated = data['movie'] as Movie[];
          this.numofMoviesTopRated = data['movie_page'] * 10;

          this.currentMovies = this.moviesTopRated;
          this.numOfCurrentMovies = this.numofMoviesTopRated;

          console.log(this.moviesTopRated);
          localStorage.setItem('topRated', JSON.stringify(this.moviesTopRated));
        },
        error => console.log('Failed to fetch movies top rated')
      );
  }

  getMoviesTrending(): any {
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
  }

  setListView(isListView: boolean) {
    this.isListView = isListView;
  }

  selectTab(tab) {
    this.categoriesService.setSelectedTab(tab);
  }

  isTabActive(tab) {
    return this.categoriesService.getTabActive(tab);
  }

  // styling tab
  // selectTab(tab) {
  //   this.selectedTab = tab;
  // }
  // isTabActive(tab) {
  //   return this.selectedTab === tab;
  // }

}
