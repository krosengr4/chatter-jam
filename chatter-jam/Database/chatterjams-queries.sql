# ---------------------------------------------------------------------- #
# Target DBMS:           MySQL                                           #
# Project name:          ChatterJams                               #
# ---------------------------------------------------------------------- #
DROP DATABASE IF EXISTS chatterjams;

CREATE DATABASE IF NOT EXISTS chatterjams;

USE chatterjams;

# ---------------------------------------------------------------------- #
# Tables                                                                 #
# ---------------------------------------------------------------------- #

CREATE TABLE posts (
post_id INT NOT NULL AUTO_INCREMENT,
title varchar(255) NOT NULL,
content TEXT NOT NULL,
author varchar(75), 
date_posted DATETIME NOT NULL,
likes INT,
dislikes INT,
PRIMARY KEY (post_id)
);

CREATE TABLE comments (
comment_id INT AUTO_INCREMENT NOT NULL,
post_id INT NOT NULL,
content TEXT NOT NULL,
author varchar(75),
date_posted DATETIME NOT NULL,
PRIMARY KEY (comment_id),
FOREIGN KEY (post_id) REFERENCES posts(post_id)
);
 