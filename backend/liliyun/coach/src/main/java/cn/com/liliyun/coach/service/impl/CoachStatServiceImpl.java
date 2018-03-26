package cn.com.liliyun.coach.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.coach.mapper.CoachMapper;
import cn.com.liliyun.coach.mapper.CoachStatMapper;
import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.coach.model.CoachTeachType;
import cn.com.liliyun.coach.model.stat.AreaStat;
import cn.com.liliyun.coach.model.stat.AreaStatParam;
import cn.com.liliyun.coach.model.stat.AreaStatRecord;
import cn.com.liliyun.coach.model.stat.AreaTeachCarTypeStat;
import cn.com.liliyun.coach.model.stat.AreaTeachTypeStat;
import cn.com.liliyun.coach.model.stat.CoachAreaStat;
import cn.com.liliyun.coach.model.stat.CoachStat;
import cn.com.liliyun.coach.model.stat.CoachStatParam;
import cn.com.liliyun.coach.model.stat.CoachStatRecord;
import cn.com.liliyun.coach.model.stat.CoachStoreStat;
import cn.com.liliyun.coach.model.stat.HeadCoachAreaStat;
import cn.com.liliyun.coach.model.stat.HeadCoachStat;
import cn.com.liliyun.coach.model.stat.HeadCoachStatParam;
import cn.com.liliyun.coach.model.stat.HeadCoachStatRecord;
import cn.com.liliyun.coach.model.stat.TeachTypeAreaStat;
import cn.com.liliyun.coach.model.stat.TeachTypeCarTypeStat;
import cn.com.liliyun.coach.model.stat.TeachTypeStat;
import cn.com.liliyun.coach.service.CoachSettingService;
import cn.com.liliyun.coach.service.CoachStatService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.StoreService;
//import cn.com.liliyun.user.model.User;

@SuppressWarnings("rawtypes")
@Service
public class CoachStatServiceImpl implements CoachStatService {

	@Autowired
	private CoachMapper coachMapper;
	
	@Autowired
	private CoachStatMapper coachStatMapper;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private CoachSettingService coachSettingService;
	
	@Override
	public List<AreaStat> statByArea(AreaStatParam param)  {

		List<AreaStatRecord> stats= coachStatMapper.statByArea(param);
		Map<Integer,AreaStat> areaStatMap=new HashMap();
		
		CoachTeachType ctt=new CoachTeachType();
		
		List<CoachTeachType> coachTeachTypes=coachSettingService.listTeachType(ctt);
		Area pa=new Area();
		pa.setDblink(param.getDblink());
		List<Area> areas= areaService.selectAllList(pa);
		for(AreaStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getAreaid())){
				AreaStat areaStat=new AreaStat();
				areaStat.setAreaid(stat.getAreaid());
				for(Area area:areas){
					if(area.getId().intValue()==stat.getAreaid())
					areaStat.setArea(area.getName());
				}
				if(areaStat.getArea()==null)areaStat.setArea("其他");
				areaStatMap.put(stat.getAreaid(), areaStat);
			}
			List<AreaTeachCarTypeStat> data=areaStatMap.get(stat.getAreaid()).getData();
			AreaTeachCarTypeStat matchTeachCarTypeStat=null;
			for(AreaTeachCarTypeStat teachCarTypeStat:data){
				if(teachCarTypeStat.getCartype()==null&&stat.getTeachcartype()==null){
					matchTeachCarTypeStat=teachCarTypeStat;
				}else if(teachCarTypeStat.getCartype()==null&&stat.getTeachcartype()!=null){
					continue;
				}else if(teachCarTypeStat.getCartype().equals(stat.getTeachcartype())){
					matchTeachCarTypeStat=teachCarTypeStat;
				}
			}
			if(matchTeachCarTypeStat==null){
				matchTeachCarTypeStat=new AreaTeachCarTypeStat(stat.getTeachcartype());
				if(matchTeachCarTypeStat.getCartype()==null) matchTeachCarTypeStat.setCartype("其他");
				areaStatMap.get(stat.getAreaid()).getData().add(matchTeachCarTypeStat);
			}
			
