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

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  providers: [HomeService]
})
export class HomeComponent implements OnInit {

  currentRate = 3.14;

  // temp
  carousel: Movie[];

  constructor(private loginStatusService: LoginStatusService,
              private homeService: HomeService,
              private _http: HttpClient,
              config: NgbCarouselConfig) {
    config.interval = 5000;
    config.wrap = true;
    config.keyboard = false;
  }

  ngOnInit() {
    // temp: load poster
    this.loadPosters();

    // load carousel
    this.getCarousel();

    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
    }
  }


  loadPosters(): void {
    const movieNames = ['Blade Runner 2049', 'Coco', 'Call Me By Your Name', 'Lady Bird', 'Get Out', 'Dunkirk', 'In the Fade', 'Phantom Thread'];

    const images = ['https://images-na.ssl-images-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX300.jpg'	,
      'https://images-na.ssl-images-amazon.com/images/M/MV5BYjQ5NjM0Y2YtNjZkNC00ZDhkLWJjMWItN2QyNzFkMDE3ZjAxXkEyXkFqcGdeQXVyODIxMzk5NjA@._V1_SX300.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BNDk3NTEwNjc0MV5BMl5BanBnXkFtZTgwNzYxNTMwMzI@._V1_SX300.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BODhkZGE0NDQtZDc0Zi00YmQ4LWJiNmUtYTY1OGM1ODRmNGVkXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMjEzNzE1NF5BMl5BanBnXkFtZTgwNDYwNjUzMTI@.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BNjA4MzEzOTc0N15BMl5BanBnXkFtZTgwOTcyNDY4MjI@.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BMTYwNDI5Njg2M15BMl5BanBnXkFtZTgwMzIyNTYxNDM@.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BOTE5MzkwMjM0NV5BMl5BanBnXkFtZTgwMTQ0Mjk0NDM@.jpg',
    ];

    const imageContainers: NodeListOf<Element> = document.getElementsByClassName('l-cards__image');
    const movieTitles: NodeListOf<Element> = document.getElementsByClassName('l-cards__text');

    let i = 0, y = 0;
    while (i < movieTitles.length) {
      // create img element and append to container
      const img = document.createElement('img');
      img.setAttribute('src', images[y]);
      img.setAttribute('alt', movieNames[y]);
      img.style.height = '16em';
      imageContainers[i].appendChild(img);

      // create span and append movie names and ratings
      const ratings = document.createElement('p');
      ratings.style.color = 'rgb(229, 9, 20)';
      ratings.innerHTML = '4.9/5.0';

      movieTitles[i].innerHTML = movieNames[y];
      movieTitles[i].appendChild(ratings);
      // movieTitles[i].style.fontSize = '2em';

      i++;
      y++;
      if (y == 4) { y = 0; }
    }
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



}
