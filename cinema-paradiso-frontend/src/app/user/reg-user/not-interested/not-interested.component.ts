import {Component, Input, OnInit} from '@angular/core';
import {Movie} from '../../../global/models/movie.model';
import {RegUserService} from '../reg-user.service';
import {ToastrService} from 'ngx-toastr';
import {ListMovieStatus} from '../../../global/models/ListMovieStatus.model';

@Component({
  selector: 'app-not-interested',
  templateUrl: './not-interested.component.html',
  styleUrls: ['./../reg-user.component.scss', './not-interested.component.scss']
})
export class NotInterestedComponent implements OnInit {

  @Input() profile;
  listMovieStatusEnum = ListMovieStatus;

  constructor(private regUserService: RegUserService,
              private toastrService: ToastrService) { }

  ngOnInit() {
  }

  removeFromNotInterestedList(movie: Movie) {
    this.regUserService.removeFromNotInterestedList(movie.imdbId).subscribe(data => {
      movie.listMovieStatus = this.listMovieStatusEnum.NONE;
      this.profile.notInterestedList.splice(this.profile.notInterestedList.indexOf(movie), 1);
      this.toastrService.success('Successfully Removed');

    }, error1 => {
      console.log(error1);
    });
  }


}
