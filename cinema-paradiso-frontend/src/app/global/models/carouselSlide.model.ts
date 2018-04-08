import {Movie} from './movie.model';
import {Celebrity} from './celebrity.model';

export class CarouselSlide {
  movie: Movie;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
