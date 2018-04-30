import {Movie} from './movie.model';

export class Review {
  reviewId: number;
  author: number;
  criticReview: boolean;
  likeCount: number;
  postedDate: Date;
  reviewContent: string;
  title: string;
  // movie: Movie;
  imdbId: string;
  // authorImage: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
