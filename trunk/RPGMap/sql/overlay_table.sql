USE rpgmap;

DROP TABLE IF EXISTS overlay;

CREATE TABLE `overlay` (
	`id` mediumint(8) unsigned NOT NULL auto_increment,
	`parent_map_id` mediumint(8) unsigned NOT NULL,
	`latitude` double NOT NULL,
	`longitude` double NOT NULL,
	`name` varchar(2000) NOT NULL,
	`type` varchar(40) NOT NULL,
	`last_updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
	PRIMARY KEY  (`id`),
	INDEX (`parent_map_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;