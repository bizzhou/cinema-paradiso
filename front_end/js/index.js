$(document).ready(function () {
    var imageContainers = document.getElementsByClassName("l-cards__image");
    var titles = document.getElementsByClassName("l-cards__text");

    var i = 0, y = 0;
    while (i < titles.length) {
        // add film image
        var img = $('<img />').attr({
            'src': images[y],
            'alt': filmNames[y],
        }).appendTo(imageContainers[i]);

        // add film name and rating
        // titles[i].innerHTML = filmNames[y];
        var span = $('<span> 4.9/5.0</span>').appendTo(titles[i]);

        i++;
        y++;
        if (y == 4) y = 0;
    }

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


    $('.show_biography').click(function (e) {
        e.preventDefault();
        $('.actor_overall_container').hide();
        $('.actor_picture_container').hide();
        $('.actor_biography_container').show();
    });

    $('.show_overview').click(function (e) {
        e.preventDefault();
        $('.actor_picture_container').hide();
        $('.actor_biography_container').hide();
        $('.actor_overall_container').show();
    });

    $('.show_people_pictures').click(function (e) {
        e.preventDefault();
        $('.actor_overall_container').hide();
        $('.actor_biography_container').hide();
        $('.actor_picture_container').show();
    });


});

var filmNames = ["Blade Runner 2049", "Coco", "Call Me By Your Name", "Lady Bird", "Get Out", "Dunkirk", "In the Fade", "Phantom Thread"];

var images = ["https://images-na.ssl-images-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX300.jpg"	,
							"https://images-na.ssl-images-amazon.com/images/M/MV5BYjQ5NjM0Y2YtNjZkNC00ZDhkLWJjMWItN2QyNzFkMDE3ZjAxXkEyXkFqcGdeQXVyODIxMzk5NjA@._V1_SX300.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BNDk3NTEwNjc0MV5BMl5BanBnXkFtZTgwNzYxNTMwMzI@._V1_SX300.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BODhkZGE0NDQtZDc0Zi00YmQ4LWJiNmUtYTY1OGM1ODRmNGVkXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMjEzNzE1NF5BMl5BanBnXkFtZTgwNDYwNjUzMTI@.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BNjA4MzEzOTc0N15BMl5BanBnXkFtZTgwOTcyNDY4MjI@.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BMTYwNDI5Njg2M15BMl5BanBnXkFtZTgwMzIyNTYxNDM@.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BOTE5MzkwMjM0NV5BMl5BanBnXkFtZTgwMTQ0Mjk0NDM@.jpg",
];

$('a').on('click', function(e){
  $(e).preventDefault();
});

$('.pagination li').on('click', function(){

  $(this).siblings().removeClass('active');
  $(this).addClass('active');
  
})