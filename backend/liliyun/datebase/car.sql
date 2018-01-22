
drop table if exists t_regist_office;

/*==============================================================*/
/* Table: t_regist_office                                       */
/*==============================================================*/
CREATE TABLE `t_regist_office` (
  `id` int(11) NOT NULL AUTO_INCREMENT comment '����ID',
  `channel` tinyint(4) NOT NULL DEFAULT '1' comment '������1-�Ǽǻ��أ�2-�������ƣ�3-�̶��ʲ�����',
  `regist_id` int(11) DEFAULT NULL comment '����ID',
  `name` varchar(126) NOT NULL comment '��������',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' comment 'ɾ��״̬��0����δɾ����1������ɾ��',
  `cuid` bigint(20) DEFAULT NULL comment '������id',
  `muid` bigint(20) DEFAULT NULL comment '������id',
  `ctime` datetime DEFAULT NULL comment '����ʱ��',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '�Ǽǻ�����Ϣ��';




drop table if exists t_common_type;

/*==============================================================*/
/* Table: t_car_type    ������Ϣά����                          */
/*==============================================================*/
CREATE TABLE `t_common_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `channel` tinyint(4) NOT NULL COMMENT '������1-�������ͣ�2-����Ʒ�ƣ�3-������;��4-�ƺ����ࣻ5-ʹ�����ʣ�6-�˶��ؿ�����;7-ȼ�����ࣻ8-�������9ʹ��״̬',
  `name` varchar(126) NOT NULL COMMENT '����',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '״ֵ̬��ÿ��channel��һ����',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'ɾ��״̬��0����δɾ����1������ɾ��',
  `cuid` bigint(20) DEFAULT NULL COMMENT '������id',
  `muid` bigint(20) DEFAULT NULL COMMENT '������id',
  `ctime` datetime DEFAULT NULL COMMENT '����ʱ��',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='������Ϣά����';


drop table if exists t_car;

/*==============================================================*/
/* Table: t_car_detect  ����������Ϣ��                            */
/*==============================================================*/
CREATE TABLE `t_car` (
  `car_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID������',
  `motor_id` int(11) DEFAULT NULL COMMENT '�������Ǽ�����',
  `motor_code` varchar(32) DEFAULT NULL COMMENT '�������Ǽ�֤����',
  `motor_fcode` varchar(32) DEFAULT NULL COMMENT '��������������',
  `car_no` varchar(32) DEFAULT NULL COMMENT '���ƺ�',
  `frame_num` varchar(32) DEFAULT NULL COMMENT '���ܺ�',
  `engine_num` varchar(32) DEFAULT NULL COMMENT '��������',
  `car_vin` varchar(128) DEFAULT NULL COMMENT '����ʶ����',
  `car_type` tinyint(4) DEFAULT '1' COMMENT '��������1-���г���2-�������ó�',
  `car_brand` varchar(128) DEFAULT NULL COMMENT '����Ʒ��',
  `car_owner` varchar(32) DEFAULT NULL COMMENT '����������',
  `asset_num` varchar(64) DEFAULT NULL COMMENT '�ʲ����',
  `reg_office` varchar(128) DEFAULT NULL COMMENT '�Ǽǻ���',
  `file_no` varchar(64) DEFAULT NULL COMMENT '�������',
  `area_id` int(11) DEFAULT NULL COMMENT '����ID',
  `area` varchar(128) DEFAULT NULL COMMENT '����Ƭ��',
  `status` tinyint(4) DEFAULT NULL COMMENT 'ʹ��״̬: 1-ʹ���У�2-���ã�3-�Ѽ���',
  `drive_type` tinyint(4) DEFAULT NULL COMMENT '��ʻ���� ��1-C1,2-C2',
  `brand_id` int(11) DEFAULT NULL COMMENT 'Ʒ��ID(t_brand_car)',
  `brand_name` varchar(128) DEFAULT NULL COMMENT 'Ʒ������',
  `drive_num` varchar(64) DEFAULT NULL COMMENT '��ʻ֤���',
  `drive_photo` varchar(128) DEFAULT NULL COMMENT '��ʻ֤��Ƭ',
  `school_id` int(11) DEFAULT '1' COMMENT '��Уid',
  `city_id` int(11) DEFAULT NULL COMMENT 'ѵ������������id',
  `car_color` tinyint(4) DEFAULT NULL COMMENT '������ɫ��1��,2��,3��,4��,5��,6��,7��,8��,9����',
  `car_img` varchar(256) DEFAULT NULL COMMENT '����ͼƬ',
  `manufacturer` varchar(128) DEFAULT NULL COMMENT '��������',
  `leave_date` date DEFAULT NULL COMMENT '��������',
  `buy_date` date DEFAULT NULL COMMENT '��������',
  `buy_fee` int(11) DEFAULT '0' COMMENT '����۸�',
  `reg_date` date DEFAULT NULL COMMENT '�Ǽ�����',
  `verify_end` date DEFAULT NULL COMMENT '����������',
  `scrap_date` date DEFAULT NULL COMMENT 'ǿ�Ʊ�������',
  `insurance_end` date DEFAULT NULL COMMENT '���յ�����',
  `coach_id` int(11) DEFAULT NULL COMMENT '����ID',
  `coach_name` varchar(32) DEFAULT NULL COMMENT '��������',
  `cuid` bigint(20) DEFAULT NULL COMMENT '������id',
  `muid` bigint(20) DEFAULT NULL COMMENT '������id',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '����ʱ��',
  `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '����ʱ��',
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20160106 DEFAULT CHARSET=utf8 COMMENT='������Ϣ��';




drop table if exists t_car_detect;

/*==============================================================*/
/* Table: t_car_detect  ���̱����                            */
/*==============================================================*/
CREATE TABLE `t_car_detect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `car_id` int(11) NOT NULL COMMENT '����ID',
  `detect_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '������ͣ�1-�Ʊꣻ2-�̱�',
  `cname` varchar(32) NOT NULL COMMENT '������',
  `cuid` bigint(20) DEFAULT NULL COMMENT '������ID',
  `detect_year` varchar(8) DEFAULT NULL COMMENT '������',
  `detect_date` datetime DEFAULT NULL COMMENT '�������',
  `next_detect` datetime DEFAULT NULL COMMENT '�´μ������',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'ɾ��״̬��0����δɾ����1������ɾ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='���̱����';



drop table if exists t_car_tax;

/*==============================================================*/
/* Table: t_car_tax     ����˰��                                */
/*==============================================================*/
CREATE TABLE `t_car_tax` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `car_id` int(11) NOT NULL COMMENT '����ID',
  `tax` int(11) NOT NULL DEFAULT '0' COMMENT '����˰����λ��',
  `cname` varchar(32) NOT NULL COMMENT '������',
  `cuid` bigint(20) DEFAULT NULL COMMENT '������ID',
  `pay_date` datetime DEFAULT NULL COMMENT '�ɷ�����',
  `next_test` datetime DEFAULT NULL COMMENT '�´νɷ�����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `is_del` tinyint(4) DEFAULT '0' COMMENT 'ɾ��״̬��0����δɾ����1������ɾ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='����˰��';


drop table if exists t_car_oilwear;

/*==============================================================*/
/* Table: t_car_oilwear     �ͺļ�¼��                          */
/*==============================================================*/
CREATE TABLE `t_car_oilwear` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `car_id` int(11) NOT NULL COMMENT '����ID',
  `oil_supplier` varchar(32) NOT NULL DEFAULT '0' COMMENT '������',
  `oil_date` datetime DEFAULT NULL COMMENT '��������',
  `oil_ml` int(11) DEFAULT NULL COMMENT '������',
  `oil_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '��Ʒ:0-0#��1-90#��2-92#��3-93#��4-95#��5-97#��6-98#��7-��',
  `cost` int(11) NOT NULL DEFAULT '0' COMMENT '���ۣ���λ��',
  `sum_cost` int(11) NOT NULL DEFAULT '0' COMMENT '�ܼ�',
  `cost_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '���ʽ��1-�Ϳ���2-�ֽ�',
  `oilstat_name` varchar(255) DEFAULT NULL COMMENT '��վ��',
  `cname` varchar(32) DEFAULT NULL COMMENT '¼����',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '¼��ʱ��',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `is_del` tinyint(4) DEFAULT '0' COMMENT 'ɾ��״̬��0����δɾ����1������ɾ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='�ͺļ�¼��';


drop table if exists t_car_repair;

/*==============================================================*/
/* Table: t_car_repair    ά�޼�¼��                            */
/*==============================================================*/
CREATE TABLE `t_car_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `car_id` int(11) NOT NULL COMMENT '����ID',
  `reason` varchar(255) NOT NULL COMMENT 'ά��ԭ��',
  `state` varchar(32) NOT NULL DEFAULT '0' COMMENT 'ά��״̬:0-������1-���ޣ�2-���ޣ�3-С�ޣ�4-�Է�ά�ޣ�5-�ⵥλά�ޣ�6-�¹ʳ���7������Ӽ���8-���ֱ��ޣ�9-һ��',
  `item_num` int(11) NOT NULL DEFAULT '0' COMMENT 'ά����Ŀ��',
  `hwu` varchar(255) NOT NULL COMMENT 'ά�޵�λ',
  `stime` datetime DEFAULT NULL COMMENT '��ʼ����',
  `etime` datetime DEFAULT NULL COMMENT '��������',
  `parts` varchar(255) NOT NULL COMMENT '����������ID����|�߷ָ�',
  `parts_id` varchar(255) DEFAULT NULL COMMENT '���ID',
  `money` int(11) NOT NULL DEFAULT '0' COMMENT '���',
  `repairman` varchar(255) DEFAULT NULL COMMENT '�����������|�߷ָ�',
  `repairman_id` varchar(255) DEFAULT NULL COMMENT '����ID',
  `cname` varchar(32) DEFAULT NULL COMMENT '������',
  `cuid` bigint(20) DEFAULT NULL COMMENT '������ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `is_del` tinyint(4) DEFAULT '0' COMMENT 'ɾ��״̬��0����δɾ����1������ɾ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='ά�޼�¼��';


drop table if exists t_car_annual;

/*==============================================================*/
/* Table: t_car_annual     �����¼��                           */
/*==============================================================*/
CREATE TABLE `t_car_annual` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `car_id` int(11) NOT NULL COMMENT '����ID',
  `annual_indate` datetime NOT NULL COMMENT '��ʻ֤�����Ч��',
  `annual_cost` int(11) DEFAULT NULL COMMENT '������',
  `reim_date` date DEFAULT NULL COMMENT '��챨������',
  `yg_sign` tinyint(4) NOT NULL DEFAULT '1' COMMENT '���������־��1-�Ʊꣻ2-�̱�',
  `yg_cost` int(11) DEFAULT '0' COMMENT '���̱����',
  `detect_address` tinyint(4) DEFAULT '0' COMMENT '�ۼ���ص㣺0-δ֪��1-��¡�2-��˳ͨ',
  `rohs_date` datetime NOT NULL COMMENT '������Ч����',
  `detect_cost` int(11) DEFAULT '0' COMMENT '�ۼ����',
  `dcost_date` datetime DEFAULT NULL COMMENT '�ۼ챨������',
  `cname` varchar(32) NOT NULL COMMENT '������',
  `cuid` bigint(20) DEFAULT NULL COMMENT '������ID',
  `assert_date` datetime DEFAULT NULL COMMENT '����ά������',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `is_del` tinyint(4) DEFAULT '0' COMMENT 'ɾ��״̬��0����δɾ����1������ɾ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='�����¼��';



drop table if exists t_car_insurance;

/*==============================================================*/
/* Table: t_car_insurance    ������Ϣ��                         */
/*==============================================================*/
CREATE TABLE `t_car_insurance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '����ID',
  `car_id` int(11) NOT NULL COMMENT '����ID',
  `car_owner` varchar(64) DEFAULT NULL COMMENT '����',
  `type` varchar(16) NOT NULL COMMENT '�������ࣺ1-�����ѣ�2-��ǿ�գ�3-��ҵ��',
  `nature` tinyint(4) NOT NULL DEFAULT '1' COMMENT '�������ʣ�1-��ѵ��ѧ��2-���ڽ�ѧ��3-�����ѧ',
  `paid` varchar(255) DEFAULT NULL COMMENT '�⸶���:1-������,2-����,3-�˿�4��*10��,4-�˿�4��*20��,5-�����գ�6-������10��7-������30��8-������50��9-������100��10-˾��2��11-˾��5��12-˾��10��13-˾��20��',
  `paid_name` varchar(255) NOT NULL COMMENT '�⸶������ƣ������|�߷ָ�',
  `busrisks_date` datetime NOT NULL COMMENT '��ҵ��Ͷ��ʱ��',
  `busrisks_no` varchar(64) NOT NULL COMMENT '��ҵ�ձ�����',
  `busrisks_fee` int(11) NOT NULL DEFAULT '0' COMMENT '��ҵ��ë�ѽ��',
  `busrisks_rates` int(11) NOT NULL DEFAULT '0' COMMENT '��ҵ�շ�����',
  `busrisks_rfee` int(11) DEFAULT NULL COMMENT '��ҵ�շ������',
  `busrisks_pay` int(11) DEFAULT '0' COMMENT '��ҵ��ʵ������',
  `busrisks_stime` datetime NOT NULL COMMENT '��ҵ�տ�ʼʱ��',
  `busrisks_etime` datetime NOT NULL COMMENT '��ҵ����ֹʱ��',
  `cname` varchar(32) NOT NULL COMMENT '¼����',
  `cuid` bigint(20) DEFAULT NULL COMMENT '������ID',
  `sali_date` datetime NOT NULL COMMENT '��ǿ��Ͷ��ʱ��',
  `sali_no` varchar(64) DEFAULT NULL COMMENT '��ǿ�յ���',
  `sali_fee` int(11) NOT NULL DEFAULT '0' COMMENT '��ǿ��ë�ѽ��',
  `sali_rates` int(11) NOT NULL DEFAULT '0' COMMENT '��ǿ�շ�����',
  `sali_rfee` int(11) NOT NULL DEFAULT '0' COMMENT '��ǿ�շ������',
  `sali_pay` int(11) DEFAULT '0' COMMENT '��ǿ��ʵ�����',
  `sali_stime` datetime DEFAULT NULL COMMENT '��ǿ�տ�ʼ����',
  `sali_etime` datetime DEFAULT NULL COMMENT '��ǿ�ս�������',
  `tax_fee` int(11) NOT NULL DEFAULT '0' COMMENT '����˰���',
  `insure_companyId` tinyint(4) NOT NULL DEFAULT '1' COMMENT '���չ�˾ID��1-���ͱ��չ�˾��2-ƽ�����չ�˾��3-�˱����չ�˾��4-���Ǳ��չ�˾',
  `insure_company` varchar(255) DEFAULT NULL COMMENT '���չ�˾����',
  `insure_pay` int(11) DEFAULT '0' COMMENT '֧�����',
  `remark` varchar(255) DEFAULT NULL COMMENT '��ע',
  `account_date` datetime DEFAULT NULL COMMENT '��������',
  `is_del` tinyint(4) DEFAULT '0' COMMENT 'ɾ��״̬��0����δɾ����1������ɾ��',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='������Ϣ��';