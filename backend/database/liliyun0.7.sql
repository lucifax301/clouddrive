-- �½� �����˺�
CREATE TABLE `t_coachAccount`( 
`id` BIGINT NOT NULL AUTO_INCREMENT, 
`coachid` INT COMMENT '�������������', 
`isLimit` VARCHAR(6) COMMENT '�Ƿ����ƽ��1�����ƣ�2����', 
`limitQuantity` VARCHAR(20) COMMENT '�޶�', 
`auth` VARCHAR(6) DEFAULT '1' NULL COMMENT '��Ȩ 1�� 2�� (Ĭ��1)',
`status` VARCHAR(6) COMMENT '״̬1��Ч2��Ч', PRIMARY KEY (`id`) ) CHARSET=utf8 COLLATE=utf8_german2_ci;

-- �½� ��ֵ��¼
CREATE TABLE `t_recharge`( `rid` BIGINT NOT NULL AUTO_INCREMENT, 
`serialNumber` VARCHAR(50), `rtime` VARCHAR(30), `rtype` VARCHAR(6), 
`amount` VARCHAR(20), `balance` VARCHAR(20), `rstatus` VARCHAR(30), 
`message` VARCHAR(500),PRIMARY KEY (`rid`) ) CHARSET=utf8 COLLATE=utf8_general_ci;


