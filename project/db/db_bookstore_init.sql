DROP DATABASE IF EXISTS bookstore;
CREATE DATABASE bookstore DEFAULT CHARACTER SET utf8;

USE bookstore;

GRANT ALL ON bookstore.* TO 'root'@'%' IDENTIFIED BY 'root';

FLUSH PRIVILEGES;