-- 新建 交易账号
CREATE TABLE `t_coachAccount`( 
`id` BIGINT NOT NULL AUTO_INCREMENT, 
`coachid` INT COMMENT '外键关联教练表', 
`isLimit` VARCHAR(6) COMMENT '是否限制金额1不限制，2限制', 
`limitQuantity` VARCHAR(20) COMMENT '限额', 
`auth` VARCHAR(6) DEFAULT '1' NULL COMMENT '授权 1有 2无 (默认1)',
`status` VARCHAR(6) COMMENT '状态1有效2无效', PRIMARY KEY (`id`) ) CHARSET=utf8 COLLATE=utf8_german2_ci;

-- 新建 充值记录
CREATE TABLE `t_recharge`( `rid` BIGINT NOT NULL AUTO_INCREMENT, 
`serialNumber` VARCHAR(50), `rtime` VARCHAR(30), `rtype` VARCHAR(6), 
`amount` VARCHAR(20), `balance` VARCHAR(20), `rstatus` VARCHAR(30), 
`message` VARCHAR(500),PRIMARY KEY (`rid`) ) CHARSET=utf8 COLLATE=utf8_general_ci;


