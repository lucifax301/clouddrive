package cn.com.liliyun.market.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.market.mapper.EnrolDetailMapper;
import cn.com.liliyun.market.model.AreaEnrolIndex;
import cn.com.liliyun.market.model.EnrolClassStat;
import cn.com.liliyun.market.model.EnrolDetail;
import cn.com.liliyun.market.model.EnrolDetailItem;
import cn.com.liliyun.market.model.EnrolDetailParam;
import cn.com.liliyun.market.service.EnrolDetailService;
import cn.com.liliyun.market.service.EnrolIndexService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.User;

@Service
public class EnrolDetailServiceImpl implements EnrolDetailService {
	
	@Autowired
	private EnrolDetailMapper enrolDetailMapper;
	@Autowired
	private ClassinfoService classinfoService;
	@Autowired
	private EnrolIndexService enrolIndexService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private AreaService areaService;
	
	DecimalFormat df = new DecimalFormat("0.00");
	
	@Override
	public List<EnrolDetail> stat(EnrolDetailParam param) {
		if (param.getEnddate() != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(param.getEnddate());
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			calendar.add(Calendar.SECOND, -1);
			param.setEnddate(calendar.getTime());
		}
		if(param.getType()==1){
			return statByArea(param);
		}else{
			return statByStore(param);
		}
		
	}

	java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy-MM");
	
