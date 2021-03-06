import {Component, OnInit} from '@angular/core';
import {Movie} from '../models/movie.model';
import {MovieService} from './movie.service';
import {ActivatedRoute, Router} from '@angular/router';
import {LoginStatusService} from '../login/login.status.service';
import {ToastrService} from 'ngx-toastr';
import {Review} from '../models/review.model';
import {ListMovieStatus} from '../models/ListMovieStatus.model';
import {Sidebar} from '../models/sidebar.model';
import {CategoriesService} from '../categories/categories.service';
import {RegUserService} from '../../user/reg-user/reg-user.service';
import {AppConstant} from '../../app.constant';

@Component({
  selector: 'app-movie-detail',
  templateUrl: './movie-detail.component.html',
  styleUrls: ['./movie-detail.component.scss'],
  providers: []
})
export class MovieDetailComponent implements OnInit {

  movie: Movie;
  selectedMovieId: string;
  selectedFilmType: string;
  review = new Review();

  currentRating = 0;
  ngbRatingReadOnly = false;
  loggedInFlag: boolean;
  trailer: string;
  listMovieStatusEnum = ListMovieStatus;
  sidebarEnum = Sidebar;
  user: Object;

  currentUsername: string;
  currentProfileImage: string;
  isPhotosExpanded: boolean;
  isCastsExpanded: boolean;

  constructor(private movieService: MovieService,
              private loginStatusService: LoginStatusService,
              private route: ActivatedRoute,
              private toastr: ToastrService,
              private router: Router,
              private categoriesService: CategoriesService,
              private regUserService: RegUserService,
              private toastrService: ToastrService) {

    this.selectedFilmType = route.snapshot.url[0].toString();
    this.selectedMovieId = route.snapshot.params['id'];
    this.isPhotosExpanded = false;
    this.isCastsExpanded = false;
  }

  ngOnInit() {
    window.scroll(0, 0);
    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
      this.loggedInFlag = true;

      this.regUserService.getProfile().subscribe(data => {
        console.log('data obj ', data);
        if (data['profileImage'] !== 'default.jpeg') {
          data['profileImage'] = AppConstant.API_ENDPOINT + '/user/avatar/' + data['profileImage'];
        } else {
          data['profileImage'] = '../../../assets/images/default_profile.png';
        }
        data['name'] = this.loginStatusService.getTokenDetails()['username'];
        this.user = data;
      });

    }

    console.log('id: ' + this.selectedMovieId);

