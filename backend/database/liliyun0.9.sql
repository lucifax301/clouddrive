-- 油耗记录表新增字段
ALTER TABLE `t_car_oilwear` ADD COLUMN `discount` DOUBLE NULL COMMENT '折让' AFTER `oilstat_name`, 
ADD COLUMN `discount_rate` DOUBLE NULL COMMENT '折让率' AFTER `discount`, 
ADD COLUMN `actual_discount_rate` DOUBLE NULL COMMENT '实际折让率' AFTER `discount_rate`, 
ADD COLUMN `hang_brand_price` DOUBLE NULL COMMENT '挂牌价' AFTER `actual_discount_rate`, 
ADD COLUMN `surplus_discount` DOUBLE NULL COMMENT '剩余折扣' AFTER `hang_brand_price`, 
ADD COLUMN `previous_discount` DOUBLE NULL COMMENT '上期折扣' AFTER `surplus_discount`, 
ADD COLUMN `oil_station_code` VARCHAR(50) NULL COMMENT '油站代码' AFTER `previous_discount`, 
ADD COLUMN `oil_type_code` VARCHAR(50) NULL COMMENT '油品代码' AFTER `oil_station_code`, 
ADD COLUMN `oil_card_price` DOUBLE NULL COMMENT '油卡金额' AFTER `oil_type_code`,
ADD COLUMN `oil_card` VARCHAR(50) NULL COMMENT '油卡' AFTER `oil_card_price`;

ALTER TABLE `t_car_oilwear` MODIFY COLUMN `oil_card` VARCHAR(50) NULL COMMENT '油卡' AFTER `oil_card_price`;
ALTER TABLE `t_car_oilwear` MODIFY COLUMN `cost` decimal(10,5) NULL COMMENT '单价' ;





ALTER TABLE `t_car_log` ADD COLUMN `packing_place` VARCHAR(50) NULL COMMENT '停车地点' AFTER `type`, 
ADD COLUMN `cur_areaid`  int(11) DEFAULT NULL COMMENT '调入片区' AFTER `packing_place`,
ADD COLUMN `lead_date`  date DEFAULT NULL COMMENT '领用时间' AFTER `cur_areaid` ,
ADD COLUMN `property`  int(11) DEFAULT NULL COMMENT '使用类型' AFTER `lead_date` ,
ADD COLUMN `remark`  VARCHAR(100) DEFAULT NULL COMMENT '备注' AFTER `property` ,
ADD COLUMN `reason`  VARCHAR(100) DEFAULT NULL COMMENT '调动原因' AFTER `remark` ,
ADD COLUMN `change_or_not`  int(11) DEFAULT NULL COMMENT '是否换车' AFTER `reason` ,
ADD COLUMN `change_carNo`  VARCHAR(50) DEFAULT NULL COMMENT '换车牌号' AFTER `change_or_not` ,
ADD COLUMN `change_area_id`  int(11) DEFAULT NULL COMMENT '换车原片区' AFTER `change_carNo` ,
ADD COLUMN `change_name`   VARCHAR(50) DEFAULT NULL COMMENT '换车原保管人' AFTER `change_area_id` ,
ADD COLUMN `cur_change_area_id`   int(11) DEFAULT NULL COMMENT '换车调入片区' AFTER `chang_name` ,
ADD COLUMN `cur_change_name`   VARCHAR(50) DEFAULT NULL COMMENT '换车现保管人' AFTER `cur_change_area_id` ,
ADD COLUMN `change_lead_date`   date DEFAULT NULL COMMENT '换车领用时间' AFTER `cur_change_name` ,
ADD COLUMN `change_packing_place`   VARCHAR(50) DEFAULT NULL COMMENT '换车停车地点' AFTER `change_lead_date` ,
ADD COLUMN `change_property`  int(11) DEFAULT NULL COMMENT '换车使用类型' AFTER `change_packing_place` ,
ADD COLUMN `change_remark`  VARCHAR(100) DEFAULT NULL COMMENT '换车备注' AFTER `change_property` ,
ADD COLUMN `cur_audit_id`  int(11) DEFAULT NULL COMMENT '当前审批人' AFTER `change_remark` ,
ADD COLUMN `submit_id`  int(11) DEFAULT NULL COMMENT '提交人' AFTER `cur_audit_id` ,
ADD COLUMN `audit_status`  int(11) DEFAULT NULL COMMENT '审批状态:1-办理中 2退办 3-结束' AFTER `submit_id`;


ALTER TABLE `t_car_log` ADD COLUMN `transaction_id` VARCHAR(50) NULL  AFTER `audit_status`;