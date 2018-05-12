
import {Component} from '@angular/core';
import {MovieService} from '../movie-detail/movie.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent {

  constructor (private movieService: MovieService) {}

  upload(event) {
    const fileList: FileList = event.target.files;
    if (fileList.length > 0) {
      this.movieService.uploadImages(fileList).subscribe(data => {
        console.log(data);
      });
    }
  }

}
