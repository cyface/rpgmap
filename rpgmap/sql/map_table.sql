CREATE TABLE rpgmap.map (
	id  mediumint(8) unsigned NOT NULL auto_increment,
	name varchar(2000) NOT NULL,
	owner_id mediumint(8) unsigned NOT NULL,
  	PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1;