export class Review {
  review_id: number;
  author: string;
  is_critic_review: boolean;
  like_count: number;
  posted_date: Date;
  review_content: string;
  title: string;
  imdb_id: string;
  user_profile_id: number;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
