import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-celebrity',
  templateUrl: './celebrity.component.html',
  styleUrls: ['./celebrity.component.scss']
})
export class CelebrityComponent implements OnInit {

  constructor() { }

  ngOnInit() {

    $('.show_biography').click(function (e) {
      e.preventDefault();
      $('.overview-wrap').hide();
      $('.photos-wrap').hide();
      $('.bio-wrap').show();
    });

    $('.show_overview').click(function (e) {
      e.preventDefault();
      $('.photos-wrap').hide();
      $('.bio-wrap').hide();
      $('.overview-wrap').show();
    });

    $('.show_people_pictures').click(function (e) {
      e.preventDefault();
      $('.overview-wrap').hide();
      $('.bio-wrap').hide();
      $('.photos-wrap').show();
    });
  }

}
