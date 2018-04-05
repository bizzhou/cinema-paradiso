CREATE DATABASE IF NOT EXISTS rotten_tomatoes;

USE rotten_tomatoes;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
  userId INT(20) NOT NULL AUTO_INCREMENT,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(20) NOT NULL,
  PRIMARY KEY (userId)
);

DROP TABLE IF EXISTS movie;
CREATE TABLE movie (


);

-- joins movie table and celebrity table
DROP TABLE IF EXISTS knownFor;
CREATE TABLE knownFor (
	

);

