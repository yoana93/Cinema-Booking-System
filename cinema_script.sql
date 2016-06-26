-- MySQL Script generated by MySQL Workbench
-- 06/26/16 09:49:18
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cinema_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinema_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinema_db` DEFAULT CHARACTER SET utf8 ;
USE `cinema_db` ;

-- -----------------------------------------------------
-- Table `cinema_db`.`hall`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_db`.`hall` (
  `name` VARCHAR(45) NOT NULL,
  `capacity` INT NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema_db`.`film-show`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_db`.`film-show` (
  `film_name` VARCHAR(45) NOT NULL,
  `hall` VARCHAR(45) NOT NULL,
  `date` DATETIME NOT NULL,
  `duration_in_min` INT NOT NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`film_name`),
  INDEX `hall_idx` (`hall` ASC),
  CONSTRAINT `hall`
    FOREIGN KEY (`hall`)
    REFERENCES `cinema_db`.`hall` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_db`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `is_customer` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinema_db`.`sold_ticket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema_db`.`sold_ticket` (
  `show_id` VARCHAR(45) NOT NULL,
  `seat` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `id_of_user` INT NOT NULL,
  PRIMARY KEY (`show_id`, `seat`),
  INDEX `id_of_user_idx` (`id_of_user` ASC),
  CONSTRAINT `show_id`
    FOREIGN KEY (`show_id`)
    REFERENCES `cinema_db`.`film-show` (`film_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_of_user`
    FOREIGN KEY (`id_of_user`)
    REFERENCES `cinema_db`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
