import {Movie} from './movie.model';

export class Review {
  reviewId: number;
  authorId: number;

  authorName: string;
  criticReview: boolean;
  likeCount: number;
  postDate: Date;
  reviewContent: string;
  title: string;
  imdbId: string;
  authorImage: string;
  userRating: number;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
