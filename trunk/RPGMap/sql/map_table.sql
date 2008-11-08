use rpgmap;

DROP TABLE IF EXISTS map;

CREATE TABLE map (
	id  mediumint(8) unsigned NOT NULL auto_increment,
	name varchar(2000) NOT NULL,
	owner_id mediumint(8) unsigned NOT NULL,
	`last_updated` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  	PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;