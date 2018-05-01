import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Review} from '../../models/review.model';

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

        this.reviews.forEach(review => {
            if (review.isCriticReview === true) {
                this.criticReviews.push(review);
            } else {
                this.userReviews.push(review);
            }

            console.log('critic review ', this.criticReviews);
            console.log('user review ', this.userReviews);
        });

    }

}
