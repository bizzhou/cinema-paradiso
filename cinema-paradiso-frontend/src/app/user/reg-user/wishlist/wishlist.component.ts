import {Component, Input, OnInit} from '@angular/core';
import {Movie} from '../../../global/models/movie.model';
import {UserService} from '../user.service';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./../reg-user.component.scss', './wishlist.component.scss']
})
export class WishlistComponent implements OnInit {

  @Input() profile;

  constructor(private  regUserService: UserService, private toastrService: ToastrService) {
  }

  ngOnInit() {
  }

  removeFromWishList(movie: Movie) {
    this.regUserService.removeFromWishList(movie.imdbId).subscribe(data => {

      this.profile.wishList.splice(this.profile.wishList.indexOf(movie), 1);
      this.toastrService.success('SUCCESS');

    }, error1 => {
      console.log(error1);
    });
  }

}
