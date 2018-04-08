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

    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
    }

    this.movieService.movieIdObservable.subscribe(observable => this.selectedMovieId = observable);
  }

  getCarousel(): any {
    this.homeService.getCarousel()
      .subscribe(
        data => {
          // console.log(data);
          this.carousel = data as Movie[];
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

  // pass the selected movie id to movie detail page for rendering
  setImdbId(imdbId: string) {
    this.movieService.setSelectedMovieId(imdbId);
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

}
