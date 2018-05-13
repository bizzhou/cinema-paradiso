import {Component, OnInit} from '@angular/core';
import {RegUserService} from '../reg-user/reg-user.service';
import {MovieService} from '../../global/movie-detail/movie.service';
import {Movie} from '../../global/models/movie.model';
import 'rxjs/add/operator/toPromise';
import {User} from '../user/user.model';
import {ToastrService} from 'ngx-toastr';
import {ModalDismissReasons, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {Review} from '../../global/models/review.model';
import {CriticApplication} from '../../global/models/critic-application.model';
import {AdminService} from './admin.service';
import {ReportReview} from '../../global/models/report-review.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
  providers: [RegUserService, AdminService]
})
export class AdminComponent implements OnInit {

  selectedTab = 'manage-user';
  users: User[];
  filmId: string;
  closeReason: string;
  reviewId: number;
  modalRef: NgbModalRef;
  addMovieFlag = false;
  reviews: Review[];
  criticApplications: CriticApplication[];
  movie: Movie;
  reportReviews: ReportReview[];
  currReportReview: ReportReview;
  reportReason: string;
  deletedReviewReports: ReportReview[];

  constructor(private userService: RegUserService, private movieService: MovieService,
              private toastrService: ToastrService, private modalService: NgbModal,
              private adminService: AdminService) {
  }

  ngOnInit() {
    this.getUsers();
    this.getAllReviews();
    this.getAllCriticApplictions();
    this.getReportedReviews();
  }

  private getMovie(imdbId: string) {
    this.movieService.getMovieDetails(imdbId).toPromise().then(data => {
      this.movie = data as Movie;
    });
  }

  private addMovie(content) {
    this.movie = new Movie();
    this.open(content);
    this.addMovieFlag = true;
  }

  private insertMovieIntoDB() {
    console.log('this movie', this.movie);
    this.formatStringIntoArray();
    console.log('this movie', this.movie);
    this.movieService.addMovie(this.movie).subscribe(data => {
      console.log('', data);
      // this.movie = undefined;
      this.modalRef.close();
      this.toastrService.success('Success');
    }, error => {
      this.toastrService.error(error['error']['message']);
    });
  }

  private formatStringIntoArray() {
    if (this.movie.genres != null) {
      this.movie.genres = this.movie.genres.toString().split(',');
    }

    if (this.movie.awards != null) {
      this.movie.awards = this.movie.awards.toString().split(';');
    }
  }

  private updateMovie(movie: Movie) {

    this.addMovieFlag = false;
    this.formatStringIntoArray();

    this.movieService.updateMovie(movie).subscribe(data => {
      this.modalRef.close();
      console.log(data);
      console.log(this.movie);
      this.movie = undefined;
      this.toastrService.success('Success');
    }, error => {
      this.modalRef.close();
      this.toastrService.error('Error has occured');
    });
  }

  private deleteMovie(filmId) {
    this.movieService.deleteMovie(filmId).subscribe(data => {
      this.movie = undefined;
      this.toastrService.success('Success');
    }, error => {
      this.toastrService.error('Failed');
    });
  }

  private getUsers() {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data['users'] as User[];
      console.log('', this.users);
    });
  }

  private deleteUser(user: User) {
    this.userService.deleteUser(user.userID).subscribe(data => {

      this.users.splice(this.users.indexOf(user), 1);
      this.toastrService.success('Success');

    });
  }

  tabChangeHandler(event) {
    this.selectedTab = event['nextId'];
  }

  open(content) {
    this.modalRef = this.modalService.open(content, {size: 'lg'});
    console.log(this.modalRef);
    this.modalRef.result.then(result => {
      this.closeReason = `Reason ${result}`;
    }, (reason) => {
      this.closeReason = `Dismissed ${this.getDismissReason(reason)}`;
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

  private deleteReview(review) {
    this.movieService.deleteReviewForMovie(review.reviewId).subscribe(data => {
      this.reviews.splice(this.reviews.indexOf(review), 1);
      this.toastrService.success('Success');
    });
  }

  private getAllReviews() {
    this.movieService.getAllReviews().subscribe(data => {
      console.log(data);
      this.reviews = data as Review[];
    });
  }

  private getAllCriticApplictions() {
    this.userService.getAllCriticApplications().subscribe(data => {
      console.log(data);
      this.criticApplications = data as CriticApplication[];
    });
  }

  private verifyApplication(criticApplication: CriticApplication) {
    const userId = criticApplication.id;
    this.userService.verifyCriticApplications(userId).subscribe(data => {
      this.criticApplications.splice(this.criticApplications.indexOf(criticApplication), 1);
      console.log(this.criticApplications);
      this.toastrService.success('Success');
    });
  }

  private deleteReportedReview(report, reviewId) {
    this.movieService.deleteReviewForMovie(reviewId).subscribe(data => {
      // for (var i = 0; i < this.reportReviews.length; i++) {
      //   if (this.reportReviews[i].review.reviewId === reviewId) {
      //     this.deletedReviewReports.push(this.reportReviews[i]);
      //   }
      // }

      this.reportReviews.splice(this.reportReviews.indexOf(report), 1);
      this.toastrService.success('Success');
    });
  }

  private getReportedReviews() {
    this.adminService.getReportedReviews().subscribe(data => {
      this.reportReviews = data as ReportReview[];
      console.log(this.reportReviews);
    }, error => {
      console.log(error);
    });
  }

  private dismissReportedReview(report) {
    this.reportReviews.splice(this.reportReviews.indexOf(report), 1);
    this.toastrService.success('Success');
  }

}
