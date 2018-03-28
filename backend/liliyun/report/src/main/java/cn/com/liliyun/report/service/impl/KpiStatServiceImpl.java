package cn.com.liliyun.report.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.coach.model.CoachTeachType;
import cn.com.liliyun.coach.service.CoachSettingService;
import cn.com.liliyun.report.mapper.KpiAreaStatMapper;
import cn.com.liliyun.report.mapper.KpiClassStatMapper;
import cn.com.liliyun.report.mapper.KpiCoachStatMapper;
import cn.com.liliyun.report.mapper.KpiExamStatMapper;
import cn.com.liliyun.report.mapper.KpiHeadCoachStatMapper;
import cn.com.liliyun.report.mapper.KpiStoreStatMapper;
import cn.com.liliyun.report.model.KpiAreaStat;
import cn.com.liliyun.report.model.KpiAreaStatParam;
import cn.com.liliyun.report.model.KpiAreaStatRecord;
import cn.com.liliyun.report.model.KpiClassAreaStat;
import cn.com.liliyun.report.model.KpiClassStat;
import cn.com.liliyun.report.model.KpiClassStatParam;
import cn.com.liliyun.report.model.KpiClassStatRecord;
import cn.com.liliyun.report.model.KpiCoachStat;
import cn.com.liliyun.report.model.KpiCoachStatParam;
import cn.com.liliyun.report.model.KpiCoachStatRecord;
import cn.com.liliyun.report.model.KpiCoachStoreStat;
import cn.com.liliyun.report.model.KpiExamStat;
import cn.com.liliyun.report.model.KpiExamStatParam;
import cn.com.liliyun.report.model.KpiExamStatRecord;
import cn.com.liliyun.report.model.KpiHeadCoachStat;
import cn.com.liliyun.report.model.KpiHeadCoachStatParam;
import cn.com.liliyun.report.model.KpiHeadCoachStatRecord;
import cn.com.liliyun.report.model.KpiStatRecord;
import cn.com.liliyun.report.model.KpiStoreStat;
import cn.com.liliyun.report.model.KpiStoreStatParam;
import cn.com.liliyun.report.model.KpiStoreStatRecord;
import cn.com.liliyun.report.service.KpiStatService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.trainorg.service.StoreService;


@SuppressWarnings("rawtypes")
@Service
public class KpiStatServiceImpl implements KpiStatService {

	@Autowired
	private KpiAreaStatMapper kpiAreaStatMapper;
	
	@Autowired
	private KpiClassStatMapper kpiClassStatMapper;
	@Autowired
	private KpiCoachStatMapper kpiCoachStatMapper;
	@Autowired
	private KpiStoreStatMapper kpiStoreStatMapper;
	@Autowired
	private KpiHeadCoachStatMapper kpiHeadCoachStatMapper;
	@Autowired
	private KpiExamStatMapper kpiExamStatMapper;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private ClassinfoService classinfoService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private CoachSettingService coachSettingService;
	
	/**
	 *  case 0 :
            return "不合格";
            case 1 :
            return "合格";
            case 2 :
            return "未到";
            case 3 :
            return "未考";
            case 4 :
            return "取消";
	 */
	
	private String getSubject(int subjectid){
		if(subjectid==1) return "科目一";
		if(subjectid==3) return "科目二";
		if(subjectid==5) return "科目三";
		if(subjectid==7) return "科目四";
		return "未知科目";
	}
	
	@Override
	public List<KpiAreaStat> statByArea(KpiAreaStatParam param) {

		//报名成功的人
		List<KpiAreaStatRecord> stats= kpiAreaStatMapper.statByArea1(param);
		//有考试成绩的人		
		List<KpiAreaStatRecord> stats2= kpiAreaStatMapper.statByArea2(param);
		Map<Integer,KpiAreaStat> areaStatMap=new HashMap();
		
		List<Area> areas= areaService.selectAllList(null);
		for(KpiAreaStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getAreaid())){
				KpiAreaStat kpiAreaStat=new KpiAreaStat();
				areaStatMap.put(stat.getAreaid(), kpiAreaStat);
				for(Area area:areas){
					if(stat.getAreaid()!=null&&stat.getAreaid()==area.getId()){
						kpiAreaStat.setArea(area.getName());
					}
				}
			}
			
