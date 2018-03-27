import { Component, OnInit } from '@angular/core';
import {NgbRatingConfig} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss'],

})
export class MovieDetailComponent implements OnInit {

  constructor(config: NgbRatingConfig) {
    // customize default values of ratings used by this component tree
    config.max = 5;
    config.readonly = true;
  }

  ngOnInit() {

    $(document).ready(function() {
      $('.bar span').hide();
      $('#bar-five').animate({
        width: '100%'}, 1000);
      $('#bar-four').animate({
        width: '90%'}, 1000);
      $('#bar-three').animate({
        width: '60%'}, 1000);
      $('#bar-two').animate({
        width: '50%'}, 1000);
      $('#bar-one').animate({
        width: '20%'}, 1000);

      $('#bar-ten').animate({
        width: '100%'}, 1000);
      $('#bar-nine').animate({
        width: '90%'}, 1000);
      $('#bar-eight').animate({
        width: '60%'}, 1000);
      $('#bar-seven').animate({
        width: '50%'}, 1000);
      $('#bar-six').animate({
        width: '20%'}, 1000);

      setTimeout(function() {
        $('.bar span').fadeIn('slow');
      }, 2000);

    });

  }

}
