-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema lunchApp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema lunchApp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `lunchApp` DEFAULT CHARACTER SET utf8 ;
USE `lunchApp` ;

-- -----------------------------------------------------
-- Table `lunchApp`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lunchApp`.`user` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NULL,
  `last_name` VARCHAR(255) NULL,
  `updated_at` DATETIME NULL,
  `created_at` DATETIME NULL,
  `user_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lunchApp`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lunchApp`.`admin` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `lunchApp`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lunchApp`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lunchApp`.`customer` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `balance` INT(11) NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_user2`
  FOREIGN KEY (`user_id`)
  REFERENCES `lunchApp`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lunchApp`.`vendor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lunchApp`.`vendor` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `vendor_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lunchApp`.`food_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lunchApp`.`food_item` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `price` INT(11) NOT NULL,
  `status` SMALLINT(6) NULL,
  `vendor_id` BIGINT(20) UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_vendor_idx` (`vendor_id` ASC),
  CONSTRAINT `fk_vendor`
  FOREIGN KEY (`vendor_id`)
  REFERENCES `lunchApp`.`vendor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lunchApp`.`office_boy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lunchApp`.`office_boy` (
  `user_id` BIGINT(20) UNSIGNED NOT NULL,
  `created_by` BIGINT(20) UNSIGNED NULL,
  PRIMARY KEY (`user_id`),
  INDEX `fk_admin_idx` (`created_by` ASC),
  CONSTRAINT `fk_user3`
  FOREIGN KEY (`user_id`)
  REFERENCES `lunchApp`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_admin`
  FOREIGN KEY (`created_by`)
  REFERENCES `lunchApp`.`admin` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lunchApp`.`order_status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lunchApp`.`order_status` (
  `id` SMALLINT(6) UNSIGNED NOT NULL,
  `title` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lunchApp`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lunchApp`.`orders` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `ordered_by` BIGINT(20) UNSIGNED NULL,
  `ordered_for` BIGINT(20) UNSIGNED NULL,
  `assigned_to` BIGINT(20) UNSIGNED NULL,
  `total_price` INT(11) NULL,
  `order_status` SMALLINT(6) UNSIGNED NULL,
  `order_time` DATETIME NULL,
  `delivery_time` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_employee_idx` (`ordered_by` ASC),
  INDEX `fk_office_boy_idx` (`assigned_to` ASC),
  INDEX `fk_order_status_idx` (`order_status` ASC),
  INDEX `fk_employee2_idx` (`ordered_for` ASC),
  CONSTRAINT `fk_employee`
  FOREIGN KEY (`ordered_by`)
  REFERENCES `lunchApp`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_office_boy`
  FOREIGN KEY (`assigned_to`)
  REFERENCES `lunchApp`.`office_boy` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_status`
  FOREIGN KEY (`order_status`)
  REFERENCES `lunchApp`.`order_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_employee2`
  FOREIGN KEY (`ordered_for`)
  REFERENCES `lunchApp`.`customer` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lunchApp`.`order_item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `lunchApp`.`order_item` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `item_id` BIGINT(20) UNSIGNED NOT NULL,
  `quantity` INT(11) NOT NULL,
  `order_id` BIGINT(20) UNSIGNED NOT NULL,
  INDEX `fk_order_idx` (`order_id` ASC),
  INDEX `fk_food_item_idx` (`item_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_order`
  FOREIGN KEY (`order_id`)
  REFERENCES `lunchApp`.`orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_food_item`
  FOREIGN KEY (`item_id`)
  REFERENCES `lunchApp`.`food_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
