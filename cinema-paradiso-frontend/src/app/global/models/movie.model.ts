import {Celebrity} from './celebrity.model';
import {Trailer} from './trailer.model';
import {Review} from './review.model';

export class Movie {
  imdbId: string;
  title: string;
  year: number;
  rated: string;
  releaseDate: Date;
  genres: string[];
  awards: string[];
  photos: string[];
  director: Celebrity;
  cast: Celebrity[];
  trailers: Trailer[];
  reviews: Review[];
  plot: string;
  language: string;
  country: string;
  poster: string;
  rating: number;
  production: string;
  website: string;
  boxOffice: number;
  runTime: number;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
