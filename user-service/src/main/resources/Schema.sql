DROP TABLE IF EXISTS `User`;
CREATE TABLE Users (
                       `id` SMALLINT AUTO_INCREMENT PRIMARY KEY,
                       `name` VARCHAR(50) NOT NULL UNIQUE,
                       `gender` varchar(50)
);

INSERT INTO `User` (`name`)
VALUES  ('Aliko');