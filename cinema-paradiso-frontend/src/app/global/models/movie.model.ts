import {Celebrity} from './celebrity.model';
import {Trailer} from './trailer.model';
import {Review} from './review.model';

export class Movie {
  imdb_id: string;
  title: string;
  year: number;
  rated: string;
  release_date: Date;
  genres: string[];
  awards: string[];
  photos: string[];
  director: Celebrity;
  casts: Celebrity[];
  trailers: Trailer[];
  reviews: Review[];
  plot: string;
  language: string;
  country: string;
  poster: string;
  rating: number;
  production: string;
  website: string;
  box_office: number;
  runTime: number;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
