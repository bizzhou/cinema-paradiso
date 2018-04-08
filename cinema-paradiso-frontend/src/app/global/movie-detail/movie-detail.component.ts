import { Component, OnInit, Input } from '@angular/core';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';
import {Movie} from '../models/movie.model';
import {MovieDetailService} from './movie-detail.service';
import {MovieService} from '../movie/movie.service';
// import {MovieService} from "../movie/movie.service";

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss'],
})
export class MovieDetailComponent implements OnInit {

  movie: Movie;
  sub: any;

  selectedMovieId: string;

  constructor(config: NgbRatingConfig,
              private movieService: MovieService,
              private movieDetailService: MovieDetailService
              /*private route: ActivatedRoute*/) {
    // customize default values of ratings used by this component tree
    config.max = 5;
    config.readonly = true;
  }

  ngOnInit() {
    // TODO: get data from route instead of the movieService.movieIdObservable
    // this.sub = this.route
    //   .data
    //   .subscribe(v => console.log(v)
    //   );
    this.movieService.movieIdObservable.subscribe(movieIdObs => this.selectedMovieId = movieIdObs);

    this.getMovie();

    this.ratingAnimation();
  }

  getMovie(): any {
    console.log('Selected movie: ' + this.selectedMovieId);
    this.movie = this.movieDetailService.getMovie(this.selectedMovieId);
    console.log(this.movie);
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
