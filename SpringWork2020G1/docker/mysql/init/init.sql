-- DROP TABLE IF EXISTS `characters`;

-- CREATE TABLE `characters`
-- (
--     `id`         INT (10) unsigned NOT NULL AUTO_INCREMENT,
--     `name`       VARCHAR(255) NOT NULL,
--     `special_attack`   VARCHAR(255) NOT NULL,
--     PRIMARY KEY (`id`)
-- ) ENGINE = InnoDB
--   DEFAULT CHARSET = utf8
--   COLLATE = utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS `users` (
  `id` VARCHAR(16) NOT NULL,
  `name` VARCHAR(20) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
