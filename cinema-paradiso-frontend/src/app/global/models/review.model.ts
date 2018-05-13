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
  imdbId: string;
  authorImage: string;
  userRating: number;
  movie: Movie;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
