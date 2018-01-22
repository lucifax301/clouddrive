
drop table if exists t_regist_office;

/*==============================================================*/
/* Table: t_regist_office                                       */
/*==============================================================*/
CREATE TABLE `t_regist_office` (
  `id` int(11) NOT NULL AUTO_INCREMENT comment '主键ID',
  `channel` tinyint(4) NOT NULL DEFAULT '1' comment '渠道：1-登记机关；2-车主名称；3-固定资产所属',
  `regist_id` int(11) DEFAULT NULL comment '机关ID',
  `name` varchar(126) NOT NULL comment '机关名称',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' comment '删除状态：0代表未删除，1代表已删除',
  `cuid` bigint(20) DEFAULT NULL comment '创建人id',
  `muid` bigint(20) DEFAULT NULL comment '更新人id',
  `ctime` datetime DEFAULT NULL comment '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 comment '登记机关信息表';




drop table if exists t_common_type;

/*==============================================================*/
/* Table: t_car_type    基础信息维护表                          */
/*==============================================================*/
CREATE TABLE `t_common_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `channel` tinyint(4) NOT NULL COMMENT '渠道：1-车辆类型；2-车辆品牌；3-车辆用途；4-牌号种类；5-使用性质；6-核定载客人数;7-燃料种类；8-车辆类别；9使用状态',
  `name` varchar(126) NOT NULL COMMENT '名称',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态值，每个channel不一定有',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0代表未删除，1代表已删除',
  `cuid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `muid` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `mtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='基础信息维护表';


drop table if exists t_car;

/*==============================================================*/
/* Table: t_car_detect  车辆基础信息表                            */
/*==============================================================*/
CREATE TABLE `t_car` (
  `car_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '车辆ID，主键',
  `motor_id` int(11) DEFAULT NULL COMMENT '机动车登记主键',
  `motor_code` varchar(32) DEFAULT NULL COMMENT '机动车登记证书编号',
  `motor_fcode` varchar(32) DEFAULT NULL COMMENT '机动车档案编码',
  `car_no` varchar(32) DEFAULT NULL COMMENT '车牌号',
  `frame_num` varchar(32) DEFAULT NULL COMMENT '车架号',
  `engine_num` varchar(32) DEFAULT NULL COMMENT '发动机号',
  `car_vin` varchar(128) DEFAULT NULL COMMENT '车辆识别码',
  `car_type` tinyint(4) DEFAULT '1' COMMENT '车辆类型1-自有车；2-加盟商用车',
  `car_brand` varchar(128) DEFAULT NULL COMMENT '车辆品牌',
  `car_owner` varchar(32) DEFAULT NULL COMMENT '车辆所有人',
  `asset_num` varchar(64) DEFAULT NULL COMMENT '资产编号',
  `reg_office` varchar(128) DEFAULT NULL COMMENT '登记机关',
  `file_no` varchar(64) DEFAULT NULL COMMENT '档案编号',
  `area_id` int(11) DEFAULT NULL COMMENT '区域ID',
  `area` varchar(128) DEFAULT NULL COMMENT '所属片区',
  `status` tinyint(4) DEFAULT NULL COMMENT '使用状态: 1-使用中，2-闲置，3-已减少',
  `drive_type` tinyint(4) DEFAULT NULL COMMENT '驾驶类型 ：1-C1,2-C2',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID(t_brand_car)',
  `brand_name` varchar(128) DEFAULT NULL COMMENT '品牌名称',
  `drive_num` varchar(64) DEFAULT NULL COMMENT '行驶证编号',
  `drive_photo` varchar(128) DEFAULT NULL COMMENT '行驶证照片',
  `school_id` int(11) DEFAULT '1' COMMENT '驾校id',
  `city_id` int(11) DEFAULT NULL COMMENT '训练车所属城市id',
  `car_color` tinyint(4) DEFAULT NULL COMMENT '车身颜色：1白,2红,3黑,4灰,5黄,6蓝,7绿,8紫,9其他',
  `car_img` varchar(256) DEFAULT NULL COMMENT '车辆图片',
  `manufacturer` varchar(128) DEFAULT NULL COMMENT '生产厂家',
  `leave_date` date DEFAULT NULL COMMENT '出厂日期',
  `buy_date` date DEFAULT NULL COMMENT '购买日期',
  `buy_fee` int(11) DEFAULT '0' COMMENT '购买价格',
  `reg_date` date DEFAULT NULL COMMENT '登记日期',
  `verify_end` date DEFAULT NULL COMMENT '年审到期日期',
  `scrap_date` date DEFAULT NULL COMMENT '强制报废日期',
  `insurance_end` date DEFAULT NULL COMMENT '保险到期日',
  `coach_id` int(11) DEFAULT NULL COMMENT '教练ID',
  `coach_name` varchar(32) DEFAULT NULL COMMENT '教练姓名',
  `cuid` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `muid` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20160106 DEFAULT CHARSET=utf8 COMMENT='车辆信息表';




drop table if exists t_car_detect;

/*==============================================================*/
/* Table: t_car_detect  黄绿标检测表                            */
/*==============================================================*/
CREATE TABLE `t_car_detect` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `car_id` int(11) NOT NULL COMMENT '车辆ID',
  `detect_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '检测类型：1-黄标；2-绿标',
  `cname` varchar(32) NOT NULL COMMENT '经办人',
  `cuid` bigint(20) DEFAULT NULL COMMENT '经办人ID',
  `detect_year` varchar(8) DEFAULT NULL COMMENT '检测年份',
  `detect_date` datetime DEFAULT NULL COMMENT '检测日期',
  `next_detect` datetime DEFAULT NULL COMMENT '下次检测日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除状态：0代表未删除，1代表已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='黄绿标检测表';



drop table if exists t_car_tax;

/*==============================================================*/
/* Table: t_car_tax     车船税表                                */
/*==============================================================*/
CREATE TABLE `t_car_tax` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `car_id` int(11) NOT NULL COMMENT '车辆ID',
  `tax` int(11) NOT NULL DEFAULT '0' COMMENT '车船税，单位分',
  `cname` varchar(32) NOT NULL COMMENT '经办人',
  `cuid` bigint(20) DEFAULT NULL COMMENT '经办人ID',
  `pay_date` datetime DEFAULT NULL COMMENT '缴费日期',
  `next_test` datetime DEFAULT NULL COMMENT '下次缴费日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '删除状态：0代表未删除，1代表已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='车船税表';


drop table if exists t_car_oilwear;

/*==============================================================*/
/* Table: t_car_oilwear     油耗记录表                          */
/*==============================================================*/
CREATE TABLE `t_car_oilwear` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `car_id` int(11) NOT NULL COMMENT '车辆ID',
  `oil_supplier` varchar(32) NOT NULL DEFAULT '0' COMMENT '加油人',
  `oil_date` datetime DEFAULT NULL COMMENT '加油日期',
  `oil_ml` int(11) DEFAULT NULL COMMENT '加油量',
  `oil_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '油品:0-0#；1-90#；2-92#；3-93#；4-95#；5-97#；6-98#；7-电',
  `cost` int(11) NOT NULL DEFAULT '0' COMMENT '单价：单位分',
  `sum_cost` int(11) NOT NULL DEFAULT '0' COMMENT '总价',
  `cost_type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '付款方式：1-油卡；2-现金',
  `oilstat_name` varchar(255) DEFAULT NULL COMMENT '油站名',
  `cname` varchar(32) DEFAULT NULL COMMENT '录入人',
  `ctime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '录入时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '删除状态：0代表未删除，1代表已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='油耗记录表';


drop table if exists t_car_repair;

/*==============================================================*/
/* Table: t_car_repair    维修记录表                            */
/*==============================================================*/
CREATE TABLE `t_car_repair` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `car_id` int(11) NOT NULL COMMENT '车辆ID',
  `reason` varchar(255) NOT NULL COMMENT '维修原因',
  `state` varchar(32) NOT NULL DEFAULT '0' COMMENT '维修状态:0-二保；1-大修；2-中修；3-小修；4-自费维修；5-外单位维修；6-事故车；7柴油添加剂；8-四轮保修；9-一保',
  `item_num` int(11) NOT NULL DEFAULT '0' COMMENT '维修项目数',
  `hwu` varchar(255) NOT NULL COMMENT '维修单位',
  `stime` datetime DEFAULT NULL COMMENT '开始日期',
  `etime` datetime DEFAULT NULL COMMENT '结束日期',
  `parts` varchar(255) NOT NULL COMMENT '配件，存配件ID，以|线分割',
  `parts_id` varchar(255) DEFAULT NULL COMMENT '配件ID',
  `money` int(11) NOT NULL DEFAULT '0' COMMENT '金额',
  `repairman` varchar(255) DEFAULT NULL COMMENT '修理工，多个以|线分割',
  `repairman_id` varchar(255) DEFAULT NULL COMMENT '修理工ID',
  `cname` varchar(32) DEFAULT NULL COMMENT '经办人',
  `cuid` bigint(20) DEFAULT NULL COMMENT '经办人ID',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '删除状态：0代表未删除，1代表已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='维修记录表';


drop table if exists t_car_annual;

/*==============================================================*/
/* Table: t_car_annual     年审记录表                           */
/*==============================================================*/
CREATE TABLE `t_car_annual` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `car_id` int(11) NOT NULL COMMENT '车辆ID',
  `annual_indate` datetime NOT NULL COMMENT '行驶证年检有效期',
  `annual_cost` int(11) DEFAULT NULL COMMENT '年检费用',
  `reim_date` date DEFAULT NULL COMMENT '年检报销日期',
  `yg_sign` tinyint(4) NOT NULL DEFAULT '1' COMMENT '环保分类标志：1-黄标；2-绿标',
  `yg_cost` int(11) DEFAULT '0' COMMENT '黄绿标费用',
  `detect_address` tinyint(4) DEFAULT '0' COMMENT '综检检测地点：0-未知；1-华隆达；2-恒顺通',
  `rohs_date` datetime NOT NULL COMMENT '环保有效期至',
  `detect_cost` int(11) DEFAULT '0' COMMENT '综检费用',
  `dcost_date` datetime DEFAULT NULL COMMENT '综检报销日期',
  `cname` varchar(32) NOT NULL COMMENT '经办人',
  `cuid` bigint(20) DEFAULT NULL COMMENT '经办人ID',
  `assert_date` datetime DEFAULT NULL COMMENT '二级维护日期',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '删除状态：0代表未删除，1代表已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='年审记录表';



drop table if exists t_car_insurance;

/*==============================================================*/
/* Table: t_car_insurance    保险信息表                         */
/*==============================================================*/
CREATE TABLE `t_car_insurance` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `car_id` int(11) NOT NULL COMMENT '车辆ID',
  `car_owner` varchar(64) DEFAULT NULL COMMENT '车主',
  `type` varchar(16) NOT NULL COMMENT '保险种类：1-车船费；2-交强险；3-商业险',
  `nature` tinyint(4) NOT NULL DEFAULT '1' COMMENT '保险性质：1-长训教学；2-场内教学；3-场外教学',
  `paid` varchar(255) DEFAULT NULL COMMENT '赔付额度:1-玻璃险,2-车损,3-乘客4座*10万,4-乘客4座*20万,5-盗抢险；6-第三者10万；7-第三者30万；8-第三者50万；9-第三者100万；10-司机2万；11-司机5万；12-司机10万；13-司机20万',
  `paid_name` varchar(255) NOT NULL COMMENT '赔付额度名称，多个以|线分割',
  `busrisks_date` datetime NOT NULL COMMENT '商业险投保时间',
  `busrisks_no` varchar(64) NOT NULL COMMENT '商业险保单号',
  `busrisks_fee` int(11) NOT NULL DEFAULT '0' COMMENT '商业险毛费金额',
  `busrisks_rates` int(11) NOT NULL DEFAULT '0' COMMENT '商业险返还率',
  `busrisks_rfee` int(11) DEFAULT NULL COMMENT '商业险返还金额',
  `busrisks_pay` int(11) DEFAULT '0' COMMENT '商业险实付保费',
  `busrisks_stime` datetime NOT NULL COMMENT '商业险开始时间',
  `busrisks_etime` datetime NOT NULL COMMENT '商业险终止时间',
  `cname` varchar(32) NOT NULL COMMENT '录入人',
  `cuid` bigint(20) DEFAULT NULL COMMENT '经办人ID',
  `sali_date` datetime NOT NULL COMMENT '交强险投保时间',
  `sali_no` varchar(64) DEFAULT NULL COMMENT '交强险单号',
  `sali_fee` int(11) NOT NULL DEFAULT '0' COMMENT '交强险毛费金额',
  `sali_rates` int(11) NOT NULL DEFAULT '0' COMMENT '交强险返还率',
  `sali_rfee` int(11) NOT NULL DEFAULT '0' COMMENT '交强险返还金额',
  `sali_pay` int(11) DEFAULT '0' COMMENT '交强险实付金额',
  `sali_stime` datetime DEFAULT NULL COMMENT '交强险开始日期',
  `sali_etime` datetime DEFAULT NULL COMMENT '交强险结束日期',
  `tax_fee` int(11) NOT NULL DEFAULT '0' COMMENT '车船税金额',
  `insure_companyId` tinyint(4) NOT NULL DEFAULT '1' COMMENT '保险公司ID：1-鼎和保险公司；2-平安保险公司；3-人保保险公司；4-永城保险公司',
  `insure_company` varchar(255) DEFAULT NULL COMMENT '保险公司名称',
  `insure_pay` int(11) DEFAULT '0' COMMENT '支付金额',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `account_date` datetime DEFAULT NULL COMMENT '报账日期',
  `is_del` tinyint(4) DEFAULT '0' COMMENT '删除状态：0代表未删除，1代表已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='保险信息表';