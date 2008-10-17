USE rpgmap;

DROP TABLE IF EXISTS point;

CREATE TABLE `point` (
	`id` mediumint(8) unsigned NOT NULL auto_increment,
	`parent_map_id` mediumint(8) unsigned NOT NULL,
	`latitude` double NOT NULL,
	`longitude` double NOT NULL,
	`name` varchar(2000) NOT NULL,
	PRIMARY KEY  (`id`),
	INDEX (`parent_map_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;