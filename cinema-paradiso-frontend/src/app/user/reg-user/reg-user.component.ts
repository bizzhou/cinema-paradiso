import {Component, OnInit} from '@angular/core';
import {ModalDismissReasons, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {RegUserService} from './reg-user.service';
import {Token} from '../../global/models/token.model';
import {LoginStatusService} from '../../global/login/login.status.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {ToastrService} from 'ngx-toastr';
import {LoginService} from '../../global/login/login.service';
import {Movie} from '../../global/models/movie.model';

import {Router} from '@angular/router';
import {Rating} from '../../global/models/rating.model';
import {AppConstant} from '../../app.constant';
import {Review} from '../../global/models/review.model';

class Profile {
  name: string;
  id: number;
  profileImage: string;
  biography: string;
  isCritic: boolean;
  isPrivate: boolean;
  username: string;
  email: string;
  wishList: Movie[];
  notInterestedList: Movie[];
  userRatings: Rating[];
}

@Component({
  selector: 'app-reg-user',
  templateUrl: './reg-user.component.html',
  styleUrls: ['./reg-user.component.scss'],
  providers: [RegUserService, LoginService],
})
export class RegUserComponent implements OnInit {
  currentIndex = 1;
  closeReason: string;
  profile = new Profile();
  tokenHelper = new JwtHelperService();
  profile_url: string;
  myReviews: Review[];
  reason: string;
  password: string;

  modalRef: NgbModalRef;

  constructor(private router: Router, private loginService: LoginService, private modalService: NgbModal,
              private regUserService: RegUserService, private loginStatusService: LoginStatusService, private toastr: ToastrService) {
  }

  showDiv(index) {
    this.currentIndex = index;
  }

  ngOnInit() {
    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
      this.regUserService.getProfile().subscribe(profileDetails => {
        console.log(profileDetails);

        // TODO add biography
        this.profile = profileDetails as Profile;
        const decodedToken = this.tokenHelper.decodeToken(localStorage.getItem('token'));
        this.profile.email = decodedToken['email'];
        this.profile.id = decodedToken['profileId'];
        this.profile.username = decodedToken['username'];
        this.profile.profileImage = profileDetails['profileImage'];
        this.profile.wishList = profileDetails['wishList'] as Movie[];
        this.profile.notInterestedList = profileDetails['notInterestedList'] as Movie[];
        this.profile.userRatings = profileDetails['userRatings'] as Rating[];
        this.profile.isPrivate = profileDetails['isPrivate'];
        this.profile.isCritic = profileDetails['isCritic'];
        if (this.profile.profileImage === undefined) {
          this.profile_url = '../../../assets/images/default_profile.png';
        } else if (this.profile.profileImage === 'default.jpeg') {
          this.profile_url = '../../../assets/images/default_profile.png';
        } else {
          this.profile_url = AppConstant.API_ENDPOINT + '/user/avatar/' + profileDetails['profileImage'];
        }
      });
    } else {
      this.router.navigateByUrl('home');
    }


    this.regUserService.getUserReviews().subscribe(data => {
      console.log(data);
      this.myReviews = data as Review[];
    });


  }

  updateProfile() {

    const tempProfile = new Profile();
    tempProfile.username = this.profile.username;
    tempProfile.email = this.profile.email;
    tempProfile.biography = this.profile.biography;
    tempProfile.id = this.profile.id;

    this.regUserService.update(tempProfile).toPromise().then(data => {
      this.toastr.success('Success');
    }, error => {
      this.toastr.error('Failed to update profile');
    });


  }

  open(content) {
    this.modalRef = this.modalService.open(content);
    console.log(this.modalRef);
    this.modalRef.result.then(result => {
      this.closeReason = `Reason ${result}`;
    }, (reason) => {
      this.closeReason = `Dismissed ${this.getDissmissReason(reason)}`;
    });
  }

  getDissmissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing escape';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking x';
    } else {
      return `With ${reason}`;
    }
  }

  upload(event) {
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      const file: File = fileList[0];
      const formData: FormData = new FormData();
      const user = JSON.parse(localStorage.getItem('credential')) as Token;
      formData.append('file', file, file.name);
      formData.append('userId', user.id.toString());

      this.regUserService.upload(formData).subscribe(data => {
        this.modalRef.close();
        window.location.reload();
      }, error => {
        this.toastr.error('Failed');
        console.log(error);
      });
    }
  }

  deleteRating(rating: Rating) {
    this.regUserService.deleteUserRating(rating.ratedMovie.imdbId).subscribe(data => {
      this.toastr.success('Successfully deleted');
      this.profile.userRatings.splice(this.profile.userRatings.indexOf(rating), 1);
    });
  }

  submitCriticApplication(reason: string) {
    this.regUserService.applyForCritc(reason).subscribe(data => {
      console.log(data);
      this.reason = undefined;
      this.toastr.success('SUCCESS');
    }, error2 => {
      console.log(error2);
      this.toastr.error('FAILED: YOU HAVE ALREADY APPLIED');
    });
  }

  updateUsername(password) {
    if (confirm('Changing your username can have unintended side effects.')) {
      this.regUserService.changeUsername(this.profile.username, password).toPromise().then(success => {
        this.password = undefined;
        this.toastr.success('Success');
      }, error => {
        this.toastr.error('Failed');
      });
    }
  }

  updateEmail(password) {
    this.regUserService.changeEmail(this.profile.email, password).toPromise().then(success => {
      this.password = undefined;
      this.toastr.success('Success');
    }, error => {
      this.toastr.error('Failed');

    });
  }

  updateBiography() {
    this.regUserService.changeBiography(this.profile.biography).toPromise().then(success => {
      this.toastr.success('Biography updated');
    }, error => {
      this.toastr.error('Failed');
    });

  }


}
