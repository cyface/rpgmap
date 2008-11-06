USE rpgmap;

DROP TABLE IF EXISTS user;

CREATE TABLE `user` (
	`id` MEDIUMINT( 8 ) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	`username` VARCHAR( 50 ) NOT NULL ,
	`name` VARCHAR( 200 ) NOT NULL ,
	`password` VARCHAR( 50 ) NOT NULL ,
	`last_updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
	INDEX ( `user_id` )
) ENGINE = MYISAM ;