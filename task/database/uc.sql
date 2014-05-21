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
CREATE DATABASE IF NOT EXISTS uc default charset utf8 COLLATE utf8_general_ci;

use uc;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `uc_account`
-- ----------------------------
DROP TABLE IF EXISTS `uc_account`;
CREATE TABLE `uc_account` (
  `id` varchar(10) NOT NULL,
  `member_id` varchar(10) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `first` int(15) NOT NULL DEFAULT '0',
  `second` int(15) NOT NULL DEFAULT '0',
  `third` int(15) NOT NULL DEFAULT '0',
  `fourth` int(15) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_account
-- ----------------------------
INSERT INTO `uc_account` VALUES ('1', '100', '张三', '1040121', '0', '0', '0');

-- ----------------------------
-- Table structure for `uc_account_log`
-- ----------------------------
DROP TABLE IF EXISTS `uc_account_log`;
CREATE TABLE `uc_account_log` (
  `id` int(10) NOT NULL,
  `member_id` int(10) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `wealth_type` varchar(20) NOT NULL COMMENT '消费类型',
  `account` varchar(20) NOT NULL COMMENT '消费账户',
  `wealth` bigint(15) NOT NULL DEFAULT '0',
  `wealth_balance` bigint(15) NOT NULL,
  `serial_number` varchar(20) NOT NULL COMMENT '流水号',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `uc_account_log_tmp`
-- ----------------------------
DROP TABLE IF EXISTS `uc_account_log_tmp`;
CREATE TABLE `uc_account_log_tmp` (
  `id` int(10) NOT NULL,
  `member_id` int(10) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `wealth_type` varchar(20) NOT NULL COMMENT '消费类型',
  `acount` varchar(20) NOT NULL COMMENT '消费账户',
  `wealth` bigint(15) NOT NULL DEFAULT '0',
  `serial_number` varchar(20) NOT NULL COMMENT '流水号',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc_account_log_tmp
-- ----------------------------

-- ----------------------------
-- Table structure for `uc_member`
-- ----------------------------
DROP TABLE IF EXISTS `uc_member`;
CREATE TABLE `uc_member` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `uc_member_thirdplat`
-- ----------------------------
DROP TABLE IF EXISTS `uc_member_thirdplat`;
CREATE TABLE `uc_member_thirdplat` (
  `id` varchar(10) NOT NULL,
  `member_id` varchar(10) NOT NULL,
  `member_name` varchar(50) NOT NULL,
  `plat` varchar(10) NOT NULL,
  `open_id` varchar(50) NOT NULL,
  `token` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;