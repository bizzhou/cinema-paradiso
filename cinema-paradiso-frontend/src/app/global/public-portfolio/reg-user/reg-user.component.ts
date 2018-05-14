import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RegUserService} from '../../../user/reg-user/reg-user.service';
import {Movie} from '../../models/movie.model';
import {Rating} from '../../models/rating.model';
import {AppConstant} from '../../../app.constant';
import {Review} from '../../models/review.model';

import {LoginStatusService} from '../../login/login.status.service';


class PublicProfile {
  profileImage: string;
  biography: string;
  isCritic: boolean;
  isPrivate: boolean;
  username: string;
  accountCreatedDate: Date;
  wishList: Movie[];
  notInterestedList: Movie[];
  userRatings: Rating[];
  userReviews: Review[];
}

@Component({
  selector: 'app-reg-user-portfolio',
  templateUrl: './reg-user.component.html',
  styleUrls: ['../../movie-detail/md-review/md-review.component.scss', '../critic/critic.component.scss', './reg-user.component.scss']
})
export class RegUserPortfolioComponent implements OnInit {

  selectedUsername: string;
  publicProfile = new PublicProfile();
  bestReviews: Review[];
  worstReviews: Review[];
  isUserExist: boolean;

  constructor(private route: ActivatedRoute,
              private regUserService: RegUserService,
              private loginStatusService: LoginStatusService) {
    this.selectedUsername = route.snapshot.params['username'];
  }

  ngOnInit() {
    window.scroll(0, 0);

    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
    }

    this.getPublicProfile(this.selectedUsername);
  }

  getPublicProfile(username: string): any {
    this.regUserService.getPublicProfile(username).subscribe(data => {
      this.isUserExist = true;
      // get general detail
      this.publicProfile.username = username;
      // if (data['profileImage'] === 'undefined') {
      //   this.publicProfile.profileImage = AppConstant.API_ENDPOINT + 'user/avatar/default.jpeg';
      // } else {
      //   this.publicProfile.profileImage = AppConstant.API_ENDPOINT + '/user/avatar/' + data['profileImage'];
      // }


      if (data['profileImage'] === 'undefined') {
        this.publicProfile.profileImage = '../../../../assets/images/default_profile.png';
      } else if (this.publicProfile.profileImage === 'default.jpeg') {
        this.publicProfile.profileImage = '../../../../assets/images/default_profile.png';
      } else {
        this.publicProfile.profileImage = AppConstant.API_ENDPOINT + '/user/avatar/' + data['profileImage'];
      }
      // get specific if profile is public
      if ((this.publicProfile.isPrivate = data['isPrivate']) === false) {
        this.publicProfile.biography = data['biography'];
        this.publicProfile.isCritic = data['isCritic'];
        this.publicProfile.accountCreatedDate = data['accountCreatedDate'];
        this.publicProfile.wishList = data['wishList'] as Movie[];
        this.publicProfile.notInterestedList = data['notInterestedList'] as Movie[];
        this.publicProfile.userRatings = data['userRatings'] as Rating[];
        this.publicProfile.userReviews = data['userReviews'] as Review[];

        if (this.publicProfile.isCritic) {
          this.bestReviews = this.getBestReviews();
          this.worstReviews = this.getWorstReviews();
        }
      }

      console.log(this.publicProfile);

    }, error => {
      // in case the user has deleted the account, or changed username
      console.log(error);
      this.isUserExist = false;
    });
  }

  getBestReviews(): Review[] {
    return this.publicProfile.userReviews.filter(
      review => review.userRating >= 3
    );
  }

  getWorstReviews(): Review[] {
    return this.publicProfile.userReviews.filter(
      review => review.userRating < 3
    );
  }

}
