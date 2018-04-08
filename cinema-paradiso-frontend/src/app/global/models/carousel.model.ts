import {Movie} from './movie.model';
import {CarouselSlide} from './carouselSlide.model';

export class Carousel {
  slides: CarouselSlide[];

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
