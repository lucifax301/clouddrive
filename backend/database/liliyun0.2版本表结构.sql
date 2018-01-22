
-- ----------------------------
-- Table structure for t_class_manage
-- ----------------------------
DROP TABLE IF EXISTS `t_class_manage`;
CREATE TABLE `t_class_manage` (
  `classid` int(11) NOT NULL AUTO_INCREMENT,
  `classname` varchar(255) DEFAULT NULL,
  `opentime` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `singleamount` int(11) DEFAULT NULL,
  `personnum` int(11) DEFAULT NULL,
  `graduatednum` int(11) DEFAULT NULL,
  `passrate` varchar(255) DEFAULT NULL,
  `period` varchar(255) DEFAULT NULL,
  `classdesc` varchar(255) DEFAULT NULL,
  `cuid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `muid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`classid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_coach
-- ----------------------------
DROP TABLE IF EXISTS `t_coach`;
CREATE TABLE `t_coach` (
  `coachId` int(11) NOT NULL AUTO_INCREMENT,
  `instid` int(11) DEFAULT NULL COMMENT '培训机构主键',
  `inscode` varchar(16) DEFAULT NULL COMMENT '培训机构编号 统一编号',
  `coachnum` varchar(16) DEFAULT NULL COMMENT '教练员编号 统一编号',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别 1:男性;2:女性',
  `idcard` varchar(128) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(256) DEFAULT NULL COMMENT '联系地址',
  `photo` int(11) DEFAULT NULL COMMENT 'NOT NULL: 照片文件ID，成功上传的教练员头像文件ID',
  `fingerprint` int(11) DEFAULT NULL COMMENT '指纹图片ID 成功上传的指纹图片ID',
  `photo_url` varchar(256) DEFAULT NULL COMMENT '照片url路径',
  `fingerprint_url` varchar(256) DEFAULT NULL COMMENT '指纹图片url',
  `drilicence` varchar(128) DEFAULT NULL COMMENT '驾驶证号',
  `fstdrilicdate` varchar(64) DEFAULT NULL COMMENT '驾驶证初领日期 YYYYMMDD',
  `occupationno` varchar(128) DEFAULT NULL COMMENT '职业资格证号',
  `occupationlevel` varchar(8) DEFAULT NULL COMMENT '职业资格等级\r\n1:一级\r\n2:二级\r\n3:三级\r\n4:四级',
  `dripermitted` varchar(8) DEFAULT NULL COMMENT '准驾车型\r\n下列编码单选：\r\nA1,A2,A3,B1,B2,C1,C2,C3\r\n,C4,C5,D,E,F,M,N,P',
  `teachpermitted` varchar(8) DEFAULT NULL COMMENT ' 准教车型\r\n下列编码单选：\r\nA1,A2,A3,B1,B2,C1,C2,C3\r\n,C4,C5,D,E,F,M,N,P',
  `employstatus` varchar(4) DEFAULT NULL COMMENT '供职状态\r\n0:在职\r\n1:离职',
  `hiredate` varchar(64) DEFAULT NULL COMMENT '入职日期 YYYYMMDD',
  `leavedate` varchar(64) DEFAULT NULL COMMENT '离职日期 YYYYMMDD',
  `trainareaid` int(11) DEFAULT NULL COMMENT '训练场地主键',
  `operate` tinyint(4) DEFAULT '0' COMMENT '操作类型，0未上传，1上传，2更新，3删除',
  `errorcode` int(11) DEFAULT NULL COMMENT '操作结果',
  `message` varchar(128) DEFAULT NULL COMMENT '操作提示信息',
  `utime` datetime DEFAULT NULL COMMENT '成功上传时间',
  `ltime` datetime DEFAULT NULL COMMENT '最后成功更新上传时间',
  `cuid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `muid` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`coachId`),
  UNIQUE KEY `coachnum` (`coachnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教练表';

-- ----------------------------
-- Table structure for t_coachcar
-- ----------------------------
DROP TABLE IF EXISTS `t_coachcar`;
CREATE TABLE `t_coachcar` (
  `carid` int(11) NOT NULL AUTO_INCREMENT,
  `instid` int(11) DEFAULT NULL COMMENT '培训机构主键',
  `inscode` char(16) DEFAULT NULL COMMENT '培训机构编号 统一编号',
  `carnum` char(16) DEFAULT NULL COMMENT '教练车编号 统一编号',
  `franum` varchar(32) DEFAULT NULL COMMENT '车架号',
  `engnum` varchar(32) DEFAULT NULL COMMENT '发动机号',
  `licnum` varchar(64) DEFAULT NULL COMMENT '车辆牌号',
  `platecolor` varchar(64) DEFAULT NULL COMMENT '车牌颜色\r\n1:蓝色\r\n2:黄色\r\n3:黑色\r\n4:白色\r\n5:绿色\r\n9:其他',
  `photo` int(11) DEFAULT NULL COMMENT 'NOT NULL: 照片文件ID\r\n成功上传的教练车照片文\r\n件ID',
  `photo_url` varchar(256) DEFAULT NULL COMMENT '照片url路径',
  `manufacture` varchar(256) DEFAULT NULL COMMENT '生产厂家 如“上汽大众”\r\nbrand varchar( V256 车辆品牌 如“桑塔纳”\r\nmodel 否 varchar( V128 车辆型号\r\n如“普通桑塔纳”、“桑塔纳\r\n2000”',
  `brand` varchar(255) DEFAULT NULL,
  `perdritype` varchar(8) DEFAULT NULL COMMENT '培训车型\r\n下列编码单选：\r\nA1,A2,A3,B1,B2,C1,C2,C3\r\n23\r\n,C4,C5,D,E,F,M,N,P',
  `buydate` varchar(64) DEFAULT NULL COMMENT '购买日期 YYYYMMDD',
  `status` varchar(255) DEFAULT NULL,
  `skillstatus` varchar(255) DEFAULT NULL,
  `skilldate` varchar(255) DEFAULT NULL,
  `checkstatus` varchar(255) DEFAULT NULL,
  `checkdate` varchar(255) DEFAULT NULL,
  `secondstatus` varchar(255) DEFAULT NULL,
  `seconddate` varchar(255) DEFAULT NULL,
  `coachId` int(11) DEFAULT NULL COMMENT '教练编号',
  `operate` tinyint(4) DEFAULT '0' COMMENT '操作类型，0未上传，1上传，2更新，3删除',
  `errorcode` int(11) DEFAULT NULL COMMENT '操作结果',
  `message` varchar(128) DEFAULT NULL COMMENT '操作提示信息',
  `utime` datetime DEFAULT NULL COMMENT '成功上传时间',
  `ltime` datetime DEFAULT NULL COMMENT '最后成功更新上传时间',
  `cuid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `muid` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`carid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教练车表';

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `apid` int(11) NOT NULL COMMENT '权限主键：100-999为页面权限，XXX00-XXX99为菜单权限，XXXXX00-XXXXX99为按钮，10000000以上为接口权限',
  `atype` tinyint(4) NOT NULL COMMENT '权限类型：1页面，2菜单，3按钮，4接口',
  `aname` varchar(50) NOT NULL COMMENT '权限名称',
  `aurl` varchar(256) NOT NULL COMMENT '权限路径：atype中1，2，3都是web页面路径，4是接口路径',
  `dtype` tinyint(4) NOT NULL DEFAULT '1' COMMENT '接口对应的数据读写类型:1读，2增，3该，4删，5增或改',
  `afield` varchar(256) DEFAULT NULL COMMENT '拥有权限的字段，为空代表拥有所有字段权限，dtype中1则限制返回和web页面显示，2，3，4，5限制写入',
  `spel` varchar(20000) DEFAULT NULL COMMENT '接口参数限制的spel表达式,为空则说明对参数没有限制，只有该表达式为true的时候，用户才能获得该接口的权限',
  `pid` int(11) NOT NULL DEFAULT '0' COMMENT '权限父id，根权限的该值为0:api权限的全部父id都为0,一级菜单的父id都为0',
  `aid` int(11) DEFAULT NULL COMMENT '该页面关联的api接口权限',
  PRIMARY KEY (`apid`),
  UNIQUE KEY `apid` (`apid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='全部页面权限列表，采用白名单机制';

-- ----------------------------
-- Table structure for t_personnel
-- ----------------------------
DROP TABLE IF EXISTS `t_personnel`;
CREATE TABLE `t_personnel` (
  `examnum` varchar(16) DEFAULT NULL COMMENT '人员编号',
  `instid` bigint(18) NOT NULL AUTO_INCREMENT COMMENT '人员id',
  `inscode` varchar(18) DEFAULT NULL COMMENT '培训机构编号',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(8) DEFAULT NULL COMMENT '性别',
  `idcard` varchar(128) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(32) DEFAULT NULL COMMENT '电话号码',
  `address` varchar(100) DEFAULT NULL COMMENT '联系地址',
  `photo` bigint(20) DEFAULT NULL COMMENT '照片文件 ID',
  `fingerprint` bigint(20) DEFAULT NULL COMMENT '指纹图片 ID',
  `drilicence` varchar(128) DEFAULT NULL COMMENT '驾驶证号',
  `fstdrilicdate` varchar(64) DEFAULT NULL COMMENT '驾驶证初领日期YYYYMMDD',
  `occupationno` varchar(128) DEFAULT NULL COMMENT '职业资格证号',
  `occupationlevel` varchar(8) DEFAULT NULL COMMENT '职业资格等级(一级二级三级四级)',
  `dripermitted` varchar(8) DEFAULT NULL COMMENT '准驾车型',
  `teachpermitted` varchar(8) DEFAULT NULL COMMENT '准教车型',
  `employstatus` varchar(8) DEFAULT NULL COMMENT '供职状态(0在职1离职)',
  `hiredate` varchar(64) DEFAULT NULL COMMENT '入职日期',
  `leavedate` varchar(64) DEFAULT NULL COMMENT '离职日期',
  `post` varchar(10) DEFAULT NULL COMMENT '职务',
  `recordType` varchar(10) DEFAULT NULL COMMENT '备案状态',
  `operate` tinyint(6) DEFAULT NULL COMMENT 'NOT NULL操作类型，0未上传，1上传，2更新，3删除',
  `errorcode` int(100) DEFAULT NULL COMMENT 'NOT NULL ''操作结果'',',
  `message` varchar(128) DEFAULT NULL COMMENT 'NOT NULL''操作提示信息'',',
  `utime` datetime DEFAULT NULL COMMENT '成功上传时间',
  `ltime` datetime DEFAULT NULL COMMENT '最后成功更新上传时间',
  `cuid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `muid` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `photo_url` varchar(256) DEFAULT NULL COMMENT '照片url路径',
  `fingerprint_url` varchar(256) DEFAULT NULL COMMENT '指纹图片url',
  PRIMARY KEY (`instid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_region
-- ----------------------------
DROP TABLE IF EXISTS `t_region`;
CREATE TABLE `t_region` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `inscode` varchar(16) NOT NULL COMMENT '培训机构编号',
  `seq` int(4) NOT NULL COMMENT '教学区域编号',
  `name` varchar(256) DEFAULT NULL COMMENT '教学区域名称',
  `address` varchar(255) DEFAULT NULL COMMENT '教学区域地址',
  `area` int(11) DEFAULT NULL COMMENT '教学区域面积 单位:平方米',
  `vehicletype` varchar(255) DEFAULT NULL COMMENT '培训车型',
  `polygon` varchar(255) DEFAULT NULL COMMENT '多边形坐标序列',
  `totalvehnum` int(11) DEFAULT NULL COMMENT '可容纳车辆数',
  `curvehnum` int(11) DEFAULT NULL COMMENT '已投放车辆数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(255) DEFAULT NULL,
  `ctime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `rpid` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色主键',
  `rname` varchar(50) NOT NULL COMMENT '角色名称',
  `apid` int(11) NOT NULL COMMENT '权限项',
  PRIMARY KEY (`rpid`),
  UNIQUE KEY `rpid` (`rpid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限列表';

-- ----------------------------
-- Table structure for t_store
-- ----------------------------
DROP TABLE IF EXISTS `t_store`;
CREATE TABLE `t_store` (
  `storeid` int(11) NOT NULL AUTO_INCREMENT,
  `storename` varchar(255) DEFAULT NULL COMMENT '门店名称',
  `storeaddress` varchar(255) DEFAULT NULL COMMENT '门店地址',
  `storeimage` varchar(255) DEFAULT NULL COMMENT '门店图片',
  `tel` varchar(255) DEFAULT NULL COMMENT '联系电话',
  `lng` double DEFAULT NULL COMMENT '经度',
  `lat` double DEFAULT NULL COMMENT '纬度',
  `status` int(11) DEFAULT NULL COMMENT '状态 0:正常教学中1：建设筹备中2：停业整顿中3：开业招生中',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `mtime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`storeid`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student` (
  `stunum` varchar(50) DEFAULT NULL COMMENT '学员编号',
  `instid` bigint(20) NOT NULL AUTO_INCREMENT,
  `inscode` varchar(50) DEFAULT NULL COMMENT '培训机构编号',
  `cardtype` varchar(17) DEFAULT '1' COMMENT '证件类型',
  `idcard` varchar(128) DEFAULT NULL COMMENT '证件号',
  `nationality` varchar(128) DEFAULT NULL COMMENT '国籍',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(20) DEFAULT NULL COMMENT '性别',
  `phone` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `address` varchar(256) DEFAULT NULL COMMENT '联系地址',
  `photo` int(16) DEFAULT NULL COMMENT '照片文件 ID',
  `fingerprint` int(16) DEFAULT NULL COMMENT '指纹图片 ID',
  `busitype` varchar(6) DEFAULT NULL COMMENT '业务类型(初领、增领)',
  `drilicnum` varchar(128) DEFAULT NULL COMMENT '驾驶证号',
  `perdritype` varchar(8) DEFAULT NULL COMMENT '原准驾车型',
  `fstdrilicdate` varchar(64) DEFAULT NULL COMMENT '驾驶证初领日期YYYYMMDD',
  `traintype` varchar(8) DEFAULT NULL COMMENT '培训车型',
  `completeTime` varchar(16) DEFAULT NULL COMMENT '已学学时',
  `applydate` varchar(128) DEFAULT NULL COMMENT '报名时间YYYYMMDD',
  `recordType` varchar(16) DEFAULT NULL COMMENT '备案状态',
  `applyexam` varchar(8) DEFAULT NULL COMMENT '学员状态()',
  `applytype` varchar(18) DEFAULT NULL COMMENT '报名方式',
  `operate` tinyint(6) DEFAULT NULL COMMENT 'NOT NULL操作类型，0未上传，1上传，2更新，3删除',
  `errorcode` int(100) DEFAULT NULL COMMENT 'NOT NULL ''操作结果'',',
  `message` varchar(128) DEFAULT NULL COMMENT 'NOT NULL''操作提示信息'',',
  `utime` datetime DEFAULT NULL COMMENT '成功上传时间',
  `ltime` datetime DEFAULT NULL COMMENT '最后成功更新上传时间',
  `cuid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `muid` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `photo_url` varchar(256) DEFAULT NULL COMMENT '照片url路径',
  `fingerprint_url` varchar(256) DEFAULT '' COMMENT '指纹图片url',
  `trainmethod` varchar(8) DEFAULT NULL COMMENT '培训方式(先学后付..)',
  `coachId` varchar(50) DEFAULT NULL COMMENT '教练id',
  `classid` int(50) DEFAULT NULL COMMENT '班级id',
  `paystatus` varchar(8) DEFAULT NULL COMMENT '支付状态',
  `money` double DEFAULT NULL COMMENT '订单金额',
  `islogout` varchar(8) DEFAULT '2' COMMENT '1：已注销 2： 正常',
  PRIMARY KEY (`instid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_trainarea
-- ----------------------------
DROP TABLE IF EXISTS `t_trainarea`;
CREATE TABLE `t_trainarea` (
  `instid` bigint(100) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `inscode` varchar(16) DEFAULT NULL COMMENT '培训机构编号',
  `seq` varchar(50) DEFAULT NULL COMMENT '教学区域编号',
  `name` varchar(256) DEFAULT NULL COMMENT '教学区域名称',
  `address` varchar(256) DEFAULT NULL COMMENT '教学区域地址',
  `area` int(6) DEFAULT NULL COMMENT '教学区域面积',
  `vehicletype` varchar(256) DEFAULT NULL COMMENT '培训车型(多选)',
  `polygon` varchar(1024) DEFAULT NULL COMMENT '多边形坐标序列',
  `totalvehnum` int(6) DEFAULT NULL COMMENT '可容纳车辆数',
  `curvehnum` int(6) DEFAULT NULL COMMENT '已投放车辆数',
  `operate` tinyint(6) DEFAULT NULL COMMENT 'NOT NULL操作类型，0未上传，1上传，2更新，3删除',
  `errorcode` int(100) DEFAULT NULL COMMENT 'NOT NULL ''操作结果'',',
  `message` varchar(128) DEFAULT NULL COMMENT 'NOT NULL''操作提示信息'',',
  `utime` datetime DEFAULT NULL COMMENT '成功上传时间',
  `ltime` datetime DEFAULT NULL COMMENT '最后成功更新上传时间',
  `cuid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `muid` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`instid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_user_permission`;
CREATE TABLE `t_user_permission` (
  `upid` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户权限主键',
  `user_id` mediumtext NOT NULL COMMENT '用户id',
  `apid` int(11) NOT NULL COMMENT '权限项主键',
  PRIMARY KEY (`upid`),
  UNIQUE KEY `upid` (`upid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='最终用户权限，含权限范围';