			List<KpiAreaStatRecord> data=areaStatMap.get(stat.getAreaid()).getData();
			KpiAreaStatRecord matchKpiAreaStatRecord=null;
			for(KpiAreaStatRecord kpiAreaStatRecord:data){
				if(kpiAreaStatRecord.getSubjectid().equals(stat.getSubjectid())){
					matchKpiAreaStatRecord=kpiAreaStatRecord;
				}
			}
			if(matchKpiAreaStatRecord==null){
				matchKpiAreaStatRecord=new KpiAreaStatRecord();
				matchKpiAreaStatRecord.setSubjectid(stat.getSubjectid());
				matchKpiAreaStatRecord.setSubject(getSubject(stat.getSubjectid()));
				matchKpiAreaStatRecord.setAreaid(stat.getAreaid());
				areaStatMap.get(stat.getAreaid()).getData().add(matchKpiAreaStatRecord);
			}
			//报名成功人数
			matchKpiAreaStatRecord.setCount(stat.getCount());
			
		}
		
		java.util.Iterator<Integer> it= areaStatMap.keySet().iterator();
		while(it.hasNext()){
			Integer areaid=it.next();
			List<KpiAreaStatRecord> data= areaStatMap.get(areaid).getData();
			for(KpiAreaStatRecord record:data){
				//到考人=通过+失败的人
				
				for(KpiAreaStatRecord r: stats2){
					if(record.getAreaid().intValue()==r.getAreaid().intValue()&&record.getSubjectid().intValue()==r.getSubjectid().intValue()){
						
						if(r.getResult()==1){//no及格
							record.setFailedcount(r.getCount());
						}else if(r.getResult()==4){//取消
							record.setCancelcount(r.getCount());
						}else if(r.getResult()==2){//未到
							record.setUnreachcount(r.getCount());
						}else if(r.getResult()==0){//及格
							record.setPasscount(r.getCount());
						}
						record.setReachcount(record.getFailedcount()+record.getPasscount());
						//报名成功的人-到的人-未到的人-取消的人
//						record.setOthercount(r.getCount()-record.getReachcount()-record.getUnreachcount()-record.getCancelcount());
//						//合格人数/实到人数
//						record.setPassrate(record.getReachcount()==0?0:record.getPasscount()/record.getReachcount());
//						//合格人数/（实到人数+未到人数）
//						record.setQualifiedrate(record.getReachcount()==0?0:record.getPasscount()/(record.getReachcount()+record.getUnreachcount()));
//						//未到率=未到人数/（实到人数+未到人数）
//						record.setUnreachrate(record.getUnreachcount()==0?0:record.getUnreachcount()/(record.getReachcount()+record.getUnreachcount()));
//						//取消率=取消人数/（实到人数+未到人数+取消人数）
//						record.setCancelrate(record.getCancelcount()==0?0:record.getCancelcount()/(record.getReachcount()+record.getUnreachcount()+record.getCancelcount()));
						
						
					}
				}
				
				System.out.println("======================================================");
				processRate(record);
			}
		}
		
		List<KpiAreaStat> result=new ArrayList(); 
		result.addAll(areaStatMap.values());
		return result;
	}
	
	DecimalFormat df = new DecimalFormat("0.00");
	
	private void processRate(KpiStatRecord record){
		//报名成功的人-到的人-未到的人-取消的人
		record.setOthercount(record.getCount()-record.getReachcount()-record.getUnreachcount()-record.getCancelcount());
		//合格人数/实到人数
		//record.setPassrate(record.getReachcount()==0?0:record.getPasscount()/record.getReachcount());
		
		float passrate= record.getReachcount()==0?0:((float)record.getPasscount()/record.getReachcount()*100);
		record.setPassrate(df.format(passrate)+"%");
		
		//合格人数/（实到人数+未到人数）
		//record.setQualifiedrate((record.getPasscount()==0||record.getReachcount()+record.getUnreachcount()==0)?0:record.getPasscount()/(record.getReachcount()+record.getUnreachcount()));
		float qualifiedrate=(record.getPasscount()==0||record.getReachcount()+record.getUnreachcount()==0)?0:(float)record.getPasscount()/(record.getReachcount()+record.getUnreachcount())*100;
		record.setQualifiedrate(df.format(qualifiedrate)+"%");
		
		//未到率=未到人数/（实到人数+未到人数）
		//record.setUnreachrate((record.getUnreachcount()==0||(record.getReachcount()+record.getUnreachcount())==0)?0:record.getUnreachcount()/(record.getReachcount()+record.getUnreachcount()));
		float unreachrate=(record.getUnreachcount()==0||(record.getReachcount()+record.getUnreachcount())==0)?0:(float)record.getUnreachcount()/(record.getReachcount()+record.getUnreachcount())*100;
		record.setUnreachrate(df.format(unreachrate)+"%");
		//取消率=取消人数/（实到人数+未到人数+取消人数）
		//record.setCancelrate((record.getCancelcount()==0||(record.getReachcount()+record.getUnreachcount()+record.getCancelcount())==0)?0:record.getCancelcount()/(record.getReachcount()+record.getUnreachcount()+record.getCancelcount()));
		float cancelrate=(record.getCancelcount()==0||(record.getReachcount()+record.getUnreachcount()+record.getCancelcount())==0)?0:(float)record.getCancelcount()/(record.getReachcount()+record.getUnreachcount()+record.getCancelcount())*100;
		record.setCancelrate(df.format(cancelrate)+"%");
	}

	@Override
	public List<KpiClassStat> statByClass(KpiClassStatParam param) {
		List<KpiClassStatRecord> stats= kpiClassStatMapper.statByArea1(param);
		List<KpiClassStatRecord> stats2= kpiClassStatMapper.statByArea2(param);
		Map<Integer,KpiClassStat> areaStatMap=new HashMap();
		
		List<Area> areas= areaService.selectAllList(null);
		
		List<Classinfo> classes= classinfoService.selectAllList(null);
		for(KpiClassStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getClassid())){
				KpiClassStat kpiClassStat=new KpiClassStat();
				areaStatMap.put(stat.getClassid(), kpiClassStat);
				for(Classinfo clz:classes){
					if(stat.getClassid()!=null&&stat.getClassid()==clz.getId()){
						kpiClassStat.setClassname(clz.getName());
					}
				}
				if(kpiClassStat.getClassname()==null)kpiClassStat.setClassname("未知班别");
			}
			
			List<KpiClassAreaStat> data=areaStatMap.get(stat.getClassid()).getData();
			KpiClassAreaStat kpiClassAreaStat=null;
			for(KpiClassAreaStat kpiAreaStatRecord:data){
				if(kpiAreaStatRecord.getAreaid()==stat.getAreaid().intValue()){
					kpiClassAreaStat=kpiAreaStatRecord;
				}
			}
			if(kpiClassAreaStat==null){
				kpiClassAreaStat=new KpiClassAreaStat();
				kpiClassAreaStat.setAreaid(stat.getAreaid());
				for(Area area:areas){
					if(stat.getAreaid()!=null&&stat.getAreaid()==area.getId()){
						kpiClassAreaStat.setArea(area.getName());
					}
				}
				areaStatMap.get(stat.getClassid()).getData().add(kpiClassAreaStat);
			}
			
			List<KpiClassStatRecord> areadata=kpiClassAreaStat.getData();
			KpiClassStatRecord matchkpiClassStatRecord=null;
			for(KpiClassStatRecord kpiClassStatRecord:areadata){
				if(kpiClassStatRecord.getSubjectid().intValue()==stat.getSubjectid().intValue()){
					matchkpiClassStatRecord=kpiClassStatRecord;
					break;
				}
			}
			
			if(matchkpiClassStatRecord==null){
				matchkpiClassStatRecord=new KpiClassStatRecord();
				matchkpiClassStatRecord.setAreaid(stat.getAreaid());
				matchkpiClassStatRecord.setClassid(stat.getClassid());
				matchkpiClassStatRecord.setSubjectid(stat.getSubjectid());
				matchkpiClassStatRecord.setSubject(getSubject(stat.getSubjectid()));
				kpiClassAreaStat.getData().add(matchkpiClassStatRecord);
			}
			
			matchkpiClassStatRecord.setCount(stat.getCount());
			
			
			
		}
		
		java.util.Iterator<Integer> it= areaStatMap.keySet().iterator();
		while(it.hasNext()){
			Integer classid=it.next();
			List<KpiClassAreaStat> data= areaStatMap.get(classid).getData();
			for(KpiClassAreaStat arearecord:data){
				List<KpiClassStatRecord> areadata= arearecord.getData();
				for(KpiClassStatRecord record:areadata){
					
					for(KpiClassStatRecord r: stats2){
						if( record.getClassid().intValue()==r.getClassid().intValue()&&record.getAreaid().intValue()==r.getAreaid().intValue() && record.getSubjectid().intValue()==r.getSubjectid().intValue()){
							
							if(r.getResult()==1){//及格
								record.setFailedcount(r.getCount());
								
							}else if(r.getResult()==4){//取消
								record.setCancelcount(r.getCount());
							}else if(r.getResult()==2){//未到
								record.setUnreachcount(r.getCount());
							}else if(r.getResult()==0){//不及格
								record.setPasscount(r.getCount());
							}
							record.setReachcount(record.getFailedcount()+record.getPasscount());
							//record.setOthercount(r.getCount()-record.getReachcount()-record.getUnreachcount());
							
						}
					}
					processRate(record);
				}
			}
		}
		
		List<KpiClassStat> result=new ArrayList(); 
		result.addAll(areaStatMap.values());
		return result;
	}

	@Override
	public List<KpiCoachStat> statByCoach(KpiCoachStatParam param) {
		List<KpiCoachStatRecord> stats= kpiCoachStatMapper.statByArea1(param);
		List<KpiCoachStatRecord> stats2= kpiCoachStatMapper.statByArea2(param);
		Map<Integer,KpiCoachStat> areaStatMap=new HashMap();
		
		
		List<Area> areas= areaService.selectAllList(null);
		
		
		List<Store> stores= storeService.selectAllList(null);
		
		CoachTeachType ctt=new CoachTeachType();
		
		
		List<CoachTeachType> teachtypes= coachSettingService.listTeachType(ctt);
		for(KpiCoachStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getCoachid())){
				KpiCoachStat kpiCoachStat=new KpiCoachStat();
				areaStatMap.put(stat.getCoachid(), kpiCoachStat);
				kpiCoachStat.setCoachname(stat.getCoachname());
				for(Area area:areas){
					if(stat.getAreaid()!=null&&stat.getAreaid()==area.getId()){
						kpiCoachStat.setArea(area.getName());
					}
				}
				for(CoachTeachType teachtype:teachtypes){
					if(stat.getTeachtypeid()!=null&& teachtype.getId().intValue()==stat.getTeachtypeid().intValue()){
						kpiCoachStat.setTeachtype(teachtype.getType());
					}
				}
				kpiCoachStat.setTeachtypeid(stat.getTeachtypeid());
			}
			
			List<KpiCoachStoreStat> data=areaStatMap.get(stat.getCoachid()).getData();
			KpiCoachStoreStat kpiCoachStoreStat=null;
			for(KpiCoachStoreStat record:data){
				if(record.getStoreid()==stat.getStoreid().intValue()){
					kpiCoachStoreStat=record;
				}
			}
			if(kpiCoachStoreStat==null){
				kpiCoachStoreStat=new KpiCoachStoreStat();
				kpiCoachStoreStat.setStoreid(stat.getStoreid());
				for(Store store:stores){
					if(stat.getStoreid()!=null&&stat.getStoreid()==store.getId()){
						kpiCoachStoreStat.setStore(store.getName());
					}
				}
				areaStatMap.get(stat.getCoachid()).getData().add(kpiCoachStoreStat);
			}
			
			List<KpiCoachStatRecord> areadata=kpiCoachStoreStat.getData();
			KpiCoachStatRecord matchkpiCoachStatRecord=null;
			for(KpiCoachStatRecord kpiCoachStatRecord:areadata){
				if(kpiCoachStatRecord.getSubjectid().intValue()==stat.getSubjectid().intValue()){
					matchkpiCoachStatRecord=kpiCoachStatRecord;
					break;
				}
			}
			
			if(matchkpiCoachStatRecord==null){
				matchkpiCoachStatRecord=new KpiCoachStatRecord();
				matchkpiCoachStatRecord.setSubjectid(stat.getSubjectid());
				matchkpiCoachStatRecord.setSubject(getSubject(stat.getSubjectid()));
				kpiCoachStoreStat.getData().add(matchkpiCoachStatRecord);
				
			}
			
			matchkpiCoachStatRecord.setCount(stat.getCount());
			
			
			
		}
		
		java.util.Iterator<Integer> it= areaStatMap.keySet().iterator();
		while(it.hasNext()){
			Integer coachid=it.next();
			List<KpiCoachStoreStat> data= areaStatMap.get(coachid).getData();
			for(KpiCoachStoreStat arearecord:data){
				List<KpiCoachStatRecord> areadata= arearecord.getData();
				for(KpiCoachStatRecord record:areadata){
					
					for(KpiCoachStatRecord r: stats2){
						System.out.println(arearecord.getStoreid()+" "+r.getStoreid()+" "+record.getSubjectid()+" "+r.getSubjectid());
						if(arearecord.getStoreid().intValue()==r.getStoreid().intValue()&& record.getSubjectid().intValue()==r.getSubjectid().intValue()){
							
							if(r.getResult()==1){//及格
								record.setFailedcount(r.getCount());
								
							}else if(r.getResult()==4){//取消
								record.setCancelcount(r.getCount());
							}else if(r.getResult()==2){//未到
								record.setUnreachcount(r.getCount());
							}else if(r.getResult()==0){//不及格
								record.setPasscount(r.getCount());
							}
							record.setReachcount(record.getFailedcount()+record.getPasscount());
							//record.setOthercount(r.getCount()-record.getReachcount()-record.getUnreachcount());
							
						}
					}
					processRate(record);
				}
			}
		}
		
		List<KpiCoachStat> result=new ArrayList(); 
		result.addAll(areaStatMap.values());
		return result;
	}

	@Override
	public List<KpiStoreStat> statByStore(KpiStoreStatParam param) {
		List<KpiStoreStatRecord> stats= kpiStoreStatMapper.statByArea1(param);
		List<KpiStoreStatRecord> stats2= kpiStoreStatMapper.statByArea2(param);
		Map<Integer,KpiStoreStat> areaStatMap=new HashMap();
		
		
		List<Area> areas= areaService.selectAllList(null);
		
		
		List<Store> stores= storeService.selectAllList(null);
		
		for(KpiStoreStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getStoreid())){
				KpiStoreStat kpiStoreStat=new KpiStoreStat();
				areaStatMap.put(stat.getStoreid(), kpiStoreStat);
				for(Store store:stores){
					if(stat.getStoreid()!=null&&stat.getStoreid()==store.getId()){
						kpiStoreStat.setStore(store.getName());
					}
				}
				for(Area area:areas){
					if(stat.getAreaid()!=null&&stat.getAreaid()==area.getId()){
						kpiStoreStat.setArea(area.getName());
					}
				}
			}
			
			List<KpiStoreStatRecord> data=areaStatMap.get(stat.getStoreid()).getData();
			KpiStoreStatRecord matchKpiStoreStatRecord=null;
			for(KpiStoreStatRecord kpiAreaStatRecord:data){
				if(kpiAreaStatRecord.getSubjectid().equals(stat.getSubjectid())){
					matchKpiStoreStatRecord=kpiAreaStatRecord;
				}
			}
			if(matchKpiStoreStatRecord==null){
				matchKpiStoreStatRecord=new KpiStoreStatRecord();
				matchKpiStoreStatRecord.setSubjectid(stat.getSubjectid());
				matchKpiStoreStatRecord.setSubject(getSubject(stat.getSubjectid()));
				areaStatMap.get(stat.getStoreid()).getData().add(matchKpiStoreStatRecord);
			}
			
			matchKpiStoreStatRecord.setCount(stat.getCount());
			
			
			
		}
		
		java.util.Iterator<Integer> it= areaStatMap.keySet().iterator();
		while(it.hasNext()){
			Integer storeid=it.next();
			List<KpiStoreStatRecord> data= areaStatMap.get(storeid).getData();
			for(KpiStoreStatRecord record:data){
				
				for(KpiStoreStatRecord r: stats2){
					if(record.getAreaid()!=null&&r.getAreaid()!=null&&record.getAreaid().intValue()==r.getAreaid().intValue()&&record.getSubjectid().intValue()==r.getSubjectid().intValue()){
						
						if(r.getResult()==1){//及格
							record.setFailedcount(r.getCount());
							
						}else if(r.getResult()==4){//取消
							record.setCancelcount(r.getCount());
						}else if(r.getResult()==2){//未到
							record.setUnreachcount(r.getCount());
						}else if(r.getResult()==0){//不及格
							record.setPasscount(r.getCount());
						}

						record.setReachcount(record.getFailedcount()+record.getPasscount());
						//record.setOthercount(r.getCount()-record.getReachcount()-record.getUnreachcount());
						
					}
				}
				processRate(record);
			}
		}
		
		List<KpiStoreStat> result=new ArrayList(); 
		result.addAll(areaStatMap.values());
		return result;
	}

	@Override
	public List<KpiHeadCoachStat> statByHeadCoach(KpiHeadCoachStatParam param) {
		List<KpiHeadCoachStatRecord> stats= kpiHeadCoachStatMapper.statByArea1(param);
		List<KpiHeadCoachStatRecord> stats2= kpiHeadCoachStatMapper.statByArea2(param);
		Map<Integer,KpiHeadCoachStat> areaStatMap=new HashMap();
		
		List<Area> areas= areaService.selectAllList(null);
		
		
		for(KpiHeadCoachStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getCoachid())){
				KpiHeadCoachStat kpiHeadCoachStat=new KpiHeadCoachStat();
				areaStatMap.put(stat.getCoachid(), kpiHeadCoachStat);
				
				for(Area area:areas){
					if(stat.getAreaid()!=null&&stat.getAreaid()==area.getId()){
						kpiHeadCoachStat.setArea(area.getName());
					}
				}
				kpiHeadCoachStat.setCoachname(stat.getCoachname());
			}
			
			List<KpiHeadCoachStatRecord> data=areaStatMap.get(stat.getCoachid()).getData();
			KpiHeadCoachStatRecord matchKpiStoreStatRecord=null;
			for(KpiHeadCoachStatRecord kpiAreaStatRecord:data){
				if(kpiAreaStatRecord.getSubjectid().equals(stat.getSubjectid())){
					matchKpiStoreStatRecord=kpiAreaStatRecord;
				}
			}
			if(matchKpiStoreStatRecord==null){
				matchKpiStoreStatRecord=new KpiHeadCoachStatRecord();
				matchKpiStoreStatRecord.setSubjectid(stat.getSubjectid());
				matchKpiStoreStatRecord.setSubject(getSubject(stat.getSubjectid()));
				areaStatMap.get(stat.getCoachid()).getData().add(matchKpiStoreStatRecord);
			}
			
			matchKpiStoreStatRecord.setCount(stat.getCount());
			
			
			
		}
		
		java.util.Iterator<Integer> it= areaStatMap.keySet().iterator();
		while(it.hasNext()){
			Integer coachid=it.next();
			List<KpiHeadCoachStatRecord> data= areaStatMap.get(coachid).getData();
			for(KpiHeadCoachStatRecord record:data){
				
				for(KpiHeadCoachStatRecord r: stats2){
					if(record.getAreaid()!=null&&r.getAreaid()!=null&&record.getAreaid().intValue()==r.getAreaid().intValue()&& record.getSubjectid().intValue()==r.getSubjectid().intValue()){
						if(r.getResult()==1){//及格
							record.setFailedcount(r.getCount());
							
						}else if(r.getResult()==4){//取消
							record.setCancelcount(r.getCount());
						}else if(r.getResult()==2){//未到
							record.setUnreachcount(r.getCount());
						}else if(r.getResult()==0){//不及格
							record.setPasscount(r.getCount());
						}
						record.setReachcount(record.getFailedcount()+record.getPasscount());
						//record.setOthercount(r.getCount()-record.getReachcount()-record.getUnreachcount());
						
					}
				}
				processRate(record);
			}
		}
		
		List<KpiHeadCoachStat> result=new ArrayList(); 
		result.addAll(areaStatMap.values());
		return result;
	}

	@Override
	public List<KpiExamStat> statByExam(KpiExamStatParam param) {
		List<KpiExamStatRecord> stats= kpiExamStatMapper.statByArea1(param);
		List<KpiExamStatRecord> stats2= kpiExamStatMapper.statByArea2(param);
		Map<String,KpiExamStat> areaStatMap=new HashMap();
		
		List<Area> areas= areaService.selectAllList(null);
		
		List<Classinfo> classes= classinfoService.selectAllList(null);
		for(KpiExamStatRecord stat:stats){
			if(!areaStatMap.containsKey(stat.getExam())){
				KpiExamStat kpiExamStat=new KpiExamStat();
				areaStatMap.put(stat.getExam(), kpiExamStat);
				kpiExamStat.setExam(stat.getExam());
				for(Area area:areas){
					if(stat.getAreaid()!=null&&stat.getAreaid()==area.getId()){
						kpiExamStat.setArea(area.getName());
					}
				}
			}
			
			List<KpiExamStatRecord> data=areaStatMap.get(stat.getExam()).getData();
			
			KpiExamStatRecord matchKpiAreaStatRecord=null;
			for(KpiExamStatRecord kpiAreaStatRecord:data){
				if(kpiAreaStatRecord.getSubjectid().equals(stat.getSubjectid())){
					matchKpiAreaStatRecord=kpiAreaStatRecord;
				}
			}
			if(matchKpiAreaStatRecord==null){
				matchKpiAreaStatRecord=new KpiExamStatRecord();
				matchKpiAreaStatRecord.setSubjectid(stat.getSubjectid());
				matchKpiAreaStatRecord.setExam(stat.getExam());
				matchKpiAreaStatRecord.setSubject(getSubject(stat.getSubjectid()));
				areaStatMap.get(stat.getExam()).getData().add(matchKpiAreaStatRecord);
			}
			
			matchKpiAreaStatRecord.setCount(stat.getCount());
			
			
			
		}
		
		java.util.Iterator<String> it= areaStatMap.keySet().iterator();
		while(it.hasNext()){
			String exam=it.next();
			List<KpiExamStatRecord> data= areaStatMap.get(exam).getData();
			for(KpiExamStatRecord record:data){
				
				for(KpiExamStatRecord r: stats2){
					if(record.getExam().equals(r.getExam())&&record.getSubjectid().intValue()==r.getSubjectid().intValue()){
						//record.setOthercount(r.getCount()-record.getReachcount()-record.getUnreachcount());
						
						if(r.getResult()==1){//及格
							record.setFailedcount(r.getCount());
							
						}else if(r.getResult()==4){//取消
							record.setCancelcount(r.getCount());
						}else if(r.getResult()==2){//未到
							record.setUnreachcount(r.getCount());
						}else if(r.getResult()==0){//不及格
							record.setPasscount(r.getCount());
						}
						record.setReachcount(record.getFailedcount()+record.getPasscount());
					}
				}
				processRate(record);
			}
		}
		
		List<KpiExamStat> result=new ArrayList(); 
		result.addAll(areaStatMap.values());
		return result;
	}

	
}
