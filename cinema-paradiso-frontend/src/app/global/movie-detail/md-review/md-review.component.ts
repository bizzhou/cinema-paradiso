import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Review} from '../../models/review.model';
import {AppConstant} from '../../../app.constant';
import {ModalDismissReasons, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {MovieService} from '../movie.service';
import {ToastrService} from 'ngx-toastr';

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

    clickedReview: Review;
    modalRef: NgbModalRef;
    private closeResult: string;

    constructor(private movieService: MovieService, private modalService: NgbModal,
                private toastrService: ToastrService) {
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

            // console.log('critic review ', this.criticReviews);
            // console.log('user review ', this.userReviews);
          });

        }

    }

  openModal(reportReviewContent, review: Review) {
    this.clickedReview = review;
    this.modalRef = this.modalService.open(reportReviewContent);
    this.modalRef.result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  report(reason: string) {
    this.movieService.reportReview(this.clickedReview.reviewId, reason)
      .subscribe(data => {
        this.reviews.splice(this.reviews.indexOf(this.clickedReview), 1);
        this.toastrService.success('Success');
        this.modalRef.close();
      }, error => {
        console.log(error);
        this.toastrService.error('Sorry, you\'are unable to report at this moment');
      });
  }
}


















