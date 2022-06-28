/*
 Navicat Premium Data Transfer

 Source Server         : MySQL_3306
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : springboot-vue

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 28/06/2022 11:58:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `identifier` int(0) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` int(0) NULL DEFAULT NULL,
  `tip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`identifier`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomId` int(0) NOT NULL,
  `type` int(0) NOT NULL,
  `identifier` int(0) NULL DEFAULT NULL,
  `day` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`roomId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (211, 1, NULL, NULL);
INSERT INTO `room` VALUES (212, 1, NULL, NULL);
INSERT INTO `room` VALUES (223, 2, NULL, NULL);
INSERT INTO `room` VALUES (224, 2, NULL, NULL);
INSERT INTO `room` VALUES (331, 3, NULL, NULL);
INSERT INTO `room` VALUES (332, 3, NULL, NULL);
INSERT INTO `room` VALUES (343, 4, NULL, NULL);
INSERT INTO `room` VALUES (344, 4, NULL, NULL);
INSERT INTO `room` VALUES (451, 5, NULL, NULL);
INSERT INTO `room` VALUES (452, 5, NULL, NULL);
INSERT INTO `room` VALUES (463, 6, NULL, NULL);
INSERT INTO `room` VALUES (464, 6, NULL, NULL);
INSERT INTO `room` VALUES (571, 7, NULL, NULL);
INSERT INTO `room` VALUES (572, 7, NULL, NULL);

-- ----------------------------
-- Table structure for roomstandard
-- ----------------------------
DROP TABLE IF EXISTS `roomstandard`;
CREATE TABLE `roomstandard`  (
  `type` int(0) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `area` int(0) NULL DEFAULT NULL,
  `bedNum` int(0) NULL DEFAULT NULL,
  `priceToday` int(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roomstandard
-- ----------------------------
INSERT INTO `roomstandard` VALUES (1, '优享大床房', 30, 1, 250);
INSERT INTO `roomstandard` VALUES (2, '优享双床房', 30, 2, 300);
INSERT INTO `roomstandard` VALUES (3, '舒适大床房', 30, 1, 350);
INSERT INTO `roomstandard` VALUES (4, '舒适双床房', 30, 2, 400);
INSERT INTO `roomstandard` VALUES (5, '轻享大床房', 32, 1, 450);
INSERT INTO `roomstandard` VALUES (6, '轻享套房', 64, 4, 500);
INSERT INTO `roomstandard` VALUES (7, '舒适套房', 64, 4, 600);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` int(0) NULL DEFAULT NULL,
  `jurisdiction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0, '123456', 'user', NULL, '1', NULL);
INSERT INTO `user` VALUES (1, '1111', '111', NULL, '2', NULL);
INSERT INTO `user` VALUES (3, '2222222', '2222', NULL, '2', NULL);

SET FOREIGN_KEY_CHECKS = 1;
