import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./../reg-user.component.scss', './wishlist.component.scss']
})
export class WishlistComponent implements OnInit {

  @Input() profile;

  constructor() { }

  ngOnInit() {
  }

}