	private List<EnrolDetail> statByArea(EnrolDetailParam param){
		
		List<EnrolDetailItem> list= enrolDetailMapper.statByArea(param);
		List<EnrolDetailItem> returnlist= enrolDetailMapper.getAreaReturnCount(param);
		
		
		List<Classinfo> clss= classinfoService.selectAllList(null);
		
		List<Area> areas= areaService.selectAllList(null);
		List<EnrolDetail> result=new ArrayList();
		
		Map<Integer,EnrolDetail> map=new HashMap();
		
		for(EnrolDetailItem item:list){
			if(!map.containsKey(item.getAreaid())){
				EnrolDetail detail=new EnrolDetail();
				detail.setAreaid(item.getAreaid());
				map.put(item.getAreaid(), detail);
				for(Area area:areas){
					if(item.getAreaid()!=null&&item.getAreaid().intValue()==area.getId().intValue()){
						detail.setArea(area.getName());
					}
				}
			}
			if(item.getClassinfoid()!=null){
			map.get(item.getAreaid()).getClassinfo().put(item.getClassinfoid(), item.getCount());
			map.get(item.getAreaid()).setEnrolsum(map.get(item.getAreaid()).getEnrolsum()+item.getCount());
			}
		}
		
		Map<Integer,Integer> zeroclass=new HashMap();
		java.util.Iterator<Integer> it= map.keySet().iterator();
		if(param.getBegindate()==null)param.setBegindate(new Date());
		String yearmonth[]= format.format(param.getBegindate()).split("-");
		int year=Integer.parseInt(yearmonth[0]);
		int month=Integer.parseInt(yearmonth[1]);
		while(it.hasNext()){
			Integer areaid=it.next();
			AreaEnrolIndex areaindex=new AreaEnrolIndex();
			
			areaindex.setAreaid(areaid);
			List<AreaEnrolIndex> indexs=enrolIndexService.listAreaEnrollIndex(areaindex);
			AreaEnrolIndex matchindex=null;
			for(AreaEnrolIndex index:indexs){
				if(index.getYear()==year&&index.getMonth()==month){
					matchindex=index;break;
				}
			}
			
			//招生指标
			EnrolDetail detail=map.get(areaid);
			
			
			//计算退费人数，实际报名人数
			for(EnrolDetailItem r: returnlist){
				if(r.getAreaid().intValue()==detail.getAreaid().intValue()){
					detail.setReturnsum(r.getCount());
				}
			}
			
			detail.setRealenrolsum(detail.getEnrolsum()-detail.getReturnsum());
			
			
			if(matchindex!=null){
				detail.setEnrolindex(matchindex.getEnrolindex());
				float enrolrate=(float)detail.getRealenrolsum()/matchindex.getEnrolindex()*100;
				detail.setEnrolrate(df.format(enrolrate)+"%");
			}else{
				detail.setEnrolindex(0);
				detail.setEnrolrate(0+"%");
			}
			
			for(Classinfo cls:clss){
				if(!detail.getClassinfo().containsKey(cls.getId())){
					detail.getClassinfo().put(cls.getId(), 0);
				}
			}
			
			/*
			 * 计算班别均价，外地班均价，高端班占比
			 */
			Map<Integer,Integer> detailclass= detail.getClassinfo();
			java.util.Iterator<Integer> dsit= detailclass.keySet().iterator();
			int sumprice=0;
			int outsumprice=0;
			int outcount=0;
			int highcount=0;
			while(dsit.hasNext()){
				Integer ds=dsit.next();
				
				Integer pcount= detailclass.get(ds);
				
				if(!zeroclass.containsKey(ds)){
					zeroclass.put(ds, pcount);
				}else{
					if(zeroclass.get(ds)==0){//之前记录班级人数为0, 更新班级记录人数
						zeroclass.put(ds, pcount);
					}
				}
				
				for(Classinfo cls:clss){
					if(ds!=null&&ds.intValue()==cls.getId()){
						sumprice=cls.getAmount();
						if(cls.getLocalflag()==1){//外地班
							outcount++;
							outsumprice+=cls.getAmount();
						}
						if(cls.getHighflag()==1){//高端班
							highcount++;
						}
					}
				}
			}
			
			int avgprice=(detailclass.keySet().size()==0)?0: sumprice/detailclass.keySet().size();
			detail.setAvgprice(avgprice);
			detail.setOutavgprice(outcount==0?0:outsumprice/outcount);
			detail.setHighrate((detailclass.keySet().size()==0?0:highcount/detailclass.keySet().size())+"%");
		}
		
		result.addAll(map.values());
		
		if(param.getShowzero()==null||param.getShowzero()==0){//不显示0
			java.util.Iterator<Integer> zsit= zeroclass.keySet().iterator();
			while(zsit.hasNext()){
				Integer ds=zsit.next();
				if(zeroclass.get(ds)==0){
					for(EnrolDetail stat:result){
						
						Map<Integer,Integer> detailclass= stat.getClassinfo();
						detailclass.remove(ds);
					}
				}
			}
		}
		
		return result;
	}
	
	
	private List<EnrolDetail> statByStore(EnrolDetailParam param){
		List<EnrolDetailItem> list= enrolDetailMapper.statByStore(param);
		List<EnrolDetailItem> returnlist= enrolDetailMapper.getStoreReturnCount(param);
		
		
		List<Classinfo> clss= classinfoService.selectAllList(null);
		
		List<Store> stores= storeService.selectAllList(null);
		List<EnrolDetail> result=new ArrayList();
		
		Map<Integer,EnrolDetail> map=new HashMap();
		
		for(EnrolDetailItem item:list){
			if(item.getStoreid()!=null){
				if(!map.containsKey(item.getStoreid())){
					EnrolDetail detail=new EnrolDetail();
					map.put(item.getStoreid(), detail);
					detail.setStoreid(item.getStoreid());
					for(Store store:stores){
						if(detail.getStoreid()!=null&&detail.getStoreid().intValue()==store.getId()){
							detail.setStore(store.getName());
						}
					}
					if(detail.getStore()==null)detail.setStore("其他");
				}
				if(item.getClassinfoid()!=null){
				map.get(item.getStoreid()).getClassinfo().put(item.getClassinfoid(), item.getCount());
				map.get(item.getStoreid()).setEnrolsum(map.get(item.getStoreid()).getEnrolsum()+item.getCount());
				}
			}
		}
		
		java.util.Iterator<Integer> it= map.keySet().iterator();
		if(param.getBegindate()==null)param.setBegindate(new Date());
		String yearmonth[]= format.format(param.getBegindate()).split("-");
		int year=Integer.parseInt(yearmonth[0]);
		int month=Integer.parseInt(yearmonth[1]);
		while(it.hasNext()){
			Integer storeid=it.next();
			AreaEnrolIndex areaindex=new AreaEnrolIndex();
			Store storeparam=new Store();
			storeparam.setId(storeid);
			
			Store store=storeService.selectOne(storeparam);
			if(store==null) continue;
			areaindex.setAreaid(store.getAreaid());
			
			List<AreaEnrolIndex> indexs=enrolIndexService.listAreaEnrollIndex(areaindex);
			AreaEnrolIndex matchindex=null;
			for(AreaEnrolIndex index:indexs){
				if(index.getYear()==year&&index.getMonth()==month){
					matchindex=index;break;
				}
			}
			
			//招生指标
			EnrolDetail detail=map.get(storeid);
			
			
			//计算退费人数，实际报名人数
			for(EnrolDetailItem r: returnlist){
				if(r.getAreaid().intValue()==detail.getAreaid().intValue()){
					detail.setReturnsum(r.getCount());
					
				}
			}
			
			detail.setRealenrolsum(detail.getEnrolsum()-detail.getReturnsum());
			
			if(matchindex!=null){
				detail.setEnrolindex(matchindex.getEnrolindex());
				float enrolrate=(float)detail.getRealenrolsum()/matchindex.getEnrolindex()*100;
				detail.setEnrolrate(df.format(enrolrate)+"%");
			}else{
				detail.setEnrolindex(0);
				detail.setEnrolrate(0+"%");
			}
			
			for(Classinfo cls:clss){
				if(!detail.getClassinfo().containsKey(cls.getId())){
					detail.getClassinfo().put(cls.getId(), 0);
				}
			}
			
			/*
			 * 计算班别均价，外地班均价，高端班占比
			 */
			Map<Integer,Integer> detailclass= detail.getClassinfo();
			java.util.Iterator<Integer> dsit= detailclass.keySet().iterator();
			int sumprice=0;
			int outsumprice=0;
			int outcount=0;
			int highcount=0;
			while(dsit.hasNext()){
				Integer ds=dsit.next();
				for(Classinfo cls:clss){
					if(ds!=null&&ds.intValue()==cls.getId()){
						sumprice=cls.getAmount();
						if(cls.getLocalflag()==1){//外地班
							outcount++;
							outsumprice+=cls.getAmount();
						}
						if(cls.getHighflag()==1){//高端班
							highcount++;
						}
					}
				}
			}
			int avgprice=(detailclass.keySet().size()==0)?0: sumprice/detailclass.keySet().size();
			detail.setAvgprice(avgprice);
			detail.setOutavgprice(outcount==0?0:outsumprice/outcount);
			detail.setHighrate((detailclass.keySet().size()==0?0:highcount/detailclass.keySet().size())+"%");
		}
		
		result.addAll(map.values());
		return result;
	}
}
