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

        $('.container').replaceWith('<div class="container search_results">\n' +
            '\n' +
            '    <div class="row">\n' +
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
            '                relationship with an amphibious\n' +
            '                creature that is being held in captivity.</p>\n' +
            '            <p>\n' +
            '                Starring\n' +
            '                <span> Sally Hawkins, Octavia Spencer, Michael Shannon</span>\n' +
            '            </p>\n' +
            '            <p>\n' +
            '                Director\n' +
            '                <span>Guillermo del Toro</span>\n' +
            '            </p>\n' +
            '            <p>\n' +
            '                Genres\n' +
            '                <span>Adventure, Drama, Fantasy, Horror, Romance</span>\n' +
            '            </p>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '\n' +
            '    <div class="row">\n' +
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
            '                relationship with an amphibious\n' +
            '                creature that is being held in captivity.</p>\n' +
            '            <p>\n' +
            '                Starring\n' +
            '                <span> Sally Hawkins, Octavia Spencer, Michael Shannon</span>\n' +
            '            </p>\n' +
            '            <p>\n' +
            '                Director\n' +
            '                <span>Guillermo del Toro</span>\n' +
            '            </p>\n' +
            '            <p>\n' +
            '                Genres\n' +
            '                <span>Adventure, Drama, Fantasy, Horror, Romance</span>\n' +
            '            </p>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '\n' +
            '\n' +
            '    <div class="show_more">\n' +
            '        <a class="l-cards__more" id="load_more_movies" href="javascript:void(0)">more\n' +
            '            <i class="fa fa-angle-double-right"></i>\n' +
            '        </a>\n' +
            '    </div>\n' +
            '\n' +
            '\n' +
            '</div>')

    });


    $('.show_tv').click(function (e) {

        e.preventDefault();
        console.log(';;');

        $('.container').replaceWith('<div class="container tv_result">\n' +
            '\n' +
            '    <div class="row">\n' +
            '        <div class="col-4 image-wrap">\n' +
            '            <img src="https://images-na.ssl-images-amazon.com/images/M/MV5BMjE3NTQ1NDg1Ml5BMl5BanBnXkFtZTgwNzY2NDA0MjI@._V1_UX182_CR0,0,182,268_AL_.jpg"\n' +
            '                alt="">\n' +
            '        </div>\n' +
            '        <div class="col-8">\n' +
            '            <p class="tv_name">Game of Throne</p>\n' +
            '\n' +
            '            <div class="flex">\n' +
            '                <i class="fa fa-star"></i>\n' +
            '                <i class="fa fa-star"></i>\n' +
            '                <i class="fa fa-star"></i>\n' +
            '                <i class="fa fa-star"></i>\n' +
            '                <i class="fa fa-star"></i>\n' +
            '                <h6>5.0/5.0</h6>\n' +
            '                <h6>RR</h6>\n' +
            '            </div>\n' +
            '\n' +
            '            <p class="c-plot">Nine noble families fight for control over the mythical lands of Westeros, while an ancient enemy returns\n' +
            '                after being dormant for thousands of years. </p>\n' +
            '            <p>\n' +
            '                Starring\n' +
            '                <span> Emilia Clarke, Peter Dinklage, Kit Harington </span>\n' +
            '            </p>\n' +
            '            <p>\n' +
            '                Director\n' +
            '                <span> David Benioff, D.B. Weiss </span>\n' +
            '            </p>\n' +
            '            <p>\n' +
            '                Genres\n' +
            '                <span> Action | Adventure | Drama | Fantasy | Romance </span>\n' +
            '            </p>\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>');

    });

    $('.show_people').click(function (e) {

        e.preventDefault();

        $('.container').replaceWith('\n' +
            '<div class="container people_result">\n' +
            '\n' +
            '    <div class="row">\n' +
            '        <div class="col-4 image-wrap">\n' +
            '            <img src="https://images-na.ssl-images-amazon.com/images/M/MV5BODE4MDE0MDEzMl5BMl5BanBnXkFtZTgwNjI1NTU5MDE@._V1_UY317_CR14,0,214,317_AL_.jpg"\n' +
            '                alt="">\n' +
            '        </div>\n' +
            '        <div class="col-8">\n' +
            '            <p class="actor_name">Sally Hawkins</p>\n' +
            '            <p class="actor-bio">Sally Cecilia Hawkins was born in 1976 in Lewisham hospital, London, England, to Jacqui and Colin Hawkins,\n' +
            '                authors and illustrators of children\'s books. She is of English and Irish descent. Hawkins was brought\n' +
            '                up in Greenwich, in southeast London. She attended James Allen\'s Girls\' School in Dulwich. She graduated\n' +
            '                from the Royal Academy of ...</p>\n' +
            '            <p class="actor_birth_information">\n' +
            '                Born in:\n' +
            '                <span class="actor_birth_date">April 27, 1976 in Dulwich, London, England, UK</span>\n' +
            '            </p>\n' +
            '\n' +
            '        </div>\n' +
            '    </div>\n' +
            '</div>')
    })


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


