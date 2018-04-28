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
  casts: Celebrity[];
  trailers: Trailer[];
  reviews: Review[];
  plot: string;
  language: string;
  country: string;
  poster: string;
  regUserRating: number;
  criticRating: number;
  production: string;
  website: string;
  boxOffice: number;
  runTime: number;
  isInWishlist: boolean;
  numOfRegUserRatings: number;
  numOfCriticRatings: number;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
