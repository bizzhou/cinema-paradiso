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


    $('#load_more_movies').click(function (e) {
        console.log('..');

        for (var i = 0; i < 5; i++) {

            $('.show_more').prepend('<div class="row">\n' +
                '        <div class="col-4 image-wrap">\n' +
                '            <img src="https://images-na.ssl-images-amazon.com/images/M/MV5BNGNiNWQ5M2MtNGI0OC00MDA2LWI5NzEtMmZiYjVjMDEyOWYzXkEyXkFqcGdeQXVyMjM4NTM5NDY@._V1_UX182_CR0,0,182,268_AL_.jpg"\n' +
                '                 alt="">\n' +
                '        </div>\n' +
                '        <div class="col-8">\n' +
                '            <p class="film_name">Shape of Water</p>\n' +
                '\n' +
                '            <div class="flex">\n' +
                '                <i class="fa fa-star"></i>\n' +
                '                <i class="fa fa-star"></i>\n' +
                '                <i class="fa fa-star"></i>\n' +
                '                <i class="fa fa-star"></i>\n' +
                '                <i class="fa fa-star"></i>\n' +
                '                <h6>5.0/5.0</h6>\n' +
                '                <h6>R</h6>\n' +
                '            </div>\n' +
                '\n' +
                '            <p class="c-plot">At a top secret research facility in the 1960s, a lonely janitor forms a unique\n' +
                '                relationship with an amphibious creature that is being held in captivity.</p>\n' +
                '            <p>\n' +
                '                Starring <span> Sally Hawkins, Octavia Spencer, Michael Shannon</span>\n' +
                '            </p>\n' +
                '            <p>\n' +
                '                Director <span>Guillermo del Toro</span>\n' +
                '            </p>\n' +
                '            <p>\n' +
                '                Genres <span>Adventure, Drama, Fantasy, Horror, Romance</span>\n' +
                '            </p>\n' +
                '        </div>\n' +
                '    </div>');

        }


    });


});

var filmNames = ["Blade Runner 2049", "Coco", "Call Me By Your Name", "Lady Bird", "Get Out", "Dunkirk", "In the Fade", "Phantom Thread"];

var images = ["https://images-na.ssl-images-amazon.com/images/M/MV5BNzA1Njg4NzYxOV5BMl5BanBnXkFtZTgwODk5NjU3MzI@._V1_SX300.jpg",
    "https://images-na.ssl-images-amazon.com/images/M/MV5BYjQ5NjM0Y2YtNjZkNC00ZDhkLWJjMWItN2QyNzFkMDE3ZjAxXkEyXkFqcGdeQXVyODIxMzk5NjA@._V1_SX300.jpg",
    "https://images-na.ssl-images-amazon.com/images/M/MV5BNDk3NTEwNjc0MV5BMl5BanBnXkFtZTgwNzYxNTMwMzI@._V1_SX300.jpg",
    "https://images-na.ssl-images-amazon.com/images/M/MV5BODhkZGE0NDQtZDc0Zi00YmQ4LWJiNmUtYTY1OGM1ODRmNGVkXkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg",
    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMjEzNzE1NF5BMl5BanBnXkFtZTgwNDYwNjUzMTI@.jpg",
    "https://images-na.ssl-images-amazon.com/images/M/MV5BNjA4MzEzOTc0N15BMl5BanBnXkFtZTgwOTcyNDY4MjI@.jpg",
    "https://images-na.ssl-images-amazon.com/images/M/MV5BMTYwNDI5Njg2M15BMl5BanBnXkFtZTgwMzIyNTYxNDM@.jpg",
    "https://images-na.ssl-images-amazon.com/images/M/MV5BOTE5MzkwMjM0NV5BMl5BanBnXkFtZTgwMTQ0Mjk0NDM@.jpg",
];


