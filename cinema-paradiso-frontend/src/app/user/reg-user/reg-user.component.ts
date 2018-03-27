import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reg-user',
  templateUrl: './reg-user.component.html',
  styleUrls: ['./reg-user.component.scss']
})
export class RegUserComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    this.loadPosters();

    $('.show_Account').click(function (e) {

      e.preventDefault();
      $('.Account').show();
      $('.Portfolio').hide();
      $('.Summary_results').hide();
      $('.Movie_Ratings_results').hide();
      $('.TV_Ratings_results').hide();
      $('.Wish_List_results').hide();
      $('.Movie_List_results').hide();
      $('.My_Critics_results').hide();

    });

    $('.show_Portfolio').click(function (e) {

      e.preventDefault();
      $('.Account').hide();
      $('.Portfolio').show();
      $('.Summary_results').show();
      $('.Movie_Ratings_results').hide();
      $('.TV_Ratings_results').hide();
      $('.Wish_List_results').hide();
      $('.Movie_List_results').hide();
      $('.My_Critics_results').hide();

    });

    $('.show_Summary').click(function (e) {

      e.preventDefault();
      $('.Summary_results').show();
      $('.Movie_Ratings_results').hide();
      $('.TV_Ratings_results').hide();
      $('.Wish_List_results').hide();
      $('.Movie_List_results').hide();
      $('.My_Critics_results').hide();

    });

    $('.show_Movie_Ratings').click(function (e) {

      e.preventDefault();
      $('.Summary_results').hide();
      $('.Movie_Ratings_results').show();
      $('.TV_Ratings_results').hide();
      $('.Wish_List_results').hide();
      $('.Movie_List_results').hide();
      $('.My_Critics_results').hide();

    });

    $('.show_TV_Ratings').click(function (e) {

      e.preventDefault();
      $('.Summary_results').hide();
      $('.Movie_Ratings_results').hide();
      $('.TV_Ratings_results').show();
      $('.Wish_List_results').hide();
      $('.Movie_List_results').hide();
      $('.My_Critics_results').hide();

    });

    $('.show_Wish_List').click(function (e) {

      e.preventDefault();
      $('.Summary_results').hide();
      $('.Movie_Ratings_results').hide();
      $('.TV_Ratings_results').hide();
      $('.Wish_List_results').show();
      $('.Movie_List_results').hide();
      $('.My_Critics_results').hide();

    });

    $('.show_Movie_List').click(function (e) {

      e.preventDefault();
      $('.Summary_results').hide();
      $('.Movie_Ratings_results').hide();
      $('.TV_Ratings_results').hide();
      $('.Wish_List_results').hide();
      $('.Movie_List_results').show();
      $('.My_Critics_results').hide();

    });

    $('.show_My_Critics').click(function (e) {

      e.preventDefault();
      $('.Summary_results').hide();
      $('.Movie_Ratings_results').hide();
      $('.TV_Ratings_results').hide();
      $('.Wish_List_results').hide();
      $('.Movie_List_results').hide();
      $('.My_Critics_results').show();

    });

    $('.show_movies').click(function (e) {

      e.preventDefault();
      $('.movie_results').show();
      $('.people_results').hide();
      $('.tv_results').hide();

    });


    $('.show_tv').click(function (e) {

      e.preventDefault();
      $('.movie_results').hide();
      $('.people_results').hide();
      $('.tv_results').show();


    });

    $('.show_people').click(function (e) {

      e.preventDefault();
      $('.movie_results').hide();
      $('.people_results').show();
      $('.tv_results').hide();

    });


  }
  loadPosters(): void {
    let movieNames = ["Blade Runner 2049", "Coco", "Call Me By Your Name", "Lady Bird", "Get Out", "Dunkirk", "In the Fade", "Phantom Thread"];

    let images = ["https://images-na.ssl-images-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX300.jpg"	,
      "https://images-na.ssl-images-amazon.com/images/M/MV5BYjQ5NjM0Y2YtNjZkNC00ZDhkLWJjMWItN2QyNzFkMDE3ZjAxXkEyXkFqcGdeQXVyODIxMzk5NjA@._V1_SX300.jpg",
      "https://images-na.ssl-images-amazon.com/images/M/MV5BNDk3NTEwNjc0MV5BMl5BanBnXkFtZTgwNzYxNTMwMzI@._V1_SX300.jpg",
      "https://images-na.ssl-images-amazon.com/images/M/MV5BODhkZGE0NDQtZDc0Zi00YmQ4LWJiNmUtYTY1OGM1ODRmNGVkXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
      "https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMjEzNzE1NF5BMl5BanBnXkFtZTgwNDYwNjUzMTI@.jpg",
      "https://images-na.ssl-images-amazon.com/images/M/MV5BNjA4MzEzOTc0N15BMl5BanBnXkFtZTgwOTcyNDY4MjI@.jpg",
      "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYwNDI5Njg2M15BMl5BanBnXkFtZTgwMzIyNTYxNDM@.jpg",
      "https://images-na.ssl-images-amazon.com/images/M/MV5BOTE5MzkwMjM0NV5BMl5BanBnXkFtZTgwMTQ0Mjk0NDM@.jpg",
    ];

    let imageContainers : NodeListOf<Element> = document.getElementsByClassName("l-cards__image");
    let movieTitles : NodeListOf<Element> = document.getElementsByClassName("l-cards__text");

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
