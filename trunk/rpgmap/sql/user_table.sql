USE rpgmap;

DROP TABLE IF EXISTS user;

CREATE TABLE `user` (
	`id` MEDIUMINT( 8 ) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	`user_id` VARCHAR( 200 ) NOT NULL ,
	`name` VARCHAR( 200 ) NOT NULL ,
	`password` VARCHAR( 200 ) NOT NULL ,
	`last_updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
	INDEX ( `user_id` )
) ENGINE = MYISAM ;