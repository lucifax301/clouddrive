CREATE TABLE `t_flow_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(128) unique NOT NULL COMMENT '',
  `rule` varchar(128) DEFAULT NULL COMMENT 'roleid,roleid,roleid',
  `status` smallint DEFAULT 0 COMMENT '0 avail, 1 unavail',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程模板';

CREATE TABLE `t_flow_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `templateid` int(11) NOT NULL,
  `businessid` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY(`templateid`,`businessid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务流程绑定';

CREATE TABLE `t_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `businessid` varchar(128) NOT NULL UNIQUE,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务';

CREATE TABLE `t_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `transactionid` varchar(32) NOT NULL unique,
  `templateid` int(11) NOT NULL,
  `businessid` varchar(128) NOT NULL,
  `fuserid` int(11) NOT NULL COMMENT '业务流发起人',
  `curuserid` int(11) DEFAULT NULL COMMENT '当前审核人',
  `step` int(11) NOT NULL,
  `description` varchar(128)  NOT NULL COMMENT '',
  `status` smallint DEFAULT 0 COMMENT '0 notprocess 1 audit 2 reject 3 cancel',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务事务流程';

CREATE TABLE `t_flow_step` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `flowid` int(11) NOT NULL,
  `step` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` smallint DEFAULT 0 COMMENT '0 waitforprocess 1 processed',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务事务流程明细';

CREATE TABLE `t_action_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `actionid` varchar(128) NOT NULL unique,
  `businessid` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='动作业务绑定';

CREATE TABLE `t_teach_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` smallint default 0,
  `remark` varchar(64) DEFAULT NULL,
  `subject` varchar(32) NOT NULL COMMENT '1,2,3,4',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='带教类型';

CREATE TABLE `t_class_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` smallint default 0,
  `remark` varchar(64) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='带教班型';

CREATE TABLE `t_car_type` (
  `type` varchar(10) NOT NULL,
  `userid` int(11) NOT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='带教车型';

CREATE TABLE `t_job_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `job` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` smallint default 0,
  `remark` varchar(64) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='带教职务';

CREATE TABLE `t_log_common` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `menuid` int(11) NOT NULL COMMENT '菜单栏ID',
  `menuname` varchar(128) DEFAULT NULL COMMENT '操作菜单名称',
  `url` varchar(128) DEFAULT NULL COMMENT 'url具体到action',
  `action` tinyint(4) NOT NULL COMMENT '操作类型 0 其他, 1 增加，2修改，3删除',
  `username` varchar(32) NOT NULL COMMENT '操作人',
  `userid` varchar(32) NOT NULL COMMENT '操作人ID',
  `operatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  `ip` varchar(32) DEFAULT NULL COMMENT '操作所属IP地址',
  `relateid` varchar(34) NOT NULL COMMENT '操作记录关联表ID',
  `tableid` int(11) DEFAULT NULL COMMENT '关联表ID',
  `relatetable` varchar(50) DEFAULT NULL COMMENT '操作记录表',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '操作状态：1成功，2失败',
  `remark` varchar(5000) DEFAULT NULL,
  `schoolid` int(11) DEFAULT '0' COMMENT '驾校id',
  `detail` varchar(500) DEFAULT NULL COMMENT '修改字段详细记录json',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志表';

CREATE TABLE `t_coach_mod_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `businessid` varchar(128) NOT NULL COMMENT '业务ID',
  `transactionid` varchar(32) NOT NULL,
  `coachid` int(11) NOT NULL COMMENT '教练ID',
  `applyuserid` int(11) NOT NULL COMMENT '申请人id',
  `audituserid` int(11) DEFAULT NULL COMMENT '审核人id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '操作状态：1审核通过，2审核不通过 3撤回取消',
  `remark` varchar(500) DEFAULT NULL,
  `detail` varchar(2000) DEFAULT NULL COMMENT '修改字段详细记录json',
  `createdate` datetime DEFAULT NULL ,
  `auditdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教练修改申请表';

CREATE TABLE `t_student_pauseresume_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `businessid` varchar(128) NOT NULL COMMENT '业务ID',
  `transactionid` varchar(32) NOT NULL,
  `type` smallint DEFAULT 1  COMMENT '1 pause 2 resume',
  `pauseid` varchar(128) DEFAULT NULL COMMENT '暂停业务ID',
  `studentid` int(11) NOT NULL COMMENT '学员ID',
  `begindate` datetime DEFAULT NULL COMMENT '暂停开始时间',
  `enddate`datetime DEFAULT NULL COMMENT '暂停结束时间',
  `reason` varchar(128) DEFAULT NULL COMMENT '暂停原因',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '审核状态',
  `applyuserid` int(11) NOT NULL COMMENT '申请人id',
  `audituserid` int(11) DEFAULT NULL COMMENT '审核人id',
  `hasnext` smallint default 1 comment '0 no 1 yes',
  `remark` varchar(5000) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1870 DEFAULT CHARSET=utf8 COMMENT='学员暂停学车申请';



CREATE TABLE `t_coach` (
  `coachid` int(11) NOT NULL AUTO_INCREMENT,
  `instid` int(11) DEFAULT NULL COMMENT '培训机构主键',
  `inscode` varchar(16) DEFAULT NULL COMMENT '培训机构编号 统一编号',
  `coachnum` varchar(16) DEFAULT NULL COMMENT '教练员编号 统一编号',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(4) DEFAULT NULL COMMENT '性别 1:男性;2:女性',
  `idcard` varchar(128) DEFAULT NULL COMMENT '身份证号',
  `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
  `carno` varchar(32) DEFAULT NULL COMMENT '教练车',
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
  `teachpermitted` varchar(8) DEFAULT NULL COMMENT '准教车型\r\n下列编码单选：\r\nA1,A2,A3,B1,B2,C1,C2,C3\r\n,C4,C5,D,E,F,M,N,P',
  `employstatus` varchar(4) DEFAULT NULL COMMENT '供职状态\r\n0:在职\r\n1:离职',
  `hiredate` varchar(64) DEFAULT NULL COMMENT '入职日期 YYYYMMDD',
  `leavedate` varchar(64) DEFAULT NULL COMMENT '离职日期 YYYYMMDD',
  `teachStartDate` varchar(30) DEFAULT NULL COMMENT '起教时间',
  `workType` varchar(8) DEFAULT NULL COMMENT '供职方式',
  `teaching` varchar(8) DEFAULT NULL COMMENT '教龄',
  `driving` varchar(128) DEFAULT NULL COMMENT '驾龄',
  `expireDate` varchar(64) DEFAULT NULL COMMENT '驾证过期日期',
  `trainareaid` int(11) DEFAULT NULL COMMENT '训练场地主键',
  `areaid` int(11) DEFAULT NULL COMMENT '片区主键',
  `headcoachid` int(11) DEFAULT 0 COMMENT '组长主键',
  `teachtypeid` int(11) DEFAULT NULL COMMENT '带教类型主键',
  `jobid` int(11) DEFAULT NULL COMMENT '带教职务主键',
  `teachcartype` varchar(8) DEFAULT NULL COMMENT '带教车型 A1,A2,A3,B1,B2,C1,C2,C3\r\n,C4,C5,D,E,F,M,N,P',
  `teachstate` smallint DEFAULT 1 COMMENT '带教状态 1 正常 2停止',
  `studentload` smallint DEFAULT 0 COMMENT '负荷标准',
  `curstudentload` smallint DEFAULT 0 COMMENT '当前负荷',
  `headcoachflag` smallint DEFAULT 0 COMMENT '0 不是组长 1是',
  `mainstoreid` int(11) DEFAULT NULL COMMENT '主要门店id',
  `operate` tinyint(4) DEFAULT '0' COMMENT '操作类型，0未上传，1上传，2更新，3删除',
  `errorcode` int(11) DEFAULT NULL COMMENT '操作结果',
  `message` varchar(128) DEFAULT NULL COMMENT '操作提示信息',
  `utime` datetime DEFAULT NULL COMMENT '成功上传时间',
  `ltime` datetime DEFAULT NULL COMMENT '最后成功更新上传时间',
  `cuid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `muid` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `xid` int(11) DEFAULT NULL COMMENT 'app后台数据映射id',
  PRIMARY KEY (`coachid`),
  UNIQUE KEY `coachnum` (`coachnum`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='教练表';


CREATE TABLE `t_headcoach` (
	`coachid` int(11) NOT NULL,
	`overcoach` smallint DEFAULT 0 COMMENT '管辖教练数',
	`overcoachcar` smallint DEFAULT 0 COMMENT '管辖教练车',
	`ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`muserid` int DEFAULT NULL COMMENT '修改用户ID',
	`mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	PRIMARY KEY (`coachid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教学组长表';

CREATE TABLE `t_coach_classinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coachid` int(11) NOT NULL,
  `classinfoid` int(11) NOT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`coachid`,`classinfoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教练班别关联表';

CREATE TABLE `t_coach_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coachid` int(11) NOT NULL,
  `storeid` int(11) NOT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`coachid`,`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教练门店关联表';

CREATE TABLE `t_coach_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `coachid` int(11) NOT NULL,
  `areaid` int(11) NOT NULL,
  `step` int(11) NOT NULL COMMENT '科目二 2, 科目三 3',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`coachid`,`areaid`,`step`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教练考场关联表';

CREATE TABLE `t_theory_lesson` (
  `theoryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '理论课主键',
  `areaId` int(11) DEFAULT NULL COMMENT '片区ID',
  `areaName` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '冗余：片区名字',
  `lessonName` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '课程名称',
  `startTime` datetime DEFAULT NULL COMMENT '上课时间',
  `endTime` datetime DEFAULT NULL COMMENT '下课时间',
  `place` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '上课地点',
  `contactName` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '联系人',
  `contactPhone` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '联系人手机号',
  `cardtype` tinyint(4) DEFAULT NULL COMMENT '证件类型：1身份证2护照3军官证4其它',
  `idcard` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '证件描述',
  `recomNum` int(11) DEFAULT NULL COMMENT '上课名额（不校验）',
  `arrangeNum` int(11) DEFAULT '0' COMMENT '已安排人数',
  `state` int(11) DEFAULT NULL COMMENT '状态：0取消、1未安排学员、2待审核、3审核通过、4已开课、5已完成、9审核拒绝',
  `isdel` tinyint(4) DEFAULT '0' COMMENT '删除状态：0未删除、1已删除',
  `reviewerId` int(11) DEFAULT NULL COMMENT '片区审核人ID',
  `reviewState` int(11) DEFAULT NULL COMMENT '片区审核状态：1通过、9拒绝',
  `reviewTime` datetime DEFAULT NULL COMMENT '片区审核时间',
  `cuid` int(11) DEFAULT NULL COMMENT '创建人ID',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`theoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='理论课表'


CREATE TABLE `t_theory_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `theoryId` int(11) DEFAULT NULL COMMENT '理论课ID',
  `storeId` int(11) DEFAULT NULL COMMENT '门店ID',
  `recomNum` int(11) DEFAULT NULL COMMENT '上课名额（不校验）',
  `arrangeNum` int(11) DEFAULT '0' COMMENT '已安排人数',
  `extra` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '额外冗余字段（未安排学员：1、审核拒绝，重新提交名单之前，extra=9）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='理论课门店名额表'


CREATE TABLE `t_theory_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `theoryId` int(11) DEFAULT NULL COMMENT '理论课ID',
  `storeId` int(11) DEFAULT NULL COMMENT '门店ID',
  `studentId` int(11) DEFAULT NULL COMMENT '学员ID',
  `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '备注',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `extra` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '冗余',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='理论课上课学员表'
