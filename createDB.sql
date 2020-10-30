-- MySQL Workbench Forward Engineering
drop schema if exists cinema;
create schema cinema;

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cinema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cinema` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `cinema` ;

-- -----------------------------------------------------
-- Table `cinema`.`films`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`films` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `image_path` VARCHAR(255) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema`.`sessions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`sessions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `seats_counter` INT NOT NULL DEFAULT '0',
  `start_time` TIME NOT NULL,
  `day_id` BIGINT NOT NULL,
  `film_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKn2m0d43s7i2gofapl0d8qkvq7` (`film_id` ASC) VISIBLE,
  CONSTRAINT `FKn2m0d43s7i2gofapl0d8qkvq7`
    FOREIGN KEY (`film_id`)
    REFERENCES `cinema`.`films` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `active` TINYINT(1) NOT NULL DEFAULT '1',
  `password` VARCHAR(255) NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `UK_r43af9ap4edm43mmtq01oddj6` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `cinema`.`seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinema`.`seats` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `number` INT NULL DEFAULT NULL,
  `session_id` BIGINT NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK33dx7gosckagw9mggkjb7ljgy` (`session_id` ASC) VISIBLE,
  INDEX `FK6k1o4fm1a3ipwguktr45y5wi4` (`user_id` ASC) VISIBLE,
  CONSTRAINT `FK33dx7gosckagw9mggkjb7ljgy`
    FOREIGN KEY (`session_id`)
    REFERENCES `cinema`.`sessions` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK6k1o4fm1a3ipwguktr45y5wi4`
    FOREIGN KEY (`user_id`)
    REFERENCES `cinema`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

insert into users (active, password, username, role) values (true, '1', 'vova', 'USER');
insert into users (active, password, username, role) values (true, '1', 'admin', 'ADMIN');
INSERT INTO films (title, description, image_path) VALUES ('The Lord of the Rings', 'Cool film', '/images/TLOTR.jpg');
INSERT INTO films (title, description, image_path) VALUES ('Space Odyssey', '2001', '/images/space_odyssey.jpg');
INSERT INTO sessions (start_time, film_id, day_id, seats_counter) VALUES ('9:00', 1, 1, 0);
INSERT INTO sessions (start_time, film_id, day_id, seats_counter) VALUES ('12:00', 2, 1, 0);
INSERT INTO sessions (start_time, film_id, day_id, seats_counter) VALUES ('15:00', 1, 1, 0);
INSERT INTO sessions (start_time, film_id, day_id, seats_counter) VALUES ('18:00', 2, 1, 0);
