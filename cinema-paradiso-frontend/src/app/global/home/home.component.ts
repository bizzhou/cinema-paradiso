import {Component, OnInit, ViewChild} from '@angular/core';
import * as $ from 'jquery';
import {LoginStatusService} from '../login/login.status.service';
import {HomeService} from './home.service';
import {Movie} from '../models/movie.model';
import {Celebrity} from '../models/celebrity.model';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';
import {CarouselSlide} from '../models/carouselSlide.model';
import {MovieService} from '../movie-detail/movie.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {Slide} from '../models/slide.model';
import {ToastrService} from 'ngx-toastr';
import {ListMovieStatus} from '../models/ListMovieStatus.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

  loginStatus = false;
  carousel: Slide[];
  moviesPlaying: Movie[];
  moviesTrending: Movie[];
  moviesComingSoon: Movie[];
  moviesTopBoxOffice: Movie[];
  selectedMovieId: string;
  isMovieExistInWishList: boolean;              // button changes accordingly
  listMovieStatusEnum = ListMovieStatus;

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
    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
      this.loginStatus = true;
    }

    // TODO: if in local storage, check here, otherwise check in backend
    if (localStorage.getItem('slides') !== null) {
      this.carousel = JSON.parse(localStorage.getItem('slides')) as Slide[];
    } else {
      if (this.loginStatus) {
        console.log('Fetching custom carousel');
        this.getCustomCarousel();
      } else {
        console.log('Fetching normal carousel');
        this.getCarousel();
      }
    }

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

  }

  getCarousel(): any {

    this.homeService.getCarousel()
      .subscribe(
        data => {
          this.carousel = data as Slide[];
          console.log(this.carousel);
          // localStorage.setItem('slides', JSON.stringify(this.carousel));
        },
        error => console.log('Failed to fetch carousel data')
      );
  }

  getCustomCarousel(): any {

    this.homeService.getCustomCarousel()
      .subscribe(
        data => {
          this.carousel = data as Slide[];
          console.log(this.carousel);
          // localStorage.setItem('slides', JSON.stringify(this.carousel));
        },
        error => console.log('Failed to fetch custome carousel data')
      );
  }

  getMoviesPlaying(): any {
    this.movieService.getMoviesPlaying('0', '6')
      .subscribe(
        data => {
          console.log(this.moviesPlaying);
          this.moviesPlaying = data['movie'] as Movie[];
          localStorage.setItem('nowPlaying', JSON.stringify(this.moviesPlaying));
        },
        error => console.log('Failed to fetch movies playing')
      );
  }

  getMoviesTrending(): any {
    this.movieService.getMoviesTrending('0', '6')
      .subscribe(
        data => {
          this.moviesTrending = data['movie'] as Movie[];
          console.log(this.moviesTrending);
          localStorage.setItem('movieTrending', JSON.stringify(this.moviesTrending));
        },
        error => console.log('Failed to fetch movies trending')
      );
  }

  getMoviesComingSoon(): any {
    this.movieService.getMoviesComingSoon('0', '6')
      .subscribe(
        data => {
          this.moviesComingSoon = data['movie'] as Movie[];
          console.log(this.moviesComingSoon);
          localStorage.setItem('comingSoon', JSON.stringify(this.moviesComingSoon));
        },
        error => console.log('Failed to fetch movies coming soon')
      );
  }

  getTopBoxOffice(): any {
    this.movieService.getTopBoxOffice('0', '6')
      .subscribe(
        data => {
          this.moviesTopBoxOffice = data['movie'] as Movie[];
          console.log(this.moviesTopBoxOffice);
          localStorage.setItem('topBoxOffice', JSON.stringify(this.moviesTopBoxOffice));
        },
        error => console.log('Failed to fetch top box office')
      );
  }

  addToWishList(movie: Movie) {
    this.movieService.addToWishList(movie.imdbId)
      .subscribe(
        data => {
          movie.listMovieStatus = this.listMovieStatusEnum.WISHLIST;
          console.log('Added movie ' + movie.imdbId + ' to wish list');
        },
        error => {
          this.toastr.error('Please Login!');
          $('.modal-wrapper').toggleClass('open');
        }
      );
  }

  removeFromWishList(movie: Movie) {
    this.movieService.removeFromWishList(movie.imdbId)
      .subscribe(
        data => {
          movie.listMovieStatus = this.listMovieStatusEnum.NONE;
          console.log('Removed movie ' + movie.imdbId + ' from wish list');
        },
        error => {
          this.toastr.error('Please Login!');
          $('.modal-wrapper').toggleClass('open');
        }
      );
  }

  addToNotInterestedList(movie: Movie) {
    this.movieService.addToNotInterestedList(movie.imdbId)
      .subscribe(
        data => {
          movie.listMovieStatus = this.listMovieStatusEnum.NOT_INTERESTED_LIST;
          console.log('Added movie ' + movie.imdbId + ' to not interested list');
        },
        error => {
          this.toastr.error('Please Login!');
          $('.modal-wrapper').toggleClass('open');
        }
      );
  }

  removeFromNotInterestedList(movie: Movie) {
    this.movieService.removeFromNotInterestedList(movie.imdbId)
      .subscribe(
        data => {
          movie.listMovieStatus = this.listMovieStatusEnum.NONE;
          console.log('Removed movie ' + movie.imdbId + ' from not interested list');
        },
        error => {
          this.toastr.error('Please Login!');
          $('.modal-wrapper').toggleClass('open');
        }
      );
  }




}
