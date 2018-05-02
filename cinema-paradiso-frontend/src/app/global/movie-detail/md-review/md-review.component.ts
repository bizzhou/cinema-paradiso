import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Review} from '../../models/review.model';
import {AppConstant} from '../../../app.constant';

@Component({
    selector: 'app-md-review',
    templateUrl: './md-review.component.html',
    styleUrls: ['../movie-detail.component.scss', './md-review.component.scss']
})
export class MdReviewComponent implements OnInit, OnChanges {

    @Input()
    reviews: Review[];

    userReviews: Review[] = [];
    criticReviews: Review[] = [];

    constructor() {
    }

    ngOnInit() {
        console.log('init ', this.reviews);
    }

    ngOnChanges(changes: SimpleChanges): void {

        console.log('reviews in component ', this.reviews);

        if (this.reviews !== undefined && this.reviews !== null) {

          this.reviews.forEach(review => {

            const img_link = AppConstant.API_ENDPOINT + `user/avatar/${review.authorId}.jpeg`;
            // review.authorImage = img_link;
            review['authorImage'] = img_link;

            if (review.criticReview === true) {
              this.criticReviews.push(review);
            } else {
              this.userReviews.push(review);
            }

            console.log('critic review ', this.criticReviews);
            console.log('user review ', this.userReviews);
          });

        }

    }

}
