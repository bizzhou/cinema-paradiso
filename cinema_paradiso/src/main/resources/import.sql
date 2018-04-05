INSERT INTO Users (email, suspended, password, role, username) VALUES ("admin@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_ADMIN", "admin");
INSERT INTO Users (email, suspended, password, role, username) VALUES ("critic@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_CRITIC", "critic");
INSERT INTO Users (email, suspended, password, role, username) VALUES ("user@admin.com", FALSE , "ce9e5e60e4ef914d949c2f942695f9191e932b7aed7cf87526e633b6fec67037f7ca344a85f2c2f4c619be567a449983a81e4d9814268e8726c0724cc6953b9a", "ROLE_USER", "user");

-- -- dummy Movies
-- INSERT INTO Movies (filmId, imdbId, title) VALUES (1, "tt000001", "cinema paradiso")
-- INSERT INTO Movies (filmId, imdbId, title) VALUES (2, "tt000002", "coco")
--
-- -- dummy celebrities
-- INSERT INTO Celebrities (celebrityId, imdbId, name) VALUES (1, "nm000001", "Melanie")
-- INSERT INTO Celebrities (celebrityId, imdbId, name) VALUES (2, "nm000002", "Mel")
--
-- -- dummy trailers
-- -- INSERT INTO Trailers ()