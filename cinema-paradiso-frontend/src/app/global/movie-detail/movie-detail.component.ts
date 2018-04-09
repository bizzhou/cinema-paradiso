import {Component, OnInit, Input} from '@angular/core';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {Movie} from '../models/movie.model';
import {MovieDetailService} from './movie-detail.service';
import {MovieService} from '../movie/movie.service';
import {ActivatedRoute} from '@angular/router';
import {LoginStatusService} from '../login/login.status.service';


@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss'],
  providers: [MovieDetailService]
})
export class MovieDetailComponent implements OnInit {

  movie: Movie;
  sub: any;
  selectedMovieId: string;
  selected = 0;
  hovered = 0;
  review: string;

  constructor(config: NgbRatingConfig,
              private movieService: MovieService,
              private movieDetailService: MovieDetailService,
              private loginStatusService: LoginStatusService,
              route: ActivatedRoute) {

    this.selectedMovieId = route.snapshot.params['id'];

    // customize default values carousel slider
    config.max = 5;
    config.readonly = true;
  }

  addReview() {

  }


  rateMovie() {
    this.movieDetailService.rateMovie(this.hovered, this.selectedMovieId).subscribe(result => {
      console.log(result);
    });
  }


  ngOnInit() {
    // TODO: get data from route instead of from movieService.movieIdObservable
    // this.sub = this.route
    //   .data
    //   .subscribe(v => console.log(v)
    //   );


    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
    }

    console.log('id: ' + this.selectedMovieId);
    this.getMovie(this.selectedMovieId);

    this.ratingAnimation();
    console.log('user token ', this.loginStatusService.getTokenDetails());
  }

  getMovie(imdbId: string): any {
    console.log('Selected movie: ' + imdbId);
    this.movieDetailService.getMovieDetails(imdbId)
      .subscribe(
        data => {
          console.log(data);
          this.movie = data as Movie;
        },
        error => console.log('Failed to fetch movie with id')
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
