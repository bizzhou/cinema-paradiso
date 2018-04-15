import {Movie} from './movie.model';

export class Slide {
  slideId: number;
  backgroundImage: string;
  movie: Movie;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }

}
