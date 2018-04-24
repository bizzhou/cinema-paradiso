export class Review {
  reviewId: number;
  author: string;
  isCriticReview: boolean;
  likeCount: number;
  postedDate: Date;
  reviewContent: string;
  title: string;
  imdbId: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
