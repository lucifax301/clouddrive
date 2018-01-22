package cn.com.liliyun.coach.manager.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.coach.manager.CoachSettingManager;
import cn.com.liliyun.coach.mapper.CarTypeMapper;
import cn.com.liliyun.coach.mapper.ClassTypeMapper;
import cn.com.liliyun.coach.mapper.JobMapper;
import cn.com.liliyun.coach.mapper.TeachTypeMapper;
import cn.com.liliyun.coach.model.CoachCarType;
import cn.com.liliyun.coach.model.CoachClassType;
import cn.com.liliyun.coach.model.CoachJob;
import cn.com.liliyun.coach.model.CoachTeachType;
import cn.com.liliyun.user.model.User;

@Deprecated
@SuppressWarnings("rawtypes")
public class CoachSettingManagerImpl {

//	private static Logger logger=Logger.getLogger(CoachSettingManagerImpl.class);
//	
//	@Autowired
//	private TeachTypeMapper teachTypeMapper;
//	
//	@Autowired
//	private CarTypeMapper carTypeMapper;
//	
//	@Autowired
//	private ClassTypeMapper classTypeMapper;
//	
//	@Autowired
//	private JobMapper jobMapper;
//	
//	@Override
//	public void addTeachType(CoachTeachType type) {
//		teachTypeMapper.addTeachType(type);
//
//	}
//
//	@Override
//	public void updateTeachType(CoachTeachType oldtype,CoachTeachType newtype) {
//		teachTypeMapper.updateTeachType(newtype);
//		
//	}
//
//	@Override
//	public CoachTeachType getTeachType(int id) {
//		return teachTypeMapper.getTeachType(id);
//	}
//
//	@Override
//	public void deleteTeachType(int id) {
//		teachTypeMapper.deleteTeachType(id);
//
//	}
//
//	@Override
//	public void updateTeachTypeStatus(CoachTeachType oldtype,CoachTeachType newtype) {
//		teachTypeMapper.updateTeachTypeStatus(newtype);
//		
//	}
//
//	@Override
//	public List<CoachTeachType> listTeachType() {
//		return teachTypeMapper.listTeachType(null);
//	}
//
//	@Override
//	public void addClassType(CoachClassType type) {
//		classTypeMapper.addClassType(type);
//
//	}
//
//	@Override
//	public void updateClassType(CoachClassType oldtype,CoachClassType newtype) {
//		classTypeMapper.updateClassType(newtype);
//
//	}
//
//	@Override
//	public CoachClassType getClassType(int id) {
//		return classTypeMapper.getClassType(id);
//	}
//
//	@Override
//	public void deleteClassType(int id) {
//		classTypeMapper.deleteClassType(id);
//
//	}
//
//	@Override
//	public void updateClassTypeStatus(CoachClassType type) {
//		classTypeMapper.updateClassTypeStatus(type);
//
//	}
//
//	@Override
//	public List<CoachClassType> listClassType() {
//		return classTypeMapper.listClassType(null);
//	}
//
//	@Override
//	public void addCarType(CoachCarType type) {
//		carTypeMapper.addCarType(type);
//
//	}
//
//	@Override
//	public void deleteCarType(String type) {
//		carTypeMapper.deleteCarType(type);
//
//	}
//
//	@Override
//	public List<CoachCarType> listCarType() {
//		return carTypeMapper.listCarType();
//	}
//
//	public void saveCarType(List<CoachCarType> addtypes,List<CoachCarType> deltypes,User user) {
//		if(addtypes.size()>0){
//			Map params=new HashMap();
//			params.put("list", addtypes);
//			params.put("dblink", user.getDblink());
//			carTypeMapper.batchAddCarType(params);
//		}
//		if(deltypes.size()>0){
//			Map params=new HashMap();
//			params.put("list", deltypes);
//			params.put("dblink", user.getDblink());
//			carTypeMapper.batchDelCarType(params);
//		}
//	}
//
//	@Override
//	public void addJob(CoachJob job) {
//		jobMapper.addJob(job);
//		
//	}
//
//	@Override
//	public void updateJob(CoachJob oldtype, CoachJob newtype) {
//		jobMapper.updateJob(newtype);
//		
//	}
//
//	@Override
//	public CoachJob getJob(int id) {
//		return null;
//	}
//
//	@Override
//	public void deleteJob(int id) {
//		
//		
//	}
//
//	@Override
//	public List<CoachJob> listJob() {
//		return null;
//	}
	
	
}
