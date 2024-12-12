/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 101102 (10.11.2-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : mini_link

 Target Server Type    : MySQL
 Target Server Version : 101102 (10.11.2-MariaDB)
 File Encoding         : 65001

 Date: 12/12/2024 15:19:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for mini_link_group
-- ----------------------------
DROP TABLE IF EXISTS `mini_link_group`;
CREATE TABLE `mini_link_group`  (
  `id` bigint NOT NULL COMMENT '主键',
  `account_id` bigint NULL DEFAULT NULL COMMENT '账号',
  `group_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '分组名称',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_link_group
-- ----------------------------

-- ----------------------------
-- Table structure for mini_link_url_0
-- ----------------------------
DROP TABLE IF EXISTS `mini_link_url_0`;
CREATE TABLE `mini_link_url_0`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `group_id` bigint NULL DEFAULT NULL COMMENT '分组id',
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账号',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `short_link` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '短链接',
  `long_link` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '长链接',
  `expired_time` datetime NULL DEFAULT NULL COMMENT '到期时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mini_link_url_0
-- ----------------------------
INSERT INTO `mini_link_url_0` VALUES (1866760454220283905, 123219939, 'xuzhibin', NULL, 'LcZQegpa', 'www.baidu.com', NULL, '2024-12-11 16:22:29', '2024-12-11 16:22:29', 0);
INSERT INTO `mini_link_url_0` VALUES (1866760468308951042, 123219939, 'xuzhibin', NULL, 'RQTjpfHJ', 'www.baidu.com', NULL, '2024-12-11 16:22:33', '2024-12-11 16:22:33', 0);
INSERT INTO `mini_link_url_0` VALUES (1866760470074753025, 123219939, 'xuzhibin', NULL, 'EIFYNZmz', 'www.baidu.com', NULL, '2024-12-11 16:22:33', '2024-12-11 16:22:33', 0);
INSERT INTO `mini_link_url_0` VALUES (1866762519181127682, 123219939, 'xuzhibin', NULL, 'NwZTCIDv', 'www.baidu.com', NULL, '2024-12-11 16:30:42', '2024-12-11 16:30:42', 0);
INSERT INTO `mini_link_url_0` VALUES (1866762519252430851, 123219939, 'xuzhibin', NULL, 'dHdKnsom', 'www.baidu.com', NULL, '2024-12-11 16:30:42', '2024-12-11 16:30:42', 0);
INSERT INTO `mini_link_url_0` VALUES (1866762519311151105, 123219939, 'xuzhibin', NULL, 'nbtJRvsX', 'www.baidu.com', NULL, '2024-12-11 16:30:42', '2024-12-11 16:30:42', 0);
INSERT INTO `mini_link_url_0` VALUES (1866762519374065665, 123219939, 'xuzhibin', NULL, 'vxmdTukR', 'www.baidu.com', NULL, '2024-12-11 16:30:42', '2024-12-11 16:30:42', 0);

-- ----------------------------
-- Table structure for mini_link_url_1
-- ----------------------------
DROP TABLE IF EXISTS `mini_link_url_1`;
CREATE TABLE `mini_link_url_1`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `group_id` bigint NULL DEFAULT NULL COMMENT '分组id',
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账号',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `short_link` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '短链接',
  `long_link` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '长链接',
  `expired_time` datetime NULL DEFAULT NULL COMMENT '到期时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mini_link_url_1
-- ----------------------------
INSERT INTO `mini_link_url_1` VALUES (1866760468308951043, 123219939, 'xuzhibin', NULL, 'nrfZxFKv', 'www.baidu.com', NULL, '2024-12-11 16:22:33', '2024-12-11 16:22:33', 0);
INSERT INTO `mini_link_url_1` VALUES (1866760468438974466, 123219939, 'xuzhibin', NULL, 'kQbSMBPG', 'www.baidu.com', NULL, '2024-12-11 16:22:33', '2024-12-11 16:22:33', 0);
INSERT INTO `mini_link_url_1` VALUES (1866760470074753026, 123219939, 'xuzhibin', NULL, 'wkAgGxoM', 'www.baidu.com', NULL, '2024-12-11 16:22:33', '2024-12-11 16:22:33', 0);
INSERT INTO `mini_link_url_1` VALUES (1866760470141861889, 123219939, 'xuzhibin', NULL, 'KOKTcZXV', 'www.baidu.com', NULL, '2024-12-11 16:22:33', '2024-12-11 16:22:33', 0);
INSERT INTO `mini_link_url_1` VALUES (1866762505331535874, 123219939, 'xuzhibin', NULL, 'EvvhvGnd', 'www.baidu.com', NULL, '2024-12-11 16:30:38', '2024-12-11 16:30:38', 0);
INSERT INTO `mini_link_url_1` VALUES (1866762519252430849, 123219939, 'xuzhibin', NULL, 'PKoWNcJt', 'www.baidu.com', NULL, '2024-12-11 16:30:42', '2024-12-11 16:30:42', 0);
INSERT INTO `mini_link_url_1` VALUES (1866762519311151106, 123219939, 'xuzhibin', NULL, 'aazciJen', 'www.baidu.com', NULL, '2024-12-11 16:30:42', '2024-12-11 16:30:42', 0);
INSERT INTO `mini_link_url_1` VALUES (1866762519374065666, 123219939, 'xuzhibin', NULL, 'YMkztNYy', 'www.baidu.com', NULL, '2024-12-11 16:30:42', '2024-12-11 16:30:42', 0);

-- ----------------------------
-- Table structure for mini_link_url_2
-- ----------------------------
DROP TABLE IF EXISTS `mini_link_url_2`;
CREATE TABLE `mini_link_url_2`  (
  `id` bigint NOT NULL COMMENT '主键id',
  `group_id` bigint NULL DEFAULT NULL COMMENT '分组id',
  `account_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '账号',
  `title` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `short_link` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '短链接',
  `long_link` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '长链接',
  `expired_time` datetime NULL DEFAULT NULL COMMENT '到期时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of mini_link_url_2
-- ----------------------------
INSERT INTO `mini_link_url_2` VALUES (1866759996336615425, 123219939, 'xuzhibin', NULL, 'fdjisi', 'www.baidu.com', NULL, '2024-12-11 16:20:40', '2024-12-11 16:20:40', 0);
INSERT INTO `mini_link_url_2` VALUES (1866760468371865601, 123219939, 'xuzhibin', NULL, 'lHIIapXq', 'www.baidu.com', NULL, '2024-12-11 16:22:33', '2024-12-11 16:22:33', 0);
INSERT INTO `mini_link_url_2` VALUES (1866760470141861890, 123219939, 'xuzhibin', NULL, 'UosCWEvR', 'www.baidu.com', NULL, '2024-12-11 16:22:33', '2024-12-11 16:22:33', 0);
INSERT INTO `mini_link_url_2` VALUES (1866760470204776450, 123219939, 'xuzhibin', NULL, 'WAoVzNbH', 'www.baidu.com', NULL, '2024-12-11 16:22:33', '2024-12-11 16:22:33', 0);
INSERT INTO `mini_link_url_2` VALUES (1866762519252430850, 123219939, 'xuzhibin', NULL, 'XUhQcCeW', 'www.baidu.com', NULL, '2024-12-11 16:30:42', '2024-12-11 16:30:42', 0);
INSERT INTO `mini_link_url_2` VALUES (1866762519311151107, 123219939, 'xuzhibin', NULL, 'MJungqbO', 'www.baidu.com', NULL, '2024-12-11 16:30:42', '2024-12-11 16:30:42', 0);

-- ----------------------------
-- Table structure for mini_link_user
-- ----------------------------
DROP TABLE IF EXISTS `mini_link_user`;
CREATE TABLE `mini_link_user`  (
  `id` bigint NOT NULL COMMENT '主键',
  `account_id` bigint NULL DEFAULT NULL COMMENT '账号id',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '邮箱',
  `avatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '头像',
  `nick_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '盐',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `deleted` tinyint(1) NULL DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_email`(`email` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mini_link_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
