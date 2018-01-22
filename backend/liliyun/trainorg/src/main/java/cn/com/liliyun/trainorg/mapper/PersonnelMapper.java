package cn.com.liliyun.trainorg.mapper;

import java.util.List;

import cn.com.liliyun.trainorg.model.Personnel;

public interface PersonnelMapper {
	//新增
	public int savePersonnel(Personnel personnel);
	//删除
	public int deletePersonnel(Personnel personnel);
	//更新
	public int updatePersonnel(Personnel personnel);
	//人员列表
	public List getAllPersonnel(Personnel personnel);
	//根据编号，姓名，身份证查询
	public List getPersonnelbyers(Personnel personnel);
	//根据编号查
	public Personnel getPersonnelById(Personnel personnel);
}