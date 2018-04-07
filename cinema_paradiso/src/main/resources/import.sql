INSERT INTO Users (email, suspended, password, role, username) VALUES ("admin@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_ADMIN", "admin");
INSERT INTO Users (email, suspended, password, role, username) VALUES ("critic@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_CRITIC", "critic");
INSERT INTO Users (email, suspended, password, role, username) VALUES ("user@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_USER", "user");
-- INSERT INTO Users (email, suspended, password, role, username) VALUES ("mel@gmail.com", FALSE , "79024eeec45d4494fbd5eaa4dee5345f5a3596f93f32a17132e8cbf72be0a8d398cf6656e2f52de63c853dca7490ec576e31e5e99112092955fe49cf5abb44d9", "ROLE_USER", "user");

INSERT INTO Celebrities (celebrity_id, name, is_director) VALUES ("nm000001", "Guillermo del Toro", 1);

INSERT INTO Movies (imdb_id, title, rating, number_of_ratings) VALUES ("tt000002", "The Shape Of Water", 4.3, 999);
INSERT INTO Movies (imdb_id, title, rating, number_of_ratings) VALUES ("tt000003", "Blade Runner", 0, 0);

INSERT INTO user_profiles (id, biography, is_critic, is_private, name, profile_image, watch_list_watch_list_id, wish_list_wishlist_id) VALUES (1, "Hello World", FALSE, FALSE, "John Doe", NULL, NULL, NULL);
INSERT INTO user_profiles (id, biography, is_critic, is_private, name, profile_image, watch_list_watch_list_id, wish_list_wishlist_id) VALUES (2, "Raze World", TRUE, FALSE, "Peter Vim", NULL, NULL, NULL);

INSERT INTO reviews (review_id, imdb_id, user_profile_id, is_critic_review ) VALUES (5, "tt000003", 2, 1);