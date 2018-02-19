CREATE DATABASE IF NOT EXISTS rotten_tomatoes;

USE rotten_tomatoes;

DROP TABLE IF EXISTS rotten_tomatoes;

CREATE TABLE user (
  user_id int(20) NOT NULL,
  user_name varchar(255) NULL,
  PRIMARY KEY (user_id)
);