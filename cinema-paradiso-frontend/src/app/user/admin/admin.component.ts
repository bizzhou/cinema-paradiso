import {Component, OnInit} from '@angular/core';
import {UserService} from '../reg-user/user.service';
import {User} from '../user/user.model';
import {MovieService} from '../../global/movie-detail/movie.service';
import {Movie} from '../../global/models/movie.model';
import 'rxjs/add/operator/toPromise';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
  providers: [UserService]
})
export class AdminComponent implements OnInit {

  constructor(private userService: UserService, private movieService: MovieService) {
  }

  movie: Movie;

  ngOnInit() {
    this.getUsers();


  }

  private getMovie(imdbId: string) {
    this.movieService.getMovieDetails('tt2194499').toPromise().then(data => {
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
      console.log('', data);
    });
  }

  private getUsers() {
    this.userService.getAllUsers().subscribe(data => {
      console.log(data);
    });
  }


}
