-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 23, 2012 at 06:23 PM
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `assessmentdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `assessment`
--

CREATE TABLE IF NOT EXISTS `assessment` (
  `assesment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `assessor_id` bigint(20) NOT NULL,
  `domain_id` bigint(20) NOT NULL,
  `score` varchar(10) NOT NULL,
  `assesment_date` datetime NOT NULL,
  PRIMARY KEY (`assesment_id`),
  KEY `fk_assessment_user_id` (`user_id`),
  KEY `fk_assessment_assessor_id` (`assessor_id`),
  KEY `fk_assessment_domain_id` (`domain_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `assessment`
--


-- --------------------------------------------------------

--
-- Table structure for table `component_rule`
--

CREATE TABLE IF NOT EXISTS `component_rule` (
  `component_rule_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `component_name` varchar(40) NOT NULL,
  `component_type` varchar(40) NOT NULL,
  PRIMARY KEY (`component_rule_id`),
  UNIQUE KEY `component_name` (`component_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `component_rule`
--

INSERT INTO `component_rule` (`component_rule_id`, `component_name`, `component_type`) VALUES
(1, 'actionButton', 'button');

-- --------------------------------------------------------

--
-- Table structure for table `domain`
--

CREATE TABLE IF NOT EXISTS `domain` (
  `domain_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_user` bigint(20) DEFAULT NULL,
  `modification_user` bigint(20) DEFAULT NULL,
  `domain_name` varchar(50) NOT NULL,
  `wikipedia_link` varchar(150) DEFAULT NULL,
  `is_parent` bit(1) NOT NULL DEFAULT b'0',
  `modification_date` datetime NOT NULL,
  `creation_date` datetime NOT NULL,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`domain_id`),
  KEY `fk_domain_create_user_id` (`creation_user`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `domain`
--

INSERT INTO `domain` (`domain_id`, `creation_user`, `modification_user`, `domain_name`, `wikipedia_link`, `is_parent`, `modification_date`, `creation_date`, `is_active`) VALUES
(1, NULL, NULL, 'Social science', NULL, b'1', '2012-08-23 16:53:56', '2012-08-23 16:53:56', b'1'),
(2, NULL, NULL, 'Geography', NULL, b'0', '2012-08-23 17:17:41', '2012-08-23 16:54:35', b'1'),
(3, NULL, NULL, 'History', NULL, b'0', '2012-08-23 16:54:53', '2012-08-23 16:54:53', b'1'),
(4, NULL, NULL, 'Economics', NULL, b'0', '2012-08-23 16:55:03', '2012-08-23 16:55:03', b'1'),
(5, NULL, NULL, 'Civics', NULL, b'0', '2012-08-23 16:55:21', '2012-08-23 16:55:21', b'1'),
(6, NULL, NULL, 'Law', NULL, b'0', '2012-08-23 16:55:28', '2012-08-23 16:55:28', b'1'),
(7, NULL, NULL, 'India', NULL, b'0', '2012-08-23 16:55:51', '2012-08-23 16:55:51', b'1'),
(8, NULL, NULL, 'Indian history', NULL, b'0', '2012-08-23 16:56:14', '2012-08-23 16:56:14', b'1'),
(9, NULL, NULL, 'Indian economy', NULL, b'0', '2012-08-23 16:56:35', '2012-08-23 16:56:35', b'1'),
(10, NULL, NULL, 'Software testing', NULL, b'1', '2012-08-23 16:56:56', '2012-08-23 16:56:56', b'1'),
(11, NULL, NULL, 'Manual testing', NULL, b'0', '2012-08-23 16:57:17', '2012-08-23 16:57:17', b'1'),
(12, NULL, NULL, 'Automation testing', NULL, b'0', '2012-08-23 16:57:39', '2012-08-23 16:57:39', b'1'),
(13, NULL, NULL, 'Stress testing', NULL, b'0', '2012-08-23 16:58:02', '2012-08-23 16:58:02', b'1'),
(14, NULL, NULL, 'Load testing', NULL, b'0', '2012-08-23 16:58:11', '2012-08-23 16:58:11', b'1'),
(15, NULL, NULL, 'Regression testing', NULL, b'0', '2012-08-23 16:58:38', '2012-08-23 16:58:38', b'1'),
(16, NULL, NULL, 'Sanity testing', NULL, b'0', '2012-08-23 16:58:58', '2012-08-23 16:58:58', b'1'),
(17, NULL, NULL, 'Functional testing', NULL, b'0', '2012-08-23 16:59:29', '2012-08-23 16:59:29', b'1'),
(18, NULL, NULL, 'UI Testing', NULL, b'0', '2012-08-23 16:59:42', '2012-08-23 16:59:42', b'1'),
(19, NULL, NULL, 'Performance testing', NULL, b'0', '2012-08-23 17:00:05', '2012-08-23 17:00:05', b'1'),
(20, NULL, NULL, 'Agile software development', NULL, b'1', '2012-08-23 17:00:22', '2012-08-23 17:00:22', b'1'),
(21, NULL, NULL, 'Requirements analysis', NULL, b'0', '2012-08-23 17:00:47', '2012-08-23 17:00:47', b'1');

-- --------------------------------------------------------

--
-- Table structure for table `domain_mapping`
--

CREATE TABLE IF NOT EXISTS `domain_mapping` (
  `domain_mapping_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `domain_id` bigint(20) NOT NULL,
  `sub_domain_id` bigint(20) NOT NULL,
  `weightage` int(4) NOT NULL,
  `modification_date` datetime NOT NULL,
  `creation_date` datetime NOT NULL,
  PRIMARY KEY (`domain_mapping_id`),
  KEY `fk_domainmapping_domain_d` (`domain_id`),
  KEY `fk_domainmapping_sub_domain_id` (`sub_domain_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `domain_mapping`
--

INSERT INTO `domain_mapping` (`domain_mapping_id`, `domain_id`, `sub_domain_id`, `weightage`, `modification_date`, `creation_date`) VALUES
(1, 1, 2, 10, '2012-08-23 17:17:41', '2012-08-23 16:54:35'),
(2, 1, 3, 30, '2012-08-23 16:54:53', '2012-08-23 16:54:53'),
(3, 1, 4, 20, '2012-08-23 16:55:03', '2012-08-23 16:55:03'),
(4, 1, 5, 20, '2012-08-23 16:55:21', '2012-08-23 16:55:21'),
(5, 1, 6, 20, '2012-08-23 16:55:28', '2012-08-23 16:55:28'),
(6, 2, 7, 40, '2012-08-23 16:55:51', '2012-08-23 16:55:51'),
(7, 3, 8, 50, '2012-08-23 16:56:14', '2012-08-23 16:56:14'),
(8, 4, 9, 50, '2012-08-23 16:56:35', '2012-08-23 16:56:35'),
(9, 10, 11, 20, '2012-08-23 16:57:17', '2012-08-23 16:57:17'),
(10, 10, 12, 20, '2012-08-23 16:57:39', '2012-08-23 16:57:39'),
(11, 10, 13, 20, '2012-08-23 16:58:02', '2012-08-23 16:58:02'),
(12, 10, 14, 20, '2012-08-23 16:58:11', '2012-08-23 16:58:11'),
(13, 10, 15, 10, '2012-08-23 16:58:38', '2012-08-23 16:58:38'),
(14, 10, 16, 10, '2012-08-23 16:58:58', '2012-08-23 16:58:58'),
(15, 11, 17, 40, '2012-08-23 16:59:29', '2012-08-23 16:59:29'),
(16, 11, 18, 60, '2012-08-23 16:59:42', '2012-08-23 16:59:42'),
(17, 12, 19, 50, '2012-08-23 17:00:05', '2012-08-23 17:00:05'),
(18, 20, 21, 30, '2012-08-23 17:00:47', '2012-08-23 17:00:47');

-- --------------------------------------------------------

--
-- Table structure for table `grp`
--

CREATE TABLE IF NOT EXISTS `grp` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(40) NOT NULL,
  `group_description` varchar(40) NOT NULL,
  PRIMARY KEY (`group_id`),
  UNIQUE KEY `group_name` (`group_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `grp`
--

INSERT INTO `grp` (`group_id`, `group_name`, `group_description`) VALUES
(1, 'GROUP_OWNER', ''),
(2, 'GROUP_USER', ''),
(3, 'IS_AUTHENTICATED_ANONYMOUSLY', 'Anyone has access');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(40) NOT NULL,
  `role_description` varchar(40) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`role_id`, `role_name`, `role_description`) VALUES
(1, 'ROLE_ADMIN', ''),
(2, 'ROLE_USER', ''),
(3, 'IS_AUTHENTICATED_ANONYMOUSLY', 'Anyone has access'),
(4, 'ROLE_SUPERUSER', '');

-- --------------------------------------------------------

--
-- Table structure for table `role_component_rule`
--

CREATE TABLE IF NOT EXISTS `role_component_rule` (
  `role_id` bigint(20) NOT NULL,
  `component_rule_id` bigint(20) NOT NULL,
  PRIMARY KEY (`component_rule_id`,`role_id`),
  KEY `FK816FFBE754730D5B` (`component_rule_id`),
  KEY `FK816FFBE7DAA91374` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_component_rule`
--

INSERT INTO `role_component_rule` (`role_id`, `component_rule_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `role_group`
--

CREATE TABLE IF NOT EXISTS `role_group` (
  `group_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`group_id`),
  KEY `FK6C322976DAA91374` (`role_id`),
  KEY `FK6C322976A7ED1F20` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_group`
--

INSERT INTO `role_group` (`group_id`, `role_id`) VALUES
(1, 1),
(1, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `role_url_rule`
--

CREATE TABLE IF NOT EXISTS `role_url_rule` (
  `role_id` bigint(20) NOT NULL,
  `url_rule_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`url_rule_id`),
  KEY `FK89656D75DAA91374` (`role_id`),
  KEY `FK89656D75440A0FBF` (`url_rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role_url_rule`
--

INSERT INTO `role_url_rule` (`role_id`, `url_rule_id`) VALUES
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7);

-- --------------------------------------------------------

--
-- Table structure for table `tb_velocity_template`
--

CREATE TABLE IF NOT EXISTS `tb_velocity_template` (
  `template_key` varchar(40) NOT NULL,
  `template_definition` longtext NOT NULL,
  `template_timestamp` datetime NOT NULL,
  `template_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_velocity_template`
--

INSERT INTO `tb_velocity_template` (`template_key`, `template_definition`, `template_timestamp`, `template_id`) VALUES
('Account Created', '<html><body>hi changed<h3>Hi ${user.userName}, welcome to the Chipping Sodbury On-the-Hill message boards!</h3><div>   Your email address is <a ref="mailto:${user.emailAddress}">${user.emailAddress}</a>.</div></body>\r\n\r\n</html>', '2012-06-27 12:45:47', 1),
('Account Created', '<html>\r\n<body>\r\n<h3>Hi ${user.userName}, Your account created successfully!</h3>\r\n\r\n<div>\r\n   Your email address is <a href="mailto:${user.emailAddress}">${user.emailAddress}</a>.\r\n</div>\r\n</body>\r\n\r\n</html>', '2012-06-27 13:34:30', 2),
('Account Created', 'abc', '2012-06-29 11:57:38', 3);

-- --------------------------------------------------------

--
-- Table structure for table `url_rule`
--

CREATE TABLE IF NOT EXISTS `url_rule` (
  `url_rule_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(40) NOT NULL,
  `method` varchar(40) NOT NULL,
  PRIMARY KEY (`url_rule_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `url_rule`
--

INSERT INTO `url_rule` (`url_rule_id`, `url`, `method`) VALUES
(1, '/login', 'GET'),
(3, '/home', 'GET'),
(4, '/domains', 'GET'),
(5, '/updateDomain', 'POST'),
(6, '/saveDomain', 'POST'),
(7, '/deleteDomain', 'POST');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(30) NOT NULL UNIQUE,
  `password` varchar(50) NOT NULL,
  `referral_name` varchar(30) NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `account_non_expired` bit(1) NOT NULL DEFAULT b'1',
  `account_non_locked` bit(1) NOT NULL DEFAULT b'1',
  `credentials_non_expired` bit(1) NOT NULL DEFAULT b'1',
  `creation_date` datetime NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `password`, `referral_name`, `enabled`, `account_non_expired`, `account_non_locked`, `credentials_non_expired`, `creation_date`) VALUES
(1, 'anuj', '6b2b4943d89010467a198a872e646337618f47b9', '', b'1', b'1', b'1', b'1', '2012-08-01 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `user_group`
--

CREATE TABLE IF NOT EXISTS `user_group` (
  `user_id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL,
  PRIMARY KEY (`group_id`,`user_id`),
  KEY `FK72A9010B52DD32E5` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_group`
--

INSERT INTO `user_group` (`user_id`, `group_id`) VALUES
(1, 1),
(0, 2);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `role_component_rule`
--
ALTER TABLE `role_component_rule`
  ADD CONSTRAINT `FK816FFBE754730D5B` FOREIGN KEY (`component_rule_id`) REFERENCES `component_rule` (`component_rule_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK816FFBE7DAA91374` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `role_group`
--
ALTER TABLE `role_group`
  ADD CONSTRAINT `FK6C322976A7ED1F20` FOREIGN KEY (`group_id`) REFERENCES `grp` (`group_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK6C322976DAA91374` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `role_url_rule`
--
ALTER TABLE `role_url_rule`
  ADD CONSTRAINT `FK89656D75440A0FBF` FOREIGN KEY (`url_rule_id`) REFERENCES `url_rule` (`url_rule_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK89656D75DAA91374` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
