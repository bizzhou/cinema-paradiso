import {Component, OnInit} from '@angular/core';
import {LoginStatusService} from '../login/login.status.service';
import {HomeService} from './home.service';
import {Movie} from '../models/movie.model';
import {NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';
import {MovieService} from '../movie-detail/movie.service';
import {Slide} from '../models/slide.model';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

  carousel: Slide[];
  moviesPlaying: Movie[];
  moviesTrending: Movie[];
  moviesComingSoon: Movie[];
  moviesTopBoxOffice: Movie[];
  selectedMovieId: string;

  isMovieExistInWishList: boolean;              // button changes accordingly

  constructor(private loginStatusService: LoginStatusService,
              private homeService: HomeService,
              private movieService: MovieService,
              private toastr: ToastrService,
              config: NgbCarouselConfig) {
    config.interval = 3000;
    config.wrap = true;
    config.keyboard = false;
  }

  ngOnInit() {
    // load carousel
    this.getCarousel();

    // this.getMoviesPlaying();
    // this.getMoviesTrending();
    // this.getMoviesComingSoon();
    // this.getTopBoxOffice();

    if (localStorage.getItem('nowPlaying') !== null) {
      this.moviesPlaying = JSON.parse(localStorage.getItem('nowPlaying')) as Movie[];
    } else {
      this.getMoviesPlaying();
    }

    if (localStorage.getItem('movieTrending') !== null) {
      this.moviesTrending = JSON.parse(localStorage.getItem('movieTrending')) as Movie[];
    } else {
      this.getMoviesTrending();
    }

    if (localStorage.getItem('comingSoon') !== null) {
      this.moviesComingSoon = JSON.parse(localStorage.getItem('comingSoon')) as Movie[];
    } else {
      this.getMoviesComingSoon();
    }

    if (localStorage.getItem('topBoxOffice') !== null) {
      this.moviesTopBoxOffice = JSON.parse(localStorage.getItem('topBoxOffice')) as Movie[];
    } else {
      this.getTopBoxOffice();
    }

    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
    }

  }

  getCarousel(): any {
    this.homeService.getCarousel()
      .subscribe(
        data => {
          // assign movies to carousel
          this.carousel = data as Slide[];
          console.log(this.carousel);
        },
        error => console.log('Failed to fetch carousel data')
      );
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

  getMoviesTrending(): any {
    this.movieService.getMoviesTrending()
      .subscribe(
        data => {
          this.moviesTrending = data as Movie[];
          console.log(this.moviesTrending);
          localStorage.setItem('movieTrending', JSON.stringify(this.moviesTrending));
        },
        error => console.log('Failed to fetch movies trending')
      );
  }

  getMoviesComingSoon(): any {
    this.movieService.getMoviesComingSoon()
      .subscribe(
        data => {
          this.moviesComingSoon = data as Movie[];
          console.log(this.moviesComingSoon);
          localStorage.setItem('comingSoon', JSON.stringify(this.moviesComingSoon));
        },
        error => console.log('Failed to fetch movies coming soon')
      );
  }

  getTopBoxOffice(): any {
    this.movieService.getTopBoxOffice()
      .subscribe(
        data => {
          this.moviesTopBoxOffice = data as Movie[];
          console.log(this.moviesTopBoxOffice);
          localStorage.setItem('topBoxOffice', JSON.stringify(this.moviesTopBoxOffice));
        },
        error => console.log('Failed to fetch top box office')
      );
  }

  addToWishList(imdbId: string) {
    this.movieService.addToWishList(imdbId)
      .subscribe(
        data => {
          console.log(data);
        },
        // error => console.log('Failed to add to wish list')
        error => this.toastr.error('Please Login!')
      );
  }

  removeFromWishList(imdbId: string) {
    this.movieService.removeFromWishList(imdbId);
  }

  checkMoviesInWishList(movies: Movie[]) {
    // movies.forEach(function(movie, index, movieArray) {
    //   if (this.isMovieInWishList(movie.imdbId)) {
    //     movie.isInWishlist = true;
    //   } else {
    //     movie.isInWishlist = false;
    //   }
    // });

    // movies.forEach(function(part, index, theArray) {
    //   theArray[index].isInWishlist = false;
    // });
  }

  /**
   *
   * @param {string} imdbId
   * true if in the list, false otherwise
   */
  isMovieInWishList(imdbId: string) {
    this.movieService.isMovieInWishList(imdbId)
      .map((res: Response) => {
        if (res) {
          return res.status === 200;
        }
      });
  }

}
