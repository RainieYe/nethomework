/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : springboot-vue

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 01/07/2022 06:42:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for example
-- ----------------------------
DROP TABLE IF EXISTS `example`;
CREATE TABLE `example`  (
  `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` int(11) NULL DEFAULT NULL,
  `bednum` int(11) NULL DEFAULT NULL,
  `pricetoday` int(11) NULL DEFAULT NULL,
  `type` int(11) NOT NULL,
  `roomid` int(11) NOT NULL,
  `day` int(11) NULL DEFAULT NULL,
  `id` int(11) NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` int(11) NULL DEFAULT NULL,
  `jurisdiction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roomid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of example
-- ----------------------------
INSERT INTO `example` VALUES ('优享大床房', 30, 1, 250, 1, 211, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('优享大床房', 30, 1, 250, 1, 212, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('优享双床房', 30, 2, 300, 2, 223, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('优享双床房', 30, 2, 300, 2, 224, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('舒适大床房', 30, 1, 350, 3, 331, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('舒适大床房', 30, 1, 350, 3, 332, 1, 11111, '1', '111', '叶佳雯', 111111, '2', '消费者');
INSERT INTO `example` VALUES ('舒适双床房', 30, 2, 400, 4, 343, 2, 22222, '2', '222', '邵江辉', 222222, '2', '消费者');
INSERT INTO `example` VALUES ('舒适双床房', 30, 2, 400, 4, 344, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('轻享大床房', 32, 1, 450, 5, 451, 3, 33333, '3', '333', '朱津毅', 333333, '2', '消费者');
INSERT INTO `example` VALUES ('轻享大床房', 32, 1, 450, 5, 452, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('轻享套房', 64, 4, 500, 6, 463, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('轻享套房', 64, 4, 500, 6, 464, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('舒适套房', 64, 4, 600, 7, 571, 3, 99999, '999', '999', '99999', 999999, '2', '消费者');
INSERT INTO `example` VALUES ('舒适套房', 64, 4, 600, 7, 572, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomtype` int(11) NOT NULL,
  `roomid` int(11) NOT NULL,
  `day` int(11) NULL DEFAULT NULL,
  `userid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`roomid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, 211, NULL, NULL);
INSERT INTO `room` VALUES (1, 212, NULL, NULL);
INSERT INTO `room` VALUES (2, 223, NULL, NULL);
INSERT INTO `room` VALUES (2, 224, NULL, NULL);
INSERT INTO `room` VALUES (3, 331, NULL, NULL);
INSERT INTO `room` VALUES (3, 332, 1, 11111);
INSERT INTO `room` VALUES (4, 343, 2, 22222);
INSERT INTO `room` VALUES (4, 344, NULL, NULL);
INSERT INTO `room` VALUES (5, 451, 3, 33333);
INSERT INTO `room` VALUES (5, 452, NULL, NULL);
INSERT INTO `room` VALUES (6, 463, NULL, NULL);
INSERT INTO `room` VALUES (6, 464, NULL, NULL);
INSERT INTO `room` VALUES (7, 571, 3, 99999);
INSERT INTO `room` VALUES (7, 572, NULL, NULL);

-- ----------------------------
-- Table structure for roomstandard
-- ----------------------------
DROP TABLE IF EXISTS `roomstandard`;
CREATE TABLE `roomstandard`  (
  `typename` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `area` int(11) NULL DEFAULT NULL,
  `bednum` int(11) NULL DEFAULT NULL,
  `pricetoday` int(11) NULL DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roomstandard
-- ----------------------------
INSERT INTO `roomstandard` VALUES ('优享大床房', 30, 1, 250, 1);
INSERT INTO `roomstandard` VALUES ('优享双床房', 30, 2, 300, 2);
INSERT INTO `roomstandard` VALUES ('舒适大床房', 30, 1, 350, 3);
INSERT INTO `roomstandard` VALUES ('舒适双床房', 30, 2, 400, 4);
INSERT INTO `roomstandard` VALUES ('轻享大床房', 32, 1, 450, 5);
INSERT INTO `roomstandard` VALUES ('轻享套房', 64, 4, 500, 6);
INSERT INTO `roomstandard` VALUES ('舒适套房', 64, 4, 600, 7);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` int(11) NULL DEFAULT NULL,
  `jurisdiction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0, '123456', 'user', '管理员', 0, '1', '管理员');
INSERT INTO `user` VALUES (11111, '1', '111', '叶佳雯', 111111, '2', '消费者');
INSERT INTO `user` VALUES (22222, '2', '222', '邵江辉', 222222, '2', '消费者');
INSERT INTO `user` VALUES (33333, '3', '333', '朱津毅', 333333, '2', '消费者');
INSERT INTO `user` VALUES (99999, '999', '999', '99999', 999999, '2', '消费者');
INSERT INTO `user` VALUES (121323, '123456', '11113123123', '21312', 1231231, '2', '312312');
INSERT INTO `user` VALUES (666666, '123456', '66666', '66666666', 666, '2', '666666');
INSERT INTO `user` VALUES (5555555, '123456', '5555555', '555555555', 555, '2', '555555');
INSERT INTO `user` VALUES (11111111, '123456', '1111', '1', 1, '2', '1');
INSERT INTO `user` VALUES (35465437, '486474', '36345', '46764', NULL, '2', NULL);
INSERT INTO `user` VALUES (222222222, '123456', '2222', '22222', 22222, '2', '22222');

SET FOREIGN_KEY_CHECKS = 1;
