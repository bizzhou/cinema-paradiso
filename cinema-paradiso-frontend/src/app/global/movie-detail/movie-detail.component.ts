import {Component, OnInit} from '@angular/core';
import {Movie} from '../models/movie.model';
import {MovieService} from './movie.service';
import {ActivatedRoute} from '@angular/router';
import {LoginStatusService} from '../login/login.status.service';
import {ToastrService} from 'ngx-toastr';


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
  loggedInFlag: boolean;

  constructor(private movieService: MovieService,
              private loginStatusService: LoginStatusService,
              private route: ActivatedRoute,
              private toastrService: ToastrService) {

    this.selectedMovieId = route.snapshot.params['id'];
  }


  addReview() {
    console.log('adding review dummy');
  }

  ratingOperations(data: number) {

    if (this.loggedInFlag === true) {
      console.log('Logged in');
      console.log(typeof (data), data);

      this.movieService.addRatingToMovie(this.selectedMovieId, data).subscribe(newRating => {
        console.log(newRating);
        this.toastrService.success('SUCCESS, new rating:' + newRating);
      }, error1 => {

        const errorMessage = error1['error']['message'];

        if (errorMessage === 'USER RATING FOR MOVIE EXISTS') {
          this.movieService.editRatingForMovie(this.selectedMovieId, data).subscribe(newRating => {
            console.log('edited rating ', newRating);
            this.toastrService.success('You have edited your previous rating, new Rating: ' +
              newRating);
          }, error2 => {
            this.toastrService.error('FAILED TO CHANGE YOUR PREVIOUS RATING');
          });
        } else {
          this.toastrService.error(error1['error']['message']);
        }


      });

    } else {
      this.toastrService.error('PLEASE LOGIN TO PERFORM THIS ACTION');
    }

  }


  // rateMovie() {
  //   this.movieService.rateMovie(this.hovered, this.selectedMovieId).subscribe(result => {
  //     console.log(result);
  //   });
  // }


  ngOnInit() {
    window.scroll(0, 0);

    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
      this.loggedInFlag = true;
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
          console.log(this.movie);
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
