USE rpgmap;

DROP TABLE IF EXISTS attachment;

CREATE TABLE `attachment` (
	`id` MEDIUMINT( 8 ) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	`name` VARCHAR( 100 ) NOT NULL ,
	`parent_object_id` MEDIUMINT( 8 ) NOT NULL ,
	`attachment` TEXT NOT NULL ,
	INDEX ( `parent_object_id` )
) ENGINE = MYISAM ;