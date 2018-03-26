package cn.com.liliyun.market.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.market.mapper.PerformanceStatMapper;
import cn.com.liliyun.market.model.CSEnrolIndex;
import cn.com.liliyun.market.model.CoachEnrolIndex;
import cn.com.liliyun.market.model.CoachItemPerformanceStat;
import cn.com.liliyun.market.model.CoachPerformanceStat;
import cn.com.liliyun.market.model.CoachPerformanceStatItem;
import cn.com.liliyun.market.model.CoachStorePerformanceStat;
import cn.com.liliyun.market.model.PerformanceParam;
import cn.com.liliyun.market.model.PerformanceStat;
import cn.com.liliyun.market.model.StaffItemPerformanceStat;
import cn.com.liliyun.market.model.StaffPerformanceStat;
import cn.com.liliyun.market.model.StaffPerformanceStatItem;
import cn.com.liliyun.market.service.EnrolIndexService;
import cn.com.liliyun.market.service.PerformanceService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.Dept;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.DeptService;

@Service
public class PerformanceServiceImpl implements PerformanceService {

	@Autowired
	private PerformanceStatMapper performanceStatMapper;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private EnrolIndexService enrolIndexService;
	
	@Override
	public List<CoachPerformanceStat> coachstat(PerformanceParam param,User user) {
		List<CoachPerformanceStatItem> stats=performanceStatMapper.statByCoach(param);
		Map<Integer,CoachPerformanceStat> areaStatMap=new HashMap();
		
		List<Area> areas= areaService.selectAllList(null);
		
		
		List<Store> stores= storeService.selectAllList(null);
		
		CoachEnrolIndex last= enrolIndexService.getLastCoachEnrolIndex(user);
		int enrolindex=last==null?0:last.getEnrolindex();
		
		for(CoachPerformanceStatItem item:stats){
			if(!areaStatMap.containsKey(item.getAreaid())){
				CoachPerformanceStat stat=new CoachPerformanceStat();
				areaStatMap.put(item.getAreaid(), stat);
				stat.setAreaid(item.getAreaid());
				for(Area area:areas){
					if(stat.getAreaid()!=null&&stat.getAreaid()==area.getId()){
						stat.setArea(area.getName());
					}
				}
			}
			
			List<CoachStorePerformanceStat> data=areaStatMap.get(item.getAreaid()).getData();
			CoachStorePerformanceStat storeStat=null;
			for(CoachStorePerformanceStat storeitem:data){
				if(storeitem.getStoreid()==item.getStoreid().intValue()){
					storeStat=storeitem;break;
				}
			}
			
			if(storeStat==null){
				storeStat=new CoachStorePerformanceStat();
				storeStat.setStoreid(item.getStoreid());
				for(Store store:stores){
					if(storeStat.getStoreid()!=null&&storeStat.getStoreid().intValue()==store.getId()){
						storeStat.setStore(store.getName());
					}
				}
				areaStatMap.get(item.getAreaid()).getData().add(storeStat);
			}
			
			if(storeStat.getStore()==null)storeStat.setStore("其他");
			
			List<CoachItemPerformanceStat> areadata=storeStat.getData();
			CoachItemPerformanceStat pstat=new CoachItemPerformanceStat();
			pstat.setEnrolcount(item.getCount());
			pstat.setName(item.getName());
			pstat.setIndex(enrolindex);
			pstat.setCoachid(item.getCoachid());
			pstat.setExceedcount(item.getCount()>enrolindex?(item.getCount()-enrolindex):0);
			areadata.add(pstat);
		}
		
		List<CoachPerformanceStat> result=new ArrayList();
		result.addAll(areaStatMap.values());
		
		for(CoachPerformanceStat areastat:result){
			CoachStorePerformanceStat sumstat=new CoachStorePerformanceStat();
			sumstat.setStore("合计");
			CoachItemPerformanceStat sumstats=new CoachItemPerformanceStat();
			sumstats.setName("-");
			areastat.getData().add(sumstat);
			
			
			List<CoachStorePerformanceStat> storestats= areastat.getData();
			for(CoachStorePerformanceStat storestat:storestats){
				List<CoachItemPerformanceStat> atts= storestat.getData();
				for(PerformanceStat att:atts){
					sumstats.setEnrolcount(sumstats.getEnrolcount()+att.getEnrolcount());
					sumstats.setIndex(sumstats.getIndex()+att.getIndex());
					sumstats.setExceedcount(sumstats.getExceedcount()+att.getExceedcount());
					
				}
			}
			sumstat.getData().add(sumstats);
		}
		
		
		return result;
	}

	@Override
	public List<StaffPerformanceStat> staffstat(PerformanceParam param,User user) {
		List<StaffPerformanceStatItem> stats=performanceStatMapper.statByStaff(param);
		Map<Integer,StaffPerformanceStat> areaStatMap=new HashMap();
		Dept pa=new Dept();
		pa.setDblink(user.getDblink());
		List<Dept> depts= deptService.selectList(pa);
		
		
		CSEnrolIndex last= enrolIndexService.getLastCSEnrolIndex(user);
		int enrolindex=last==null?0:last.getEnrolindex();
		
		for(StaffPerformanceStatItem item:stats){
			if(!areaStatMap.containsKey(item.getDeptid())){
				StaffPerformanceStat stat=new StaffPerformanceStat();
				areaStatMap.put(item.getDeptid(), stat);
				stat.setDeptid(item.getDeptid());
				for(Dept dept:depts){
					if(stat.getDeptid()!=null&&stat.getDeptid().intValue()==dept.getId()){
						stat.setDept(dept.getName());
					}
				}
			}
			
			List<StaffItemPerformanceStat> data=areaStatMap.get(item.getDeptid()).getData();
			
			
			StaffItemPerformanceStat pstat=new StaffItemPerformanceStat();
			pstat.setEnrolcount(item.getCount());
			pstat.setName(item.getName());
			pstat.setStaffid(item.getStaffid());
			pstat.setIndex(enrolindex);
			pstat.setExceedcount(item.getCount()>enrolindex?(item.getCount()-enrolindex):0);
			data.add(pstat);
		}
		
		List<StaffPerformanceStat> result=new ArrayList();
		result.addAll(areaStatMap.values());
		
		for(StaffPerformanceStat areastat:result){
			StaffItemPerformanceStat sumstat=new StaffItemPerformanceStat();
			sumstat.setName("合计");
			areastat.getData().add(sumstat);
			
			
			List<StaffItemPerformanceStat> storestats= areastat.getData();
			for(StaffItemPerformanceStat storestat:storestats){
				sumstat.setEnrolcount(sumstat.getEnrolcount()+storestat.getEnrolcount());
				sumstat.setIndex(sumstat.getIndex()+storestat.getIndex());
				sumstat.setExceedcount(sumstat.getExceedcount()+storestat.getExceedcount());
			}
			
		}
		
		
		return result;
	}
}
