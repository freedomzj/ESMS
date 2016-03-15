/*
Navicat MySQL Data Transfer

Source Server         : yunwei
Source Server Version : 50623
Source Host           : 192.168.2.54:3306
Source Database       : esms

Target Server Type    : MYSQL
Target Server Version : 50623
File Encoding         : 65001

Date: 2016-03-14 15:29:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `iss_check`
-- ----------------------------
DROP TABLE IF EXISTS `iss_check`;
CREATE TABLE `iss_check` (
  `check_id` int(11) NOT NULL AUTO_INCREMENT,
  `check_code` varchar(255) DEFAULT NULL,
  `check_date` date DEFAULT NULL,
  `check_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`check_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_check
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_check_item`
-- ----------------------------
DROP TABLE IF EXISTS `iss_check_item`;
CREATE TABLE `iss_check_item` (
  `ch_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `ch_item_code` varchar(255) DEFAULT NULL,
  `ch_item_qty` int(11) DEFAULT NULL,
  `ch_item_status` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  `check_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ch_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_check_item
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_customer`
-- ----------------------------
DROP TABLE IF EXISTS `iss_customer`;
CREATE TABLE `iss_customer` (
  `cus_id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_code` varchar(255) DEFAULT NULL,
  `cus_name` varchar(255) DEFAULT NULL,
  `cus_pym` varchar(255) DEFAULT NULL,
  `cus_age` int(11) DEFAULT NULL,
  `cus_phone` varchar(255) DEFAULT NULL,
  `cus_sex` int(11) DEFAULT NULL,
  `cus_comaddress` int(11) DEFAULT NULL,
  `cus_status` int(11) DEFAULT NULL,
  `cus_comname` date DEFAULT NULL,
  PRIMARY KEY (`cus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_customer
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_employee`
-- ----------------------------
DROP TABLE IF EXISTS `iss_employee`;
CREATE TABLE `iss_employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_code` varchar(255) DEFAULT NULL,
  `emp_name` varchar(255) DEFAULT NULL,
  `emp_age` int(11) DEFAULT NULL,
  `emp_pym` varchar(255) DEFAULT NULL,
  `emp_base_sal` double DEFAULT NULL,
  `emp_phone` varchar(255) DEFAULT NULL,
  `emp_sex` int(11) DEFAULT NULL,
  `emp_address` varchar(255) DEFAULT NULL,
  `emp_in_date` date DEFAULT NULL,
  `emp_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_employee
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_goods`
-- ----------------------------
DROP TABLE IF EXISTS `iss_goods`;
CREATE TABLE `iss_goods` (
  `goods_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_code` varchar(255) DEFAULT NULL,
  `goods_name` varchar(255) DEFAULT NULL,
  `goods_pym` varchar(255) DEFAULT NULL,
  `goods_comm` varchar(255) DEFAULT NULL,
  `goods_price` double DEFAULT NULL,
  `goods_product` varchar(255) DEFAULT NULL,
  `goods_status` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  `size_id` int(11) DEFAULT NULL,
  `sup_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_in`
-- ----------------------------
DROP TABLE IF EXISTS `iss_in`;
CREATE TABLE `iss_in` (
  `in_id` int(11) NOT NULL AUTO_INCREMENT,
  `in_code` varchar(255) DEFAULT NULL,
  `in_date` date DEFAULT NULL,
  `in_status` int(11) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`in_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_in
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_in_item`
-- ----------------------------
DROP TABLE IF EXISTS `iss_in_item`;
CREATE TABLE `iss_in_item` (
  `in_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `in_item_price` double DEFAULT NULL,
  `in_item_qty` int(11) DEFAULT NULL,
  `in_item_status` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`in_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_in_item
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_return`
-- ----------------------------
DROP TABLE IF EXISTS `iss_return`;
CREATE TABLE `iss_return` (
  `re_id` int(11) NOT NULL AUTO_INCREMENT,
  `re_code` varchar(255) DEFAULT NULL,
  `re_date` date DEFAULT NULL,
  `re_status` int(11) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`re_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_return
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_return_item`
-- ----------------------------
DROP TABLE IF EXISTS `iss_return_item`;
CREATE TABLE `iss_return_item` (
  `re_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `re_item_code` varchar(255) DEFAULT NULL,
  `re_item_price` double DEFAULT NULL,
  `re_item_qty` int(11) DEFAULT NULL,
  `re_item_status` int(11) DEFAULT NULL,
  `re_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`re_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_return_item
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_role`
-- ----------------------------
DROP TABLE IF EXISTS `iss_role`;
CREATE TABLE `iss_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `role_desc` varchar(255) DEFAULT NULL,
  `role_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_role
-- ----------------------------
INSERT INTO `iss_role` VALUES ('1', '001', '管理员', '拥有一切权限', '1');

-- ----------------------------
-- Table structure for `iss_sell`
-- ----------------------------
DROP TABLE IF EXISTS `iss_sell`;
CREATE TABLE `iss_sell` (
  `sell_id` int(11) NOT NULL AUTO_INCREMENT,
  `sell_code` varchar(255) DEFAULT NULL,
  `sell_date` date DEFAULT NULL,
  `sell_status` int(11) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sell_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_sell
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_sell_item`
-- ----------------------------
DROP TABLE IF EXISTS `iss_sell_item`;
CREATE TABLE `iss_sell_item` (
  `se_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `se_item_price` varchar(255) DEFAULT NULL,
  `se_item_qty` int(11) DEFAULT NULL,
  `sell_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`se_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_sell_item
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_se_return`
-- ----------------------------
DROP TABLE IF EXISTS `iss_se_return`;
CREATE TABLE `iss_se_return` (
  `se_re_id` int(11) NOT NULL AUTO_INCREMENT,
  `se_re_code` varchar(255) DEFAULT NULL,
  `se_re_date` date DEFAULT NULL,
  `se_re_comm` int(11) DEFAULT NULL,
  `emp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`se_re_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_se_return
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_se_re_item`
-- ----------------------------
DROP TABLE IF EXISTS `iss_se_re_item`;
CREATE TABLE `iss_se_re_item` (
  `se_re_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `se_re_item_code` varchar(255) DEFAULT NULL,
  `se_re_item_price` int(11) DEFAULT NULL,
  `se_re_item_qyt` int(11) DEFAULT NULL,
  `se_re_item_status` int(11) DEFAULT NULL,
  `se_re_id` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`se_re_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_se_re_item
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_size`
-- ----------------------------
DROP TABLE IF EXISTS `iss_size`;
CREATE TABLE `iss_size` (
  `size_id` int(11) NOT NULL AUTO_INCREMENT,
  `size_code` varchar(255) DEFAULT NULL,
  `size_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`size_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_size
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_stock`
-- ----------------------------
DROP TABLE IF EXISTS `iss_stock`;
CREATE TABLE `iss_stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `stock_code` varchar(255) DEFAULT NULL,
  `stock_qty` int(11) DEFAULT NULL,
  `stock_status` int(11) DEFAULT NULL,
  `goods_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`stock_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_stock
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_supplie`
-- ----------------------------
DROP TABLE IF EXISTS `iss_supplie`;
CREATE TABLE `iss_supplie` (
  `sup_id` int(11) NOT NULL AUTO_INCREMENT,
  `sup_code` varchar(20) DEFAULT NULL,
  `sup_name` varchar(20) DEFAULT NULL,
  `sup_pym` varchar(20) DEFAULT NULL,
  `sup_phone` varchar(20) DEFAULT NULL,
  `sup_linkman` varchar(20) DEFAULT NULL,
  `sup_email` varchar(20) DEFAULT NULL,
  `sup_address` varchar(20) DEFAULT NULL,
  `sup_comment` varchar(20) DEFAULT NULL,
  `sup_status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`sup_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_supplie
-- ----------------------------

-- ----------------------------
-- Table structure for `iss_type`
-- ----------------------------
DROP TABLE IF EXISTS `iss_type`;
CREATE TABLE `iss_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_code` varchar(20) DEFAULT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  `type_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_type
-- ----------------------------
INSERT INTO `iss_type` VALUES ('1', '001', '饮料', '1');
INSERT INTO `iss_type` VALUES ('2', '002', '食品', null);

-- ----------------------------
-- Table structure for `iss_user`
-- ----------------------------
DROP TABLE IF EXISTS `iss_user`;
CREATE TABLE `iss_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_code` varchar(50) DEFAULT NULL,
  `user_account` varchar(50) DEFAULT NULL,
  `user_pwd` varchar(255) DEFAULT NULL,
  `user_status` int(11) DEFAULT NULL,
  `user_comm` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of iss_user
-- ----------------------------
INSERT INTO `iss_user` VALUES ('1', '01', 'admin', 'admin', '1', '管理员', '1');
