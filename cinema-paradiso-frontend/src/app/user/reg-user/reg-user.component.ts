import {Component, OnInit} from '@angular/core';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {RegUserService} from './reg-user.service';
import {Token} from '../../global/models/token.model';
import {LoginStatusService} from '../../global/login/login.status.service';
import {JwtHelperService} from '@auth0/angular-jwt';
import {ToastrService} from 'ngx-toastr';
import {NgForm} from '@angular/forms';
import {LoginService} from '../../global/login/login.service';
import {Movie} from '../../global/models/movie.model';

import {Router} from '@angular/router';

class Profile {
  name: string;
  id: number;
  profileImage: string;
  biography: string;
  isCritic: boolean;
  username: string;
  email: string;
  wishList: Movie[];
  watchList: Movie[];
}

@Component({
  selector: 'app-reg-user',
  templateUrl: './reg-user.component.html',
  styleUrls: ['./reg-user.component.scss'],
  providers: [RegUserService, LoginService]
})
export class RegUserComponent implements OnInit {
  currentIndex = 1;
  closeReason: string;
  profile = new Profile();
  tokenHelper = new JwtHelperService();
  profile_url: string;
  oldPassword: string;
  newPassword: string;
  changePasswordSuccess: boolean;
  changePasswordFailure: boolean;


  constructor(private router: Router, private loginService: LoginService, private modalService: NgbModal,
              private regUserService: RegUserService, private loginStatusService: LoginStatusService, private toastr: ToastrService) {
  }

  showDiv(index) {
    this.currentIndex = index;
    console.log(this.currentIndex);
  }

  ngOnInit() {
    // this.loadPosters();

    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
      this.regUserService.getProfile().subscribe(profileDetails => {
        console.log(profileDetails);

        this.profile = profileDetails as Profile;
        const decodedToken = this.tokenHelper.decodeToken(localStorage.getItem('token'));
        this.profile.email = decodedToken['email'];
        this.profile.id = decodedToken['profileId'];
        this.profile.username = decodedToken['username'];
        this.profile.profileImage = profileDetails['profileImage'];
        this.profile.wishList = profileDetails['wishList'] as Movie[];

        console.log(profileDetails);

        if (this.profile.profileImage === undefined) {
          this.profile_url = 'http://localhost:8080/user/avatar/default.jpeg';
        } else {
          this.profile_url = 'http://localhost:8080/user/avatar/' + profileDetails['profileImage'];
        }
      });
    }
  }

  updateProfile() {
    this.regUserService.update(this.profile).subscribe(data => {
      this.toastr.success('Success');
    }, error => {
      this.toastr.error('Failed to update profile');
    });
  }

  open(content) {
    this.modalService.open(content).result.then(result => {
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
        this.router.navigateByUrl('/user');
      }, error => {
        this.toastr.success('Failure');
        console.log(error);
      });
    }
  }

  changePassword(form: NgForm) {
    this.regUserService.changePassword(this.oldPassword, this.newPassword).subscribe(result => {
      if (result['success'] === true) {
        form.resetForm();
        this.changePasswordSuccess = true;
        this.toastr.success('Success');
        this.loginService.logout();
        this.router.navigateByUrl('/home');
      } else {
        this.changePasswordFailure = true;
        console.log('fail to cahnge the password');
        this.toastr.error('Failed to change the password, make sure you passwords are correct');
        form.resetForm();
      }
    });
  }

}
