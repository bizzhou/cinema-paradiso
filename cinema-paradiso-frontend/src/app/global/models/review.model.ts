import {Movie} from './movie.model';

export class Review {
  reviewId: number;
  authorId: number;
  authorName: string;
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
