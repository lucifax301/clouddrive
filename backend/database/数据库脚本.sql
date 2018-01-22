CREATE TABLE `t_flow_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `name` varchar(128) unique NOT NULL COMMENT '',
  `rule` varchar(128) DEFAULT NULL COMMENT 'roleid,roleid,roleid',
  `status` smallint DEFAULT 0 COMMENT '0 avail, 1 unavail',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����ģ��';

CREATE TABLE `t_flow_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `templateid` int(11) NOT NULL,
  `businessid` varchar(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY(`templateid`,`businessid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ҵ�����̰�';

CREATE TABLE `t_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `businessid` varchar(128) NOT NULL UNIQUE,
  `description` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ҵ��';

CREATE TABLE `t_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `transactionid` varchar(32) NOT NULL unique,
  `templateid` int(11) NOT NULL,
  `businessid` varchar(128) NOT NULL,
  `fuserid` int(11) NOT NULL COMMENT 'ҵ����������',
  `curuserid` int(11) DEFAULT NULL COMMENT '��ǰ�����',
  `step` int(11) NOT NULL,
  `description` varchar(128)  NOT NULL COMMENT '',
  `status` smallint DEFAULT 0 COMMENT '0 notprocess 1 audit 2 reject 3 cancel',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ҵ����������';

CREATE TABLE `t_flow_step` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `flowid` int(11) NOT NULL,
  `step` int(11) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` smallint DEFAULT 0 COMMENT '0 waitforprocess 1 processed',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ҵ������������ϸ';

CREATE TABLE `t_action_business` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `actionid` varchar(128) NOT NULL unique,
  `businessid` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����ҵ���';

CREATE TABLE `t_teach_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `type` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` smallint default 0,
  `remark` varchar(64) DEFAULT NULL,
  `subject` varchar(32) NOT NULL COMMENT '1,2,3,4',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��������';

CREATE TABLE `t_class_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `type` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` smallint default 0,
  `remark` varchar(64) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='���̰���';

CREATE TABLE `t_car_type` (
  `type` varchar(10) NOT NULL,
  `userid` int(11) NOT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='���̳���';

CREATE TABLE `t_job_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `job` varchar(64) NOT NULL,
  `userid` int(11) NOT NULL,
  `status` smallint default 0,
  `remark` varchar(64) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����ְ��';

CREATE TABLE `t_log_common` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `menuid` int(11) NOT NULL COMMENT '�˵���ID',
  `menuname` varchar(128) DEFAULT NULL COMMENT '�����˵�����',
  `url` varchar(128) DEFAULT NULL COMMENT 'url���嵽action',
  `action` tinyint(4) NOT NULL COMMENT '�������� 0 ����, 1 ���ӣ�2�޸ģ�3ɾ��',
  `username` varchar(32) NOT NULL COMMENT '������',
  `userid` varchar(32) NOT NULL COMMENT '������ID',
  `operatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `ip` varchar(32) DEFAULT NULL COMMENT '��������IP��ַ',
  `relateid` varchar(34) NOT NULL COMMENT '������¼������ID',
  `tableid` int(11) DEFAULT NULL COMMENT '������ID',
  `relatetable` varchar(50) DEFAULT NULL COMMENT '������¼��',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '����״̬��1�ɹ���2ʧ��',
  `remark` varchar(5000) DEFAULT NULL,
  `schoolid` int(11) DEFAULT '0' COMMENT '��Уid',
  `detail` varchar(500) DEFAULT NULL COMMENT '�޸��ֶ���ϸ��¼json',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������־��';

CREATE TABLE `t_coach_mod_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `businessid` varchar(128) NOT NULL COMMENT 'ҵ��ID',
  `transactionid` varchar(32) NOT NULL,
  `coachid` int(11) NOT NULL COMMENT '����ID',
  `applyuserid` int(11) NOT NULL COMMENT '������id',
  `audituserid` int(11) DEFAULT NULL COMMENT '�����id',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '����״̬��1���ͨ����2��˲�ͨ�� 3����ȡ��',
  `remark` varchar(500) DEFAULT NULL,
  `detail` varchar(2000) DEFAULT NULL COMMENT '�޸��ֶ���ϸ��¼json',
  `createdate` datetime DEFAULT NULL ,
  `auditdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�����޸������';

CREATE TABLE `t_student_pauseresume_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `businessid` varchar(128) NOT NULL COMMENT 'ҵ��ID',
  `transactionid` varchar(32) NOT NULL,
  `type` smallint DEFAULT 1  COMMENT '1 pause 2 resume',
  `pauseid` varchar(128) DEFAULT NULL COMMENT '��ͣҵ��ID',
  `studentid` int(11) NOT NULL COMMENT 'ѧԱID',
  `begindate` datetime DEFAULT NULL COMMENT '��ͣ��ʼʱ��',
  `enddate`datetime DEFAULT NULL COMMENT '��ͣ����ʱ��',
  `reason` varchar(128) DEFAULT NULL COMMENT '��ͣԭ��',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '���״̬',
  `applyuserid` int(11) NOT NULL COMMENT '������id',
  `audituserid` int(11) DEFAULT NULL COMMENT '�����id',
  `hasnext` smallint default 1 comment '0 no 1 yes',
  `remark` varchar(5000) DEFAULT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1870 DEFAULT CHARSET=utf8 COMMENT='ѧԱ��ͣѧ������';



CREATE TABLE `t_coach` (
  `coachid` int(11) NOT NULL AUTO_INCREMENT,
  `instid` int(11) DEFAULT NULL COMMENT '��ѵ��������',
  `inscode` varchar(16) DEFAULT NULL COMMENT '��ѵ������� ͳһ���',
  `coachnum` varchar(16) DEFAULT NULL COMMENT '����Ա��� ͳһ���',
  `name` varchar(64) DEFAULT NULL COMMENT '����',
  `sex` varchar(4) DEFAULT NULL COMMENT '�Ա� 1:����;2:Ů��',
  `idcard` varchar(128) DEFAULT NULL COMMENT '���֤��',
  `mobile` varchar(32) DEFAULT NULL COMMENT '�ֻ�����',
  `carno` varchar(32) DEFAULT NULL COMMENT '������',
  `address` varchar(256) DEFAULT NULL COMMENT '��ϵ��ַ',
  `photo` int(11) DEFAULT NULL COMMENT 'NOT NULL: ��Ƭ�ļ�ID���ɹ��ϴ��Ľ���Աͷ���ļ�ID',
  `fingerprint` int(11) DEFAULT NULL COMMENT 'ָ��ͼƬID �ɹ��ϴ���ָ��ͼƬID',
  `photo_url` varchar(256) DEFAULT NULL COMMENT '��Ƭurl·��',
  `fingerprint_url` varchar(256) DEFAULT NULL COMMENT 'ָ��ͼƬurl',
  `drilicence` varchar(128) DEFAULT NULL COMMENT '��ʻ֤��',
  `fstdrilicdate` varchar(64) DEFAULT NULL COMMENT '��ʻ֤�������� YYYYMMDD',
  `occupationno` varchar(128) DEFAULT NULL COMMENT 'ְҵ�ʸ�֤��',
  `occupationlevel` varchar(8) DEFAULT NULL COMMENT 'ְҵ�ʸ�ȼ�\r\n1:һ��\r\n2:����\r\n3:����\r\n4:�ļ�',
  `dripermitted` varchar(8) DEFAULT NULL COMMENT '׼�ݳ���\r\n���б��뵥ѡ��\r\nA1,A2,A3,B1,B2,C1,C2,C3\r\n,C4,C5,D,E,F,M,N,P',
  `teachpermitted` varchar(8) DEFAULT NULL COMMENT '׼�̳���\r\n���б��뵥ѡ��\r\nA1,A2,A3,B1,B2,C1,C2,C3\r\n,C4,C5,D,E,F,M,N,P',
  `employstatus` varchar(4) DEFAULT NULL COMMENT '��ְ״̬\r\n0:��ְ\r\n1:��ְ',
  `hiredate` varchar(64) DEFAULT NULL COMMENT '��ְ���� YYYYMMDD',
  `leavedate` varchar(64) DEFAULT NULL COMMENT '��ְ���� YYYYMMDD',
  `teachStartDate` varchar(30) DEFAULT NULL COMMENT '���ʱ��',
  `workType` varchar(8) DEFAULT NULL COMMENT '��ְ��ʽ',
  `teaching` varchar(8) DEFAULT NULL COMMENT '����',
  `driving` varchar(128) DEFAULT NULL COMMENT '����',
  `expireDate` varchar(64) DEFAULT NULL COMMENT '��֤��������',
  `trainareaid` int(11) DEFAULT NULL COMMENT 'ѵ����������',
  `areaid` int(11) DEFAULT NULL COMMENT 'Ƭ������',
  `headcoachid` int(11) DEFAULT 0 COMMENT '�鳤����',
  `teachtypeid` int(11) DEFAULT NULL COMMENT '������������',
  `jobid` int(11) DEFAULT NULL COMMENT '����ְ������',
  `teachcartype` varchar(8) DEFAULT NULL COMMENT '���̳��� A1,A2,A3,B1,B2,C1,C2,C3\r\n,C4,C5,D,E,F,M,N,P',
  `teachstate` smallint DEFAULT 1 COMMENT '����״̬ 1 ���� 2ֹͣ',
  `studentload` smallint DEFAULT 0 COMMENT '���ɱ�׼',
  `curstudentload` smallint DEFAULT 0 COMMENT '��ǰ����',
  `headcoachflag` smallint DEFAULT 0 COMMENT '0 �����鳤 1��',
  `mainstoreid` int(11) DEFAULT NULL COMMENT '��Ҫ�ŵ�id',
  `operate` tinyint(4) DEFAULT '0' COMMENT '�������ͣ�0δ�ϴ���1�ϴ���2���£�3ɾ��',
  `errorcode` int(11) DEFAULT NULL COMMENT '�������',
  `message` varchar(128) DEFAULT NULL COMMENT '������ʾ��Ϣ',
  `utime` datetime DEFAULT NULL COMMENT '�ɹ��ϴ�ʱ��',
  `ltime` datetime DEFAULT NULL COMMENT '���ɹ������ϴ�ʱ��',
  `cuid` bigint(20) DEFAULT NULL COMMENT '������id',
  `muid` bigint(20) DEFAULT NULL COMMENT '������id',
  `ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `xid` int(11) DEFAULT NULL COMMENT 'app��̨����ӳ��id',
  PRIMARY KEY (`coachid`),
  UNIQUE KEY `coachnum` (`coachnum`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='������';


CREATE TABLE `t_headcoach` (
	`coachid` int(11) NOT NULL,
	`overcoach` smallint DEFAULT 0 COMMENT '��Ͻ������',
	`overcoachcar` smallint DEFAULT 0 COMMENT '��Ͻ������',
	`ctime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`muserid` int DEFAULT NULL COMMENT '�޸��û�ID',
	`mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
	PRIMARY KEY (`coachid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��ѧ�鳤��';

CREATE TABLE `t_coach_classinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `coachid` int(11) NOT NULL,
  `classinfoid` int(11) NOT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`coachid`,`classinfoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������������';

CREATE TABLE `t_coach_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `coachid` int(11) NOT NULL,
  `storeid` int(11) NOT NULL,
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`coachid`,`storeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�����ŵ������';

CREATE TABLE `t_coach_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `coachid` int(11) NOT NULL,
  `areaid` int(11) NOT NULL,
  `step` int(11) NOT NULL COMMENT '��Ŀ�� 2, ��Ŀ�� 3',
  `createdate` datetime DEFAULT NULL ,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`coachid`,`areaid`,`step`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='��������������';

CREATE TABLE `t_theory_lesson` (
  `theoryId` int(11) NOT NULL AUTO_INCREMENT COMMENT '���ۿ�����',
  `areaId` int(11) DEFAULT NULL COMMENT 'Ƭ��ID',
  `areaName` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '���ࣺƬ������',
  `lessonName` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '�γ�����',
  `startTime` datetime DEFAULT NULL COMMENT '�Ͽ�ʱ��',
  `endTime` datetime DEFAULT NULL COMMENT '�¿�ʱ��',
  `place` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '�Ͽεص�',
  `contactName` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '��ϵ��',
  `contactPhone` varchar(32) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '��ϵ���ֻ���',
  `cardtype` tinyint(4) DEFAULT NULL COMMENT '֤�����ͣ�1���֤2����3����֤4����',
  `idcard` varchar(128) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '֤������',
  `recomNum` int(11) DEFAULT NULL COMMENT '�Ͽ������У�飩',
  `arrangeNum` int(11) DEFAULT '0' COMMENT '�Ѱ�������',
  `state` int(11) DEFAULT NULL COMMENT '״̬��0ȡ����1δ����ѧԱ��2����ˡ�3���ͨ����4�ѿ��Ρ�5����ɡ�9��˾ܾ�',
  `isdel` tinyint(4) DEFAULT '0' COMMENT 'ɾ��״̬��0δɾ����1��ɾ��',
  `reviewerId` int(11) DEFAULT NULL COMMENT 'Ƭ�������ID',
  `reviewState` int(11) DEFAULT NULL COMMENT 'Ƭ�����״̬��1ͨ����9�ܾ�',
  `reviewTime` datetime DEFAULT NULL COMMENT 'Ƭ�����ʱ��',
  `cuid` int(11) DEFAULT NULL COMMENT '������ID',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `mtime` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '�޸�ʱ��',
  PRIMARY KEY (`theoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='���ۿα�'


CREATE TABLE `t_theory_store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `theoryId` int(11) DEFAULT NULL COMMENT '���ۿ�ID',
  `storeId` int(11) DEFAULT NULL COMMENT '�ŵ�ID',
  `recomNum` int(11) DEFAULT NULL COMMENT '�Ͽ������У�飩',
  `arrangeNum` int(11) DEFAULT '0' COMMENT '�Ѱ�������',
  `extra` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '���������ֶΣ�δ����ѧԱ��1����˾ܾ��������ύ����֮ǰ��extra=9��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='���ۿ��ŵ������'


CREATE TABLE `t_theory_student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `theoryId` int(11) DEFAULT NULL COMMENT '���ۿ�ID',
  `storeId` int(11) DEFAULT NULL COMMENT '�ŵ�ID',
  `studentId` int(11) DEFAULT NULL COMMENT 'ѧԱID',
  `remark` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '��ע',
  `ctime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `extra` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '����',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='���ۿ��Ͽ�ѧԱ��'
