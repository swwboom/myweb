/*
Navicat MySQL Data Transfer

Source Server         : 工作站-通用web服务
Source Server Version : 50635
Source Host           : 192.168.1.249:3306
Source Database       : uws_dev

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-09-21 16:21:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `id` int(11) NOT NULL,
  `app_name` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL,
  `author` varchar(20) DEFAULT NULL COMMENT '发布人',
  `tiele` varchar(60) NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COMMENT '正文',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` tinyint(255) NOT NULL COMMENT '状态，0待传图片/视频，1正常，2用户删除，3违规删除，4，隐藏(用于我行我秀)  5我行我秀转发到朋友圈，6系统怀疑色情需人工review，7系统识别色情',
  `file_type` tinyint(255) NOT NULL COMMENT '1纯文字,2含视频,3,含图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for article_files
-- ----------------------------
DROP TABLE IF EXISTS `article_files`;
CREATE TABLE `article_files` (
  `id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `file_name` varchar(150) NOT NULL,
  `file_type` tinyint(255) NOT NULL,
  `file_bucket` varchar(50) NOT NULL,
  `sort_num` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_fk` (`article_id`),
  CONSTRAINT `id_fk` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `banner_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for banner_node
-- ----------------------------
DROP TABLE IF EXISTS `banner_node`;
CREATE TABLE `banner_node` (
  `id` int(11) NOT NULL,
  `img_url` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `jump_url` varchar(255) DEFAULT NULL,
  `banner_id` int(11) NOT NULL,
  `status` tinyint(255) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `typefk` (`banner_id`),
  CONSTRAINT `typefk` FOREIGN KEY (`banner_id`) REFERENCES `banner` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for modules
-- ----------------------------
DROP TABLE IF EXISTS `modules`;
CREATE TABLE `modules` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `app_id` int(4) NOT NULL,
  `type` int(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type_fk` (`type`),
  KEY `app_fk` (`app_id`),
  CONSTRAINT `app_fk` FOREIGN KEY (`app_id`) REFERENCES `app_info` (`id`),
  CONSTRAINT `type_fk` FOREIGN KEY (`type`) REFERENCES `modules_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for modules_article
-- ----------------------------
DROP TABLE IF EXISTS `modules_article`;
CREATE TABLE `modules_article` (
  `id` int(11) NOT NULL,
  `article_id` int(11) NOT NULL,
  `modules_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `article_fk` (`article_id`),
  KEY `modules_fk` (`modules_id`),
  CONSTRAINT `article_fk` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`),
  CONSTRAINT `modules_fk` FOREIGN KEY (`modules_id`) REFERENCES `modules` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for modules_banner
-- ----------------------------
DROP TABLE IF EXISTS `modules_banner`;
CREATE TABLE `modules_banner` (
  `id` int(11) NOT NULL,
  `modules_id` int(11) NOT NULL,
  `banner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `mod_fk` (`modules_id`),
  KEY `banner_fk` (`banner_id`),
  CONSTRAINT `banner_fk` FOREIGN KEY (`banner_id`) REFERENCES `banner` (`id`),
  CONSTRAINT `mod_fk` FOREIGN KEY (`modules_id`) REFERENCES `modules` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for modules_list
-- ----------------------------
DROP TABLE IF EXISTS `modules_list`;
CREATE TABLE `modules_list` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `modules_id` int(11) NOT NULL,
  `sort_num` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `modu_fk` (`modules_id`),
  CONSTRAINT `modu_fk` FOREIGN KEY (`modules_id`) REFERENCES `modules` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for modules_list_article
-- ----------------------------
DROP TABLE IF EXISTS `modules_list_article`;
CREATE TABLE `modules_list_article` (
  `id` int(11) NOT NULL,
  `modules_list_id` int(11) NOT NULL,
  `modules_list_article` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `list_fk` (`modules_list_id`),
  KEY `art_fk` (`modules_list_article`),
  CONSTRAINT `art_fk` FOREIGN KEY (`modules_list_article`) REFERENCES `article` (`id`),
  CONSTRAINT `list_fk` FOREIGN KEY (`modules_list_id`) REFERENCES `modules_list` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for modules_type
-- ----------------------------
DROP TABLE IF EXISTS `modules_type`;
CREATE TABLE `modules_type` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `describe` varchar(255) NOT NULL,
  `is_banner` tinyint(255) NOT NULL,
  `is_article` tinyint(255) NOT NULL,
  `is_list` tinyint(255) NOT NULL,
  `html_tpl` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
