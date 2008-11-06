USE rpgmap;

DROP TABLE IF EXISTS user;

CREATE TABLE `user` (
	`id` MEDIUMINT( 8 ) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	`username` VARCHAR( 50 ) NOT NULL ,
	`name` VARCHAR( 200 ) NOT NULL ,
	`password` VARCHAR( 60 ) NOT NULL ,
	`enabled` BIT NOT NULL default true,
	`last_updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
	INDEX ( `username` )
) ENGINE = MYISAM ;