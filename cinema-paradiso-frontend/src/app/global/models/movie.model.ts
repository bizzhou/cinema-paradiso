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

  // userReviews: Review[];
  // TODO: add critic reviews;
  // criticReviews: Review[];

  plot: string;
  language: string;
  country: string;
  poster: string;
  rating: number;
  production: string;
  website: string;
  boxOffice: number;
  runTime: number;
  isInWishlist: boolean;
  numberOfRating: number;


  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
