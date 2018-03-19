CREATE TABLE `t_student_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '学员学车状态',
  `status_name` varchar(50) NOT NULL COMMENT '状态名称',
  `sort` int(11) DEFAULT NULL COMMENT '状态顺序',
  `ctime` datetime DEFAULT NULL,
  `mtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学员进度表';

