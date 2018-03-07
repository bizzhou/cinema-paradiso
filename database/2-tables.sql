CREATE DATABASE IF NOT EXISTS rotten_tomatoes;

USE rotten_tomatoes;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  user_id INT(20) NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(20) NOT NULL,
  PRIMARY KEY (user_id)
);