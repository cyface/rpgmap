USE rpgmap;

DROP TABLE IF EXISTS attachment;

CREATE TABLE `attachment` (
	`id` MEDIUMINT( 8 ) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
	`name` VARCHAR( 100 ) NOT NULL ,
	`parent_user_id` MEDIUMINT( 8 ),
	`parent_map_id` MEDIUMINT( 8 ),
	`parent_overlay_id` MEDIUMINT( 8 ),
	`attachment` TEXT NOT NULL ,
	`last_updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
	INDEX ( `parent_user_id` ),
	INDEX ( `parent_map_id` ),
	INDEX ( `parent_overlay_id` )
) ENGINE = MYISAM ;