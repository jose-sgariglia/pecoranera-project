-- MySQL Script generated by MySQL Workbench
-- ven 5 mag 2023, 22:24:46
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pecoranera_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pecoranera_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pecoranera_db` ;
USE `pecoranera_db` ;

-- -----------------------------------------------------
-- Table `pecoranera_db`.`artist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`artist` (
  `id_artist` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id_artist`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`user` (
  `id_user` INT NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `role` ENUM('user', 'admin') NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`cart` (
  `id_cart` INT NOT NULL,
  `id_user` INT NOT NULL,
  PRIMARY KEY (`id_cart`),
  INDEX `fk_cart_1_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_cart_1`
    FOREIGN KEY (`id_user`)
    REFERENCES `pecoranera_db`.`user` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`event`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`event` (
  `id_event` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` MEDIUMTEXT NULL DEFAULT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `available_tickets` INT UNSIGNED NOT NULL,
  `max_tickets` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id_event`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`carts_events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`carts_events` (
  `id_cart` INT NOT NULL,
  `id_event` INT NOT NULL,
  PRIMARY KEY (`id_cart`, `id_event`),
  INDEX `fk_carts_events_2` (`id_event` ASC) VISIBLE,
  CONSTRAINT `fk_carts_events_1`
    FOREIGN KEY (`id_cart`)
    REFERENCES `pecoranera_db`.`cart` (`id_cart`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_carts_events_2`
    FOREIGN KEY (`id_event`)
    REFERENCES `pecoranera_db`.`event` (`id_event`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`events_artists`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`events_artists` (
  `id_event` INT NOT NULL,
  `id_artist` INT NOT NULL,
  `role` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_event`, `id_artist`),
  INDEX `fk_events_artists_2` (`id_artist` ASC) VISIBLE,
  CONSTRAINT `fk_events_artists_1`
    FOREIGN KEY (`id_event`)
    REFERENCES `pecoranera_db`.`event` (`id_event`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_events_artists_2`
    FOREIGN KEY (`id_artist`)
    REFERENCES `pecoranera_db`.`artist` (`id_artist`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`tag` (
  `id_tag` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_tag`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`events_tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`events_tags` (
  `id_event` INT NOT NULL,
  `id_tag` INT NOT NULL,
  PRIMARY KEY (`id_event`, `id_tag`),
  INDEX `fk_events_tags_2` (`id_tag` ASC) VISIBLE,
  CONSTRAINT `fk_events_tags_1`
    FOREIGN KEY (`id_event`)
    REFERENCES `pecoranera_db`.`event` (`id_event`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_events_tags_2`
    FOREIGN KEY (`id_tag`)
    REFERENCES `pecoranera_db`.`tag` (`id_tag`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`order` (
  `id_order` INT NOT NULL,
  `tickets` INT UNSIGNED NOT NULL,
  `price` DECIMAL(5,2) UNSIGNED NOT NULL,
  `date` DATETIME NOT NULL,
  `user` INT NOT NULL,
  `event` INT NOT NULL,
  PRIMARY KEY (`id_order`),
  INDEX `fk_order_1` (`user` ASC) VISIBLE,
  INDEX `fk_order_2` (`event` ASC) VISIBLE,
  CONSTRAINT `fk_order_1`
    FOREIGN KEY (`user`)
    REFERENCES `pecoranera_db`.`user` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_2`
    FOREIGN KEY (`event`)
    REFERENCES `pecoranera_db`.`event` (`id_event`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`product_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`product_type` (
  `id_type` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_type`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`product` (
  `id_product` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `description` MEDIUMTEXT NOT NULL,
  `price` DECIMAL(5,2) NOT NULL,
  `type` INT NOT NULL,
  PRIMARY KEY (`id_product`),
  INDEX `fk_product_1_idx` (`type` ASC) VISIBLE,
  CONSTRAINT `fk_product_1`
    FOREIGN KEY (`type`)
    REFERENCES `pecoranera_db`.`product_type` (`id_type`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `pecoranera_db`.`users_tags`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pecoranera_db`.`users_tags` (
  `id_user` INT NOT NULL,
  `id_tag` INT NOT NULL,
  PRIMARY KEY (`id_user`, `id_tag`),
  INDEX `fk_users_tags_2` (`id_tag` ASC) VISIBLE,
  CONSTRAINT `fk_users_tags_1`
    FOREIGN KEY (`id_user`)
    REFERENCES `pecoranera_db`.`user` (`id_user`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_users_tags_2`
    FOREIGN KEY (`id_tag`)
    REFERENCES `pecoranera_db`.`tag` (`id_tag`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;