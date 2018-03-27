import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  constructor() {
  }

  ngOnInit() {
    $(document).ready(function () {

      $('.mp-users').show();
      $('.mp-movies').hide();
      $('.mp-application').hide();
      $('.mp-carousel').hide();
      $('.mp-reviews').hide();
      $('.mp-celebrity').hide();

      $('body').on('click', '.larg div h3', function () {
        if ($(this).children('span').hasClass('close')) {
          $(this).children('span').removeClass('close');
        } else {
          $(this).children('span').addClass('close');
        }
        $(this).parent().children('p').slideToggle(250);
      });

      $('body').on('click', 'nav ul li a', function () {
        let title = $(this).data('title');
        $('.title').children('h2').html(title);

      });


      $('#manage-user').click(e => {
        $('.mp-users').show();
        $('.mp-movies').hide();
        $('.mp-application').hide();
        $('.mp-carousel').hide();
        $('.mp-reviews').hide();
        $('.mp-celebrity').hide();
      });

      $('#manage-movies').click(e => {

        $('.mp-users').hide();
        $('.mp-movies').show();
        $('.mp-application').hide();
        $('.mp-carousel').hide();
        $('.mp-reviews').hide();
        $('.mp-celebrity').hide();
      });


      $('#manage-reviews').click(e => {

        $('.mp-users').hide();
        $('.mp-movies').hide();
        $('.mp-application').hide();
        $('.mp-carousel').hide();
        $('.mp-reviews').show();
        $('.mp-celebrity').hide();

      });

      $('#manage-celebrities').click(e => {

        $('.mp-users').hide();
        $('.mp-movies').hide();
        $('.mp-application').hide();
        $('.mp-carousel').hide();
        $('.mp-reviews').hide();
        $('.mp-celebrity').show();
      });


      $('#manage-carousel').click(e => {

        $('.mp-users').hide();
        $('.mp-movies').hide();
        $('.mp-application').hide();
        $('.mp-carousel').show();
        $('.mp-reviews').hide();
        $('.mp-celebrity').hide();
      });

      $('#manage-application').click(e => {

        $('.mp-users').hide();
        $('.mp-movies').hide();
        $('.mp-application').show();
        $('.mp-carousel').hide();
        $('.mp-reviews').hide();
        $('.mp-celebrity').hide();
      });



    });


  }

}
