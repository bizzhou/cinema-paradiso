INSERT INTO Users (email, suspended, password, role, username) VALUES ("admin@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_ADMIN", "admin");
INSERT INTO Users (email, suspended, password, role, username) VALUES ("critic@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_CRITIC", "critic");
INSERT INTO Users (email, suspended, password, role, username) VALUES ("user@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_USER", "user");

INSERT INTO Movies (imdb_id, title) VALUES ("tt000001", "Coco");
INSERT INTO Movies (imdb_id, title) VALUES ("tt000002", "The Shape Of Water");
INSERT INTO Movies (imdb_id, title) VALUES ("tt000003", "Blade Runner");