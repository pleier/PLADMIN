/*
Navicat MySQL Data Transfer

Source Server         : pladmin
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : plei

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-02-12 20:42:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for db_pldb
-- ----------------------------
DROP TABLE IF EXISTS `db_pldb`;
CREATE TABLE `db_pldb` (
  `USERNAME` varchar(50) DEFAULT NULL,
  `BACKUP_TIME` varchar(32) DEFAULT NULL,
  `TABLENAME` varchar(50) DEFAULT NULL,
  `SQLPATH` varchar(300) DEFAULT NULL,
  `TYPE` int(1) DEFAULT NULL,
  `DBSIZE` varchar(10) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `PLDB_ID` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_pldb
-- ----------------------------

-- ----------------------------
-- Table structure for db_timingbackup
-- ----------------------------
DROP TABLE IF EXISTS `db_timingbackup`;
CREATE TABLE `db_timingbackup` (
  `JOBNAME` varchar(50) DEFAULT NULL,
  `CREATE_TIME` varchar(32) DEFAULT NULL,
  `TABLENAME` varchar(50) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  `FHTIME` varchar(30) DEFAULT NULL,
  `TIMEEXPLAIN` varchar(100) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `TIMINGBACKUP_ID` varchar(100) NOT NULL,
  PRIMARY KEY (`TIMINGBACKUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of db_timingbackup
-- ----------------------------

-- ----------------------------
-- Table structure for oa_department
-- ----------------------------
DROP TABLE IF EXISTS `oa_department`;
CREATE TABLE `oa_department` (
  `DEPARTMENT_ID` varchar(100) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `NAME_EN` varchar(50) DEFAULT NULL,
  `BIANMA` varchar(50) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `HEADMAN` varchar(30) DEFAULT NULL,
  `TEL` varchar(50) DEFAULT NULL,
  `FUNCTIONS` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_department
-- ----------------------------
INSERT INTO `oa_department` VALUES ('6a900af209f84ab4aef90ef2a7f06b57', '市场部', '市场部', '00201', 'ec5a7ef328514a6b8b05625c500e4a43', null, null, null, null, null);
INSERT INTO `oa_department` VALUES ('8c22b9d90a9b45eebd2fdf53bbfbd3f6', 'A公司', 'a', '001', '0', null, '张三', '18585858585', null, null);
INSERT INTO `oa_department` VALUES ('ec5a7ef328514a6b8b05625c500e4a43', 'B公司', 'b', '002', '0', null, null, null, null, null);
INSERT INTO `oa_department` VALUES ('faf6e761bf674edd8e9edcd8cfad889b', '财务部', 'caiwubu', '00101', '8c22b9d90a9b45eebd2fdf53bbfbd3f6', null, '李四', null, null, null);

-- ----------------------------
-- Table structure for sys_app_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_user`;
CREATE TABLE `sys_app_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `SFID` varchar(100) DEFAULT NULL,
  `START_TIME` varchar(100) DEFAULT NULL,
  `END_TIME` varchar(100) DEFAULT NULL,
  `YEARS` int(10) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app_user
-- ----------------------------

-- ----------------------------
-- Table structure for sys_createcode
-- ----------------------------
DROP TABLE IF EXISTS `sys_createcode`;
CREATE TABLE `sys_createcode` (
  `CREATECODE_ID` varchar(100) NOT NULL,
  `PACKAGENAME` varchar(50) DEFAULT NULL,
  `OBJECTNAME` varchar(50) DEFAULT NULL,
  `TABLENAME` varchar(50) DEFAULT NULL,
  `FIELDLIST` varchar(3000) DEFAULT NULL,
  `CREATETIME` varchar(100) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `FHTYPE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`CREATECODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_createcode
-- ----------------------------
INSERT INTO `sys_createcode` VALUES ('1fac1058105f43ef97bc74c47a9fede9', 'SDF', 'SDF', 'TB_,fh,SDF', 'SDF,fh,String,fh,SDF,fh,是,fh,无,fh,255Q313596790', '2016-01-27 00:25:23', 'DSFSD', 'single');

-- ----------------------------
-- Table structure for sys_dictionaries
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionaries`;
CREATE TABLE `sys_dictionaries` (
  `DICTIONARIES_ID` varchar(100) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `NAME_EN` varchar(50) DEFAULT NULL,
  `BIANMA` varchar(50) DEFAULT NULL,
  `ORDER_BY` int(11) NOT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `TBSNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`DICTIONARIES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dictionaries
-- ----------------------------
INSERT INTO `sys_dictionaries` VALUES ('192868de9ce04d7095d9d21d043b4841', '数学', 'shuxue', '00202', '2', '4e041940bda04556baaf26d2d3bab59f', '数学', null);
INSERT INTO `sys_dictionaries` VALUES ('19e3fd04902b4b8f833db8587215af24', '绿色', 'green', '00102', '2', 'df013372518a4bebb174bd6d9b2591b2', '绿色', null);
INSERT INTO `sys_dictionaries` VALUES ('4e041940bda04556baaf26d2d3bab59f', '课程', 'kecheng', '002', '2', '0', '课程', null);
INSERT INTO `sys_dictionaries` VALUES ('7ed83a428bba44cda8d052a3f586363a', '北京', 'beijing', '00301', '1', 'f3dcd0a97a0343219bed5b3417fc1cd4', '北京', null);
INSERT INTO `sys_dictionaries` VALUES ('bf0b5e40dade412bb660a3f6758f0d62', '语文', 'yuwen', '00201', '1', '4e041940bda04556baaf26d2d3bab59f', '语文', null);
INSERT INTO `sys_dictionaries` VALUES ('c724abfd58ad4c82b23aec71b2133228', '红色', 'red', '00101', '1', 'df013372518a4bebb174bd6d9b2591b2', '红色', null);
INSERT INTO `sys_dictionaries` VALUES ('df013372518a4bebb174bd6d9b2591b2', '分类', 'fenlei', '001', '1', '0', '分类', null);
INSERT INTO `sys_dictionaries` VALUES ('f3dcd0a97a0343219bed5b3417fc1cd4', '地区', 'diqu', '003', '3', '0', '地区', null);
INSERT INTO `sys_dictionaries` VALUES ('f52ae38bc7fb4741930da93241f0c6e3', '上海', 'shanghai', '00302', '2', 'f3dcd0a97a0343219bed5b3417fc1cd4', '上海', null);
INSERT INTO `sys_dictionaries` VALUES ('fb91f73f2da240e9b204d7215ec6da49', '朝阳区', 'chaoyang', '0030101', '1', '7ed83a428bba44cda8d052a3f586363a', '朝阳区', null);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL,
  `MENU_NAME` varchar(255) DEFAULT NULL,
  `MENU_URL` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `MENU_ORDER` varchar(100) DEFAULT NULL,
  `MENU_ICON` varchar(60) DEFAULT NULL,
  `MENU_TYPE` varchar(10) DEFAULT NULL,
  `MENU_STATE` int(1) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '#', '0', '1', 'menu-icon fa fa-desktop blue', '2', '1');
INSERT INTO `sys_menu` VALUES ('2', '权限管理', '#', '1', '1', 'menu-icon fa fa-lock black', '1', '1');
INSERT INTO `sys_menu` VALUES ('3', '角色(基础权限)', 'role.do', '2', '1', 'menu-icon fa fa-key orange', '1', '1');
INSERT INTO `sys_menu` VALUES ('4', '菜单管理', 'menu/listAllMenu.do', '1', '3', 'menu-icon fa fa-folder-open-o brown', '1', '1');
INSERT INTO `sys_menu` VALUES ('5', '按钮权限  ', 'buttonrights/list.do', '2', '2', 'menu-icon fa fa-key green', '1', '1');
INSERT INTO `sys_menu` VALUES ('6', '按钮管理  ', 'fhbutton/list.do', '1', '2', 'menu-icon fa fa-download orange', '1', '1');
INSERT INTO `sys_menu` VALUES ('7', '数据字典  ', 'dictionaries/listAllDict.do?DICTIONARIES_ID=0', '1', '4', 'menu-icon fa fa-book purple', '1', '1');
INSERT INTO `sys_menu` VALUES ('9', '在线管理  ', 'onlinemanager/list.do', '1', '5', 'menu-icon fa fa-laptop green', '1', '1');
INSERT INTO `sys_menu` VALUES ('10', '用户管理', '#', '0', '2', 'menu-icon fa fa-users blue', '2', '1');
INSERT INTO `sys_menu` VALUES ('11', '系统用户', 'user/listUsers.do', '10', '1', 'menu-icon fa fa-users green', '1', '1');
INSERT INTO `sys_menu` VALUES ('12', '会员管理', 'happuser/listUsers.do', '10', '2', 'menu-icon fa fa-users orange', '1', '1');
INSERT INTO `sys_menu` VALUES ('13', '系统工具', '#', '0', '3', 'menu-icon fa fa-cog black', '2', '1');
INSERT INTO `sys_menu` VALUES ('14', '代码生成器', '#', '13', '0', 'menu-icon fa fa-cogs brown', '1', '1');
INSERT INTO `sys_menu` VALUES ('15', '性能监控', 'druid/index.html', '13', '8', 'menu-icon fa fa-tachometer red', '1', '1');
INSERT INTO `sys_menu` VALUES ('16', '接口测试', 'tool/interfaceTest.do', '13', '2', 'menu-icon fa fa-exchange green', '1', '1');
INSERT INTO `sys_menu` VALUES ('17', '发送邮件', 'tool/goSendEmail.do', '13', '3', 'menu-icon fa fa-envelope-o green', '1', '1');
INSERT INTO `sys_menu` VALUES ('18', '置二维码', 'tool/goTwoDimensionCode.do', '13', '4', 'menu-icon fa fa-barcode green', '1', '1');
INSERT INTO `sys_menu` VALUES ('19', '图表报表', 'tool/fusionchartsdemo.do', '13', '5', 'menu-icon fa fa-bar-chart-o black', '1', '1');
INSERT INTO `sys_menu` VALUES ('20', '地图工具', 'tool/map.do', '13', '6', 'menu-icon fa fa-globe black', '1', '1');
INSERT INTO `sys_menu` VALUES ('21', '打印测试', 'tool/printTest.do', '13', '7', 'menu-icon fa fa-hdd-o grey', '1', '1');
INSERT INTO `sys_menu` VALUES ('22', '微信管理', '#', '0', '4', 'menu-icon fa fa-comments purple', '2', '1');
INSERT INTO `sys_menu` VALUES ('23', '关注回复', 'textmsg/goSubscribe.do', '22', '1', 'menu-icon fa fa-comment orange', '1', '1');
INSERT INTO `sys_menu` VALUES ('24', '文本回复', 'textmsg/list.do', '22', '2', 'menu-icon fa fa-comment green', '1', '1');
INSERT INTO `sys_menu` VALUES ('25', '图文回复', 'imgmsg/list.do', '22', '3', 'menu-icon fa fa-comment purple', '1', '1');
INSERT INTO `sys_menu` VALUES ('26', '应用命令', 'command/list.do', '22', '4', 'menu-icon fa fa-comment grey', '1', '1');
INSERT INTO `sys_menu` VALUES ('27', '信息管理', '#', '0', '5', 'menu-icon fa fa-credit-card green', '2', '1');
INSERT INTO `sys_menu` VALUES ('28', '图片管理', '#', '27', '1', 'menu-icon fa fa-folder-o pink', '1', '1');
INSERT INTO `sys_menu` VALUES ('29', '站内信', 'fhsms/list.do', '27', '3', 'menu-icon fa fa-envelope green', '1', '1');
INSERT INTO `sys_menu` VALUES ('30', '一级菜单', '#', '0', '8', 'menu-icon fa fa-fire orange', '2', '1');
INSERT INTO `sys_menu` VALUES ('31', '二级菜单', '#', '30', '1', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('32', '三级菜单', '#', '31', '1', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('33', '四级菜单1', '#', '32', '1', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('34', '四级菜单2', '#', '32', '2', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('35', '五级菜单1', '#', '33', '1', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('36', '五级菜单2', '#', '33', '2', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('37', '六级菜单1', '#', '35', '1', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('38', '六级菜单2', 'login_default.do', '35', '2', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('39', '七级菜单1', '#', '37', '1', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('40', '七级菜单2', '#', '37', '2', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('41', '八级菜单', 'login_default.do', '39', '1', 'menu-icon fa fa-leaf black', '1', '1');
INSERT INTO `sys_menu` VALUES ('42', 'OA办公', '#', '0', '6', 'menu-icon fa fa-laptop green', '2', '1');
INSERT INTO `sys_menu` VALUES ('43', '组织机构', 'department/listAllDepartment.do?DEPARTMENT_ID=0', '42', '1', 'menu-icon fa fa-users green', '1', '1');
INSERT INTO `sys_menu` VALUES ('44', '表单构建器', 'tool/goFormbuilder.do', '13', '1', 'menu-icon fa fa-pencil-square-o blue', '1', '1');
INSERT INTO `sys_menu` VALUES ('45', '数据库管理', '#', '0', '7', 'menu-icon fa fa-hdd-o pink', '2', '1');
INSERT INTO `sys_menu` VALUES ('46', '数据库备份', 'brdb/listAllTable.do', '45', '1', 'menu-icon fa fa-cloud-upload blue', '1', '1');
INSERT INTO `sys_menu` VALUES ('47', '备份定时器', 'timingbackup/list.do', '45', '2', 'menu-icon fa fa-tachometer blue', '1', '1');
INSERT INTO `sys_menu` VALUES ('48', '数据库还原', 'brdb/list.do', '45', '3', 'menu-icon fa fa-cloud-download blue', '1', '1');
INSERT INTO `sys_menu` VALUES ('49', 'SQL编辑器', 'sqledit/view.do', '45', '4', 'menu-icon fa fa-pencil-square-o blue', '1', '1');
INSERT INTO `sys_menu` VALUES ('50', '图片列表', 'pictures/list.do', '28', '1', 'menu-icon fa fa-folder-open-o green', '1', '1');
INSERT INTO `sys_menu` VALUES ('51', '图片爬虫', 'pictures/goImageCrawler.do', '28', '2', 'menu-icon fa fa-cloud-download green', '1', '1');
INSERT INTO `sys_menu` VALUES ('52', '主附结构', 'attached/list.do', '27', '2', 'menu-icon fa fa-folder-open blue', '1', '1');
INSERT INTO `sys_menu` VALUES ('53', '正向生成', 'createCode/list.do', '14', '1', 'menu-icon fa fa-cogs green', '1', '1');
INSERT INTO `sys_menu` VALUES ('54', '反向生成', 'recreateCode/list.do', '14', '2', 'menu-icon fa fa-cogs blue', '1', '1');

-- ----------------------------
-- Table structure for sys_plbutton
-- ----------------------------
DROP TABLE IF EXISTS `sys_plbutton`;
CREATE TABLE `sys_plbutton` (
  `PLBUTTON_ID` varchar(100) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `QX_NAME` varchar(50) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PLBUTTON_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_plbutton
-- ----------------------------
INSERT INTO `sys_plbutton` VALUES ('03dea53bf6384eed8781b3c57d388213', '发邮件', 'email', '发送电子邮件');
INSERT INTO `sys_plbutton` VALUES ('39d204b7359540f8a41a91c138e900ad', '发短信', 'sms', '发送短信');
INSERT INTO `sys_plbutton` VALUES ('9cbe838b0ab747ce893ed35057ccd0ad', '导入EXCEL', 'FromExcel', '导入EXCEL到系统用户');
INSERT INTO `sys_plbutton` VALUES ('fde6526262f4438fadb7e57bcb053a27', '站内信', 'FHSMS', '发送站内信');
INSERT INTO `sys_plbutton` VALUES ('ff948f26c0e0473296966a7796984e59', '导出EXCEL', 'toExcel', '导出EXCEL');

-- ----------------------------
-- Table structure for sys_plsms
-- ----------------------------
DROP TABLE IF EXISTS `sys_plsms`;
CREATE TABLE `sys_plsms` (
  `PLSMS_ID` varchar(100) NOT NULL,
  `CONTENT` varchar(1000) DEFAULT NULL,
  `TYPE` varchar(5) DEFAULT NULL,
  `TO_USERNAME` varchar(255) DEFAULT NULL,
  `FROM_USERNAME` varchar(255) DEFAULT NULL,
  `SEND_TIME` varchar(100) DEFAULT NULL,
  `STATUS` varchar(5) DEFAULT NULL,
  `SANME_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`PLSMS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_plsms
-- ----------------------------
INSERT INTO `sys_plsms` VALUES ('09cb6d65c59640b09b02c52566945e22', 'www', '1', 'admin', 'san', '2016-01-27 00:28:32', '1', '074d101bff4e43d0a4d039a62083035e');
INSERT INTO `sys_plsms` VALUES ('ab5e95ac9e8b48308d6705472765f8e1', 'www', '1', 'san', 'admin', '2016-01-27 00:40:00', '1', '0a93549085de46958e8caef1762161c2');
INSERT INTO `sys_plsms` VALUES ('d672b67c2f9f4e208b5110797d2cc63d', 'www', '2', 'san', 'admin', '2016-01-27 00:28:32', '2', '074d101bff4e43d0a4d039a62083035e');
INSERT INTO `sys_plsms` VALUES ('e9e0eea3c32340d69b00823377582a96', 'www', '2', 'admin', 'san', '2016-01-27 00:40:00', '2', '0a93549085de46958e8caef1762161c2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(100) NOT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `ADD_QX` varchar(255) DEFAULT NULL,
  `DEL_QX` varchar(255) DEFAULT NULL,
  `EDIT_QX` varchar(255) DEFAULT NULL,
  `CHA_QX` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理组', '36028797018963710', '0', '1', '1', '1', '1');
INSERT INTO `sys_role` VALUES ('1ddd479fe1f04fd383ad1fd0bd3d5f1b', '初级会员', '498', '2', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('2', '会员组', '498', '0', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('2f753afa151d4d41b18a659854c263d4', '二级管理员', '36028797018963710', '1', '0', '0', '0', '4398046511102');
INSERT INTO `sys_role` VALUES ('49b1919c40534ec4b66fdbcaa8fd90d3', '中级会员', '498', '2', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('70f470602b2345cbbc66ec61e28b3978', '高级会员', '498', '2', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('74e4c7a35ab348cba009ab461165dd9a', '总监', null, 'bcb873636abb4defbe8eeddf2cb410f9', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('915454d4b4244b46a1e08355f960b9d4', '经理', null, 'bcb873636abb4defbe8eeddf2cb410f9', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('94b10e8f8b1c4ae39e13d1316813b1d4', '三级管理员', '36028797018963710', '1', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('9f17e9a225ff43359b223d47ec5924f8', '一级管理员', '36028797018963710', '1', '4398046511102', '4398046511102', '4398046511102', '4398046511102');
INSERT INTO `sys_role` VALUES ('b2865b9ebe7741b8bcbb1474436ecc25', '普通用户', '36028797018963710', '1', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('bcb873636abb4defbe8eeddf2cb410f9', '职位组', null, '0', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('c99c42835b3b4c0b9ce647111da80bc3', '主管', null, 'bcb873636abb4defbe8eeddf2cb410f9', '0', '0', '0', '0');
INSERT INTO `sys_role` VALUES ('fb88b3db52b44a80bc10b810d69e1d73', '组长', null, 'bcb873636abb4defbe8eeddf2cb410f9', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for sys_role_plbutton
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_plbutton`;
CREATE TABLE `sys_role_plbutton` (
  `RB_ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `BUTTON_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`RB_ID`),
  KEY `BUTTON_ID` (`BUTTON_ID`),
  KEY `ROLE_ID` (`ROLE_ID`),
  CONSTRAINT `sys_role_plbutton_ibfk_1` FOREIGN KEY (`BUTTON_ID`) REFERENCES `sys_plbutton` (`PLBUTTON_ID`) ON DELETE CASCADE,
  CONSTRAINT `sys_role_plbutton_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_plbutton
-- ----------------------------
INSERT INTO `sys_role_plbutton` VALUES ('6720e77d550947d2b7c71587c7cf8ad3', '9f17e9a225ff43359b223d47ec5924f8', 'fde6526262f4438fadb7e57bcb053a27');
INSERT INTO `sys_role_plbutton` VALUES ('a411bcb60aa149e2b40bb106015fe6b7', '94b10e8f8b1c4ae39e13d1316813b1d4', 'fde6526262f4438fadb7e57bcb053a27');
INSERT INTO `sys_role_plbutton` VALUES ('b7b7929e230c44d7b124a4b082cabe78', '2f753afa151d4d41b18a659854c263d4', 'fde6526262f4438fadb7e57bcb053a27');
INSERT INTO `sys_role_plbutton` VALUES ('be8988d2b5d44f60ac78408060ae7090', '9f17e9a225ff43359b223d47ec5924f8', '9cbe838b0ab747ce893ed35057ccd0ad');
INSERT INTO `sys_role_plbutton` VALUES ('c40021b2b6ca423d80a94186b98bb8d1', '2f753afa151d4d41b18a659854c263d4', '03dea53bf6384eed8781b3c57d388213');
INSERT INTO `sys_role_plbutton` VALUES ('d488458695a84a3da0a2e0f9f75f89b8', '9f17e9a225ff43359b223d47ec5924f8', '39d204b7359540f8a41a91c138e900ad');
INSERT INTO `sys_role_plbutton` VALUES ('ddc26ba94c0c43448756884d810db56b', '2f753afa151d4d41b18a659854c263d4', 'ff948f26c0e0473296966a7796984e59');
INSERT INTO `sys_role_plbutton` VALUES ('de2aa2e2851d4623926ad575922d9620', '94b10e8f8b1c4ae39e13d1316813b1d4', '9cbe838b0ab747ce893ed35057ccd0ad');
INSERT INTO `sys_role_plbutton` VALUES ('e02c0c508be346458514b180427f3197', '9f17e9a225ff43359b223d47ec5924f8', 'ff948f26c0e0473296966a7796984e59');
INSERT INTO `sys_role_plbutton` VALUES ('e0f15f39efb24508a108498081daff15', '9f17e9a225ff43359b223d47ec5924f8', '03dea53bf6384eed8781b3c57d388213');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(15) DEFAULT NULL,
  `STATUS` varchar(255) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `SKIN` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'de41b7fb99201d8334c23c014db35ecd92df81bc', '系统管理员', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2018-02-10 20:15:09', '0:0:0:0:0:0:0:1', '0', '最高统治者', 'default', '247151115@qq.com', '001', '18788888888');

-- ----------------------------
-- Table structure for tb_attached
-- ----------------------------
DROP TABLE IF EXISTS `tb_attached`;
CREATE TABLE `tb_attached` (
  `NAME` varchar(255) DEFAULT NULL,
  `FDESCRIBE` varchar(255) DEFAULT NULL,
  `PRICE` decimal(11,2) DEFAULT NULL,
  `CTIME` varchar(32) DEFAULT NULL,
  `ATTACHED_ID` varchar(100) NOT NULL,
  PRIMARY KEY (`ATTACHED_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_attached
-- ----------------------------
INSERT INTO `tb_attached` VALUES ('fff', 'fff', '22.00', '2016-04-21 17:49:15', '9cc2d2fe42c5407aae2aa134f965a14d');

-- ----------------------------
-- Table structure for tb_attachedmx
-- ----------------------------
DROP TABLE IF EXISTS `tb_attachedmx`;
CREATE TABLE `tb_attachedmx` (
  `NAME` varchar(255) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `CTIME` varchar(32) DEFAULT NULL,
  `PRICE` decimal(11,2) DEFAULT NULL,
  `ATTACHEDMX_ID` varchar(100) NOT NULL,
  `ATTACHED_ID` varchar(100) NOT NULL,
  PRIMARY KEY (`ATTACHEDMX_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_attachedmx
-- ----------------------------
INSERT INTO `tb_attachedmx` VALUES ('sdf', 'sdf', '2016-04-05', '222.00', '896180afba744306a7a14335e4ce59e6', '9cc2d2fe42c5407aae2aa134f965a14d');

-- ----------------------------
-- Table structure for tb_pictures
-- ----------------------------
DROP TABLE IF EXISTS `tb_pictures`;
CREATE TABLE `tb_pictures` (
  `PICTURES_ID` varchar(100) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `PATH` varchar(255) DEFAULT NULL,
  `CREATETIME` varchar(100) DEFAULT NULL,
  `MASTER_ID` varchar(255) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`PICTURES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pictures
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_command
-- ----------------------------
DROP TABLE IF EXISTS `weixin_command`;
CREATE TABLE `weixin_command` (
  `COMMAND_ID` varchar(100) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL,
  `COMMANDCODE` varchar(255) DEFAULT NULL,
  `CREATETIME` varchar(255) DEFAULT NULL,
  `STATUS` int(1) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`COMMAND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_command
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_imgmsg
-- ----------------------------
DROP TABLE IF EXISTS `weixin_imgmsg`;
CREATE TABLE `weixin_imgmsg` (
  `IMGMSG_ID` varchar(100) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL,
  `CREATETIME` varchar(100) DEFAULT NULL,
  `STATUS` int(11) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `TITLE1` varchar(255) DEFAULT NULL,
  `DESCRIPTION1` varchar(255) DEFAULT NULL,
  `IMGURL1` varchar(255) DEFAULT NULL,
  `TOURL1` varchar(255) DEFAULT NULL,
  `TITLE2` varchar(255) DEFAULT NULL,
  `DESCRIPTION2` varchar(255) DEFAULT NULL,
  `IMGURL2` varchar(255) DEFAULT NULL,
  `TOURL2` varchar(255) DEFAULT NULL,
  `TITLE3` varchar(255) DEFAULT NULL,
  `DESCRIPTION3` varchar(255) DEFAULT NULL,
  `IMGURL3` varchar(255) DEFAULT NULL,
  `TOURL3` varchar(255) DEFAULT NULL,
  `TITLE4` varchar(255) DEFAULT NULL,
  `DESCRIPTION4` varchar(255) DEFAULT NULL,
  `IMGURL4` varchar(255) DEFAULT NULL,
  `TOURL4` varchar(255) DEFAULT NULL,
  `TITLE5` varchar(255) DEFAULT NULL,
  `DESCRIPTION5` varchar(255) DEFAULT NULL,
  `IMGURL5` varchar(255) DEFAULT NULL,
  `TOURL5` varchar(255) DEFAULT NULL,
  `TITLE6` varchar(255) DEFAULT NULL,
  `DESCRIPTION6` varchar(255) DEFAULT NULL,
  `IMGURL6` varchar(255) DEFAULT NULL,
  `TOURL6` varchar(255) DEFAULT NULL,
  `TITLE7` varchar(255) DEFAULT NULL,
  `DESCRIPTION7` varchar(255) DEFAULT NULL,
  `IMGURL7` varchar(255) DEFAULT NULL,
  `TOURL7` varchar(255) DEFAULT NULL,
  `TITLE8` varchar(255) DEFAULT NULL,
  `DESCRIPTION8` varchar(255) DEFAULT NULL,
  `IMGURL8` varchar(255) DEFAULT NULL,
  `TOURL8` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IMGMSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_imgmsg
-- ----------------------------

-- ----------------------------
-- Table structure for weixin_textmsg
-- ----------------------------
DROP TABLE IF EXISTS `weixin_textmsg`;
CREATE TABLE `weixin_textmsg` (
  `TEXTMSG_ID` varchar(100) NOT NULL,
  `KEYWORD` varchar(255) DEFAULT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `CREATETIME` varchar(100) DEFAULT NULL,
  `STATUS` int(2) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TEXTMSG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of weixin_textmsg
-- ----------------------------
INSERT INTO `weixin_textmsg` VALUES ('073bf67c581e4750bfdb75fa3f40ef74', '你好', '你也好', '2016-01-27 00:26:36', '1', '1');
