import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {RegUserService} from '../../../user/reg-user/reg-user.service';
import {Movie} from '../../models/movie.model';
import {Rating} from '../../models/rating.model';
import {AppConstant} from '../../../app.constant';

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
}
@Component({
  selector: 'app-reg-user-portfolio',
  templateUrl: './reg-user.component.html',
  styleUrls: ['../../movie-detail/md-review/md-review.component.scss', '../critic/critic.component.scss', './reg-user.component.scss']
})
export class RegUserPortfolioComponent implements OnInit {

  selectedUsername: string;
  publicProfile = new PublicProfile();

  constructor(private route: ActivatedRoute,
              private regUserService: RegUserService) {
    this.selectedUsername = route.snapshot.params['username'];
  }

  ngOnInit() {
    window.scroll(0, 0);
    this.getPublicProfile(this.selectedUsername);
  }

  getPublicProfile(username: string): any {
    this.regUserService.getPublicProfile(username).subscribe(data => {
      // get general detail
      this.publicProfile.username = username;
      if (data['profileImage'] === 'undefined') {
        this.publicProfile.profileImage = AppConstant.API_ENDPOINT + 'user/avatar/default.jpeg';
      } else {
        this.publicProfile.profileImage = AppConstant.API_ENDPOINT + '/user/avatar/' + data['profileImage'];
      }
      // get specific if profile is public
      if ((this.publicProfile.isPrivate = data['isPrivate']) === false) {
        this.publicProfile.biography = data['biography'];
        this.publicProfile.accountCreatedDate = data['accountCreatedDate'];
        this.publicProfile.wishList = data['wishList'] as Movie[];
        this.publicProfile.notInterestedList = data['notInterestedList'] as Movie[];
        this.publicProfile.userRatings = data['userRatings'] as Rating[];
      }

      console.log(this.publicProfile);

    }, error => {
      // in case the user has deleted the account, or changed username
      console.log(error);
    });
  }

}
