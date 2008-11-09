use rpgmap;

DROP TABLE IF EXISTS `map`;

CREATE TABLE `map` (
	`id`  mediumint(8) unsigned NOT NULL auto_increment,
	`name` varchar(2000) NOT NULL,
	`parent_user_id` mediumint(8) unsigned NOT NULL,
	`public` bit NOT NULL DEFAULT true,
	`last_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  	PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;