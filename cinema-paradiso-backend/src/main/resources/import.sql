INSERT INTO Users (email, accountSuspended, password, role, username, userProfileId) VALUES ("admin@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_ADMIN", "admin", 3);
INSERT INTO Users (email, accountSuspended, password, role, username, userProfileId) VALUES ("critic@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_CRITIC", "critic", 2);
INSERT INTO Users (email, accountSuspended, password, role, username, userProfileId) VALUES ("user@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_USER", "user", 1);


insert into slides (slideId, backgroundImage, imdbId) values (1, null, "tt2380307");
insert into slides (slideId, backgroundImage, imdbId) values (2, null, "tt1856101");
insert into slides (slideId, backgroundImage, imdbId) values (3, null, "tt5052448");

update Slides set backgroundImage="../../../assets/images/carousel_img/1.jpg" where slideId =1;
update slides set backgroundImage="https://images-na.ssl-images-amazon.com/images/M/MV5BMTU5NTkwNTA2NF5BMl5BanBnXkFtZTgwMjQ5MjY2MzI@.jpg" where slideId=2;
update slides set backgroundImage="https://images-na.ssl-images-amazon.com/images/M/MV5BMTUxMjEzNzE1NF5BMl5BanBnXkFtZTgwNDYwNjUzMTI@.jpg" where slideid=3;

-- INSERT INTO Celebrities (celebrityId, name, isDirector) VALUES ("nm000001", "Guillermo del Toro", 1);
--
-- INSERT INTO Movies (imdbId, title, regUserRating, numOfRegUserRatings) VALUES ("tt000002", "The Shape Of Water", 4.3, 999);
-- INSERT INTO Movies (imdbId, title, regUserRating, numOfRegUserRatings) VALUES ("tt000003", "Blade Runner", 0, 0);
--
-- INSERT INTO userProfiles (id, biography, critic, private, name, profileImage, watchListId, wishListId) VALUES (1, "Hello World", FALSE, FALSE, "John Doe", NULL, 1, 1);
-- INSERT INTO userProfiles (id, biography, critic, private, name, profileImage, watchListId, wishListId) VALUES (2, "Raze World", TRUE, FALSE, "Peter Vim", NULL, 1, 1);
--
-- INSERT INTO reviews (reviewId, imdbId, authorId, criticReview ) VALUES (5, "tt000003", 2, 1);