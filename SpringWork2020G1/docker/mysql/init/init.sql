1 DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `projects`;
DROP TABLE IF EXISTS `tasks`;
DROP TABLE IF EXISTS `participates`;
DROP TABLE IF EXISTS `handles`;
SET CHARACTER_SET_CLIENT = utf8;
SET CHARACTER_SET_CONNECTION = utf8;
CREATE TABLE `users` (
  `id` VARCHAR(16) PRIMARY KEY NOT NULL PRIMARY KEY,
  `name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
CREATE TABLE `projects` (
  `id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(30) NOT NULL,
  `overview` VARCHAR(300) NOT NULL,
  `host_id` VARCHAR(16) NOT NULL,
  `deadline` datetime NOT NULL,
  `status` INT(1) NOT NULL,
  `is_delayed` boolean NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
CREATE TABLE `tasks` (
  `id` INT(10) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `prj_id` INT(10) NOT NULL,
  `title` VARCHAR(30) NOT NULL,
  `overview` VARCHAR(400) NOT NULL,
  `deadline` datetime NOT NULL,
  `status` INT(1) NOT NULL,
  `is_delayed` boolean NOT NULL DEFAULT false
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
CREATE TABLE `participates` (
  `user_id` VARCHAR(16) NOT NULL,
  `prj_id` INT(10) NOT NULL,
  `is_prj_own` boolean NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
CREATE TABLE `handles` (
  `task_id` VARCHAR(10) NOT NULL,
  `prj_id` INT(10) NOT NULL,
  `user_id` VARCHAR(16) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;