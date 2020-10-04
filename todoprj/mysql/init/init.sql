DROP TABLE IF EXISTS `characters`;

CREATE TABLE `characters`
(
    `id`         INT (10) unsigned NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL,
    `special_attack`   VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;
