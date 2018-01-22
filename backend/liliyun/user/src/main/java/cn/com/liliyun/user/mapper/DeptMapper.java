package cn.com.liliyun.user.mapper;

import java.util.List;

import cn.com.liliyun.user.model.Dept;

public interface DeptMapper {

	int delete(Dept dept);

    int insert(Dept dept);
    
    int update(Dept dept);

    Dept get(Dept dept);
    
    List <Dept> selectList(Dept dept);
    
   
}