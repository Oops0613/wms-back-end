/*
 Navicat Premium Data Transfer

 Source Server         : 服务器
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : 124.222.30.106:3306
 Source Schema         : wms

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 23/05/2025 14:28:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `is_frame` int NULL DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `menu_type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '' COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4005 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '菜单权限表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 6, 'system', NULL, 1, 'M', '0', '0', '', 'el-icon-setting', 0, '2021-11-12 10:46:19', 0, NULL, '系统管理目录', '0');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, 'user', 'system/user/index', 1, 'C', '0', '0', 'system:user:list', 'el-icon-user-solid', 0, '2021-11-12 10:46:19', 1, '2023-08-10 06:17:35', '用户管理菜单', '0');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, 'role', 'system/role/index', 1, 'C', '0', '0', 'system:role:list', 'el-icon-key', 0, '2021-11-12 10:46:19', 0, NULL, '角色管理菜单', '0');
INSERT INTO `sys_menu` VALUES (102, '公告管理', 1, 3, 'notice', 'system/notice/index', 1, 'C', '0', '0', 'system:menu:list', 'el-icon-tickets', 0, '2021-11-12 10:46:19', 0, NULL, '菜单管理菜单', '0');
INSERT INTO `sys_menu` VALUES (1001, '用户查询', 100, 1, '', '', 1, 'F', '0', '0', 'system:user:query', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1002, '用户新增', 100, 2, '', '', 1, 'F', '0', '0', 'system:user:add', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1003, '用户修改', 100, 3, '', '', 1, 'F', '0', '0', 'system:user:edit', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1004, '用户删除', 100, 4, '', '', 1, 'F', '0', '0', 'system:user:remove', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1005, '用户导出', 100, 5, '', '', 1, 'F', '0', '0', 'system:user:export', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1006, '用户导入', 100, 6, '', '', 1, 'F', '0', '0', 'system:user:import', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1007, '重置密码', 100, 7, '', '', 1, 'F', '0', '0', 'system:user:resetPwd', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1008, '角色查询', 101, 1, '', '', 1, 'F', '0', '0', 'system:role:query', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1009, '角色新增', 101, 2, '', '', 1, 'F', '0', '0', 'system:role:add', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1010, '角色修改', 101, 3, '', '', 1, 'F', '0', '0', 'system:role:edit', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1011, '角色删除', 101, 4, '', '', 1, 'F', '0', '0', 'system:role:remove', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1012, '角色导出', 101, 5, '', '', 1, 'F', '0', '0', 'system:role:export', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1013, '菜单查询', 102, 1, '', '', 1, 'F', '0', '0', 'system:menu:query', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1014, '菜单新增', 102, 2, '', '', 1, 'F', '0', '0', 'system:menu:add', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1015, '菜单修改', 102, 3, '', '', 1, 'F', '0', '0', 'system:menu:edit', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (1016, '菜单删除', 102, 4, '', '', 1, 'F', '0', '0', 'system:menu:remove', '#', 0, '2021-11-12 10:46:19', 0, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (2017, '货物管理', 0, 2, '', '', 1, 'M', '0', '0', NULL, 'el-icon-goods', NULL, '2022-01-08 02:44:38', 1, '2022-07-31 12:34:23', '', '0');
INSERT INTO `sys_menu` VALUES (2018, '分类管理', 2017, 1, 'category', 'category/index', 1, 'C', '0', '0', 'content:category:list', 'el-icon-s-order', NULL, '2022-01-08 02:51:45', NULL, '2022-01-08 02:51:45', '', '0');
INSERT INTO `sys_menu` VALUES (2019, '货物信息', 2017, 0, 'goods', 'goods/index', 1, 'C', '0', '0', 'content:article:list', 'el-icon-s-goods', NULL, '2022-01-08 02:53:10', NULL, '2022-01-08 02:53:10', '', '0');
INSERT INTO `sys_menu` VALUES (2023, '个人主页', 0, 0, 'Home', 'Home', 1, 'M', '0', '0', 'content:article:writer', 'el-icon-info', NULL, '2022-01-08 03:39:58', 1, '2022-07-31 22:07:05', '', '0');
INSERT INTO `sys_menu` VALUES (2029, '公告栏', 0, 0, 'notice-board', 'NoticeBoard', 1, 'C', '0', '0', NULL, 'el-icon-tickets', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (2030, '仓库管理', 0, 1, 'warehouse', 'warehouse/index', 1, 'C', '0', '0', NULL, 'el-icon-s-home', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (3000, '出入库管理', 0, 3, 'movement', NULL, 1, 'M', '0', '0', NULL, 'el-icon-sort', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (3010, '入库申请', 3000, 1, 'in-apply', 'apply/in/index', 1, 'C', '0', '0', NULL, 'el-icon-circle-plus-outline', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (3011, '出库申请', 3000, 2, 'out-apply', 'apply/out/index', 1, 'C', '0', '0', NULL, 'el-icon-remove-outline', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (3012, '出入库审批', 3000, 4, 'approve', 'approve/index', 1, 'C', '0', '0', NULL, 'el-icon-s-check', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (3013, '调拨申请', 3000, 3, 'allot', 'apply/allot/index', 1, 'C', '0', '0', NULL, 'el-icon-refresh\r\n', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (3014, '库存信息', 3000, 0, 'inventory', 'inventory/index', 1, 'C', '0', '0', NULL, 'el-icon-shopping-cart-2', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (3040, '库存导出', 3014, 0, '', NULL, 1, 'F', '0', '0', 'inventory:export', '#', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (3050, '出入库导出', 3012, 0, '', NULL, 1, 'F', '0', '0', 'approve:export', '#', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (4000, '数据统计', 0, 5, 'statistics', NULL, 1, 'M', '0', '0', NULL, 'el-icon-s-data', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (4001, '仓库统计', 4000, 0, 'warehouse-stat', 'statistics/warehouse/index', 1, 'C', '0', '0', NULL, 'el-icon-s-home', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (4002, '货物统计', 4000, 0, 'goods-stat', 'statistics/goods/index', 1, 'C', '0', '0', NULL, 'el-icon-s-goods', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (4003, '分类统计', 4000, 0, 'category-stat', 'statistics/category/index', 1, 'C', '0', '0', NULL, 'el-icon-s-order', NULL, NULL, NULL, NULL, '', '0');
INSERT INTO `sys_menu` VALUES (4004, '人员统计', 4000, 0, 'user-stat', 'statistics/user/index', 1, 'C', '0', '0', NULL, 'el-icon-user-solid', NULL, NULL, NULL, NULL, '', '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NULL DEFAULT 0,
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '0', '0', 0, '2024-12-01 07:46:19', 0, NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '系统管理员', 'system', 2, '0', '0', 0, '2024-12-01 07:46:19', 1, '2025-03-08 17:30:07', '系统内部用户、角色的管理，不涉及仓储');
INSERT INTO `sys_role` VALUES (3, '货物管理员', 'goods', 3, '0', '0', 1, '2024-12-01 07:46:19', 1, '2025-03-08 17:26:54', '货物基本元信息和仓库分区的管理');
INSERT INTO `sys_role` VALUES (4, '库存管理员', 'inventory', 0, '0', '0', 1, '2024-12-10 18:51:41', 1, '2025-04-11 16:27:40', '负责发起关于货物的出入库和调拨申请');
INSERT INTO `sys_role` VALUES (5, '审批员', 'approve', 0, '0', '0', 1, '2024-12-11 10:24:54', 1, '2025-04-11 16:27:45', '负责核验出入库的货物，并审批出入库申请');
INSERT INTO `sys_role` VALUES (6, '报表分析员', 'analysis', 0, '0', '0', 1, '2024-12-15 15:13:32', 1, '2025-04-11 16:27:47', '有权浏览系统内关于仓库、货物、分类、人员等统计数据');
INSERT INTO `sys_role` VALUES (7, '角色测试001', 'test', 0, '0', '1', 1, '2025-04-29 13:00:11', 1, '2025-04-29 13:02:12', '4567');
INSERT INTO `sys_role` VALUES (8, '访客', 'visitor', 0, '0', '0', 1, '2025-05-10 22:22:30', 1, '2025-05-10 22:24:37', '');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (0, 0);
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 2023);
INSERT INTO `sys_role_menu` VALUES (2, 2029);
INSERT INTO `sys_role_menu` VALUES (3, 2017);
INSERT INTO `sys_role_menu` VALUES (3, 2018);
INSERT INTO `sys_role_menu` VALUES (3, 2019);
INSERT INTO `sys_role_menu` VALUES (3, 2023);
INSERT INTO `sys_role_menu` VALUES (3, 2029);
INSERT INTO `sys_role_menu` VALUES (3, 2030);
INSERT INTO `sys_role_menu` VALUES (4, 2023);
INSERT INTO `sys_role_menu` VALUES (4, 2029);
INSERT INTO `sys_role_menu` VALUES (4, 3000);
INSERT INTO `sys_role_menu` VALUES (4, 3010);
INSERT INTO `sys_role_menu` VALUES (4, 3011);
INSERT INTO `sys_role_menu` VALUES (4, 3013);
INSERT INTO `sys_role_menu` VALUES (4, 3014);
INSERT INTO `sys_role_menu` VALUES (5, 2023);
INSERT INTO `sys_role_menu` VALUES (5, 2029);
INSERT INTO `sys_role_menu` VALUES (5, 3000);
INSERT INTO `sys_role_menu` VALUES (5, 3012);
INSERT INTO `sys_role_menu` VALUES (5, 3014);
INSERT INTO `sys_role_menu` VALUES (6, 2023);
INSERT INTO `sys_role_menu` VALUES (6, 2029);
INSERT INTO `sys_role_menu` VALUES (6, 4000);
INSERT INTO `sys_role_menu` VALUES (6, 4001);
INSERT INTO `sys_role_menu` VALUES (6, 4002);
INSERT INTO `sys_role_menu` VALUES (6, 4003);
INSERT INTO `sys_role_menu` VALUES (6, 4004);
INSERT INTO `sys_role_menu` VALUES (7, 2017);
INSERT INTO `sys_role_menu` VALUES (7, 2018);
INSERT INTO `sys_role_menu` VALUES (7, 4000);
INSERT INTO `sys_role_menu` VALUES (7, 4003);
INSERT INTO `sys_role_menu` VALUES (7, 4004);
INSERT INTO `sys_role_menu` VALUES (8, 2023);
INSERT INTO `sys_role_menu` VALUES (8, 2029);
INSERT INTO `sys_role_menu` VALUES (8, 3000);
INSERT INTO `sys_role_menu` VALUES (8, 3014);
INSERT INTO `sys_role_menu` VALUES (8, 4000);
INSERT INTO `sys_role_menu` VALUES (8, 4001);
INSERT INTO `sys_role_menu` VALUES (8, 4002);
INSERT INTO `sys_role_menu` VALUES (8, 4003);
INSERT INTO `sys_role_menu` VALUES (8, 4004);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NULL DEFAULT NULL,
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '用户名',
  `real_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '姓名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'NULL' COMMENT '密码',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '用户类型：0代表普通用户，1代表管理员',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '账号状态（0正常 1停用）',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phonenumber` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户性别（0男，1女，2未知）',
  `avatar` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `create_by` bigint NULL DEFAULT NULL COMMENT '创建人的用户id',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, 'admin', '李华', '$2a$10$j2SPTKz387S4CQ1k7G3hVO4PuasR6eq/75UDE1uqHOb9KwznqgbNm', '1', '0', '228675@huanfqc.cn', '18888888888', '0', 'https://img1.baidu.com/it/u=1333417867,4012964063&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL, '2023-07-21 13:07:35', 1, '2025-04-26 13:09:12', 0);
INSERT INTO `sys_user` VALUES (2, 2, 'HFuser', '测试用户', '$2a$10$6.p6mWVa69LZsg8yw57zIOP9MXE28hBACEwB475BpEBaMjzMogaUu', '0', '0', '123@huanfqc.cn', '12093473451', '0', 'https://img1.baidu.com/it/u=1333417867,4012964063&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500', NULL, '2023-07-13 13:07:28', 1, '2025-03-29 12:24:31', 0);
INSERT INTO `sys_user` VALUES (4, 2, 'liming', '李明', '$2a$10$fuo3TzKwmV2BpniBDaplbOeHf4efDYctq9M4nVDgbSwtIDuMetiIe', '0', '0', '123@test.cnnnnn', '15288899976', '0', NULL, NULL, NULL, 1, '2025-05-05 14:03:15', 0);
INSERT INTO `sys_user` VALUES (5, 3, 'lihong', '李红', '$2a$10$Hp1P67xXJkOIDEjQNGJ1OuEDX2Z7vKsFAdjMqQM4mB4JYzDN7V24i', '0', '0', '123@qq.cn', '15688789423', '1', NULL, -1, '2024-12-01 14:15:58', 1, '2025-05-05 14:03:27', 0);
INSERT INTO `sys_user` VALUES (6, 4, 'zhangsan', '张三', '$2a$10$TS8lVol7FGH3e5czXlYagOY7taQofgPFMDMzUuk/I078LBPDxNSF6', '0', '0', 'zhangsan@wms.com', '15588996685', '0', NULL, 1, '2024-12-10 14:24:34', 1, '2025-05-05 14:03:34', 0);
INSERT INTO `sys_user` VALUES (7, 5, 'lilei', '李雷', '$2a$10$6B7S2DdIHWbrmAiqVti8Uu2dfdY5ZugEaRYu2IGjnvTGe8xTuVG1O', '0', '0', 'lilei@example.com', '13812345678', '0', NULL, 1, '2024-12-15 15:03:04', 1, '2025-05-05 14:08:22', 0);
INSERT INTO `sys_user` VALUES (8, 6, 'lisi', '李四', '$2a$10$EtWsavpL3qbfV.wRVcgN9etK.uIcMnLsLN0JpexCHznYY6/JDVt6y', '0', '0', '1@1.com', '15160027852', '1', NULL, 1, '2024-12-15 15:11:30', 1, '2025-05-05 14:08:29', 0);
INSERT INTO `sys_user` VALUES (9, 2, 'TC-01-03', '用户测试-001', '$2a$10$zvCDGzFlozDa7p4PXe4VS.rAVj8zl4tIQ9onZAQbjpIKctWGqDAIG', '0', '0', '1@1.com', '15112341234', '1', NULL, 1, '2025-04-26 11:49:57', 1, '2025-04-26 12:11:10', 1);
INSERT INTO `sys_user` VALUES (10, 4, 'zhangqiang', '张强', '$2a$10$nN1a/YBC9.tSpkQXel/YZudgp9M2BjIn6OYHc5bHUqopF8.RaRCIG', '0', '0', 'zhangqiang@example.com', '13923456789', '0', NULL, 1, '2025-05-05 14:30:09', 1, '2025-05-05 14:30:09', 0);
INSERT INTO `sys_user` VALUES (11, 5, 'wangfang', '王芳', '$2a$10$YMrt2hxlYkOqJFT1v/FsF.QarihKzSM2vRDGSJKMONLABe0RYyi9G', '0', '0', 'wangfang@example.com', '13734567890', '1', NULL, 1, '2025-05-05 14:30:35', 1, '2025-05-05 14:30:35', 0);
INSERT INTO `sys_user` VALUES (12, 4, 'chenjie', '陈杰', '$2a$10$6RTUlff4.l5l2luyPDZCOOFIPAdtxKLzh5.1Tfu9OubPK3Gur0JRq', '0', '0', 'chenjie@example.com', '13645678901', '0', NULL, 1, '2025-05-05 14:31:11', 1, '2025-05-05 14:31:11', 0);
INSERT INTO `sys_user` VALUES (13, 5, 'liuting', '刘婷', '$2a$10$f4OzfSjYJmbRtFyVHiuB/OgzGwjwy2blTjZg.tAJRmWH8uSC1YjDO', '0', '0', 'liuting@example.com', '13556789012', '1', NULL, 1, '2025-05-05 14:31:54', 1, '2025-05-05 14:31:54', 0);
INSERT INTO `sys_user` VALUES (14, 8, '同济大学', '李观水', '$2a$10$yRknfrE/Qwo20B5zT84P/errCl9y.QwxHfEJxegQlUH4w5DnUJQDG', '0', '0', '1@1.com', '15845562135', '0', NULL, 1, '2025-05-10 22:23:11', 1, '2025-05-10 22:23:11', 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);
INSERT INTO `sys_user_role` VALUES (4, 2);
INSERT INTO `sys_user_role` VALUES (5, 2);
INSERT INTO `sys_user_role` VALUES (6, 2);

-- ----------------------------
-- Table structure for wms_category
-- ----------------------------
DROP TABLE IF EXISTS `wms_category`;
CREATE TABLE `wms_category`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名',
  `parent_id` bigint NULL DEFAULT -1 COMMENT '父分类id，如果没有父分类为-1',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_category
-- ----------------------------
INSERT INTO `wms_category` VALUES (1, '一级分类-测试用', -1, '我是描述', '0', 1, '2023-08-11 06:45:55', 1, '2025-05-03 13:46:31', 0);
INSERT INTO `wms_category` VALUES (2, 'MySQL', -1, '数据库', '0', 1, '2023-08-11 06:46:36', 1, '2025-01-04 11:41:24', 1);
INSERT INTO `wms_category` VALUES (3, 'Redis', -1, '缓存', '0', 1, '2023-08-11 06:46:54', 1, '2024-12-15 14:52:32', 1);
INSERT INTO `wms_category` VALUES (4, 'MyBatis-plus', -1, '我是描述', '0', 1, '2023-08-11 06:47:17', 1, '2025-01-22 12:37:12', 1);
INSERT INTO `wms_category` VALUES (5, '分类001', 1, '我是描述aaaaaaaaaaaaaaaaaabbbbbbbbbb', '0', 1, '2023-08-11 06:48:04', 1, '2025-01-04 14:41:28', 1);
INSERT INTO `wms_category` VALUES (6, '分类002', -1, '我是描述', '0', 1, '2023-08-11 06:48:18', 1, '2023-08-11 06:48:18', 1);
INSERT INTO `wms_category` VALUES (7, '分类003', -1, '我是描述', '0', 1, '2023-08-11 06:48:31', 1, '2023-08-11 06:48:31', 1);
INSERT INTO `wms_category` VALUES (8, '分类004', -1, '我是描述', '0', 1, '2023-08-11 06:48:49', 1, '2025-03-10 22:37:41', 1);
INSERT INTO `wms_category` VALUES (9, '分类测试2', 1, '描述xx', '0', 1, '2024-12-13 14:10:29', 1, '2025-01-04 14:41:02', 0);
INSERT INTO `wms_category` VALUES (11, '一级分类001', -1, '111', '0', 1, '2024-12-15 14:22:52', 1, '2024-12-15 14:53:08', 1);
INSERT INTO `wms_category` VALUES (12, '一级分类的子女001-001', 11, '112', '0', 1, '2024-12-15 14:23:14', 1, '2024-12-15 14:24:06', 1);
INSERT INTO `wms_category` VALUES (14, '二级分类001', 11, '123', '0', 1, '2024-12-15 14:51:43', 1, '2024-12-15 14:51:43', 1);
INSERT INTO `wms_category` VALUES (15, '一级分类002', -1, '', '0', 1, '2024-12-15 14:52:08', 1, '2024-12-15 14:52:08', 1);
INSERT INTO `wms_category` VALUES (16, '一级分类003', -1, '3', '0', 1, '2024-12-15 14:52:43', 1, '2024-12-15 14:52:43', 1);
INSERT INTO `wms_category` VALUES (17, '二级分类3-1', 16, '', '0', 1, '2024-12-15 14:52:57', 1, '2024-12-15 14:52:57', 1);
INSERT INTO `wms_category` VALUES (18, '二级分类002', 1, '家具分类测试', '0', 1, '2025-02-18 13:34:41', 1, '2025-04-29 13:36:14', 1);
INSERT INTO `wms_category` VALUES (19, 'bug测试专用', 1, '', '0', 1, '2025-02-18 15:47:47', 1, '2025-02-18 15:47:47', 1);
INSERT INTO `wms_category` VALUES (20, '分类测试001', -1, 'ert', '0', 1, '2025-04-29 13:34:11', 1, '2025-04-29 13:34:11', 1);
INSERT INTO `wms_category` VALUES (21, '分类测试002', 20, '', '0', 1, '2025-04-29 13:36:04', 1, '2025-04-29 13:36:04', 1);
INSERT INTO `wms_category` VALUES (22, '水果', -1, '', '0', 1, '2025-05-03 11:51:12', 1, '2025-05-03 11:51:12', 0);
INSERT INTO `wms_category` VALUES (23, '蔬菜', -1, '', '0', 1, '2025-05-03 11:51:26', 1, '2025-05-03 11:51:26', 0);
INSERT INTO `wms_category` VALUES (24, '肉类', -1, '生食，需冷藏', '0', 1, '2025-05-03 11:52:22', 1, '2025-05-03 11:52:22', 0);
INSERT INTO `wms_category` VALUES (25, '海鲜', -1, '', '0', 1, '2025-05-03 11:52:42', 1, '2025-05-03 11:52:42', 0);
INSERT INTO `wms_category` VALUES (26, '乳制品', -1, '', '0', 1, '2025-05-03 11:53:00', 1, '2025-05-03 11:53:00', 0);
INSERT INTO `wms_category` VALUES (27, '饮料', -1, '', '0', 1, '2025-05-03 11:53:42', 1, '2025-05-03 11:53:42', 0);
INSERT INTO `wms_category` VALUES (28, '零食', -1, '', '0', 1, '2025-05-03 11:53:47', 1, '2025-05-03 11:53:47', 0);
INSERT INTO `wms_category` VALUES (29, '调味品', -1, '', '0', 1, '2025-05-03 11:54:14', 1, '2025-05-03 11:54:14', 0);
INSERT INTO `wms_category` VALUES (30, '清洁用品', -1, '', '0', 1, '2025-05-03 11:54:22', 1, '2025-05-03 12:33:40', 0);
INSERT INTO `wms_category` VALUES (31, '家用电器', -1, '', '0', 1, '2025-05-03 11:54:28', 1, '2025-05-03 11:54:28', 0);
INSERT INTO `wms_category` VALUES (32, '葡萄', 22, '', '0', 1, '2025-05-03 11:55:50', 1, '2025-05-03 11:55:50', 0);
INSERT INTO `wms_category` VALUES (33, '香蕉', 22, '', '0', 1, '2025-05-03 11:55:55', 1, '2025-05-03 11:55:55', 0);
INSERT INTO `wms_category` VALUES (34, '草莓', 22, '', '0', 1, '2025-05-03 11:56:06', 1, '2025-05-03 11:56:06', 0);
INSERT INTO `wms_category` VALUES (35, '西红柿', 23, '', '0', 1, '2025-05-03 11:56:29', 1, '2025-05-03 11:56:29', 0);
INSERT INTO `wms_category` VALUES (36, '黄瓜', 23, '', '0', 1, '2025-05-03 11:56:33', 1, '2025-05-03 11:56:33', 0);
INSERT INTO `wms_category` VALUES (37, '生菜', 23, '', '0', 1, '2025-05-03 11:56:37', 1, '2025-05-03 11:56:37', 0);
INSERT INTO `wms_category` VALUES (38, '牛肉', 24, '', '0', 1, '2025-05-03 11:56:48', 1, '2025-05-03 11:56:48', 0);
INSERT INTO `wms_category` VALUES (39, '羊肉', 24, '', '0', 1, '2025-05-03 11:56:56', 1, '2025-05-03 11:56:56', 0);
INSERT INTO `wms_category` VALUES (40, '鸡肉', 24, '', '0', 1, '2025-05-03 11:57:04', 1, '2025-05-03 11:57:04', 0);
INSERT INTO `wms_category` VALUES (41, '鳕鱼', 25, '大型海洋鱼类，共有三种即大西洋鳕（Gadus morhua）、太平洋鳕（Gadus macrocephalus）及狭鳕（Gadus chalcogrammus）', '0', 1, '2025-05-03 11:57:28', 1, '2025-05-03 12:03:29', 0);
INSERT INTO `wms_category` VALUES (42, '三文鱼', 25, '鲑形目鲑科鲑属的一种冷水性洄游鱼类，最初是产自大西洋北部的地区，从加拿大东海岸延伸到西欧、英格兰，以及南至葡萄牙等地', '0', 1, '2025-05-03 11:57:36', 1, '2025-05-03 12:04:47', 0);
INSERT INTO `wms_category` VALUES (43, '对虾', 25, '', '0', 1, '2025-05-03 11:57:40', 1, '2025-05-03 11:57:40', 0);
INSERT INTO `wms_category` VALUES (44, '牛奶', 26, '', '0', 1, '2025-05-03 11:58:21', 1, '2025-05-03 11:58:21', 0);
INSERT INTO `wms_category` VALUES (45, '酸奶', 26, '', '0', 1, '2025-05-03 11:58:26', 1, '2025-05-03 11:58:26', 0);
INSERT INTO `wms_category` VALUES (46, '奶酪', 26, '', '0', 1, '2025-05-03 11:58:33', 1, '2025-05-03 11:58:33', 0);
INSERT INTO `wms_category` VALUES (47, '黄油', 26, '', '0', 1, '2025-05-03 11:58:37', 1, '2025-05-03 11:58:37', 0);
INSERT INTO `wms_category` VALUES (48, '矿泉水', 27, '', '0', 1, '2025-05-03 11:59:03', 1, '2025-05-03 11:59:03', 0);
INSERT INTO `wms_category` VALUES (49, '碳酸饮料', 27, '', '0', 1, '2025-05-03 11:59:10', 1, '2025-05-03 11:59:10', 0);
INSERT INTO `wms_category` VALUES (50, '果汁', 27, '', '0', 1, '2025-05-03 11:59:14', 1, '2025-05-03 11:59:14', 0);
INSERT INTO `wms_category` VALUES (51, '薯片', 28, '', '0', 1, '2025-05-03 12:05:08', 1, '2025-05-03 12:05:08', 0);
INSERT INTO `wms_category` VALUES (52, '糖果', 28, '', '0', 1, '2025-05-03 12:05:15', 1, '2025-05-03 12:05:15', 0);
INSERT INTO `wms_category` VALUES (53, '巧克力', 28, '', '0', 1, '2025-05-03 12:05:21', 1, '2025-05-03 12:05:21', 0);
INSERT INTO `wms_category` VALUES (54, '盐', 29, '', '0', 1, '2025-05-03 12:05:52', 1, '2025-05-03 12:05:52', 0);
INSERT INTO `wms_category` VALUES (55, '生抽', 29, '', '0', 1, '2025-05-03 12:05:58', 1, '2025-05-03 12:05:58', 0);
INSERT INTO `wms_category` VALUES (56, '老抽', 29, '', '0', 1, '2025-05-03 12:06:03', 1, '2025-05-03 12:06:03', 0);
INSERT INTO `wms_category` VALUES (57, '醋', 29, '', '0', 1, '2025-05-03 12:06:12', 1, '2025-05-03 12:06:12', 0);
INSERT INTO `wms_category` VALUES (58, '洗发水', 30, '', '0', 1, '2025-05-03 12:08:04', 1, '2025-05-03 12:33:01', 0);
INSERT INTO `wms_category` VALUES (59, '拖把', 30, '', '0', 1, '2025-05-03 12:08:09', 1, '2025-05-03 12:33:10', 0);
INSERT INTO `wms_category` VALUES (60, '扫地机器人', 31, '', '0', 1, '2025-05-03 12:08:23', 1, '2025-05-03 12:32:48', 0);
INSERT INTO `wms_category` VALUES (61, '洗衣机', 31, '', '0', 1, '2025-05-03 12:08:31', 1, '2025-05-03 12:33:50', 0);
INSERT INTO `wms_category` VALUES (62, '微波炉', 31, '', '0', 1, '2025-05-03 12:08:45', 1, '2025-05-03 12:33:56', 0);
INSERT INTO `wms_category` VALUES (63, '苹果', 22, '', '0', 1, '2025-05-03 12:20:23', 1, '2025-05-03 12:20:29', 0);
INSERT INTO `wms_category` VALUES (64, '清洁用品', 30, '', '0', 1, '2025-05-03 12:33:29', 1, '2025-05-03 12:33:29', 1);

-- ----------------------------
-- Table structure for wms_goods
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods`;
CREATE TABLE `wms_goods`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '货物名',
  `category_id` bigint NULL DEFAULT NULL COMMENT '所属分类id',
  `category_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名',
  `amount` double NULL DEFAULT 0 COMMENT '库存数量',
  `unit` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计量单位',
  `volume_per_unit` double NULL DEFAULT 0 COMMENT '每单位所占容积',
  `low_threshold` double NULL DEFAULT 0 COMMENT '低库存阈值',
  `high_threshold` double NULL DEFAULT 0 COMMENT '高库存阈值',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `has_expiration_time` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '过期适用标志位0:无,1有',
  `price_per_unit` double NULL DEFAULT 0 COMMENT '单位价格',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '货物表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_goods
-- ----------------------------
INSERT INTO `wms_goods` VALUES (1, '光明纯牛奶250mL*24盒装整箱', 44, '牛奶', 103, '箱', 6, 100, 800, '0', 1, '2024-12-11 13:57:11', 1, '2025-05-03 13:40:06', 0, '超高温灭菌乳，常温避光保存，开启后需冷藏', '1', 55);
INSERT INTO `wms_goods` VALUES (2, '陕西普罗旺斯西红柿', 35, '西红柿', 162, '斤', 1, 70, 200, '0', 1, '2024-12-17 16:15:36', 7, '2025-05-15 10:30:40', 0, '', '1', 7);
INSERT INTO `wms_goods` VALUES (3, '农夫山泉5L*4桶整箱', 48, '矿泉水', 185, '箱', 25, 100, 400, '0', 1, '2025-02-18 13:32:11', 13, '2025-05-13 10:58:16', 0, '注意挤压导致的包装变形。\n保质期12个月', '1', 35);
INSERT INTO `wms_goods` VALUES (4, '山东寿光水果小黄瓜', 36, '黄瓜', 550, '斤', 2.6, 300, 600, '0', 1, '2025-02-18 14:05:39', 7, '2025-05-15 10:30:39', 0, '', '1', 5);
INSERT INTO `wms_goods` VALUES (5, '丹东牛奶草莓', 34, '草莓', 159, '盒', 1, 50, 300, '0', 1, '2025-02-18 15:43:00', 11, '2025-05-16 20:21:05', 0, '每盒500g，保质期短，极易损坏', '1', 40);
INSERT INTO `wms_goods` VALUES (6, '测试货物001', 18, '二级分类002', 0, '件', 40, 50, 500, '0', 1, '2025-04-29 13:33:07', 1, '2025-04-29 13:41:51', 1, '', '1', 12);
INSERT INTO `wms_goods` VALUES (7, '红富士苹果（青岛品牌）', 63, '苹果', 3500, '斤', 1.2, 1000, 4000, '0', 1, '2025-05-03 12:21:19', 11, '2025-05-16 20:19:45', 0, '', '1', 5.5);
INSERT INTO `wms_goods` VALUES (8, '巴西进口香蕉', 33, '香蕉', 1500, '斤', 2, 500, 1500, '0', 1, '2025-05-03 12:22:58', 7, '2025-05-15 10:30:38', 0, '', '1', 4.2);
INSERT INTO `wms_goods` VALUES (9, '新鲜无核葡萄（新疆产）', 32, '葡萄', 2200, '斤', 1.5, 500, 2000, '0', 1, '2025-05-03 12:23:38', 11, '2025-05-16 20:19:44', 0, '', '1', 12.5);
INSERT INTO `wms_goods` VALUES (10, '国产草饲牛肉（优质部位）', 38, '牛肉', 0, '斤', 1.2, 200, 600, '0', 1, '2025-05-03 12:24:23', 13, '2025-05-13 10:57:04', 0, '', '1', 50);
INSERT INTO `wms_goods` VALUES (11, '澳洲进口羊肉（羊排）', 39, '羊肉', 0, '斤', 1.2, 300, 800, '0', 1, '2025-05-03 12:24:59', 13, '2025-05-13 10:57:06', 0, '', '1', 45);
INSERT INTO `wms_goods` VALUES (12, '冷冻鳕鱼（挪威进口）', 41, '鳕鱼', 200, '斤', 1.5, 200, 500, '0', 1, '2025-05-03 12:26:21', 11, '2025-05-05 14:43:16', 0, '', '1', 50);
INSERT INTO `wms_goods` VALUES (13, '新鲜对虾（广东产）', 43, '对虾', 0, '斤', 0.5, 600, 1000, '0', 1, '2025-05-03 12:27:28', 1, '2025-05-03 12:27:28', 0, '', '1', 30);
INSERT INTO `wms_goods` VALUES (14, '蒙牛纯牛奶（250ml盒装）', 44, '牛奶', 0, '箱', 8, 1000, 2000, '0', 1, '2025-05-03 12:29:24', 1, '2025-05-03 12:29:24', 0, '最小出售单位：24*250mL纸箱装', '1', 64);
INSERT INTO `wms_goods` VALUES (15, '光明酸奶（草莓味）', 45, '酸奶', 250, '瓶', 0.8, 100, 300, '0', 1, '2025-05-03 12:30:59', 13, '2025-05-08 11:25:46', 0, '200mL每瓶', '1', 4.5);
INSERT INTO `wms_goods` VALUES (16, '多功能扫地机器人（科沃斯品牌）', 60, '扫地机器人', 50, '台', 1, 20, 100, '0', 1, '2025-05-03 12:34:41', 13, '2025-05-13 11:01:34', 0, '', '0', 1690);
INSERT INTO `wms_goods` VALUES (17, '欧舒丹洗发水（薰衣草香型）', 58, '洗发水', 160, '瓶', 3, 50, 200, '0', 1, '2025-05-03 12:36:45', 11, '2025-05-16 20:19:43', 0, '2升装', '0', 120);
INSERT INTO `wms_goods` VALUES (18, '奥妙超强吸水拖把（洁净型）', 59, '拖把', 0, '把', 4, 50, 400, '0', 1, '2025-05-03 12:37:36', 1, '2025-05-03 12:37:36', 0, '', '0', 80);
INSERT INTO `wms_goods` VALUES (19, '松下微波炉（700W）', 62, '微波炉', 200, '台', 50, 30, 200, '0', 1, '2025-05-03 12:39:15', 13, '2025-05-08 11:25:48', 0, '', '0', 899);
INSERT INTO `wms_goods` VALUES (20, '海尔洗衣机（8公斤容量）', 61, '洗衣机', 255, '台', 1500, 50, 150, '0', 1, '2025-05-03 12:40:10', 7, '2025-05-15 10:29:13', 0, '', '0', 2500);

-- ----------------------------
-- Table structure for wms_goods_monitor
-- ----------------------------
DROP TABLE IF EXISTS `wms_goods_monitor`;
CREATE TABLE `wms_goods_monitor`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `goods_id` bigint NOT NULL COMMENT '货物ID',
  `period` bigint NOT NULL COMMENT '监控间隔（秒）',
  `email` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '被通知人邮箱',
  `status` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '1' COMMENT '任务状态（1正常 0停用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '库存监控表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_goods_monitor
-- ----------------------------
INSERT INTO `wms_goods_monitor` VALUES (1, 5, 60, '2351782@tongji.edu.cn', '0');
INSERT INTO `wms_goods_monitor` VALUES (2, 1, 86400, '2154306@tongji.edu.cn', '0');
INSERT INTO `wms_goods_monitor` VALUES (3, 20, 60, '1309218780@qq.com', '0');

-- ----------------------------
-- Table structure for wms_inventory
-- ----------------------------
DROP TABLE IF EXISTS `wms_inventory`;
CREATE TABLE `wms_inventory`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `goods_id` bigint NULL DEFAULT NULL COMMENT '所属货物id',
  `goods_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '货物名',
  `warehouse_id` bigint NULL DEFAULT NULL COMMENT '所属仓库id',
  `warehouse_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名',
  `category_id` bigint NULL DEFAULT NULL COMMENT '所属分类id',
  `category_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名',
  `amount` double NULL DEFAULT 0 COMMENT '库存数量',
  `volume` double NULL DEFAULT 0 COMMENT '所占容积',
  `enter_time` datetime NULL DEFAULT NULL COMMENT '入库时间',
  `has_expiration_time` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '过期适用标志位0:无,1有',
  `expiration_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_inventory
-- ----------------------------
INSERT INTO `wms_inventory` VALUES (1, 1, '光明纯牛奶250mL*24盒装整箱', 1, '食品仓库-A1区', 44, '牛奶', 0, 0, '2025-01-13 11:15:45', '1', '2025-01-25 11:15:50', '0', NULL, NULL, 1, '2025-02-18 13:56:34', 0);
INSERT INTO `wms_inventory` VALUES (2, 1, '光明纯牛奶250mL*24盒装整箱', 1, '食品仓库-A1区', 44, '牛奶', 0, 0, '2025-01-20 13:39:58', '1', '2025-01-31 13:16:22', '0', 1, '2025-01-20 13:39:58', 11, '2025-05-05 14:44:32', 0);
INSERT INTO `wms_inventory` VALUES (3, 2, '陕西普罗旺斯西红柿', 1, '食品仓库-A1区', 35, '西红柿', 0, 0, '2025-01-20 13:48:40', '1', '2025-01-24 00:00:00', '0', 1, '2025-01-20 13:48:40', 13, '2025-05-06 10:41:25', 0);
INSERT INTO `wms_inventory` VALUES (4, 1, '光明纯牛奶250mL*24盒装整箱', 2, '果蔬仓库-A1区', 44, '牛奶', 0, 0, '2025-01-20 13:49:51', '1', NULL, '0', 1, '2025-01-20 13:49:51', 1, '2025-04-18 14:15:28', 0);
INSERT INTO `wms_inventory` VALUES (5, 1, '光明纯牛奶250mL*24盒装整箱', 3, '食品仓库-A2区', 44, '牛奶', 0, 0, '2025-01-21 11:37:10', '1', NULL, '0', 1, '2025-01-21 11:37:10', 1, '2025-05-03 14:08:49', 0);
INSERT INTO `wms_inventory` VALUES (6, 2, '陕西普罗旺斯西红柿', 2, '果蔬仓库-A1区', 35, '西红柿', 0, 0, '2025-01-24 12:28:37', '1', '2025-01-24 00:00:00', '0', 1, '2025-01-24 12:28:37', 13, '2025-05-06 10:41:23', 0);
INSERT INTO `wms_inventory` VALUES (7, 3, '农夫山泉5L*4桶整箱', 2, '果蔬仓库-A1区', 48, '矿泉水', 0, 0, '2025-02-18 13:33:12', '1', NULL, '0', 1, '2025-02-18 13:33:12', 1, '2025-05-03 14:12:24', 0);
INSERT INTO `wms_inventory` VALUES (8, 3, '农夫山泉5L*4桶整箱', 4, '生鲜仓库-A1区', 48, '矿泉水', 0, 0, '2025-02-18 13:43:06', '1', NULL, '0', 1, '2025-02-18 13:43:06', 1, '2025-05-03 14:12:26', 0);
INSERT INTO `wms_inventory` VALUES (9, 4, '山东寿光水果小黄瓜', 4, '生鲜仓库-A1区', 36, '黄瓜', 0, 0, '2025-02-18 14:06:26', '1', NULL, '0', 1, '2025-02-18 14:06:26', 13, '2025-05-08 12:15:20', 0);
INSERT INTO `wms_inventory` VALUES (10, 4, '山东寿光水果小黄瓜', 2, '果蔬仓库-A1区', 36, '黄瓜', 0, 0, '2025-02-18 14:07:29', '1', NULL, '0', 1, '2025-02-18 14:07:29', 13, '2025-05-08 12:15:19', 0);
INSERT INTO `wms_inventory` VALUES (11, 1, '光明纯牛奶250mL*24盒装整箱', 1, '食品仓库-A1区', 44, '牛奶', 50, 300, '2025-04-18 14:17:08', '1', NULL, '0', 1, '2025-04-18 14:17:08', 1, '2025-04-18 14:17:08', 0);
INSERT INTO `wms_inventory` VALUES (12, 1, '光明纯牛奶250mL*24盒装整箱', 1, '食品仓库-A1区', 44, '牛奶', 46, 276, '2025-04-19 19:06:50', '1', NULL, '0', 1, '2025-04-19 19:06:50', 1, '2025-04-19 19:07:56', 0);
INSERT INTO `wms_inventory` VALUES (13, 5, '丹东牛奶草莓', 5, '测试仓库001', 34, '草莓', 0, 0, '2025-04-29 13:26:48', '1', NULL, '0', 1, '2025-04-29 13:26:48', 1, '2025-04-29 13:31:49', 1);
INSERT INTO `wms_inventory` VALUES (14, 5, '丹东牛奶草莓', 5, '测试仓库001', 34, '草莓', 0, 0, '2025-04-29 13:27:28', '1', NULL, '0', 1, '2025-04-29 13:27:28', 1, '2025-04-29 13:31:51', 1);
INSERT INTO `wms_inventory` VALUES (15, 5, '丹东牛奶草莓', 4, '生鲜仓库-A1区', 34, '草莓', 0, 0, '2025-04-29 13:31:49', '1', NULL, '0', 1, '2025-04-29 13:31:49', 11, '2025-05-16 20:21:05', 0);
INSERT INTO `wms_inventory` VALUES (16, 5, '丹东牛奶草莓', 2, '果蔬仓库-A1区', 34, '草莓', 0, 0, '2025-04-29 13:31:51', '1', NULL, '0', 1, '2025-04-29 13:31:51', 1, '2025-04-30 10:42:17', 0);
INSERT INTO `wms_inventory` VALUES (17, 6, '测试货物001', 4, '生鲜仓库-A1区', 18, '二级分类002', 0, 0, '2025-04-29 13:40:29', '1', '2025-04-30 00:00:00', '0', 1, '2025-04-29 13:40:29', 1, '2025-04-29 13:41:51', 1);
INSERT INTO `wms_inventory` VALUES (18, 5, '丹东牛奶草莓', 1, '食品仓库-A1区', 34, '草莓', 0, 0, '2025-04-30 10:42:17', '1', NULL, '0', 1, '2025-04-30 10:42:17', 13, '2025-05-06 10:50:03', 0);
INSERT INTO `wms_inventory` VALUES (19, 1, '光明纯牛奶250mL*24盒装整箱', 1, '食品仓库-A1区', 44, '牛奶', 3, 18, '2025-05-03 14:08:49', '1', NULL, '0', 1, '2025-05-03 14:08:49', 1, '2025-05-03 14:08:49', 0);
INSERT INTO `wms_inventory` VALUES (20, 20, '海尔洗衣机（8公斤容量）', 6, '大件货物仓库-A1区', 61, '洗衣机', 100, 150000, '2025-05-05 14:39:11', '0', NULL, '0', 11, '2025-05-05 14:39:11', 11, '2025-05-05 14:39:11', 0);
INSERT INTO `wms_inventory` VALUES (21, 3, '农夫山泉5L*4桶整箱', 3, '食品仓库-A2区', 48, '矿泉水', 61, 1525, '2025-05-05 14:40:52', '1', '2025-09-18 00:00:00', '0', 11, '2025-05-05 14:40:52', 11, '2025-05-16 20:17:58', 0);
INSERT INTO `wms_inventory` VALUES (22, 10, '国产草饲牛肉（优质部位）', 4, '生鲜仓库-A1区', 38, '牛肉', 0, 0.00000000000002842170943040401, '2025-05-05 14:42:11', '1', '2025-05-07 09:00:00', '0', 11, '2025-05-05 14:42:11', 13, '2025-05-08 10:42:14', 0);
INSERT INTO `wms_inventory` VALUES (23, 12, '冷冻鳕鱼（挪威进口）', 4, '生鲜仓库-A1区', 41, '鳕鱼', 200, 300, '2025-05-05 14:43:16', '1', '2025-05-30 00:00:00', '0', 11, '2025-05-05 14:43:16', 11, '2025-05-05 14:43:16', 0);
INSERT INTO `wms_inventory` VALUES (24, 1, '光明纯牛奶250mL*24盒装整箱', 3, '食品仓库-A2区', 44, '牛奶', 4, 24, '2025-05-05 14:44:32', '1', NULL, '0', 11, '2025-05-05 14:44:32', 11, '2025-05-05 14:44:32', 0);
INSERT INTO `wms_inventory` VALUES (25, 10, '国产草饲牛肉（优质部位）', 4, '生鲜仓库-A1区', 38, '牛肉', 0, 0, '2025-05-06 10:43:55', '1', '2025-05-14 00:00:00', '0', 13, '2025-05-06 10:43:55', 13, '2025-05-13 10:57:04', 0);
INSERT INTO `wms_inventory` VALUES (26, 8, '巴西进口香蕉', 2, '果蔬仓库-A1区', 33, '香蕉', 0, 0, '2025-05-06 10:46:13', '1', '2025-05-12 00:00:00', '0', 13, '2025-05-06 10:46:13', 13, '2025-05-13 10:55:46', 0);
INSERT INTO `wms_inventory` VALUES (27, 5, '丹东牛奶草莓', 2, '果蔬仓库-A1区', 34, '草莓', 159, 159, '2025-05-06 10:49:42', '1', NULL, '0', 13, '2025-05-06 10:49:42', 13, '2025-05-06 10:49:42', 0);
INSERT INTO `wms_inventory` VALUES (28, 11, '澳洲进口羊肉（羊排）', 4, '生鲜仓库-A1区', 39, '羊肉', 0, 0, '2025-05-08 11:25:43', '1', '2025-05-16 00:00:00', '0', 13, '2025-05-08 11:25:43', 13, '2025-05-13 10:57:06', 0);
INSERT INTO `wms_inventory` VALUES (29, 2, '陕西普罗旺斯西红柿', 2, '果蔬仓库-A1区', 35, '西红柿', 0, 0, '2025-05-08 11:25:45', '1', '2025-05-14 00:00:00', '0', 13, '2025-05-08 11:25:45', 13, '2025-05-13 10:57:07', 0);
INSERT INTO `wms_inventory` VALUES (30, 15, '光明酸奶（草莓味）', 3, '食品仓库-A2区', 45, '酸奶', 250, 200, '2025-05-08 11:25:46', '1', '2025-05-22 00:00:00', '0', 13, '2025-05-08 11:25:46', 13, '2025-05-08 11:25:46', 0);
INSERT INTO `wms_inventory` VALUES (31, 19, '松下微波炉（700W）', 6, '大件货物仓库-A1区', 62, '微波炉', 200, 10000, '2025-05-08 11:25:48', '0', NULL, '0', 13, '2025-05-08 11:25:48', 13, '2025-05-08 11:25:48', 0);
INSERT INTO `wms_inventory` VALUES (32, 4, '山东寿光水果小黄瓜', 2, '果蔬仓库-A1区', 36, '黄瓜', 0, 0, '2025-05-08 12:50:46', '1', '2025-05-14 00:00:00', '0', 13, '2025-05-08 12:50:46', 13, '2025-05-13 10:57:03', 0);
INSERT INTO `wms_inventory` VALUES (33, 3, '农夫山泉5L*4桶整箱', 3, '食品仓库-A2区', 48, '矿泉水', 35, 875, '2025-05-13 10:58:16', '1', '2026-05-07 00:00:00', '0', 13, '2025-05-13 10:58:16', 13, '2025-05-13 10:58:16', 0);
INSERT INTO `wms_inventory` VALUES (34, 20, '海尔洗衣机（8公斤容量）', 6, '大件货物仓库-A1区', 61, '洗衣机', 55, 82500, '2025-05-13 11:01:11', '0', NULL, '0', 13, '2025-05-13 11:01:11', 7, '2025-05-15 10:29:13', 0);
INSERT INTO `wms_inventory` VALUES (35, 16, '多功能扫地机器人（科沃斯品牌）', 6, '大件货物仓库-A1区', 60, '扫地机器人', 50, 50, '2025-05-13 11:01:34', '0', NULL, '0', 13, '2025-05-13 11:01:34', 13, '2025-05-13 11:01:34', 0);
INSERT INTO `wms_inventory` VALUES (36, 20, '海尔洗衣机（8公斤容量）', 7, '大件货物仓库-A2区', 61, '洗衣机', 100, 150000, '2025-05-13 11:02:52', '0', NULL, '0', 13, '2025-05-13 11:02:52', 13, '2025-05-13 11:02:52', 0);
INSERT INTO `wms_inventory` VALUES (37, 8, '巴西进口香蕉', 2, '果蔬仓库-A1区', 33, '香蕉', 1500, 3000, '2025-05-15 10:30:38', '1', '2025-05-24 00:00:00', '0', 7, '2025-05-15 10:30:38', 7, '2025-05-15 10:30:38', 0);
INSERT INTO `wms_inventory` VALUES (38, 4, '山东寿光水果小黄瓜', 2, '果蔬仓库-A1区', 36, '黄瓜', 550, 1430, '2025-05-15 10:30:39', '1', '2025-05-26 00:00:00', '0', 7, '2025-05-15 10:30:39', 7, '2025-05-15 10:30:39', 0);
INSERT INTO `wms_inventory` VALUES (39, 2, '陕西普罗旺斯西红柿', 2, '果蔬仓库-A1区', 35, '西红柿', 160, 160, '2025-05-15 10:30:40', '1', '2025-05-24 00:00:00', '0', 7, '2025-05-15 10:30:40', 7, '2025-05-15 10:30:40', 0);
INSERT INTO `wms_inventory` VALUES (40, 3, '农夫山泉5L*4桶整箱', 1, '食品仓库-A1区', 48, '矿泉水', 89, 2225, '2025-05-16 20:17:58', '1', '2025-09-18 00:00:00', '0', 11, '2025-05-16 20:17:58', 11, '2025-05-16 20:17:58', 0);
INSERT INTO `wms_inventory` VALUES (41, 17, '欧舒丹洗发水（薰衣草香型）', 7, '大件货物仓库-A2区', 58, '洗发水', 160, 480, '2025-05-16 20:19:43', '0', NULL, '0', 11, '2025-05-16 20:19:43', 11, '2025-05-16 20:19:43', 0);
INSERT INTO `wms_inventory` VALUES (42, 9, '新鲜无核葡萄（新疆产）', 2, '果蔬仓库-A1区', 32, '葡萄', 2200, 3300, '2025-05-16 20:19:44', '1', '2025-05-29 00:00:00', '0', 11, '2025-05-16 20:19:44', 11, '2025-05-16 20:19:44', 0);
INSERT INTO `wms_inventory` VALUES (43, 7, '红富士苹果（青岛品牌）', 2, '果蔬仓库-A1区', 63, '苹果', 3500, 4200, '2025-05-16 20:19:45', '1', '2025-05-28 00:00:00', '0', 11, '2025-05-16 20:19:45', 11, '2025-05-16 20:19:45', 0);

-- ----------------------------
-- Table structure for wms_notice
-- ----------------------------
DROP TABLE IF EXISTS `wms_notice`;
CREATE TABLE `wms_notice`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告标题',
  `content` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '公告内容',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '公告表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_notice
-- ----------------------------
INSERT INTO `wms_notice` VALUES (4, '公告测试1', '少时诵诗书少时诵诗书是撒是撒是撒是撒', 'Remark', 1, '2025-03-03 12:58:43', 1, '2025-03-03 12:58:43', 0);
INSERT INTO `wms_notice` VALUES (5, '给自己的公告', '发啊啊啊啊啊啊挂电话说啥好手法好', '3', 1, '2025-03-03 13:52:03', 1, '2025-03-03 13:52:03', 0);
INSERT INTO `wms_notice` VALUES (6, '公告测试3', '滴答滴答滴答滴答滴答滴答嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎嘎啊', '777', 1, '2025-03-03 13:55:52', 1, '2025-03-03 14:06:55', 1);
INSERT INTO `wms_notice` VALUES (7, '2025放假通知', '各单位注意：\n根据国务院办公厅关于2025年部分节假日的安排，现将2025年元旦、春节、清明节、劳动节、端午节、国庆节和中秋节放假调休日期的具体安排通知如下：\n元旦：1月1日（周三）放假1天，不调休。\n春节：1月28日（农历除夕、周二）至2月4日（农历正月初七、周二）放假调休，共8天。1月26日（周日）、2月8日（周六）值班。\n清明节：4月4日（周五）至6日（周日）放假，共3天。\n劳动节：5月1日（周四）至5日（周一）放假调休，共5天。4月27日（周日）上课、上班，安排5月5日（周一）的教学工作。\n端午节：5月31日（周六）至6月2日（周一）放假，共3天。\n国庆节、中秋节：10月1日（周三）至8日（周三）放假调休，共8天。9月28日（周日）、10月11日（周六）上课、上班，9月28日（周日）安排10月7日（周二）的教学工作， 10月11日（周六）安排10月8日（周三）的教学工作。', '', 1, '2025-03-04 11:03:45', 1, '2025-04-30 10:52:46', 0);
INSERT INTO `wms_notice` VALUES (8, '公告测试-公告栏', '辅导活动何时何地很符合', '3', 1, '2025-03-04 11:26:01', 1, '2025-03-04 11:26:01', 0);
INSERT INTO `wms_notice` VALUES (9, '测试3', '123', '', 1, '2025-03-05 18:42:02', 1, '2025-03-05 18:42:02', 0);
INSERT INTO `wms_notice` VALUES (10, '凑数公告111', '实话实说，一般人真想不到 18 世纪的缅甸强悍到什么程度。不夸张地说，当时缅甸在整个东南亚就是毫无争议的霸主。\n\n原因无他，就一点，它的军队太能打了。\n\n从 1750 年到 1760 年代，短短十几年的时间，缅军基本上把中南半岛的所有国家灭了个遍：\n\n①在西北方向，缅军灭掉了印度东部的阿霍姆王国和曼尼普尔王国；\n\n②在北部，缅军灭掉了当时的掸邦；\n\n③在东南方向，缅军灭掉了当时的南掌王国（老挝）；\n\n④在南部，缅军正在消灭暹罗王国（泰国），已经攻克了暹罗首都大城，暹罗抵抗势力处于覆灭的边缘…', '', 1, '2025-03-08 19:26:45', 1, '2025-03-08 19:26:45', 0);
INSERT INTO `wms_notice` VALUES (11, '凑数公告222', '不过也不意外，毕竟移动有10亿+用户，光是APP的月活量就超过3亿。在这个AI大模型越来越成熟的时代，移动率先把智能体加入数智生活服务门户中国移动APP上去，也是十分天时地利人和了。对于这个事儿，其实是不用问“怎么看”的，无论站在哪个角度来看，它的意义都相当大，如果要从“提升用户体验”“推动业务发展”“增加行业竞争力”“构建AI生态”这样的“大格局”去写，那每个角度都能随随便便写出个七八千字。不过，我对“灵犀”感兴趣的理由，倒是没有这么高大上。我的关注点在于：有很多老年人非常喜欢它。甚至有不少人在社交平台上分享说，自从爸妈知道有“灵犀”，手机费能自己交了，也不怕花冤枉钱了，时不时还能告诉我们现在哪个套餐打折，有什么活动优惠之类，以前怎么教都不会用的线上营业厅，现在被他们玩得6得很。一个“智能体”真的能起到这么大的作用？那我确实是要来深挖一下的。\n\n作者：最后的轻语\n链接：https://zhuanlan.zhihu.com/p/28377068579\n来源：知乎\n著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。', '', 1, '2025-03-08 19:27:24', 1, '2025-03-08 19:27:24', 0);
INSERT INTO `wms_notice` VALUES (12, '凑数333', 'qwert', '', 1, '2025-04-09 19:11:20', 1, '2025-04-09 19:11:20', 0);
INSERT INTO `wms_notice` VALUES (13, '公告测试002', '修改了\n\nDispatcherServlet（前端控制器）\n\n所有请求的入口，相当于“调度中心”。\n负责将请求分发给对应的 Controller。\nHandlerMapping（处理器映射器）\n\n根据请求的 URL，找到对应的 Controller 和方法。\nController（控制器）\n\n处理业务逻辑（如查询数据库），返回 Model 和视图名称。\nViewResolver（视图解析器）\n\n将视图名称（如 \"home\"）解析为具体的视图（如 /WEB-INF/home.jsp）。\nView（视图）\n\n渲染页面，将 Model 数据展示给用户。', '马克', 1, '2025-04-29 13:20:23', 1, '2025-04-29 13:21:30', 1);
INSERT INTO `wms_notice` VALUES (14, '测试公告003', '正文', '', 1, '2025-04-29 13:22:25', 1, '2025-04-29 13:22:25', 1);
INSERT INTO `wms_notice` VALUES (15, '落户', '发布日期：2025-5-7\n各位老师同学：\n\n      2025年非上海生源毕业生落户成绩评定工作即将开始，请负责老师们及时通知学生准备好材料并按规则进行成绩评定。\n\n      具体办理时间为：2025年5月12日至2025年7月4日（工作日），四平校区为每周一、三、五，嘉定校区为每周二上午、周四全天；博士毕业生受理时间可延长至2025年12月31日（工作日）。\n\n      其他具体要求与细节见附件。', '', 1, '2025-05-10 23:30:19', 1, '2025-05-10 23:30:19', 0);
INSERT INTO `wms_notice` VALUES (16, '同济大学本科毕业设计（论文）成绩录入通知', '各学院：\n\n    2025届本科毕业设计（论文）成绩录入开始时间为2025年5月19日，第一批学生成绩录入截止时间为6月3日，第二批学生成绩录入截止时间为6月23日。\n\n本科毕业设计（论文）成绩由各学院教务科在“毕业设计”-“评审答辩管理”-“毕业设计（论文）成绩发布”里录入。录入成绩前需在“毕业设计”-“全局设置”-“毕设成绩占比配置”里设置本学院各专业毕设成绩占比：点击“新增”按钮，在对话框中设置毕业年份、学院、专业、成绩占比“答辩成绩”、“评阅成绩”、“指导教师成绩”，三项成绩比例之和为“100”，设置完成后点击“确定”按钮。\n\n指导教师成绩由指导教师在评阅学生论文后给出“指导教师成绩”（具体操作见《同济大学教务管理信息系统-本科生毕业设计操作手册（导师版）》的“上传评阅成绩”），被指定的评阅专家给出“评阅成绩”，并由教务员上传专家评阅成绩（具体操作见《同济大学教务管理信息系统-本科生毕业设计操作手册（教务员版）》的“上传评阅成绩”），学生论文答辩后由答辩小组组长给出“答辩成绩”（具体操作见《同济大学教务管理信息系统-本科生毕业设计操作手册（导师版）》的“导师维护答辩成绩”），这三项成绩均采用百分制，录入系统后会在教务科老师的“毕业设计（论文）成绩发布”里呈现，并显示优良比例和优良人数限制以及论文成绩可发布次数等信息，教务科老师可以进行相应的修改，并根据优良比的限制做适当的调整，确定最终成绩后“保存”、“审核发布”（与系统未升级前的操作相同）。', '演示用', 1, '2025-05-21 15:50:32', 1, '2025-05-21 15:50:32', 0);

-- ----------------------------
-- Table structure for wms_notice_role
-- ----------------------------
DROP TABLE IF EXISTS `wms_notice_role`;
CREATE TABLE `wms_notice_role`  (
  `notice_id` bigint NOT NULL COMMENT '公告ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`notice_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '公告和收件角色关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_notice_role
-- ----------------------------
INSERT INTO `wms_notice_role` VALUES (4, 2);
INSERT INTO `wms_notice_role` VALUES (4, 5);
INSERT INTO `wms_notice_role` VALUES (5, 1);
INSERT INTO `wms_notice_role` VALUES (7, 1);
INSERT INTO `wms_notice_role` VALUES (7, 2);
INSERT INTO `wms_notice_role` VALUES (7, 3);
INSERT INTO `wms_notice_role` VALUES (7, 4);
INSERT INTO `wms_notice_role` VALUES (7, 5);
INSERT INTO `wms_notice_role` VALUES (7, 6);
INSERT INTO `wms_notice_role` VALUES (8, 2);
INSERT INTO `wms_notice_role` VALUES (9, 1);
INSERT INTO `wms_notice_role` VALUES (10, 1);
INSERT INTO `wms_notice_role` VALUES (11, 1);
INSERT INTO `wms_notice_role` VALUES (12, 1);
INSERT INTO `wms_notice_role` VALUES (15, 8);
INSERT INTO `wms_notice_role` VALUES (16, 1);
INSERT INTO `wms_notice_role` VALUES (16, 2);
INSERT INTO `wms_notice_role` VALUES (16, 3);
INSERT INTO `wms_notice_role` VALUES (16, 4);
INSERT INTO `wms_notice_role` VALUES (16, 5);
INSERT INTO `wms_notice_role` VALUES (16, 6);
INSERT INTO `wms_notice_role` VALUES (16, 8);

-- ----------------------------
-- Table structure for wms_read_status
-- ----------------------------
DROP TABLE IF EXISTS `wms_read_status`;
CREATE TABLE `wms_read_status`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `notice_id` bigint NOT NULL COMMENT '公告ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `is_read` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT '0' COMMENT '是否已读0:未读,1已读',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `notice_id`(`notice_id` ASC, `user_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '公告已读状态表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_read_status
-- ----------------------------
INSERT INTO `wms_read_status` VALUES (1, 4, 2, '0');
INSERT INTO `wms_read_status` VALUES (2, 4, 4, '0');
INSERT INTO `wms_read_status` VALUES (3, 4, 7, '0');
INSERT INTO `wms_read_status` VALUES (4, 5, 1, '1');
INSERT INTO `wms_read_status` VALUES (7, 7, 1, '1');
INSERT INTO `wms_read_status` VALUES (8, 7, 2, '0');
INSERT INTO `wms_read_status` VALUES (9, 7, 4, '1');
INSERT INTO `wms_read_status` VALUES (10, 7, 5, '1');
INSERT INTO `wms_read_status` VALUES (11, 7, 6, '1');
INSERT INTO `wms_read_status` VALUES (12, 7, 7, '0');
INSERT INTO `wms_read_status` VALUES (13, 7, 8, '0');
INSERT INTO `wms_read_status` VALUES (14, 8, 2, '0');
INSERT INTO `wms_read_status` VALUES (15, 8, 4, '1');
INSERT INTO `wms_read_status` VALUES (16, 9, 1, '1');
INSERT INTO `wms_read_status` VALUES (17, 10, 1, '1');
INSERT INTO `wms_read_status` VALUES (18, 11, 1, '1');
INSERT INTO `wms_read_status` VALUES (19, 12, 1, '0');
INSERT INTO `wms_read_status` VALUES (23, 15, 14, '1');
INSERT INTO `wms_read_status` VALUES (24, 16, 1, '0');
INSERT INTO `wms_read_status` VALUES (25, 16, 2, '0');
INSERT INTO `wms_read_status` VALUES (26, 16, 4, '0');
INSERT INTO `wms_read_status` VALUES (27, 16, 5, '0');
INSERT INTO `wms_read_status` VALUES (28, 16, 6, '0');
INSERT INTO `wms_read_status` VALUES (29, 16, 7, '0');
INSERT INTO `wms_read_status` VALUES (30, 16, 8, '0');
INSERT INTO `wms_read_status` VALUES (31, 16, 10, '0');
INSERT INTO `wms_read_status` VALUES (32, 16, 11, '0');
INSERT INTO `wms_read_status` VALUES (33, 16, 12, '0');
INSERT INTO `wms_read_status` VALUES (34, 16, 13, '0');
INSERT INTO `wms_read_status` VALUES (35, 16, 14, '0');

-- ----------------------------
-- Table structure for wms_record
-- ----------------------------
DROP TABLE IF EXISTS `wms_record`;
CREATE TABLE `wms_record`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '申请类型：1入库,2出库,3调拨',
  `from_id` bigint NULL DEFAULT NULL COMMENT '源仓库id,入库记录为-1',
  `from_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '源仓库名',
  `to_id` bigint NULL DEFAULT NULL COMMENT '目的仓库id,出库记录为-1',
  `to_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '目的仓库名',
  `inventory_id` bigint NULL DEFAULT NULL COMMENT '所属库存id',
  `new_inventory_id` bigint NULL DEFAULT NULL COMMENT '调拨后新产生的库存id',
  `category_id` bigint NULL DEFAULT NULL COMMENT '分类ID',
  `category_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '分类名',
  `goods_id` bigint NULL DEFAULT NULL COMMENT '货物ID',
  `goods_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '货物名',
  `amount` double NULL DEFAULT 0 COMMENT '移动数量',
  `volume` double NULL DEFAULT 0 COMMENT '所占容积',
  `apply_by` bigint NULL DEFAULT NULL COMMENT '发起人',
  `apply_time` datetime NULL DEFAULT NULL COMMENT '发起时间',
  `apply_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '申请备注',
  `approve_by` bigint NULL DEFAULT NULL COMMENT '审批人',
  `approve_time` datetime NULL DEFAULT NULL COMMENT '审批时间',
  `approve_status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '审批状态0:未审批,1审批通过,2审批驳回,3无法审批',
  `approve_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '审批备注',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  `has_expiration_time` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '过期适用标志位0:无,1有',
  `expiration_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '出入库记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_record
-- ----------------------------
INSERT INTO `wms_record` VALUES (1, '1', -1, NULL, 1, '食品仓库-A1区', 2, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 4, 24, 1, '2025-01-14 15:02:44', '备注备注', 1, '2025-01-20 13:39:58', '1', '', '0', 1, '2025-01-14 15:03:28', 1, '2025-03-04 11:54:11', 0, '1', '2025-01-31 13:16:22');
INSERT INTO `wms_record` VALUES (2, '1', -1, NULL, 1, '食品仓库-A1区', 3, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 22, 22, 1, '2025-01-16 13:35:07', '入库申请测试', 1, '2025-01-20 13:48:40', '1', '', '0', 1, '2025-01-16 13:35:07', 1, '2025-03-04 11:54:11', 0, '1', '2025-01-24 00:00:00');
INSERT INTO `wms_record` VALUES (3, '1', -1, NULL, 2, '果蔬仓库-A1区', NULL, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 222, 1332, 8, '2025-01-16 13:36:46', '另一个用户的入库申请', 1, '2025-01-20 11:40:47', '2', '入库数量过多', '0', 8, '2025-01-16 13:36:46', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (4, '2', 1, '食品仓库-A1区', -1, NULL, 1, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 5, 30, 1, '2025-01-17 13:31:19', '出库申请测试', 1, '2025-01-21 11:33:49', '1', '允许出库', '0', 1, '2025-01-17 13:31:19', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (5, '3', 1, '食品仓库-A1区', 2, '果蔬仓库-A1区', 1, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 44, 264, 1, '2025-01-17 13:54:39', '调拨申请测试', NULL, NULL, '3', '', '0', 1, '2025-01-17 13:54:39', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (6, '2', 1, '食品仓库-A1区', -1, NULL, 1, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 7, 42, 1, '2025-01-20 13:36:47', '出库', NULL, NULL, '3', '', '0', 1, '2025-01-20 13:36:47', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (7, '1', -1, NULL, 2, '果蔬仓库-A1区', 4, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 10, 60, 1, '2025-01-20 13:47:05', '', 1, '2025-01-20 13:49:51', '1', '可通过', '0', 1, '2025-01-20 13:47:05', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (8, '3', 1, '食品仓库-A1区', 3, '食品仓库-A2区', 2, 24, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 4, 24, 1, '2025-01-21 11:36:08', '调拨', 11, '2025-05-05 14:44:32', '1', '', '0', 1, '2025-01-21 11:36:08', 11, '2025-05-05 14:44:32', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (9, '3', 1, '食品仓库-A1区', 3, '食品仓库-A2区', 1, 5, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 3, 18, 1, '2025-01-21 11:36:26', '调拨', 1, '2025-01-21 11:37:10', '1', '', '0', 1, '2025-01-21 11:36:26', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (10, '2', 1, '食品仓库-A1区', -1, NULL, 1, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 5, 30, 1, '2025-01-24 12:24:56', '全部出库', 1, '2025-01-24 12:25:05', '1', '', '0', 1, '2025-01-24 12:24:56', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (11, '2', 1, '食品仓库-A1区', -1, NULL, 3, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 15, 15, 1, '2025-01-24 12:26:04', '出库', NULL, NULL, '3', '', '0', 1, '2025-01-24 12:26:04', 1, '2025-03-04 11:54:11', 0, '1', '2025-01-24 00:00:00');
INSERT INTO `wms_record` VALUES (12, '3', 1, '食品仓库-A1区', 2, '果蔬仓库-A1区', 3, 6, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 15, 15, 1, '2025-01-24 12:26:30', '调拨', 1, '2025-01-24 12:28:37', '1', '', '0', 1, '2025-01-24 12:26:30', 1, '2025-03-04 11:54:11', 0, '1', '2025-01-24 00:00:00');
INSERT INTO `wms_record` VALUES (13, '1', -1, NULL, 1, '食品仓库-A1区', NULL, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 45, 45, 1, '2025-01-24 12:36:06', '', NULL, NULL, '3', '入库货物过期', '0', 1, '2025-01-24 12:36:06', 1, '2025-03-04 11:54:11', 0, '1', '2025-01-25 12:35:46');
INSERT INTO `wms_record` VALUES (14, '1', -1, NULL, 2, '果蔬仓库-A1区', 7, NULL, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 207, 207000, 1, '2025-02-18 13:32:48', '入库', 1, '2025-02-18 13:33:12', '1', '分类统计测试用', '0', 1, '2025-02-18 13:32:48', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (15, '3', 2, '果蔬仓库-A1区', 4, '生鲜仓库-A1区', 7, 8, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 206, 206000, 1, '2025-02-18 13:42:48', '调拨', 1, '2025-02-18 13:43:06', '1', '', '0', 1, '2025-02-18 13:42:48', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (16, '1', -1, NULL, 4, '生鲜仓库-A1区', 9, NULL, 36, '黄瓜', 4, '山东寿光水果小黄瓜', 20, 52000, 1, '2025-02-18 14:06:15', '', 1, '2025-02-18 14:06:26', '1', '', '0', 1, '2025-02-18 14:06:15', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (17, '2', 4, '生鲜仓库-A1区', -1, NULL, 8, NULL, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 6, 6000, 1, '2025-02-18 14:06:46', '', 1, '2025-02-18 14:06:51', '1', '', '0', 1, '2025-02-18 14:06:46', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (18, '3', 4, '生鲜仓库-A1区', 2, '果蔬仓库-A1区', 9, 10, 36, '黄瓜', 4, '山东寿光水果小黄瓜', 1, 2600, 1, '2025-02-18 14:07:21', '', 1, '2025-02-18 14:07:29', '1', '', '0', 1, '2025-02-18 14:07:21', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (19, '1', -1, NULL, 1, '食品仓库-A1区', NULL, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 1, 6, 1, '2025-02-18 15:35:42', 'bug测试', 1, '2025-02-18 15:36:20', '2', '', '0', 1, '2025-02-18 15:35:42', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (20, '1', -1, NULL, 1, '食品仓库-A1区', NULL, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 1, 1, 1, '2025-02-18 15:36:47', 'bug测试', NULL, NULL, '3', '入库货物过期', '0', 1, '2025-02-18 15:36:47', 1, '2025-03-04 11:54:11', 0, '1', '2025-02-27 00:00:00');
INSERT INTO `wms_record` VALUES (21, '2', 4, '生鲜仓库-A1区', -1, NULL, 8, NULL, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 50, 50000, 6, '2025-02-20 12:44:59', '', 7, '2025-02-20 12:46:27', '1', '通过', '0', 6, '2025-02-20 12:44:59', 1, '2025-03-04 11:54:11', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (22, '1', -1, NULL, 1, '食品仓库-A1区', NULL, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 1, 1, 6, '2025-02-20 13:25:15', '', NULL, NULL, '3', '入库货物过期', '0', 6, '2025-02-20 13:25:15', 1, '2025-03-04 11:54:11', 0, '1', '2025-02-20 13:25:07');
INSERT INTO `wms_record` VALUES (23, '1', -1, NULL, 1, '食品仓库-A1区', NULL, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 1, 1, 1, '2025-02-20 14:32:21', '', NULL, NULL, '3', '入库货物过期', '0', 1, '2025-02-20 14:32:21', 1, '2025-03-04 11:54:11', 0, '1', '2025-02-20 14:33:00');
INSERT INTO `wms_record` VALUES (24, '2', 2, '果蔬仓库-A1区', -1, NULL, 4, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 10, 60, 1, '2025-04-18 14:14:56', '', 1, '2025-04-18 14:15:28', '1', '', '0', 1, '2025-04-18 14:14:56', 1, '2025-04-18 14:15:28', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (25, '1', -1, NULL, 1, '食品仓库-A1区', 11, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 50, 300, 1, '2025-04-18 14:16:46', '', 1, '2025-04-18 14:17:08', '1', '', '0', 1, '2025-04-18 14:16:46', 1, '2025-04-18 14:17:08', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (26, '1', -1, NULL, 1, '食品仓库-A1区', 12, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 100, 600, 1, '2025-04-19 19:06:36', '', 1, '2025-04-19 19:06:50', '1', '', '0', 1, '2025-04-19 19:06:36', 1, '2025-04-19 19:06:50', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (27, '2', 1, '食品仓库-A1区', -1, NULL, 12, NULL, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 54, 324, 1, '2025-04-19 19:07:39', '', 1, '2025-04-19 19:07:57', '1', '', '0', 1, '2025-04-19 19:07:39', 1, '2025-04-19 19:07:57', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (28, '2', 4, '生鲜仓库-A1区', -1, NULL, 8, NULL, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 81, 81000, 1, '2025-04-23 12:17:04', '', 1, '2025-04-23 12:17:20', '1', '', '0', 1, '2025-04-23 12:17:04', 1, '2025-04-23 12:17:20', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (29, '1', -1, NULL, 5, '测试仓库001', 13, NULL, 34, '草莓', 5, '丹东牛奶草莓', 22, 22, 1, '2025-04-29 13:26:24', 'd', 1, '2025-04-29 13:26:48', '1', 'ww', '0', 1, '2025-04-29 13:26:24', 1, '2025-04-29 13:26:48', 1, '1', NULL);
INSERT INTO `wms_record` VALUES (30, '1', -1, NULL, 5, '测试仓库001', 14, NULL, 34, '草莓', 5, '丹东牛奶草莓', 2000, 2000, 1, '2025-04-29 13:27:24', '', 1, '2025-04-29 13:27:28', '1', '', '0', 1, '2025-04-29 13:27:24', 1, '2025-04-29 13:27:28', 1, '1', NULL);
INSERT INTO `wms_record` VALUES (31, '3', 5, '测试仓库001', 2, '果蔬仓库-A1区', 14, 16, 34, '草莓', 5, '丹东牛奶草莓', 2000, 2000, 1, '2025-04-29 13:31:03', '', 1, '2025-04-29 13:31:51', '1', '', '0', 1, '2025-04-29 13:31:03', 1, '2025-04-29 13:31:51', 1, '1', NULL);
INSERT INTO `wms_record` VALUES (32, '3', 5, '测试仓库001', 4, '生鲜仓库-A1区', 13, 15, 34, '草莓', 5, '丹东牛奶草莓', 22, 22, 1, '2025-04-29 13:31:37', '', 1, '2025-04-29 13:31:49', '1', '', '0', 1, '2025-04-29 13:31:37', 1, '2025-04-29 13:31:49', 1, '1', NULL);
INSERT INTO `wms_record` VALUES (33, '1', -1, NULL, 4, '生鲜仓库-A1区', NULL, NULL, 18, '二级分类002', 6, '测试货物001', 12, 480, 1, '2025-04-29 13:37:22', '', NULL, NULL, '3', '入库货物过期', '0', 1, '2025-04-29 13:37:22', 1, '2025-04-29 13:37:35', 1, '1', '2025-04-29 13:37:12');
INSERT INTO `wms_record` VALUES (34, '1', -1, NULL, 4, '生鲜仓库-A1区', 17, NULL, 18, '二级分类002', 6, '测试货物001', 20, 800, 1, '2025-04-29 13:39:52', '', 1, '2025-04-29 13:40:29', '1', '', '0', 1, '2025-04-29 13:39:52', 1, '2025-04-29 13:40:29', 1, '1', '2025-04-30 00:00:00');
INSERT INTO `wms_record` VALUES (35, '2', 4, '生鲜仓库-A1区', -1, NULL, 17, NULL, 18, '二级分类002', 6, '测试货物001', 20, 800, 1, '2025-04-29 13:41:38', '', 1, '2025-04-29 13:41:51', '1', '', '0', 1, '2025-04-29 13:41:38', 1, '2025-04-29 13:41:51', 1, '1', '2025-04-30 00:00:00');
INSERT INTO `wms_record` VALUES (36, '1', -1, NULL, 1, '食品仓库-A1区', NULL, NULL, 34, '草莓', 5, '丹东牛奶草莓', 500000, 500000, 1, '2025-04-29 14:06:34', '', NULL, NULL, '3', '目的仓库剩余容量不足', '0', 1, '2025-04-29 14:06:34', 1, '2025-04-29 14:32:18', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (37, '2', 2, '果蔬仓库-A1区', -1, NULL, 16, NULL, 34, '草莓', 5, '丹东牛奶草莓', 1500, 1500, 1, '2025-04-29 14:33:06', '', 1, '2025-04-29 14:33:24', '1', '', '0', 1, '2025-04-29 14:33:06', 1, '2025-04-29 14:33:24', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (38, '2', 2, '果蔬仓库-A1区', -1, NULL, 16, NULL, 34, '草莓', 5, '丹东牛奶草莓', 1172, 1172, 1, '2025-04-29 14:33:16', '', 1, '2025-04-29 14:33:35', '2', '数量不足', '0', 1, '2025-04-29 14:33:16', 1, '2025-04-29 14:33:35', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (39, '3', 2, '果蔬仓库-A1区', 3, '食品仓库-A2区', 16, NULL, 34, '草莓', 5, '丹东牛奶草莓', 266, 266, 1, '2025-04-30 10:41:04', '', 1, '2025-04-30 10:41:33', '2', '仓库容量不足', '0', 1, '2025-04-30 10:41:04', 1, '2025-04-30 10:41:33', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (40, '3', 2, '果蔬仓库-A1区', 1, '食品仓库-A1区', 16, 18, 34, '草莓', 5, '丹东牛奶草莓', 500, 500, 1, '2025-04-30 10:42:03', '', 1, '2025-04-30 10:42:17', '1', '', '0', 1, '2025-04-30 10:42:03', 1, '2025-04-30 10:42:17', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (41, '3', 3, '食品仓库-A2区', 1, '食品仓库-A1区', 5, 19, 44, '牛奶', 1, '光明纯牛奶250mL*24盒装整箱', 3, 18, 1, '2025-05-03 14:08:37', '', 1, '2025-05-03 14:08:49', '1', '', '0', 1, '2025-05-03 14:08:37', 1, '2025-05-03 14:08:49', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (42, '2', 4, '生鲜仓库-A1区', -1, NULL, 8, NULL, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 69, 69000, 1, '2025-05-03 14:11:31', '', 1, '2025-05-03 14:12:26', '1', '', '0', 1, '2025-05-03 14:11:31', 1, '2025-05-03 14:12:26', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (43, '2', 2, '果蔬仓库-A1区', -1, NULL, 7, NULL, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 1, 1000, 1, '2025-05-03 14:12:09', '', 1, '2025-05-03 14:12:24', '1', '', '0', 1, '2025-05-03 14:12:09', 1, '2025-05-03 14:12:24', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (44, '1', -1, NULL, 6, '大件货物仓库-A1区', 20, NULL, 61, '洗衣机', 20, '海尔洗衣机（8公斤容量）', 100, 150000, 10, '2025-05-05 14:38:22', '家用电器', 11, '2025-05-05 14:39:11', '1', '', '0', 10, '2025-05-05 14:38:22', 11, '2025-05-05 14:39:11', 0, '0', NULL);
INSERT INTO `wms_record` VALUES (45, '1', -1, NULL, 3, '食品仓库-A2区', 21, NULL, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 150, 3750, 10, '2025-05-05 14:40:40', '', 11, '2025-05-05 14:40:52', '1', '', '0', 10, '2025-05-05 14:40:40', 11, '2025-05-05 14:40:52', 0, '1', '2025-09-18 00:00:00');
INSERT INTO `wms_record` VALUES (46, '1', -1, NULL, 4, '生鲜仓库-A1区', 22, NULL, 38, '牛肉', 10, '国产草饲牛肉（优质部位）', 500, 600, 10, '2025-05-05 14:41:57', '', 11, '2025-05-05 14:42:11', '1', '', '0', 10, '2025-05-05 14:41:57', 11, '2025-05-05 14:42:11', 0, '1', '2025-05-07 09:00:00');
INSERT INTO `wms_record` VALUES (47, '1', -1, NULL, 4, '生鲜仓库-A1区', 23, NULL, 41, '鳕鱼', 12, '冷冻鳕鱼（挪威进口）', 200, 300, 10, '2025-05-05 14:42:51', '', 11, '2025-05-05 14:43:16', '1', '', '0', 10, '2025-05-05 14:42:51', 11, '2025-05-05 14:43:16', 0, '1', '2025-05-30 00:00:00');
INSERT INTO `wms_record` VALUES (48, '1', -1, NULL, 3, '食品仓库-A2区', NULL, NULL, 44, '牛奶', 14, '蒙牛纯牛奶（250ml盒装）', 260, 2080, 10, '2025-05-05 14:43:51', '', NULL, NULL, '3', '目的仓库剩余容量不足', '0', 10, '2025-05-05 14:43:51', 11, '2025-05-05 14:44:12', 0, '1', '2025-06-18 00:00:00');
INSERT INTO `wms_record` VALUES (49, '2', 1, '食品仓库-A1区', -1, NULL, 3, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 7, 7, 12, '2025-05-06 10:34:38', '过期食品清理', 13, '2025-05-06 10:41:25', '1', '', '0', 12, '2025-05-06 10:34:38', 13, '2025-05-06 10:41:25', 0, '1', '2025-01-24 00:00:00');
INSERT INTO `wms_record` VALUES (50, '2', 2, '果蔬仓库-A1区', -1, NULL, 6, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 15, 15, 12, '2025-05-06 10:41:11', '过期食品清理', 13, '2025-05-06 10:41:23', '1', '', '0', 12, '2025-05-06 10:41:11', 13, '2025-05-06 10:41:23', 0, '1', '2025-01-24 00:00:00');
INSERT INTO `wms_record` VALUES (51, '2', 4, '生鲜仓库-A1区', -1, NULL, 22, NULL, 38, '牛肉', 10, '国产草饲牛肉（优质部位）', 322, 386.4, 12, '2025-05-06 10:42:53', '', 13, '2025-05-06 10:43:10', '1', '', '0', 12, '2025-05-06 10:42:53', 13, '2025-05-06 10:43:10', 0, '1', '2025-05-07 09:00:00');
INSERT INTO `wms_record` VALUES (52, '1', -1, NULL, 4, '生鲜仓库-A1区', 25, NULL, 38, '牛肉', 10, '国产草饲牛肉（优质部位）', 100, 120, 12, '2025-05-06 10:43:52', '', 13, '2025-05-06 10:43:55', '1', '', '0', 12, '2025-05-06 10:43:52', 13, '2025-05-06 10:43:55', 0, '1', '2025-05-14 00:00:00');
INSERT INTO `wms_record` VALUES (53, '1', -1, NULL, 2, '果蔬仓库-A1区', 26, NULL, 33, '香蕉', 8, '巴西进口香蕉', 1000, 2000, 12, '2025-05-06 10:46:09', '', 13, '2025-05-06 10:46:13', '1', '', '0', 12, '2025-05-06 10:46:09', 13, '2025-05-06 10:46:13', 0, '1', '2025-05-12 00:00:00');
INSERT INTO `wms_record` VALUES (54, '3', 1, '食品仓库-A1区', 2, '果蔬仓库-A1区', 18, 27, 34, '草莓', 5, '丹东牛奶草莓', 159, 159, 12, '2025-05-06 10:48:14', '', 13, '2025-05-06 10:49:42', '1', '', '0', 12, '2025-05-06 10:48:14', 13, '2025-05-06 10:49:42', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (55, '2', 1, '食品仓库-A1区', -1, NULL, 18, NULL, 34, '草莓', 5, '丹东牛奶草莓', 341, 341, 12, '2025-05-06 10:49:51', '', 13, '2025-05-06 10:50:03', '1', '', '0', 12, '2025-05-06 10:49:51', 13, '2025-05-06 10:50:03', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (56, '2', 4, '生鲜仓库-A1区', -1, NULL, 22, NULL, 38, '牛肉', 10, '国产草饲牛肉（优质部位）', 178, 213.6, 6, '2025-05-08 10:41:59', '过期清理', 13, '2025-05-08 10:42:14', '1', '', '0', 6, '2025-05-08 10:41:59', 13, '2025-05-08 10:42:14', 0, '1', '2025-05-07 09:00:00');
INSERT INTO `wms_record` VALUES (57, '1', -1, NULL, 6, '大件货物仓库-A1区', 31, NULL, 62, '微波炉', 19, '松下微波炉（700W）', 200, 10000, 6, '2025-05-08 10:43:12', '', 13, '2025-05-08 11:25:48', '1', '', '0', 6, '2025-05-08 10:43:12', 13, '2025-05-08 11:25:48', 0, '0', NULL);
INSERT INTO `wms_record` VALUES (58, '1', -1, NULL, 3, '食品仓库-A2区', 30, NULL, 45, '酸奶', 15, '光明酸奶（草莓味）', 250, 200, 6, '2025-05-08 10:44:43', '', 13, '2025-05-08 11:25:46', '1', '', '0', 6, '2025-05-08 10:44:43', 13, '2025-05-08 11:25:46', 0, '1', '2025-05-22 00:00:00');
INSERT INTO `wms_record` VALUES (59, '1', -1, NULL, 2, '果蔬仓库-A1区', 29, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 100, 100, 6, '2025-05-08 11:25:13', '', 13, '2025-05-08 11:25:45', '1', '', '0', 6, '2025-05-08 11:25:13', 13, '2025-05-08 11:25:45', 0, '1', '2025-05-14 00:00:00');
INSERT INTO `wms_record` VALUES (60, '1', -1, NULL, 4, '生鲜仓库-A1区', 28, NULL, 39, '羊肉', 11, '澳洲进口羊肉（羊排）', 500, 600, 6, '2025-05-08 11:25:32', '', 13, '2025-05-08 11:25:43', '1', '', '0', 6, '2025-05-08 11:25:32', 13, '2025-05-08 11:25:43', 0, '1', '2025-05-16 00:00:00');
INSERT INTO `wms_record` VALUES (61, '2', 4, '生鲜仓库-A1区', -1, NULL, 9, NULL, 36, '黄瓜', 4, '山东寿光水果小黄瓜', 19, 49400, 6, '2025-05-08 12:15:07', '', 13, '2025-05-08 12:15:20', '1', '', '0', 6, '2025-05-08 12:15:07', 13, '2025-05-08 12:15:20', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (62, '2', 2, '果蔬仓库-A1区', -1, NULL, 10, NULL, 36, '黄瓜', 4, '山东寿光水果小黄瓜', 1, 2600, 6, '2025-05-08 12:15:09', '', 13, '2025-05-08 12:15:19', '1', '', '0', 6, '2025-05-08 12:15:09', 13, '2025-05-08 12:15:19', 0, '1', NULL);
INSERT INTO `wms_record` VALUES (63, '1', -1, NULL, 2, '果蔬仓库-A1区', 32, NULL, 36, '黄瓜', 4, '山东寿光水果小黄瓜', 500, 1300, 6, '2025-05-08 12:50:39', '', 13, '2025-05-08 12:50:46', '1', '', '0', 6, '2025-05-08 12:50:39', 13, '2025-05-08 12:50:46', 0, '1', '2025-05-14 00:00:00');
INSERT INTO `wms_record` VALUES (64, '2', 2, '果蔬仓库-A1区', -1, NULL, 26, NULL, 33, '香蕉', 8, '巴西进口香蕉', 1000, 2000, 12, '2025-05-13 10:55:41', '过期食品', 13, '2025-05-13 10:55:46', '1', '', '0', 12, '2025-05-13 10:55:41', 13, '2025-05-13 10:55:46', 0, '1', '2025-05-12 00:00:00');
INSERT INTO `wms_record` VALUES (65, '2', 2, '果蔬仓库-A1区', -1, NULL, 29, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 100, 100, 12, '2025-05-13 10:56:09', '', 13, '2025-05-13 10:57:07', '1', '', '0', 12, '2025-05-13 10:56:09', 13, '2025-05-13 10:57:07', 0, '1', '2025-05-14 00:00:00');
INSERT INTO `wms_record` VALUES (66, '2', 4, '生鲜仓库-A1区', -1, NULL, 28, NULL, 39, '羊肉', 11, '澳洲进口羊肉（羊排）', 500, 600, 12, '2025-05-13 10:56:25', '', 13, '2025-05-13 10:57:06', '1', '', '0', 12, '2025-05-13 10:56:25', 13, '2025-05-13 10:57:06', 0, '1', '2025-05-16 00:00:00');
INSERT INTO `wms_record` VALUES (67, '2', 4, '生鲜仓库-A1区', -1, NULL, 25, NULL, 38, '牛肉', 10, '国产草饲牛肉（优质部位）', 100, 120, 12, '2025-05-13 10:56:43', '', 13, '2025-05-13 10:57:04', '1', '', '0', 12, '2025-05-13 10:56:43', 13, '2025-05-13 10:57:04', 0, '1', '2025-05-14 00:00:00');
INSERT INTO `wms_record` VALUES (68, '2', 2, '果蔬仓库-A1区', -1, NULL, 32, NULL, 36, '黄瓜', 4, '山东寿光水果小黄瓜', 500, 1300, 12, '2025-05-13 10:57:00', '', 13, '2025-05-13 10:57:03', '1', '', '0', 12, '2025-05-13 10:57:00', 13, '2025-05-13 10:57:03', 0, '1', '2025-05-14 00:00:00');
INSERT INTO `wms_record` VALUES (69, '1', -1, NULL, 3, '食品仓库-A2区', 33, NULL, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 35, 875, 12, '2025-05-13 10:58:12', '', 13, '2025-05-13 10:58:16', '1', '', '0', 12, '2025-05-13 10:58:12', 13, '2025-05-13 10:58:16', 0, '1', '2026-05-07 00:00:00');
INSERT INTO `wms_record` VALUES (70, '1', -1, NULL, 6, '大件货物仓库-A1区', 34, NULL, 61, '洗衣机', 20, '海尔洗衣机（8公斤容量）', 200, 300000, 12, '2025-05-13 11:00:53', '', 13, '2025-05-13 11:01:11', '1', '', '0', 12, '2025-05-13 11:00:53', 13, '2025-05-13 11:01:11', 0, '0', NULL);
INSERT INTO `wms_record` VALUES (71, '1', -1, NULL, 6, '大件货物仓库-A1区', 35, NULL, 60, '扫地机器人', 16, '多功能扫地机器人（科沃斯品牌）', 50, 50, 12, '2025-05-13 11:01:30', '', 13, '2025-05-13 11:01:34', '1', '', '0', 12, '2025-05-13 11:01:30', 13, '2025-05-13 11:01:34', 0, '0', NULL);
INSERT INTO `wms_record` VALUES (72, '3', 6, '大件货物仓库-A1区', 7, '大件货物仓库-A2区', 34, 36, 61, '洗衣机', 20, '海尔洗衣机（8公斤容量）', 100, 150000, 12, '2025-05-13 11:02:43', '', 13, '2025-05-13 11:02:52', '1', '', '0', 12, '2025-05-13 11:02:43', 13, '2025-05-13 11:02:52', 0, '0', NULL);
INSERT INTO `wms_record` VALUES (73, '2', 6, '大件货物仓库-A1区', -1, NULL, 34, NULL, 61, '洗衣机', 20, '海尔洗衣机（8公斤容量）', 45, 67500, 10, '2025-05-15 10:29:03', '', 7, '2025-05-15 10:29:13', '1', '', '0', 10, '2025-05-15 10:29:03', 7, '2025-05-15 10:29:13', 0, '0', NULL);
INSERT INTO `wms_record` VALUES (74, '1', -1, NULL, 2, '果蔬仓库-A1区', 39, NULL, 35, '西红柿', 2, '陕西普罗旺斯西红柿', 160, 160, 10, '2025-05-15 10:29:40', '', 7, '2025-05-15 10:30:40', '1', '', '0', 10, '2025-05-15 10:29:40', 7, '2025-05-15 10:30:40', 0, '1', '2025-05-24 00:00:00');
INSERT INTO `wms_record` VALUES (75, '1', -1, NULL, 2, '果蔬仓库-A1区', 38, NULL, 36, '黄瓜', 4, '山东寿光水果小黄瓜', 550, 1430, 10, '2025-05-15 10:29:57', '', 7, '2025-05-15 10:30:39', '1', '', '0', 10, '2025-05-15 10:29:57', 7, '2025-05-15 10:30:39', 0, '1', '2025-05-26 00:00:00');
INSERT INTO `wms_record` VALUES (76, '1', -1, NULL, 2, '果蔬仓库-A1区', 37, NULL, 33, '香蕉', 8, '巴西进口香蕉', 1500, 3000, 10, '2025-05-15 10:30:26', '', 7, '2025-05-15 10:30:38', '1', '', '0', 10, '2025-05-15 10:30:26', 7, '2025-05-15 10:30:38', 0, '1', '2025-05-24 00:00:00');
INSERT INTO `wms_record` VALUES (77, '3', 3, '食品仓库-A2区', 1, '食品仓库-A1区', 21, 40, 48, '矿泉水', 3, '农夫山泉5L*4桶整箱', 89, 2225, 1, '2025-05-16 20:17:24', '', 11, '2025-05-16 20:17:58', '1', '', '0', 1, '2025-05-16 20:17:24', 11, '2025-05-16 20:17:58', 0, '1', '2025-09-18 00:00:00');
INSERT INTO `wms_record` VALUES (78, '1', -1, NULL, 2, '果蔬仓库-A1区', 43, NULL, 63, '苹果', 7, '红富士苹果（青岛品牌）', 3500, 4200, 1, '2025-05-16 20:18:51', '', 11, '2025-05-16 20:19:45', '1', '', '0', 1, '2025-05-16 20:18:51', 11, '2025-05-16 20:19:45', 0, '1', '2025-05-28 00:00:00');
INSERT INTO `wms_record` VALUES (79, '1', -1, NULL, 2, '果蔬仓库-A1区', 42, NULL, 32, '葡萄', 9, '新鲜无核葡萄（新疆产）', 2200, 3300, 1, '2025-05-16 20:19:11', '', 11, '2025-05-16 20:19:44', '1', '', '0', 1, '2025-05-16 20:19:11', 11, '2025-05-16 20:19:44', 0, '1', '2025-05-29 00:00:00');
INSERT INTO `wms_record` VALUES (80, '1', -1, NULL, 7, '大件货物仓库-A2区', 41, NULL, 58, '洗发水', 17, '欧舒丹洗发水（薰衣草香型）', 160, 480, 1, '2025-05-16 20:19:37', '', 11, '2025-05-16 20:19:43', '1', '', '0', 1, '2025-05-16 20:19:37', 11, '2025-05-16 20:19:43', 0, '0', NULL);
INSERT INTO `wms_record` VALUES (81, '2', 4, '生鲜仓库-A1区', -1, NULL, 15, NULL, 34, '草莓', 5, '丹东牛奶草莓', 22, 22, 1, '2025-05-16 20:20:56', '', 11, '2025-05-16 20:21:05', '1', '', '0', 1, '2025-05-16 20:20:56', 11, '2025-05-16 20:21:05', 0, '1', NULL);

-- ----------------------------
-- Table structure for wms_warehouse
-- ----------------------------
DROP TABLE IF EXISTS `wms_warehouse`;
CREATE TABLE `wms_warehouse`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '仓库名',
  `capacity` double NULL DEFAULT 0 COMMENT '仓库容量',
  `remaining_capacity` double NULL DEFAULT 0 COMMENT '剩余容量',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '状态0:正常,1禁用',
  `create_by` bigint NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` bigint NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `del_flag` int NULL DEFAULT 0 COMMENT '删除标志（0代表未删除，1代表已删除）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '仓库表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wms_warehouse
-- ----------------------------
INSERT INTO `wms_warehouse` VALUES (1, '食品仓库-A1区', 10000, 7181, '0', 1, '2024-12-22 14:09:57', 11, '2025-05-16 20:17:58', 0, '存放非散装的可食用物品，保质期一般在3个月以上');
INSERT INTO `wms_warehouse` VALUES (2, '果蔬仓库-A1区', 3200099, 3187850, '0', 1, '2025-01-05 18:12:56', 11, '2025-05-16 20:19:45', 0, '水果、蔬菜类短保质期货物，注意控制仓库温度，防货物挤压受损');
INSERT INTO `wms_warehouse` VALUES (3, '食品仓库-A2区', 5000, 2376, '0', 4, '2025-01-21 11:35:16', 11, '2025-05-16 20:17:58', 0, '');
INSERT INTO `wms_warehouse` VALUES (4, '生鲜仓库-A1区', 1000000, 999700, '0', 1, '2025-02-18 13:42:35', 11, '2025-05-16 20:21:05', 0, '生肉类，包括牲畜肉和禽类肉。低温冷库');
INSERT INTO `wms_warehouse` VALUES (5, '测试仓库001', 2000, 2000, '0', 1, '2025-04-29 13:25:28', 1, '2025-04-29 13:32:09', 1, 'dd');
INSERT INTO `wms_warehouse` VALUES (6, '大件货物仓库-A1区', 500000, 257450, '0', 1, '2025-05-05 14:37:00', 7, '2025-05-15 10:29:13', 0, '存放大型货物，单件商品在500升以上，无保质期');
INSERT INTO `wms_warehouse` VALUES (7, '大件货物仓库-A2区', 300000, 149520, '0', 1, '2025-05-13 11:02:08', 11, '2025-05-16 20:19:43', 0, '');

-- ----------------------------
-- Triggers structure for table wms_category
-- ----------------------------
DROP TRIGGER IF EXISTS `update_category_name_in_goods`;
delimiter ;;
CREATE TRIGGER `update_category_name_in_goods` AFTER UPDATE ON `wms_category` FOR EACH ROW BEGIN
    
    IF OLD.name != NEW.name THEN
        UPDATE wms_goods
        SET category_name = NEW.name
        WHERE category_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_category
-- ----------------------------
DROP TRIGGER IF EXISTS `update_category_name_in_inventory`;
delimiter ;;
CREATE TRIGGER `update_category_name_in_inventory` AFTER UPDATE ON `wms_category` FOR EACH ROW BEGIN
    
    IF OLD.name != NEW.name THEN
        UPDATE wms_inventory
        SET category_name = NEW.name
        WHERE category_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_category
-- ----------------------------
DROP TRIGGER IF EXISTS `update_category_name_in_record`;
delimiter ;;
CREATE TRIGGER `update_category_name_in_record` AFTER UPDATE ON `wms_category` FOR EACH ROW BEGIN
    
    IF OLD.name != NEW.name THEN
        UPDATE wms_record
        SET category_name = NEW.name
        WHERE category_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_goods
-- ----------------------------
DROP TRIGGER IF EXISTS `update_goods_name_in_record`;
delimiter ;;
CREATE TRIGGER `update_goods_name_in_record` AFTER UPDATE ON `wms_goods` FOR EACH ROW BEGIN
    
    IF OLD.name != NEW.name THEN
        UPDATE wms_record
        SET goods_name = NEW.name
        WHERE goods_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_goods
-- ----------------------------
DROP TRIGGER IF EXISTS `update_goods_name_in_inventory`;
delimiter ;;
CREATE TRIGGER `update_goods_name_in_inventory` AFTER UPDATE ON `wms_goods` FOR EACH ROW BEGIN
    
    IF OLD.name != NEW.name THEN
        UPDATE wms_inventory
        SET goods_name = NEW.name
        WHERE goods_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_goods
-- ----------------------------
DROP TRIGGER IF EXISTS `update_expire_in_inventory`;
delimiter ;;
CREATE TRIGGER `update_expire_in_inventory` AFTER UPDATE ON `wms_goods` FOR EACH ROW BEGIN
    
    IF OLD.has_expiration_time != NEW.has_expiration_time THEN
        UPDATE wms_inventory
        SET has_expiration_time = NEW.has_expiration_time
        WHERE goods_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_goods
-- ----------------------------
DROP TRIGGER IF EXISTS `update_expire_in_record`;
delimiter ;;
CREATE TRIGGER `update_expire_in_record` AFTER UPDATE ON `wms_goods` FOR EACH ROW BEGIN
    
    IF OLD.has_expiration_time != NEW.has_expiration_time THEN
        UPDATE wms_record
        SET has_expiration_time = NEW.has_expiration_time
        WHERE goods_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_goods
-- ----------------------------
DROP TRIGGER IF EXISTS `update_category_in_inventory`;
delimiter ;;
CREATE TRIGGER `update_category_in_inventory` AFTER UPDATE ON `wms_goods` FOR EACH ROW BEGIN
    
    IF OLD.category_id != NEW.category_id THEN
        UPDATE wms_inventory
        SET category_id = NEW.category_id
        WHERE goods_id = NEW.id;
				UPDATE wms_inventory
        SET category_name = NEW.category_name
        WHERE goods_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_goods
-- ----------------------------
DROP TRIGGER IF EXISTS `update_category_in_record`;
delimiter ;;
CREATE TRIGGER `update_category_in_record` AFTER UPDATE ON `wms_goods` FOR EACH ROW BEGIN
    
    IF OLD.category_id != NEW.category_id THEN
        UPDATE wms_record
        SET category_id = NEW.category_id
        WHERE goods_id = NEW.id;
				UPDATE wms_record
        SET category_name = NEW.category_name
        WHERE goods_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_goods
-- ----------------------------
DROP TRIGGER IF EXISTS `update_del_flag_in_inventory`;
delimiter ;;
CREATE TRIGGER `update_del_flag_in_inventory` AFTER UPDATE ON `wms_goods` FOR EACH ROW BEGIN
    
    IF OLD.del_flag != NEW.del_flag THEN
        UPDATE wms_inventory
        SET del_flag = NEW.del_flag
        WHERE goods_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_goods
-- ----------------------------
DROP TRIGGER IF EXISTS `update_del_flag_in_record`;
delimiter ;;
CREATE TRIGGER `update_del_flag_in_record` AFTER UPDATE ON `wms_goods` FOR EACH ROW BEGIN
    
    IF OLD.del_flag != NEW.del_flag THEN
        UPDATE wms_record
        SET del_flag = NEW.del_flag
        WHERE goods_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_warehouse
-- ----------------------------
DROP TRIGGER IF EXISTS `update_warehouse_name_in_inventory`;
delimiter ;;
CREATE TRIGGER `update_warehouse_name_in_inventory` AFTER UPDATE ON `wms_warehouse` FOR EACH ROW BEGIN
    
    IF OLD.name != NEW.name THEN
        UPDATE wms_inventory
        SET warehouse_name = NEW.name
        WHERE warehouse_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_warehouse
-- ----------------------------
DROP TRIGGER IF EXISTS `update_warehouse_name_in_record`;
delimiter ;;
CREATE TRIGGER `update_warehouse_name_in_record` AFTER UPDATE ON `wms_warehouse` FOR EACH ROW BEGIN
    
    IF OLD.name != NEW.name THEN
        UPDATE wms_record
        SET from_name = NEW.name
        WHERE from_id = NEW.id;
				UPDATE wms_record
        SET to_name = NEW.name
        WHERE to_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_warehouse
-- ----------------------------
DROP TRIGGER IF EXISTS `w_update_del_flag_in_inventory`;
delimiter ;;
CREATE TRIGGER `w_update_del_flag_in_inventory` AFTER UPDATE ON `wms_warehouse` FOR EACH ROW BEGIN
    
    IF OLD.del_flag != NEW.del_flag THEN
        UPDATE wms_inventory
        SET del_flag = NEW.del_flag
        WHERE warehouse_id = NEW.id;
    END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table wms_warehouse
-- ----------------------------
DROP TRIGGER IF EXISTS `w_update_del_flag_in_record`;
delimiter ;;
CREATE TRIGGER `w_update_del_flag_in_record` AFTER UPDATE ON `wms_warehouse` FOR EACH ROW BEGIN
    
    IF OLD.del_flag != NEW.del_flag THEN
        UPDATE wms_record
        SET del_flag = NEW.del_flag
        WHERE from_id=NEW.id OR to_id=NEW.id;
    END IF;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
