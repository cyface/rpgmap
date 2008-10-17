-- phpMyAdmin SQL Dump
-- version 2.9.1.1
-- http://www.phpmyadmin.net
-- 
-- Host: localhost
-- Generation Time: Oct 13, 2008 at 04:27 PM
-- Server version: 5.0.27
-- PHP Version: 5.2.0
-- 
-- Database: `rpgmap`
-- 

-- --------------------------------------------------------

-- 
-- Table structure for table `point`
-- 

CREATE TABLE `point` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `parent_map_id` mediumint(8) unsigned NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `name` varchar(2000) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;