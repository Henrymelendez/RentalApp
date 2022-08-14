-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema rentaldb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema rentaldb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `rentaldb` DEFAULT CHARACTER SET utf8 ;
USE `rentaldb` ;

-- -----------------------------------------------------
-- Table `rentaldb`.`property_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentaldb`.`property_type` ;

CREATE TABLE IF NOT EXISTS `rentaldb`.`property_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentaldb`.`address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentaldb`.`address` ;

CREATE TABLE IF NOT EXISTS `rentaldb`.`address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NULL,
  `street2` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentaldb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentaldb`.`user` ;

CREATE TABLE IF NOT EXISTS `rentaldb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `rentaldb`.`property`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentaldb`.`property` ;

CREATE TABLE IF NOT EXISTS `rentaldb`.`property` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `purchase_date` DATE NULL,
  `purchase_amount` DOUBLE NULL,
  `notes` LONGTEXT NULL,
  `property_type_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_property_property_type1`
    FOREIGN KEY (`property_type_id`)
    REFERENCES `rentaldb`.`property_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_property_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `rentaldb`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_property_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `rentaldb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_property_property_type1_idx` ON `rentaldb`.`property` (`property_type_id` ASC);

CREATE INDEX `fk_property_address1_idx` ON `rentaldb`.`property` (`address_id` ASC);

CREATE INDEX `fk_property_user1_idx` ON `rentaldb`.`property` (`user_id` ASC);


-- -----------------------------------------------------
-- Table `rentaldb`.`tenant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentaldb`.`tenant` ;

CREATE TABLE IF NOT EXISTS `rentaldb`.`tenant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `registration_date` DATE NOT NULL,
  `tenant_name` VARCHAR(45) NOT NULL,
  `phone_number` LONGTEXT NULL,
  `email` VARCHAR(45) NULL,
  `properties_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tenant_properties`
    FOREIGN KEY (`properties_id`)
    REFERENCES `rentaldb`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_tenant_properties_idx` ON `rentaldb`.`tenant` (`properties_id` ASC);


-- -----------------------------------------------------
-- Table `rentaldb`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentaldb`.`payment` ;

CREATE TABLE IF NOT EXISTS `rentaldb`.`payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE NOT NULL,
  `mode_of_payment` VARCHAR(45) NULL,
  `payment_notes` VARCHAR(45) NULL,
  `properties_id` INT NOT NULL,
  `tenant_id` INT NOT NULL,
  `payment_date` DATE NULL,
  PRIMARY KEY (`id`, `tenant_id`),
  CONSTRAINT `fk_income_properties1`
    FOREIGN KEY (`properties_id`)
    REFERENCES `rentaldb`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_tenant1`
    FOREIGN KEY (`tenant_id`)
    REFERENCES `rentaldb`.`tenant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_income_properties1_idx` ON `rentaldb`.`payment` (`properties_id` ASC);

CREATE INDEX `fk_payment_tenant1_idx` ON `rentaldb`.`payment` (`tenant_id` ASC);


-- -----------------------------------------------------
-- Table `rentaldb`.`maintenance`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentaldb`.`maintenance` ;

CREATE TABLE IF NOT EXISTS `rentaldb`.`maintenance` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` LONGTEXT NULL,
  `completed` TINYINT NULL,
  `properties_id` INT NOT NULL,
  `tenant_id` INT NOT NULL,
  PRIMARY KEY (`id`, `tenant_id`),
  CONSTRAINT `fk_maintenance_properties1`
    FOREIGN KEY (`properties_id`)
    REFERENCES `rentaldb`.`property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_maintenance_tenant1`
    FOREIGN KEY (`tenant_id`)
    REFERENCES `rentaldb`.`tenant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_maintenance_properties1_idx` ON `rentaldb`.`maintenance` (`properties_id` ASC);

CREATE INDEX `fk_maintenance_tenant1_idx` ON `rentaldb`.`maintenance` (`tenant_id` ASC);


-- -----------------------------------------------------
-- Table `rentaldb`.`contractor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentaldb`.`contractor` ;

CREATE TABLE IF NOT EXISTS `rentaldb`.`contractor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `contractor_name` VARCHAR(45) NULL,
  `contact_address` VARCHAR(45) NULL,
  `maintenance_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_contractor_maintenance1`
    FOREIGN KEY (`maintenance_id`)
    REFERENCES `rentaldb`.`maintenance` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_contractor_maintenance1_idx` ON `rentaldb`.`contractor` (`maintenance_id` ASC);


-- -----------------------------------------------------
-- Table `rentaldb`.`lease`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentaldb`.`lease` ;

CREATE TABLE IF NOT EXISTS `rentaldb`.`lease` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_date` DATETIME NULL,
  `end_date` DATETIME NULL,
  `description` LONGTEXT NULL,
  `rental_amount` DOUBLE NOT NULL,
  `security_deposit` DOUBLE NULL,
  `pet_deposit` DOUBLE NULL,
  `address_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lease_address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `rentaldb`.`address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_lease_address1_idx` ON `rentaldb`.`lease` (`address_id` ASC);


-- -----------------------------------------------------
-- Data for table `rentaldb`.`property_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaldb`;
INSERT INTO `rentaldb`.`property_type` (`id`, `name`, `description`) VALUES (1, 'condo', 'condo');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rentaldb`.`address`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaldb`;
INSERT INTO `rentaldb`.`address` (`id`, `street`, `street2`, `city`, `state`, `zip`) VALUES (1, '1 Agawam Street', NULL, 'revere', 'MA', '02151');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rentaldb`.`user`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaldb`;
INSERT INTO `rentaldb`.`user` (`id`, `user_name`, `password`, `enabled`, `role`) VALUES (1, 'admin', 'admin', 1, 'admin');

COMMIT;


-- -----------------------------------------------------
-- Data for table `rentaldb`.`property`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaldb`;
INSERT INTO `rentaldb`.`property` (`id`, `purchase_date`, `purchase_amount`, `notes`, `property_type_id`, `address_id`, `user_id`) VALUES (1, '2022-03-22', 200000, NULL, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rentaldb`.`tenant`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaldb`;
INSERT INTO `rentaldb`.`tenant` (`id`, `registration_date`, `tenant_name`, `phone_number`, `email`, `properties_id`) VALUES (1, '2019-04-03', 'John', '7815584394', 'henryomarm@gmail.com', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rentaldb`.`payment`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaldb`;
INSERT INTO `rentaldb`.`payment` (`id`, `amount`, `mode_of_payment`, `payment_notes`, `properties_id`, `tenant_id`, `payment_date`) VALUES (1, 1500, 'cash', NULL, 1, 1, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rentaldb`.`maintenance`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaldb`;
INSERT INTO `rentaldb`.`maintenance` (`id`, `description`, `completed`, `properties_id`, `tenant_id`) VALUES (1, 'fix water heater', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rentaldb`.`contractor`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaldb`;
INSERT INTO `rentaldb`.`contractor` (`id`, `contractor_name`, `contact_address`, `maintenance_id`) VALUES (1, 'john heater service', NULL, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `rentaldb`.`lease`
-- -----------------------------------------------------
START TRANSACTION;
USE `rentaldb`;
INSERT INTO `rentaldb`.`lease` (`id`, `start_date`, `end_date`, `description`, `rental_amount`, `security_deposit`, `pet_deposit`, `address_id`) VALUES (1, '2022-03-01', '2022-10-01', NULL, 1500, 1500, 0, 1);

COMMIT;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
