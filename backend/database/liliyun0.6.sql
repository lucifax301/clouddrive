-- 训练场新增字段
ALTER TABLE `t_trainarea` ADD COLUMN `recordArea` DOUBLE NULL COMMENT '备案面积' AFTER `totalvehnum`, 
ADD COLUMN `recordType` VARCHAR(6) NULL COMMENT '备案状态(1备案2未备案)' AFTER `recordArea`, 
ADD COLUMN `recordCarnum` INT(10) NULL COMMENT '备案车辆' AFTER `recordType`, 
ADD COLUMN `leaseEndDate` VARCHAR(50) NULL COMMENT '租期结束时间' AFTER `recordCarnum`, 
ADD COLUMN `leaseStartDate` VARCHAR(50) NULL COMMENT '租期开始时间' AFTER `leaseEndDate`, 
ADD COLUMN `nature` VARCHAR(6) NULL COMMENT '考场性质', 
ADD COLUMN `ownerPhone` VARCHAR(30) NULL COMMENT '业主电话' AFTER `leaseStartDate`, 
ADD COLUMN `owner` VARCHAR(20) NULL COMMENT '业主' AFTER `ownerPhone`, 
ADD COLUMN `mortgageMoney` DOUBLE NULL COMMENT '押金' AFTER `owner`, 
ADD COLUMN `leaseMoney` DOUBLE NULL COMMENT '租金' AFTER `mortgageMoney`; 


-- 新建图片表
DROP TABLE IF EXISTS `t_uploadimage`;
CREATE TABLE `t_uploadimage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `module` varchar(50) DEFAULT NULL COMMENT '所属模块',
  `businessId` bigint(20) DEFAULT NULL COMMENT '业务对象id(外键)',
  `imgeType` varchar(10) DEFAULT NULL COMMENT '图片类型',
  `imgeKey` varchar(255) DEFAULT NULL COMMENT '七牛key',
  `imgeSize` varchar(50) DEFAULT NULL COMMENT '图片大小(KB)',
  `ctime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `utime` datetime DEFAULT NULL COMMENT '成功上传时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 门店 新增字段
ALTER TABLE `t_store` ADD COLUMN `contacter` VARCHAR(30) NULL COMMENT '联系人' AFTER `lat`, 
ADD COLUMN `operatingType` VARCHAR(6) NULL COMMENT '经营类型' AFTER `contacter`, 
ADD COLUMN `leaseEndDate` VARCHAR(30) NULL COMMENT '租期结束日期' AFTER `operatingType`, 
ADD COLUMN `leaseStartDate` VARCHAR(30) NULL COMMENT '租期开始日期' AFTER `leaseEndDate`, 
ADD COLUMN `recordState` VARCHAR(6) NULL COMMENT '备案状态' AFTER `leaseStartDate`, 
ADD COLUMN `grade` VARCHAR(6) NULL COMMENT '分类等级' AFTER `recordState`, 
ADD COLUMN `owner` VARCHAR(120) NULL COMMENT '业主' AFTER `grade`, 
ADD COLUMN `mortgageMoney` DOUBLE NULL COMMENT '押金' AFTER `owner`, 
ADD COLUMN `leaseMoney` DOUBLE NULL COMMENT '租金' AFTER `mortgageMoney`, 
ADD COLUMN `ownerPhone` VARCHAR(20) NULL COMMENT '业主手机' AFTER `leaseMoney`,
ADD COLUMN `storearea` VARCHAR(50) NULL COMMENT '面积' AFTER `lat`;

-- 教练 新增字段
ALTER TABLE `t_coach` ADD COLUMN `teachStartDate` VARCHAR(30) NULL COMMENT '起教时间' AFTER `leavedate`, 
ADD COLUMN `workType` VARCHAR(8) NULL COMMENT '供职方式' AFTER `teachStartDate`,
ADD COLUMN `teaching` VARCHAR(20) NULL COMMENT '教龄' AFTER `workType`,
ADD COLUMN `driving` VARCHAR(20) NULL COMMENT '驾龄' AFTER `teaching`;

-- 教练车 新增字段
ALTER TABLE `t_coachcar` 
ADD COLUMN `model` VARCHAR(128) NULL COMMENT '车辆型号 如“普通桑塔纳”、 “桑塔纳',
ADD COLUMN `twoMaintainEnd` VARCHAR(30) NULL COMMENT '二级维护到期日' AFTER `seconddate`,
ADD COLUMN `inspectEnd` VARCHAR(30) NULL COMMENT '年审到期日期' AFTER `twoMaintainEnd`, 
ADD COLUMN `scrapdate` VARCHAR(30) NULL COMMENT '强制报废日期' AFTER `inspectEnd`, 
ADD COLUMN `insuranceEnd` VARCHAR(30) NULL COMMENT '保险到期日' AFTER `scrapdate`;

-- 教练 新增字段 2016-10-27
ALTER TABLE `t_coach` ADD COLUMN `expireDate` VARCHAR(64) NULL COMMENT '驾证过期日期' AFTER `driving`;