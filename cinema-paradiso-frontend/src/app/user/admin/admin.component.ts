import {Component, OnChanges, OnInit} from '@angular/core';
import {UserService} from '../reg-user/user.service';
import {MovieService} from '../../global/movie-detail/movie.service';
import {Movie} from '../../global/models/movie.model';
import 'rxjs/add/operator/toPromise';
import {User} from '../user/user.model';
import {ToastrService} from 'ngx-toastr';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
  providers: [UserService]
})
export class AdminComponent implements OnInit {

  selectedTab = 'manage-movies';
  users: User[];
  filmId: string;

  constructor(private userService: UserService, private movieService: MovieService,
              private toastrService: ToastrService) {
  }

  movie: Movie;

  ngOnInit() {
    this.getUsers();
  }

  private getMovie(imdbId: string) {
    this.movieService.getMovieDetails(imdbId).toPromise().then(data => {
      this.movie = data as Movie;
    });
  }

  private addMovie(movie: Movie) {
    this.movieService.addMovie(movie).subscribe(data => {
      console.log('', data);
    });
  }

  private updateMovie(movie: Movie) {
    this.movieService.updateMovie(movie).subscribe(data => {
      console.log('', data);
    });
  }

  private deleteMovie(filmId) {
    this.movieService.deleteMovie(filmId).subscribe(data => {
      this.movie = undefined;
      this.toastrService.success('SUCCESS');
    }, error => {
      this.toastrService.error('FAIL');
    });
  }

  private getUsers() {
    this.userService.getAllUsers().subscribe(data => {
      this.users = data['users'] as User[];
      console.log('', this.users);
    });
  }

  private deleteUser(user: User) {
    this.userService.deleteUser(user.userId).subscribe(data => {

      this.users.splice(this.users.indexOf(user), 1);
      this.toastrService.success('SUCCESS');

    });
  }

  private tabChangeHandler(event) {
    this.selectedTab = event['nextId'];
  }


}
