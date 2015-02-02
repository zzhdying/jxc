/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : mglf

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2015-02-02 18:25:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dic
-- ----------------------------
DROP TABLE IF EXISTS `t_dic`;
CREATE TABLE `t_dic` (
  `id` varchar(36) NOT NULL,
  `entid` varchar(36) DEFAULT NULL,
  `num` varchar(8) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `desciprtion` varchar(255) DEFAULT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(36) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dic
-- ----------------------------

-- ----------------------------
-- Table structure for t_list
-- ----------------------------
DROP TABLE IF EXISTS `t_list`;
CREATE TABLE `t_list` (
  `id` varchar(36) NOT NULL,
  `entid` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(36) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_list
-- ----------------------------

-- ----------------------------
-- Table structure for t_list_item
-- ----------------------------
DROP TABLE IF EXISTS `t_list_item`;
CREATE TABLE `t_list_item` (
  `id` varchar(36) NOT NULL,
  `num` varchar(16) DEFAULT NULL,
  `listid` varchar(36) DEFAULT NULL,
  `prd_type_id` varchar(36) DEFAULT NULL,
  `entid` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(36) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_list_item
-- ----------------------------

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` varchar(36) NOT NULL,
  `num` varchar(8) DEFAULT NULL,
  `supplier_id` varchar(36) DEFAULT NULL,
  `price` decimal(10,0) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(36) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for t_order_list
-- ----------------------------
DROP TABLE IF EXISTS `t_order_list`;
CREATE TABLE `t_order_list` (
  `id` varchar(36) NOT NULL,
  `prd_type_id` varchar(36) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `unit_price` decimal(10,0) DEFAULT NULL,
  `act_price` decimal(10,0) DEFAULT NULL,
  `all_price` decimal(10,0) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(36) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_list
-- ----------------------------

-- ----------------------------
-- Table structure for t_outer
-- ----------------------------
DROP TABLE IF EXISTS `t_outer`;
CREATE TABLE `t_outer` (
  `id` varchar(36) NOT NULL,
  `entid` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(36) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_outer
-- ----------------------------

-- ----------------------------
-- Table structure for t_outer_item
-- ----------------------------
DROP TABLE IF EXISTS `t_outer_item`;
CREATE TABLE `t_outer_item` (
  `id` varchar(36) NOT NULL,
  `entid` varchar(36) DEFAULT NULL,
  `prd_type_id` varchar(36) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(36) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_outer_item
-- ----------------------------

-- ----------------------------
-- Table structure for t_prd
-- ----------------------------
DROP TABLE IF EXISTS `t_prd`;
CREATE TABLE `t_prd` (
  `id` varchar(36) NOT NULL,
  `num` varchar(8) DEFAULT NULL,
  `entid` varchar(36) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `own_entname` varchar(255) DEFAULT NULL,
  `description` text,
  `unit` varchar(255) DEFAULT NULL,
  `prd_type_id` varchar(36) DEFAULT NULL,
  `code` varchar(36) DEFAULT NULL,
  `buy_price` decimal(10,3) DEFAULT NULL,
  `sell_price` decimal(10,3) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `create_user` varchar(36) DEFAULT NULL,
  `update_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_prd
-- ----------------------------
INSERT INTO `t_prd` VALUES ('1', 'p0000001', '1', '心相印茶香200抽纸面巾', '恒安心相印', '200抽*2层', '包', null, '6922868285730', '3.500', '6.500', null, null, null, null);

-- ----------------------------
-- Table structure for t_stock
-- ----------------------------
DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock` (
  `id` varchar(36) NOT NULL,
  `entid` varchar(36) DEFAULT NULL,
  `prd_type_id` varchar(36) DEFAULT NULL,
  `all` int(11) DEFAULT NULL,
  `leave` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_stock
-- ----------------------------

-- ----------------------------
-- Table structure for t_supplier
-- ----------------------------
DROP TABLE IF EXISTS `t_supplier`;
CREATE TABLE `t_supplier` (
  `id` varchar(36) NOT NULL,
  `num` varchar(8) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `name` varchar(36) DEFAULT NULL,
  `entname` varchar(36) DEFAULT NULL,
  `linkphone` varchar(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_user` varchar(36) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `update_user` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_supplier
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(36) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `entid` varchar(36) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num` varchar(6) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456', '1', null, null, '钟先生', null, null, null);
