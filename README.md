# prviput
Danulabs entry task
Ovde je sql kod za kreiranje tabela u MySql.

DROP TABLE IF EXISTS `jwts3`.`tblusers` ;

CREATE TABLE IF NOT EXISTS `jwts3`.`tblusers` (
  `userAccountID` CHAR(36) NOT NULL ,
  `deleted` boolean NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  
  PRIMARY KEY (`userAccountID`))
ENGINE = InnoDB;
 
DROP TABLE IF EXISTS `jwts3`.`tblbook` ;

CREATE TABLE IF NOT EXISTS `jwts3`.`tblbook` (
  `bookID` CHAR(36) NOT NULL ,
  `author` VARCHAR(256) NOT NULL,
  `deleted` boolean NOT NULL,
  `genre` VARCHAR(128) NOT NULL,
  `ISBN` CHAR(13) NOT NULL ,
  `title` VARCHAR(256) NOT NULL,
  
  PRIMARY KEY (`bookID`))
ENGINE = InnoDB; 
 
 Koristila sam Hibernate ORM, ali sam za User-a implementirala service koji koristi obicne konekcije i transakcije.
 
