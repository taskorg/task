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
CREATE DATABASE IF NOT EXISTS mc default charset utf8 COLLATE utf8_general_ci;

use mc;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mc_account`
-- ----------------------------
DROP TABLE IF EXISTS `mc.mc_account`;
CREATE TABLE `mc.mc_account` (
  `id` int(10) NOT NULL,
  `merchant_id` int(10) NOT NULL COMMENT '商家id',
  `name` varchar(50) NOT NULL COMMENT '账户名称',
  `first` bigint(10) NOT NULL DEFAULT '0' COMMENT '主账户',
  `second` bigint(10) NOT NULL DEFAULT '0' COMMENT '副账户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of mc_account
-- ----------------------------

-- ----------------------------
-- Table structure for `mc_account_log`
-- ----------------------------
DROP TABLE IF EXISTS `mc_account_log`;
CREATE TABLE `mc_account_log` (
  `id` int(10) NOT NULL,
  `merchant_id` int(10) NOT NULL,
  `account_id` int(10) NOT NULL,
  `wealth_type` varchar(20) NOT NULL DEFAULT '0' COMMENT '主账户',
  `account` varchar(20) NOT NULL,
  `wealth` bigint(15) NOT NULL,
  `wealth_balance` bigint(15) NOT NULL,
  `serial_number` varchar(20) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of mc_account_log
-- ----------------------------

-- ----------------------------
-- Table structure for `mc_account_log_tmp`
-- ----------------------------
DROP TABLE IF EXISTS `mc_account_log_tmp`;
CREATE TABLE `mc_account_log_tmp` (
  `id` int(10) NOT NULL,
  `merchant_id` int(10) NOT NULL,
  `account_id` int(10) NOT NULL,
  `wealth_type` varchar(20) NOT NULL DEFAULT '0' COMMENT '主账户',
  `wealth` bigint(15) NOT NULL,
  `first` bigint(15) NOT NULL,
  `second` bigint(15) NOT NULL,
  `serial_number` varchar(20) NOT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `priority` int(1) NOT NULL DEFAULT '0',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of mc_account_log_tmp
-- ----------------------------

-- ----------------------------
-- Table structure for `mc_gotoshop_log`
-- ----------------------------
DROP TABLE IF EXISTS `mc_gotoshop_log`;
CREATE TABLE `mc_gotoshop_log` (
  `id` int(10) NOT NULL,
  `merchant_id` int(10) NOT NULL,
  `merchant_name` varchar(50) NOT NULL,
  `position` varchar(100) DEFAULT NULL,
  `url` varchar(1000) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mc_gotoshop_log
-- ----------------------------

-- ----------------------------
-- Table structure for `mc_merchant`
-- ----------------------------
DROP TABLE IF EXISTS `mc_merchant`;
CREATE TABLE `mc_merchant` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `tags` varchar(50) DEFAULT NULL,
  `logo` varchar(1000) NOT NULL,
  `domain` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for `mc_tag`
-- ----------------------------
DROP TABLE IF EXISTS `mc_tag`;
CREATE TABLE `mc_tag` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `display_order` int(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;