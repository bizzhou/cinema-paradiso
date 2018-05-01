import {Movie} from './movie.model';

export class Slide {
  slideId: number;
  backgroundImage: string;
  movie: Movie;
  listMovieStatus: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }

}