			AreaTeachTypeStat teachTypeStat=new AreaTeachTypeStat();
			for(CoachTeachType coachTeachType:coachTeachTypes){
				if(coachTeachType.getId().intValue()==stat.getTeachtypeid()){
					teachTypeStat.setTeachtype(coachTeachType.getType());
				}
			}
			if(teachTypeStat.getTeachtype()==null) teachTypeStat.setTeachtype("其他");
			teachTypeStat.setCoachcount(stat.getCoachcount());
			teachTypeStat.setStudentcount(stat.getStudentcount());
			teachTypeStat.setCarcount(stat.getCarcount());
			teachTypeStat.setAvgcarstudentcount(teachTypeStat.getCarcount()==0?0:teachTypeStat.getStudentcount()/teachTypeStat.getCarcount());
			teachTypeStat.setAvgcoachstudentcount(teachTypeStat.getCoachcount()==0?0: teachTypeStat.getStudentcount()/teachTypeStat.getCoachcount());
			matchTeachCarTypeStat.getData().add(teachTypeStat);
		}
		
		List<AreaStat> result=new ArrayList(); 
		result.addAll(areaStatMap.values());
		
		for(AreaStat areastat:result){
			AreaTeachCarTypeStat sumstat=new AreaTeachCarTypeStat();
			AreaTeachTypeStat sumatts=new AreaTeachTypeStat();
			sumatts.setTeachtype("-");
			sumstat.getData().add(sumatts);
			sumstat.setCartype("片区合计");
			List<AreaTeachCarTypeStat> atcts= areastat.getData();
			for(AreaTeachCarTypeStat atct:atcts){
				List<AreaTeachTypeStat> atts= atct.getData();
				for(AreaTeachTypeStat att:atts){
					sumatts.setStudentcount(sumatts.getStudentcount()+ att.getStudentcount());
					sumatts.setCoachcount(sumatts.getCoachcount()+att.getCoachcount());
					sumatts.setCarcount(sumatts.getCarcount()+att.getCarcount());
				}
			}
			sumatts.setAvgcoachstudentcount(sumatts.getCoachcount()==0?0: sumatts.getStudentcount()/sumatts.getCoachcount());
			sumatts.setAvgcarstudentcount(sumatts.getCarcount()==0?0:sumatts.getStudentcount()/sumatts.getCarcount());
			areastat.getData().add(sumstat);
		}
		
		return result;
		
	}
	
	@Override
	public List<TeachTypeStat> statByTeachType(AreaStatParam param)  {
		//List<Coach> coachs= coachMapper.selectByCondition(new Coach());
		List<AreaStatRecord> stats= coachStatMapper.statByTeachType(param);
		Map<Integer,TeachTypeStat> areaStatMap=new HashMap();
		CoachTeachType ctt=new CoachTeachType();
		ctt.setDblink(param.getDblink());
		List<CoachTeachType> coachTeachTypes=coachSettingService.listTeachType(ctt);
		Area pa=new Area();
		pa.setDblink(param.getDblink());
		List<Area> areas= areaService.selectAllList(pa);
		for(AreaStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getTeachtypeid())){
				TeachTypeStat teachTypeStat=new TeachTypeStat();
				areaStatMap.put(stat.getTeachtypeid(), teachTypeStat);
				for(CoachTeachType teachtype:coachTeachTypes){
					if(teachtype.getId().intValue()==stat.getTeachtypeid()){
						teachTypeStat.setTeachtype(teachtype.getType());
					}
				}
				if(teachTypeStat.getTeachtype()==null) teachTypeStat.setTeachtype("其他");
			}
			
			List<TeachTypeCarTypeStat> data=areaStatMap.get(stat.getTeachtypeid()).getData();
			TeachTypeCarTypeStat matchTeachCarTypeStat=null;
			for(TeachTypeCarTypeStat teachCarTypeStat:data){
				if(teachCarTypeStat.getCartype()==null&&stat.getTeachcartype()==null){
					matchTeachCarTypeStat=teachCarTypeStat;
				}else if(teachCarTypeStat.getCartype()==null&&stat.getTeachcartype()!=null){
					continue;
				}else if(teachCarTypeStat.getCartype().equals(stat.getTeachcartype())){
					matchTeachCarTypeStat=teachCarTypeStat;
				}
			}
			if(matchTeachCarTypeStat==null){
				matchTeachCarTypeStat=new TeachTypeCarTypeStat(stat.getTeachcartype());
				if(matchTeachCarTypeStat.getCartype()==null) matchTeachCarTypeStat.setCartype("其他");
				areaStatMap.get(stat.getTeachtypeid()).getData().add(matchTeachCarTypeStat);
			}
			
			
			TeachTypeAreaStat teachTypeStat=new TeachTypeAreaStat();
			for(Area area:areas){
				if(stat.getAreaid()==area.getId()){
					teachTypeStat.setArea(area.getName());
				}
			}
			if(teachTypeStat.getArea()==null)teachTypeStat.setArea("其他");
			teachTypeStat.setCoachcount(stat.getCoachcount());
			teachTypeStat.setStudentcount(stat.getStudentcount());
			teachTypeStat.setCarcount(stat.getCarcount());
			teachTypeStat.setAvgcoachstudentcount(teachTypeStat.getCoachcount()==0?0: teachTypeStat.getStudentcount()/teachTypeStat.getCoachcount());
			teachTypeStat.setAvgcarstudentcount(teachTypeStat.getCarcount()==0?0:teachTypeStat.getStudentcount()/teachTypeStat.getCarcount());
			matchTeachCarTypeStat.getData().add(teachTypeStat);
		}
		List<TeachTypeStat> result=new ArrayList(); 
		result.addAll(areaStatMap.values());
		
		for(TeachTypeStat areastat:result){
			TeachTypeCarTypeStat sumstat=new TeachTypeCarTypeStat();
			TeachTypeAreaStat sumatts=new TeachTypeAreaStat();
			sumatts.setArea("-");
			sumstat.getData().add(sumatts);
			sumstat.setCartype("合计");
			List<TeachTypeCarTypeStat> atcts= areastat.getData();
			for(TeachTypeCarTypeStat atct:atcts){
				List<TeachTypeAreaStat> atts= atct.getData();
				for(TeachTypeAreaStat att:atts){
					sumatts.setStudentcount(sumatts.getStudentcount()+ att.getStudentcount());
					sumatts.setCoachcount(sumatts.getCoachcount()+att.getCoachcount());
					sumatts.setCarcount(sumatts.getCarcount()+att.getCarcount());
				}
			}
			sumatts.setAvgcoachstudentcount(sumatts.getCoachcount()==0?0: sumatts.getStudentcount()/sumatts.getCoachcount());
			sumatts.setAvgcarstudentcount(sumatts.getCarcount()==0?0:sumatts.getStudentcount()/sumatts.getCarcount());
			areastat.getData().add(sumstat);
		}
		
		return result;
		
	}
	
	@Override
	public List<CoachAreaStat> statByCoach(CoachStatParam param) {
		List<CoachStatRecord> stats= coachStatMapper.statByCoach(param);
		Map<Integer,CoachAreaStat> areaStatMap=new HashMap();
		Area pa=new Area();
		
		List<Area> areas= areaService.selectAllList(pa);
		Store ps=new Store();
		
		List<Store> stores= storeService.selectAllList(ps);
		for(CoachStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getAreaid())){
				CoachAreaStat coachAreaStat=new CoachAreaStat();
				areaStatMap.put(stat.getAreaid(), coachAreaStat);
				for(Area area:areas){
					if(stat.getAreaid()==area.getId()){
						coachAreaStat.setArea(area.getName());
					}
				}
			}
			
			List<CoachStoreStat> data=areaStatMap.get(stat.getAreaid()).getData();
			CoachStoreStat matchCoachStoreStat=null;
			for(CoachStoreStat coachStoreStat:data){
				if(coachStoreStat.getStoreid()==null&&stat.getStoreid()==null){
					matchCoachStoreStat=coachStoreStat;
				}else if(coachStoreStat.getStoreid()==null&&stat.getStoreid()!=null){
					continue;
				}else if(coachStoreStat.getStoreid()!=null&&stat.getStoreid()==null){
					continue;
				}else if(coachStoreStat.getStoreid().intValue()==stat.getStoreid().intValue()){
					matchCoachStoreStat=coachStoreStat;
				}
			}
			if(matchCoachStoreStat==null){
				matchCoachStoreStat=new CoachStoreStat(stat.getStoreid());
				if(matchCoachStoreStat.getStoreid()==null||matchCoachStoreStat.getStoreid()==0) matchCoachStoreStat.setStorename("其他");
				areaStatMap.get(stat.getAreaid()).getData().add(matchCoachStoreStat);
				for(Store store:stores){
					if(matchCoachStoreStat.getStoreid()!=null&&store.getId().intValue()==matchCoachStoreStat.getStoreid().intValue()){
						matchCoachStoreStat.setStorename(store.getName());break;
					}
				}
			}
			
			List<CoachStat> coachdata=matchCoachStoreStat.getData();
			CoachStat matchcoachStat=null;
			for(CoachStat coachstat:coachdata){
				if(coachstat.getCoachid()==stat.getCoachid()){
					matchcoachStat=coachstat;
					break;
				}
			}
			
			if(matchcoachStat==null){
				matchcoachStat=new CoachStat();
				matchcoachStat.setCoachname(stat.getCoachname());
			}
			if(stat.getApplyexam()==1){
				matchcoachStat.setStep1(matchcoachStat.getStep1()+stat.getCount());
				matchcoachStat.setSum(matchcoachStat.getSum()+stat.getCount());
			}
			if(stat.getApplyexam()==2){
				matchcoachStat.setStep2(matchcoachStat.getStep2()+stat.getCount());
				matchcoachStat.setSum(matchcoachStat.getSum()+stat.getCount());
			}
			if(stat.getApplyexam()==3){
				matchcoachStat.setStep3(matchcoachStat.getStep3()+stat.getCount());
				matchcoachStat.setSum(matchcoachStat.getSum()+stat.getCount());
			}
			if(stat.getApplyexam()==4){
				matchcoachStat.setStep4(matchcoachStat.getStep4()+stat.getCount());
				matchcoachStat.setSum(matchcoachStat.getSum()+stat.getCount());
			}
			matchCoachStoreStat.getData().add(matchcoachStat);
		}
		
		List<CoachAreaStat> result=new ArrayList();
		result.addAll(areaStatMap.values());
		
		for(CoachAreaStat cas:result){
			List<CoachStoreStat> css=cas.getData();
			
			for(CoachStoreStat cs:css){
				List<CoachStat> csdata=cs.getData();
				
				CoachStat sumstat=new CoachStat();
				sumstat.setCoachname("合计");
				for(CoachStat s:csdata){
					sumstat.setStep1(sumstat.getStep1()+ s.getStep1());
					sumstat.setStep2(sumstat.getStep2()+ s.getStep2());
					sumstat.setStep3(sumstat.getStep3()+ s.getStep3());
					sumstat.setStep4(sumstat.getStep4()+ s.getStep4());
					sumstat.setSum(sumstat.getSum()+s.getSum());
				}
				cs.getData().add(sumstat);
			}
		}
		
		return result;
	}

	@Override
	public List<HeadCoachAreaStat> statByHeadCoach(HeadCoachStatParam param)
			 {
		List<HeadCoachStatRecord> stats= coachStatMapper.statByHeadCoach(param);
		Map<Integer,HeadCoachAreaStat> areaStatMap=new HashMap();
		Area pa=new Area();
		
		List<Area> areas= areaService.selectAllList(pa);
		Store ps=new Store();
		
		List<Store> stores= storeService.selectAllList(ps);
		for(HeadCoachStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getAreaid())){
				HeadCoachAreaStat coachAreaStat=new HeadCoachAreaStat();
				areaStatMap.put(stat.getAreaid(), coachAreaStat);
				for(Area area:areas){
					if(stat.getAreaid()==area.getId()){
						coachAreaStat.setArea(area.getName());
					}
				}
				
			}
			
			List<HeadCoachStat> data=areaStatMap.get(stat.getAreaid()).getData();
			HeadCoachStat matchCoachStoreStat=null;
			for(HeadCoachStat headCoachStat:data){
				if(headCoachStat.getCoachid()==null&&stat.getHeadcoachid()==null){
					matchCoachStoreStat=headCoachStat;
				}else if(headCoachStat.getCoachid()==null&&stat.getHeadcoachid()!=null){
					continue;
				}else if(headCoachStat.getCoachid()!=null&&stat.getHeadcoachid()==null){
					continue;
				}else if(headCoachStat.getCoachid().intValue()==stat.getHeadcoachid().intValue()){
					matchCoachStoreStat=headCoachStat;
				}
			}
			if(matchCoachStoreStat==null){
				matchCoachStoreStat=new HeadCoachStat(stat.getHeadcoachid());
				areaStatMap.get(stat.getAreaid()).getData().add(matchCoachStoreStat);
				Coach pc= new Coach(stat.getHeadcoachid());
				
				Coach c= coachMapper.selectByPrimaryKey(pc);
				if(c!=null){
					matchCoachStoreStat.setCoachname(c.getName());
				}else{
					matchCoachStoreStat.setCoachname("其他");
				}
			}
			
			List<CoachStat> coachdata=matchCoachStoreStat.getData();
			CoachStat matchcoachStat=null;
			for(CoachStat coachstat:coachdata){
				if(coachstat.getCoachid()==stat.getCoachid()){
					matchcoachStat=coachstat;
					break;
				}
			}
			
			if(matchcoachStat==null){
				matchcoachStat=new CoachStat();
				matchcoachStat.setCoachname(stat.getCoachname());
			}
			if(stat.getApplyexam()==1){
				matchcoachStat.setStep1(matchcoachStat.getStep1()+stat.getCount());
				matchcoachStat.setSum(matchcoachStat.getSum()+stat.getCount());
			}
			if(stat.getApplyexam()==2){
				matchcoachStat.setStep2(matchcoachStat.getStep2()+stat.getCount());
				matchcoachStat.setSum(matchcoachStat.getSum()+stat.getCount());
			}
			if(stat.getApplyexam()==3){
				matchcoachStat.setStep3(matchcoachStat.getStep3()+stat.getCount());
				matchcoachStat.setSum(matchcoachStat.getSum()+stat.getCount());
			}
			if(stat.getApplyexam()==4){
				matchcoachStat.setStep4(matchcoachStat.getStep4()+stat.getCount());
				matchcoachStat.setSum(matchcoachStat.getSum()+stat.getCount());
			}
			matchCoachStoreStat.getData().add(matchcoachStat);
		}
		
		List<HeadCoachAreaStat> result=new ArrayList();
		result.addAll(areaStatMap.values());
		
		for(HeadCoachAreaStat cas:result){
			List<HeadCoachStat> css=cas.getData();
			
			for(HeadCoachStat cs:css){
				List<CoachStat> csdata=cs.getData();
				
				CoachStat sumstat=new CoachStat();
				sumstat.setCoachname("合计");
				for(CoachStat s:csdata){
					sumstat.setStep1(sumstat.getStep1()+ s.getStep1());
					sumstat.setStep2(sumstat.getStep2()+ s.getStep2());
					sumstat.setStep3(sumstat.getStep3()+ s.getStep3());
					sumstat.setStep4(sumstat.getStep4()+ s.getStep4());
					sumstat.setSum(sumstat.getSum()+s.getSum());
				}
				cs.getData().add(sumstat);
			}
		}
		return result;
	}

	
}
