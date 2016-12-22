CREATE SCHEMA `timetracker` DEFAULT CHARACTER SET utf8;

CREATE  TABLE IF NOT EXISTS `timetracker`.`person` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NOT NULL ,
  PRIMARY KEY (`id`) );

CREATE  TABLE IF NOT EXISTS `timetracker`.`timelog` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `person_id` INT NOT NULL,
  `log_description` VARCHAR(200) NULL ,
  `start_datetime` DATETIME NOT NULL ,
  `end_datetime` DATETIME NOT NULL ,
  PRIMARY KEY (`id`) );