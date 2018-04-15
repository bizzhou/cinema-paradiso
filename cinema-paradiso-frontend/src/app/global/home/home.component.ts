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
import {MovieService} from '../movie/movie.service';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {Slide} from '../models/slide.model';

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

    // load movies trending
    this.getMoviesTrending();

    // load movies coming soon
    this.getMoviesComingSoon();

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
        },
        error => console.log('Failed to fetch movies coming soon')
      );
  }

  addToWishList(imdbId: string) {
    this.movieService.addToWishList(imdbId)
      .subscribe(
        data => {
          console.log(data);
        },
        error => console.log('Failed to add to wish list')
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
