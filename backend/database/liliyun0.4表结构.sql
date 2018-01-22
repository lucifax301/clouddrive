
 -- 20160914 投诉 投诉人字段修改
alter table t_complain add cusername varchar(255) comment '创建人';
alter table t_complain add musername varchar(255) comment '更新人';

alter table t_complain_return add cusername varchar(255) comment '创建人';
alter table t_complain_return add musername varchar(255) comment '更新人';
