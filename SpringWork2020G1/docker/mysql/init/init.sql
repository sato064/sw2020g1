DROP DATABASE IF EXISTS mst_todo;
CREATE DATABASE mst_todo;
USE mst_todo;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` VARCHAR(16) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE (`id_UNIQUE`)
)

DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30) NOT NULL,
  `overview` VARCHAR(300) NOT NULL,
  `host_id` VARCHAR(16) NOT NULL,
  `deadline` datetime NOT NULL,
  `status` INT(1) NOT NULL,
  `is_delayed` boolean NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`host_id`)
  FOREIGN KEY(`host_id`)
    REFERENCES `users`(`id`)
)

DROP TABLE IF EXISTS `tasks`;
CREATE TABLE `tasks` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `prj_id` INT(10) NOT NULL,
  `title` VARCHAR(30) NOT NULL,
  `overview` VARCHAR(400) NOT NULL,
  `deadline` datetime NOT NULL,
  `status` INT(1) NOT NULL,
  `is_delayed` boolean NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`prj_id`)
  FOREIGN KEY(`prj_id`)
    REFERENCES `projects`(`id`)
)

DROP TABLE IF EXISTS `participates`;
CREATE TABLE `participates` (
  `user_id` VARCHAR(16) NOT NULL,
  `prj_id` INT(10) NOT NULL,
  `is_prj_own` boolean NOT NULL
)

DROP TABLE IF EXISTS `handles`;
CREATE TABLE `handles` (
  `task_id` VARCHAR(10) NOT NULL,
  `prj_id` INT(10) NOT NULL,
  `user_id` VARCHAR(16) NOT NULL,
)

  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
