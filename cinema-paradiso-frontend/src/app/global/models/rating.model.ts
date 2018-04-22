import {Movie} from './movie.model';

export class Rating {
  id: number;
  ratedMovie: Movie;
  userRating: number;
  ratedDate: Date;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
