import { Component, OnInit } from '@angular/core';
import { CelebrityService } from './celebrity.service';
import { ActivatedRoute } from '@angular/router';
import { Celebrity } from '../models/celebrity.model';
// import {Celebrity} from ''

@Component({
  selector: 'app-celebrity',
  templateUrl: './celebrity.component.html',
  styleUrls: ['./celebrity.component.scss'],
  providers: [CelebrityService]
})
export class CelebrityComponent implements OnInit {

  celebrityId: string;
  celebrity: Celebrity;

  constructor(private celebrityService: CelebrityService, private route: ActivatedRoute) { 
    this.celebrityId = route.snapshot.params['id'];
  }

  ngOnInit() {

    this.celebrityService.getCelebirty(this.celebrityId).subscribe(result => {
      this.celebrity = result as Celebrity;
      console.log('celebrity detail ', this.celebrity);
    });

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
