import {Component, OnInit} from '@angular/core';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import {RegUserService} from './reg-user.service';
import {Token} from '../../global/login/token.model';
import {LoginStatusService} from '../../global/login/login.status.service';
import { JwtHelperService } from '@auth0/angular-jwt';

class Profile {
  name: string;
  id: number;
  profile_image: string;
  biography: string;
  is_critic: boolean;
  username: string;
  email: string;
}

@Component({
  selector: 'app-reg-user',
  templateUrl: './reg-user.component.html',
  styleUrls: ['./reg-user.component.scss'],
  providers: [RegUserService]
})
export class RegUserComponent implements OnInit {

  currentIndex = 1;
  closeReason: string;
  profile = new Profile();
  tokenHelper = new JwtHelperService();

  constructor(private modalService: NgbModal, private regUserService: RegUserService, private loginStatusService: LoginStatusService) {
  }


  showDiv(index) {
    this.currentIndex = index;
    console.log(this.currentIndex);
  }

  ngOnInit() {
    this.loadPosters();

    if (this.loginStatusService.getTokenDetails() !== null) {
      this.loginStatusService.changeStatus(true);
      this.regUserService.getProfile().subscribe(profileDetails => {
        this.profile = profileDetails as Profile;
        const decodedToken = this.tokenHelper.decodeToken(localStorage.getItem('token'));
        this.profile.email = decodedToken['email'];
        this.profile.id = decodedToken['profileId'];
        this.profile.username = decodedToken['username'];
        this.profile.profile_image = decodedToken['profileImage'];

        console.log(decodedToken);
        console.log(profileDetails);
      });
    }
  }

  updateProfile() {
    this.regUserService.update(this.profile).subscribe(data => {
      console.log(data);
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
    console.log('uploading');
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      const file: File = fileList[0];
      const formData: FormData = new FormData();
      formData.append('file', file, file.name);
      const user = JSON.parse(localStorage.getItem('credential')) as Token;
      formData.append('userId', user.id.toString());
      this.regUserService.upload(formData);
    }
  }


  loadPosters(): void {
    let movieNames = ['Blade Runner 2049', 'Coco', 'Call Me By Your Name', 'Lady Bird', 'Get Out', 'Dunkirk', 'In the Fade', 'Phantom Thread'];

    let images = ['https://images-na.ssl-images-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX300.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BYjQ5NjM0Y2YtNjZkNC00ZDhkLWJjMWItN2QyNzFkMDE3ZjAxXkEyXkFqcGdeQXVyODIxMzk5NjA@._V1_SX300.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BNDk3NTEwNjc0MV5BMl5BanBnXkFtZTgwNzYxNTMwMzI@._V1_SX300.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BODhkZGE0NDQtZDc0Zi00YmQ4LWJiNmUtYTY1OGM1ODRmNGVkXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMjEzNzE1NF5BMl5BanBnXkFtZTgwNDYwNjUzMTI@.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BNjA4MzEzOTc0N15BMl5BanBnXkFtZTgwOTcyNDY4MjI@.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BMTYwNDI5Njg2M15BMl5BanBnXkFtZTgwMzIyNTYxNDM@.jpg',
      'https://images-na.ssl-images-amazon.com/images/M/MV5BOTE5MzkwMjM0NV5BMl5BanBnXkFtZTgwMTQ0Mjk0NDM@.jpg',
    ];

    let imageContainers: NodeListOf<Element> = document.getElementsByClassName('l-cards__image');
    let movieTitles: NodeListOf<Element> = document.getElementsByClassName('l-cards__text');

    var i = 0, y = 0;
    while (i < movieTitles.length) {
      // create img element and append to container
      var img = document.createElement('img');
      img.setAttribute('src', images[y]);
      img.setAttribute('alt', movieNames[y]);
      img.style.height = '16em';
      imageContainers[i].appendChild(img);

      // create span and append movie names and ratings
      var ratings = document.createElement('p');
      ratings.style.color = 'rgb(229, 9, 20)';
      ratings.innerHTML = '4.9/5.0';

      movieTitles[i].innerHTML = movieNames[y];
      movieTitles[i].appendChild(ratings);
      // movieTitles[i].style.fontSize = '2em';

      i++;
      y++;
      if (y == 4) y = 0;
    }

  }
}
