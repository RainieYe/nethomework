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

 Date: 30/06/2022 13:35:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for example
-- ----------------------------
DROP TABLE IF EXISTS `example`;
CREATE TABLE `example`  (
  `typeName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `area` int(0) NULL DEFAULT NULL,
  `bedNum` int(0) NULL DEFAULT NULL,
  `priceToday` int(0) NULL DEFAULT NULL,
  `type` int(0) NOT NULL,
  `roomId` int(0) NOT NULL,
  `day` int(0) NULL DEFAULT NULL,
  `id` int(0) NULL DEFAULT 0,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` int(0) NULL DEFAULT NULL,
  `jurisdiction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roomId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of example
-- ----------------------------
INSERT INTO `example` VALUES ('优享大床房', 30, 1, 250, 1, 211, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('优享大床房', 30, 1, 250, 1, 212, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('优享双床房', 30, 2, 300, 2, 223, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('优享双床房', 30, 2, 300, 2, 224, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('舒适大床房', 30, 1, 350, 3, 331, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('舒适大床房', 30, 1, 350, 3, 332, 1, 3, '3', '333', '朱津毅', 333333, '2', '消费者');
INSERT INTO `example` VALUES ('舒适双床房', 30, 2, 400, 4, 343, 2, 2, '2', '222', '邵江辉', 222222, '2', '消费者');
INSERT INTO `example` VALUES ('舒适双床房', 30, 2, 400, 4, 344, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('轻享大床房', 32, 1, 450, 5, 451, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('轻享大床房', 32, 1, 450, 5, 452, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('轻享套房', 64, 4, 500, 6, 463, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('轻享套房', 64, 4, 500, 6, 464, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `example` VALUES ('舒适套房', 64, 4, 600, 7, 571, 3, 1, '1', '111', '叶嘉雯', 111111, '2', '消费者');
INSERT INTO `example` VALUES ('舒适套房', 64, 4, 600, 7, 572, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `roomType` int(0) NOT NULL,
  `roomId` int(0) NOT NULL,
  `day` int(0) NULL DEFAULT NULL,
  `userId` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`roomId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES (1, 211, NULL, NULL);
INSERT INTO `room` VALUES (1, 212, NULL, NULL);
INSERT INTO `room` VALUES (2, 223, NULL, NULL);
INSERT INTO `room` VALUES (2, 224, NULL, NULL);
INSERT INTO `room` VALUES (3, 331, NULL, NULL);
INSERT INTO `room` VALUES (3, 332, 1, 3);
INSERT INTO `room` VALUES (4, 343, 2, 2);
INSERT INTO `room` VALUES (4, 344, NULL, NULL);
INSERT INTO `room` VALUES (5, 451, NULL, NULL);
INSERT INTO `room` VALUES (5, 452, NULL, NULL);
INSERT INTO `room` VALUES (6, 463, NULL, NULL);
INSERT INTO `room` VALUES (6, 464, NULL, NULL);
INSERT INTO `room` VALUES (7, 571, 3, 1);
INSERT INTO `room` VALUES (7, 572, NULL, NULL);

-- ----------------------------
-- Table structure for roomstandard
-- ----------------------------
DROP TABLE IF EXISTS `roomstandard`;
CREATE TABLE `roomstandard`  (
  `typeName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `area` int(0) NULL DEFAULT NULL,
  `bedNum` int(0) NULL DEFAULT NULL,
  `priceToday` int(0) NULL DEFAULT NULL,
  `type` int(0) NOT NULL,
  PRIMARY KEY (`type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` int(0) NULL DEFAULT NULL,
  `jurisdiction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (0, '123456', 'user', '管理员', 0, '1', '管理员');
INSERT INTO `user` VALUES (1, '1', '111', '叶嘉雯', 111111, '2', '消费者');
INSERT INTO `user` VALUES (2, '2', '222', '邵江辉', 222222, '2', '消费者');
INSERT INTO `user` VALUES (3, '3', '333', '朱津毅', 333333, '2', '消费者');

SET FOREIGN_KEY_CHECKS = 1;
