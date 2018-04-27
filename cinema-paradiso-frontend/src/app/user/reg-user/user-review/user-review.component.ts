import {Component, Input, OnInit} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {MovieService} from '../../../global/movie-detail/movie.service';
import {Review} from '../../../global/models/review.model';

@Component({
  selector: 'app-user-review',
  templateUrl: './user-review.component.html',
  styleUrls: ['./../reg-user.component.scss', './user-review.component.scss']
})
export class UserReviewComponent implements OnInit {

  @Input() reviews;
  private closeResult: string;
  clickedReview: Review;
  
  constructor(private movieService: MovieService, private modalService: NgbModal) {
  }

  ngOnInit() {
    console.log(this.reviews);
  }

  open(content, review: Review) {
    this.clickedReview = review;
    this.modalService.open(content).result.then((result) => {
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

  editReview() {
    this.movieService.editReviewForMovie(this.clickedReview).subscribe(data => {
      console.log('Return Data', data);
    }, error => {
      console.log('Error ', error);
    });
  }

  deleteReview(review: Review) {
    this.movieService.deleteReviewForMovie(review.reviewId).subscribe(data => {
      console.log('Return Data', data);
      this.reviews.splice(this.reviews.indexOf(review), 1);
    }, error => {
      console.log('Error ', error);
    });
  }

}
