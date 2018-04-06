INSERT INTO user_profiles (id, biography, is_critic, is_private, name, profile_image, watch_list_watch_list_id, wish_list_wishlist_id) VALUES (1, "Hello World", FALSE, FALSE, "John Doe", NULL, NULL, NULL);
INSERT INTO user_profiles (id, biography, is_critic, is_private, name, profile_image, watch_list_watch_list_id, wish_list_wishlist_id) VALUES (2, "Raze World", TRUE, FALSE, "Peter Vim", NULL, NULL, NULL);


INSERT INTO Users (email, suspended, password, role, username, user_profile_id) VALUES ("admin@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_ADMIN", "admin", NULL);
INSERT INTO Users (email, suspended, password, role, username, user_profile_id) VALUES ("critic@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_CRITIC", "critic", 2);
INSERT INTO Users (email, suspended, password, role, username, user_profile_id) VALUES ("user@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_USER", "user",1);
