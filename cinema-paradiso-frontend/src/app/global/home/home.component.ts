import {Component, OnInit, ViewChild} from '@angular/core';
import * as $ from 'jquery';
import {LoginStatusService} from '../login/login.status.service';
import {HomeService} from './home.service';
import {Movie} from '../models/movie.model';
import {Celebrity} from '../models/celebrity.model';
import {Carousel} from '../models/carousel.model';
import {log} from 'util';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';
import {CarouselSlide} from '../models/carouselSlide.model';
import {MovieService} from '../movie/movie.service';
import {Observable} from 'rxjs/Observable';
// import {MovieService} from '../movie/movie.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {

  currentRate = 3.14;

  // TODO:should create Slide[]
  carousel: Movie[];
  moviesPlaying: Movie[];
  selectedMovieId: string;

  isMovieExistInWishList: boolean;              // button changes accordingly

  constructor(private loginStatusService: LoginStatusService,
              private homeService: HomeService,
              private movieService: MovieService,
              config: NgbCarouselConfig) {
    config.interval = 3000;
    config.wrap = true;
    config.keyboard = false;
  }

  ngOnInit() {
    // load carousel
    this.getCarousel();

    // load movies playing
    this.getMoviesPlaying();

    // this.checkMoviesInWishList(this.carousel);

    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
    }

  }

  getCarousel(): any {
    this.homeService.getCarousel()
      .subscribe(
        data => {
          // assign movies to carousel
          this.carousel = data as Movie[];
          this.carousel.forEach(function(part, index, theArray) {
              if (this.isMovieInWishList(part.imdbId)) {
                part.isInWishlist = false;
              } else {
                part.isInWishlist = true;
              }
          }.bind(this));
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
        },
        error => console.log('Failed to fetch movies playing')
      );
  }

  addToWishList(imdbId: string) {
    this.movieService.addToWishList(imdbId)
    //   .map((res: Response) => {
    //     if (res) {
    //       if (res.status === 200) {
    //         this.isMovieExistInWishList = true;
    //       }
    //     }
    //   }).catch((error: any) => {
    //   // user not login
    //   if (error.status === 400) {
    //
    //     return Observable.throw(new Error(error.status));
    //   } else if (error.status === 409) {
    //     this.isMovieExistInWishList = true;
    //     return Observable.throw(new Error(error.status));
    //   }
    // });
      .subscribe(
        data => {
          if (data === false) {
            this.isMovieExistInWishList = false;
          } else {
            this.isMovieExistInWishList = true;
          }
        }
      );
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
  public isMovieInWishList(imdbId: string) {
    this.movieService.isMovieInWishList(imdbId)
      .map((res: Response) => {
        if (res) {
          return res.status === 200;
        }
      });
  }

}
