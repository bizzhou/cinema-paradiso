
import {Review} from './review.model';

export class ReportReview {
  reportId: number;
  review: Review;
  reportReason: string;
  reportBy: string;

  constructor(values: Object = {}) {
    Object.assign(this, values);
  }
}
