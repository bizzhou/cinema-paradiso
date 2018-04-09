import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';


@Injectable()
export class MovieService {

  private movieIdSource = new BehaviorSubject<string>('');
  movieIdObservable = this.movieIdSource.asObservable();

  constructor() { }

  setSelectedMovieId(movieId: string) {
    this.movieIdSource.next(movieId);
  }

  getSelectedMovieId(): any {
    return this.movieIdObservable;
  }


}
