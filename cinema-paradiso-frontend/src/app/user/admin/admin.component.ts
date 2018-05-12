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
import {Celebrity} from '../../global/models/celebrity.model';
import {CelebrityService} from '../../global/celebrity/celebrity.service';
import {AppConstant} from '../../app.constant';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
  providers: [RegUserService, CelebrityService]
})
export class AdminComponent implements OnInit {

  selectedTab = 'manage-users';
  users: User[];
  filmId: string;
  closeReason: string;
  reviewId: number;
  modalRef: NgbModalRef;
  addMovieFlag = false;
  reviews: Review[];
  criticApplications: CriticApplication[];
  fileList: FileList;
  celebrities: string;


  constructor(private userService: RegUserService, private movieService: MovieService,
              private toastrService: ToastrService, private modalService: NgbModal,
              private celebrityService: CelebrityService) {
  }

  movie: Movie;

  ngOnInit() {
    this.getUsers();
    this.getAllReviews();
    this.getAllCriticApplictions();
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
    this.formatStringIntoArray();
    const celeb = [];

    this.movieService.getFilmId().subscribe(newId => {

      if (this.celebrities !== undefined) {
        const celebArray = this.celebrities.split(',');

        for (let i = 0; i < celebArray.length; i++) {
          this.celebrityService.getCelebirty(celebArray[i]).subscribe(data => {
            celeb.push(data as Celebrity);

            if (i === celebArray.length - 1) {
              this.movie.casts = celeb;
              this.movie.imdbId = newId;

              console.log(this.movie);

              this.movieService.uploadPoster(this.fileList, newId).subscribe(poster => {
                this.movie.poster = AppConstant.API_ENDPOINT + `admin/${poster['body']}`;

                this.movie.numOfCriticRatings = 0;
                this.movie.numOfRegUserRatings = 0;
                this.movie.criticRating = 0;
                this.movie.regUserRating = 0;

                this.movieService.addMovie(this.movie).subscribe(data => {
                  console.log('movie added is ', data);
                  // this.modalRef.close();
                  this.toastrService.success('SUCCESS');
                }, error => {
                  this.toastrService.error(error['error']['message']);
                });

              });
            }

          });
        }

      }

    });
  }

  async extracted() {
    const celeb = [];
    for (const celebrity of this.celebrities.split(',')) {
      await this.celebrityService.getCelebirty(celebrity).subscribe(data => {
        celeb.push(data as Celebrity);
      });
    }
    return celeb;
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
      this.toastrService.success('SUCCESS');
    }, error => {
      this.modalRef.close();
      this.toastrService.error('Error has occured');
    });
  }

  private deleteMovie(filmId) {
    this.movieService.deleteMovie(filmId).subscribe(data => {
      this.movie = undefined;
      this.toastrService.success('SUCCESS');
    }, error => {
      this.toastrService.error('FAIL');
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
      this.toastrService.success('SUCCESS');

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
      // this.reviewId = undefined;
      this.reviews.splice(this.reviews.indexOf(review), 1);
      this.toastrService.success('SUCCESS');
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
      this.toastrService.success('SUCCESS');
    });
  }

  upload(event) {
    this.fileList = event.target.files;
    // if (fileList.length > 0) {
    //   this.movieService.uploadPoster(fileList).subscribe(data => {
    //     console.log(data);
    //   });
    // }
  }

}
