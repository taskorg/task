/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50513
Source Host           : 127.0.0.1:3306
Source Database       : task

Target Server Type    : MYSQL
Target Server Version : 50513
File Encoding         : 65001

Date: 2013-09-28 22:39:45
*/
CREATE DATABASE IF NOT EXISTS task default charset utf8 COLLATE utf8_general_ci;

use task;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `task_filter`
-- ----------------------------
DROP TABLE IF EXISTS `task_filter`;
CREATE TABLE `task_filter` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) DEFAULT NULL,
  `filter_name` varchar(50) NOT NULL,
  `filter` varchar(50) NOT NULL,
  `default_params` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `task_item`
-- ----------------------------
DROP TABLE IF EXISTS `task_item`;
CREATE TABLE `task_item` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type_id` int(10) NOT NULL,
  `type_name` varchar(50) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `tag` varchar(1000) DEFAULT NULL,
  `pic` varchar(1000) NOT NULL COMMENT '任务插图',
  `website` varchar(100) DEFAULT NULL COMMENT '网站名称',
  `website_introduction` varchar(1000) DEFAULT NULL COMMENT '网站简介',
  `description` varchar(2000) NOT NULL COMMENT '任务描述',
  `requires` varchar(1000) DEFAULT NULL COMMENT '任务限制条件',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `logo` varchar(100) DEFAULT NULL COMMENT '任务主图',
  `controller` varchar(100) DEFAULT NULL,
  `template` varchar(100) NOT NULL,
  `award` varchar(100) NOT NULL COMMENT '奖励范围',
  `partake_count` int(10) NOT NULL DEFAULT '0' COMMENT '参加人数',
  `receive_data_end_date` date NOT NULL COMMENT '接收数据的结束时间',
  `mc_account_id` int(10) NOT NULL,
  `max_cost` bigint(15) NOT NULL DEFAULT '0' COMMENT '该任务最大消费',
  `max_cost_perday` bigint(15) NOT NULL DEFAULT '0' COMMENT '每天最大消耗',
  `max_partake_count` int(10) NOT NULL DEFAULT '0' COMMENT '该任务最多参与人数',
  `status` varchar(20) DEFAULT '',
  `merchant_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert into `task_item`(`id`,`name`,`type_id`,`type_name`,`begin_date`,`end_date`,`tag`,`pic`,`website`,`website_introduction`,`description`,`requires`,`create_time`,`logo`,`controller`,`template`,`award`,`partake_count`,`receive_data_end_date`,`mc_account_id`,`max_cost`,`max_cost_perday`,`max_partake_count`,`status`,`merchant_id`) values ( '1','测试活动','1','测试类型','2014-04-01','2014-05-01',NULL,'http://img13.360buyimg.com/n7/g9/M03/0C/13/rBEHalCHXi8IAAAAAAJLSPja0MYAACddABOcJkAAktg929.jpg',NULL,NULL,'测试简介',NULL,CURRENT_TIMESTAMP,'http://img13.360buyimg.com/n7/g9/M03/0C/13/rBEHalCHXi8IAAAAAAJLSPja0MYAACddABOcJkAAktg929.jpg',NULL,'测试模板','1-10000','0','2014-09-01','1','0','0','0','',1);
-- ----------------------------
-- Table structure for `task_member_bind`
-- ----------------------------
DROP TABLE IF EXISTS `task_member_bind`;
CREATE TABLE `task_member_bind` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `member_id` int(10) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `task_id` int(10) NOT NULL,
  `task_name` varchar(50) NOT NULL,
  `task_member_id` varchar(50) DEFAULT NULL,
  `task_member_name` varchar(50) NOT NULL,
  `data_json` varchar(1000) DEFAULT NULL,
  `awards` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `task_member_data_tmp`
-- ----------------------------
DROP TABLE IF EXISTS `task_member_data_tmp`;
CREATE TABLE `task_member_data_tmp` (
  `id` int(10) NOT NULL,
  `task_id` int(10) NOT NULL,
  `member_id` int(10) NOT NULL,
  `task_member_id` varchar(50) DEFAULT NULL,
  `task_member_name` varchar(50) DEFAULT NULL,
  `data_json` varchar(1000) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `task_rule`
-- ----------------------------
DROP TABLE IF EXISTS `task_rule`;
CREATE TABLE `task_rule` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL,
  `key` varchar(10) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  `template` varchar(255) NOT NULL DEFAULT '1' COMMENT '1有效,0无效',
  `award_executor` varchar(20) NOT NULL,
  `ext1` varchar(50) DEFAULT NULL,
  `ext2` varchar(50) DEFAULT NULL,
  `ext3` varchar(50) DEFAULT NULL,
  `ext4` varchar(50) DEFAULT NULL,
  `ext5` varchar(50) DEFAULT NULL,
  `ext6` varchar(500) DEFAULT NULL,
  `ext7` varchar(500) DEFAULT NULL,
  `ext8` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `task_rule_random`
-- ----------------------------
DROP TABLE IF EXISTS `task_rule_random`;
CREATE TABLE `task_rule_random` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL,
  `rule_id` int(10) NOT NULL,
  `begin` int(15) NOT NULL DEFAULT '0',
  `end` int(15) NOT NULL DEFAULT '0',
  `step` int(15) NOT NULL DEFAULT '1',
  `member_wealth` varchar(100) NOT NULL DEFAULT '',
  `merchant_wealth` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `task_rule_step`
-- ----------------------------
DROP TABLE IF EXISTS `task_rule_step`;
CREATE TABLE `task_rule_step` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `task_id` int(10) NOT NULL,
  `rule_id` int(10) NOT NULL,
  `begin` int(15) NOT NULL DEFAULT '0',
  `end` int(15) NOT NULL DEFAULT '0',
  `step` int(15) NOT NULL DEFAULT '1',
  `wealth` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `task_type`
-- ----------------------------
DROP TABLE IF EXISTS `task_type`;
CREATE TABLE `task_type` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

insert into `task_type`(`id`,`name`) values ( '1','测试分类');