    // if the url is /movie/MOVIE_ID
    if (this.selectedFilmType === 'movie') {
      if (this.loggedInFlag) {
        this.getCustomMovie(this.selectedMovieId);
        this.currentUsername = JSON.parse(localStorage.getItem('credential'))['username'];

      } else {
        this.getMovie(this.selectedMovieId);
      }
    } else {
      if (this.loggedInFlag) {
        this.getCustomTV(this.selectedMovieId);
        this.currentUsername = JSON.parse(localStorage.getItem('credential'))['username'];

      } else {
        this.getTV(this.selectedMovieId);
      }
    }

  }


  addReview() {
    this.review.imdbId = this.selectedMovieId;
    this.movieService.addReview(this.review).subscribe(data => {
      this.toastrService.success('Review added');
      const authorId = JSON.parse(localStorage.getItem('credential'))['id'];
      data['authorName'] = this.currentUsername;

      this.movie.reviews.push(data as Review);

      // update the object reference so Input can reload.
      this.movie.reviews = this.movie.reviews.slice(0);

    }, error1 => {
      if (error1['error']['message'] === 'USER RATING FOR MOVIE DOES NOT EXISTS') {
        this.toastrService.error('Please rate movie');
      } else {
        this.toastrService.error('You\'ve reviewed');
      }
    });
  }

  ratingOperations(data: number) {

    const userCredential = JSON.parse(localStorage.getItem('credential'));

    if (this.loggedInFlag === true) {
      console.log('Logged in');
      console.log(typeof (data), data);

      this.movieService.addRatingToMovie(this.selectedMovieId, data).subscribe(newRating => {
        console.log(newRating);

        if (userCredential['role'] === 'ROLE_USER') {
          this.movie.numOfRegUserRatings += 1;
          this.movie.regUserRating = parseFloat(newRating.toString());
        } else {
          this.movie.numOfCriticRatings += 1;
          this.movie.criticRating = parseFloat(newRating.toString());
        }

        this.toastrService.success('New rating:' + newRating);

      }, error1 => {

        const errorMessage = error1['error']['message'];

        if (errorMessage === 'USER RATING FOR MOVIE EXISTS') {
          this.movieService.editRatingForMovie(this.selectedMovieId, data).subscribe(newRating => {
            console.log('edited rating ', newRating);

            if (userCredential['role'] === 'ROLE_USER') {
              this.movie.regUserRating = parseFloat(newRating.toString());
            } else {
              this.movie.criticRating = parseFloat(newRating.toString());
            }

            this.toastrService.success('New rating: ' +
              newRating);
          }, error2 => {
            this.toastrService.error('Failed to change your rating');
          });
        } else {
          this.toastrService.error(error1['error']['message']);
        }

      });

    } else {
      this.toastrService.error('Please log in!');
    }

  }

  getMovie(imdbId: string): any {
    this.movieService.getMovieDetails(imdbId).subscribe(data => {
        this.movie = data as Movie;

        const shrinked_photo = this.movie.photos.map(ele => this.shrinkPhoto(ele));
        this.movie.photos = shrinked_photo;
        console.log('photos', this.movie.photos);
        this.trailer = `../../../assets/trailers/${this.movie.imdbId}.mp4`;

        this.movieService.getMovieReviews(this.selectedMovieId).subscribe(reviews => {
          this.movie.reviews = reviews as Review[];
          console.log(this.movie.reviews);
        }, error1 => {
          this.toastrService.error('Failed to fetch reviews');
        });

      },
      error => {
        console.log('Failed to fetch movie with id');
        this.router.navigateByUrl('/home');
      }
    );
  }

  getCustomMovie(imdbId: string): any {
    this.movieService.getCustomMovieDetails(imdbId).subscribe(data => {
        this.movie = data as Movie;

        console.log('photos', this.movie.photos);

        const shrinked_photo = this.movie.photos.map(ele => this.shrinkPhoto(ele));
        this.movie.photos = shrinked_photo;
        this.trailer = `../../../assets/trailers/${this.movie.imdbId}.mp4`;

        this.movieService.getMovieReviews(this.selectedMovieId).subscribe(reviews => {
          this.movie.reviews = reviews as Review[];
          console.log(this.movie.reviews);
        }, error1 => {
          this.toastrService.error('Failed to fetch reviews');
        });
      },
      error => {
        console.log('Failed to fetch movie with id');
        this.router.navigateByUrl('/home');
      }
    );
  }

  getTV(imdbId: string): any {
    this.movieService.getTVDetails(imdbId).subscribe(data => {
        this.movie = data as Movie;
        const shrinked_photo = this.movie.photos.map(ele => this.shrinkPhoto(ele));
        this.movie.photos = shrinked_photo;
        this.trailer = `../../../assets/trailers/${this.movie.imdbId}.mp4`;

        // this.movieService.getTVReviews(this.selectedMovieId).subscribe(reviews => {
        //   this.movie.reviews = reviews as Review[];
        //   console.log(this.movie.reviews);
        // }, error1 => {
        //   this.toastrService.error('Failed to fetch reviews');
        // });

      },
      error => {
        console.log('Failed to fetch tv with id');
        this.router.navigateByUrl('/home');
      }
    );
  }

  getCustomTV(imdbId: string): any {
    this.movieService.getCustomTVDetails(imdbId).subscribe(data => {
        this.movie = data as Movie;
        this.trailer = `../../../assets/trailers/${this.movie.imdbId}.mp4`;
        const shrinked_photo = this.movie.photos.map(ele => this.shrinkPhoto(ele));
        this.movie.photos = shrinked_photo;
        // this.movieService.getTVReviews(this.selectedMovieId).subscribe(reviews => {
        //   this.movie.reviews = reviews as Review[];
        //   console.log(this.movie.reviews);
        // }, error1 => {
        //   this.toastrService.error('Failed to fetch reviews');
        // });
      },
      error => {
        console.log('Failed to fetch tv with id');
        this.router.navigateByUrl('/home');
      }
    );
  }

  shrinkPhoto(photo: string) {
    if (photo.includes('localhost')) {
      return photo;
    }

    return photo.substr(0, photo.indexOf('@') + 1) + '._V1_SY1000_CR0,0,1257,1000_AL_.jpg';
  }

  addToWishList(movie: Movie) {
    if (movie.listMovieStatus === this.listMovieStatusEnum.NOT_INTERESTED_LIST) {
      this.toastr.error('Already in Uninterested List');
    } else {
      this.regUserService.addToWishList(movie.imdbId)
        .subscribe(
          data => {
            movie.listMovieStatus = this.listMovieStatusEnum.WISHLIST;
          },
          error => {
            this.toastr.error('Please Login!');
            $('.modal-wrapper').toggleClass('open');
          }
        );
    }
  }

  removeFromWishList(movie: Movie) {
    this.regUserService.removeFromWishList(movie.imdbId)
      .subscribe(
        data => {
          movie.listMovieStatus = this.listMovieStatusEnum.NONE;
        },
        error => {
          this.toastr.error('Please Login!');
          $('.modal-wrapper').toggleClass('open');
        }
      );
  }

  addToNotInterestedList(movie: Movie) {
    if (movie.listMovieStatus === this.listMovieStatusEnum.WISHLIST) {
      this.toastr.error('Already in Wish List');
    } else {
      this.regUserService.addToNotInterestedList(movie.imdbId)
        .subscribe(
          data => {
            movie.listMovieStatus = this.listMovieStatusEnum.NOT_INTERESTED_LIST;
          },
          error => {
            this.toastr.error('Please Login!');
            $('.modal-wrapper').toggleClass('open');
          }
        );
    }
  }

  removeFromNotInterestedList(movie: Movie) {
    this.regUserService.removeFromNotInterestedList(movie.imdbId)
      .subscribe(
        data => {
          movie.listMovieStatus = this.listMovieStatusEnum.NONE;
        },
        error => {
          this.toastr.error('Please Login!');
          $('.modal-wrapper').toggleClass('open');
        }
      );
  }

  selectTab(tab) {
    this.categoriesService.setSelectedTab(tab);
  }

  isTabActive(tab) {
    return this.categoriesService.getTabActive(tab);
  }

  setCurrentTab(tab) {
    this.categoriesService.setCurrentTab(tab);
  }

  deletePhoto(photo: string) {
    this.movie.photos.splice(this.movie.photos.indexOf(photo), 1);
  }

  photosExpand(expand: boolean) {
    this.isPhotosExpanded = expand;
  }

  castsExpand(expand: boolean) {
    this.isCastsExpanded = expand;
  }


}
