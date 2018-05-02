import {Component, Input, OnInit} from '@angular/core';
import {Movie} from '../../../global/models/movie.model';
import {RegUserService} from '../reg-user.service';
import {ToastrService} from 'ngx-toastr';
import {ListMovieStatus} from "../../../global/models/ListMovieStatus.model";

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./../reg-user.component.scss', './wishlist.component.scss']
})
export class WishlistComponent implements OnInit {

  @Input() profile;
  listMovieStatusEnum = ListMovieStatus;

  constructor(private  regUserService: RegUserService, private toastrService: ToastrService) {
  }

  ngOnInit() {
  }

  removeFromWishList(movie: Movie) {
    this.regUserService.removeFromWishList(movie.imdbId).subscribe(data => {
      movie.listMovieStatus = this.listMovieStatusEnum.NONE;
      this.profile.wishList.splice(this.profile.wishList.indexOf(movie), 1);
      this.toastrService.success('SUCCESS');

    }, error1 => {
      console.log(error1);
    });
  }

}
