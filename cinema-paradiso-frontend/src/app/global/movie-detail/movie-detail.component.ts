import {Component, OnInit, Input} from '@angular/core';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {Movie} from '../models/movie.model';
import {MovieService} from './movie.service';
import {ActivatedRoute} from '@angular/router';
import {LoginStatusService} from '../login/login.status.service';


@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss'],
})
export class MovieDetailComponent implements OnInit {

  movie: Movie;
  selectedMovieId: string;
  review: string;

  isMovieExistInWishList: boolean;
  currentRating = 0;
  ngbRatingReadOnly = false;

  constructor(private movieService: MovieService,
              private loginStatusService: LoginStatusService,
              route: ActivatedRoute) {

    this.selectedMovieId = route.snapshot.params['id'];
  }


  addReview() {
    console.log('adding review dummy');
  }


  // rateMovie() {
  //   this.movieService.rateMovie(this.hovered, this.selectedMovieId).subscribe(result => {
  //     console.log(result);
  //   });
  // }


  ngOnInit() {

    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
    }

    console.log('id: ' + this.selectedMovieId);
    this.getMovie(this.selectedMovieId);

    this.ratingAnimation();
    console.log('user token ', this.loginStatusService.getTokenDetails());
  }


  getMovie(imdbId: string): any {
    this.movieService.getMovieDetails(imdbId)
      .subscribe(
        data => {
          this.movie = data as Movie;
          // console.log(this.movie);
          // console.log('casts ', this.movie.casts);
          // console.log('imdbId ', this.movie.imdbId);
          // console.log(this.movie.photos);
        },
        error => console.log('Failed to fetch movie with id')
      );
  }

  addToWishList(imdbId: string) {
    this.movieService.addToWishList(imdbId)
      .subscribe(
        data => {
          console.log(data);
          if (data === false) {
            this.isMovieExistInWishList = false;
          } else {
            this.isMovieExistInWishList = true;
          }
        }
      );
  }

  // TODO: temp method
  ratingAnimation(): void {
    $('.bar span').hide();
    $('#bar-five').animate({
      width: '100%'
    }, 1000);
    $('#bar-four').animate({
      width: '90%'
    }, 1000);
    $('#bar-three').animate({
      width: '60%'
    }, 1000);
    $('#bar-two').animate({
      width: '50%'
    }, 1000);
    $('#bar-one').animate({
      width: '20%'
    }, 1000);

    $('#bar-ten').animate({
      width: '100%'
    }, 1000);
    $('#bar-nine').animate({
      width: '90%'
    }, 1000);
    $('#bar-eight').animate({
      width: '60%'
    }, 1000);
    $('#bar-seven').animate({
      width: '50%'
    }, 1000);
    $('#bar-six').animate({
      width: '20%'
    }, 1000);

    setTimeout(function () {
      $('.bar span').fadeIn('slow');
    }, 500);

  }


}
