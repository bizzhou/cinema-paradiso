$( document ).ready(function() {
  var imageContainers = 	document.getElementsByClassName("l-cards__image");
	var titles = document.getElementsByClassName("l-cards__text");
	
	var i = 0, y = 0;
	while (i < titles.length) {
		// add film image
		var img = $('<img />').attr({
            'src': images[y],
            'alt': filmNames[y],
        }).appendTo(imageContainers[i]);
		
		// add film name and rating
		titles[i].innerHTML = filmNames[y];
		var span = $('<span> 4.9/5.0</span>').appendTo(titles[i]);
		
		i++; 
		y++;
		if (y == 8) y = 0;	
	}

});

var filmNames = ["Blade Runner 2049", "Coco", "Call Me By Your Name", "Lady Bird", "Get Out", "Dunkirk", "In the Fade", "Phantom Thread"];

var images = ["https://images-na.ssl-images-amazon.com/images/M/MV5BMTU5NTkwNTA2NF5BMl5BanBnXkFtZTgwMjQ5MjY2MzI@.jpg"	,
							"https://images-na.ssl-images-amazon.com/images/M/MV5BMTgyMTA2ODMxNl5BMl5BanBnXkFtZTgwMzEzMjMyNDM@.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BNzQxNDAwMTA2Ml5BMl5BanBnXkFtZTgwNzE0ODczMzI@.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BMDRlODhjN2YtYTNiMC00MzE3LWE5YzUtZGNiY2NmNTM4YzgxXkEyXkFqcGdeQXVyNjUwNzk3NDc@.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMjEzNzE1NF5BMl5BanBnXkFtZTgwNDYwNjUzMTI@.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BNjA4MzEzOTc0N15BMl5BanBnXkFtZTgwOTcyNDY4MjI@.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BMTYwNDI5Njg2M15BMl5BanBnXkFtZTgwMzIyNTYxNDM@.jpg",
							"https://images-na.ssl-images-amazon.com/images/M/MV5BOTE5MzkwMjM0NV5BMl5BanBnXkFtZTgwMTQ0Mjk0NDM@.jpg",
];