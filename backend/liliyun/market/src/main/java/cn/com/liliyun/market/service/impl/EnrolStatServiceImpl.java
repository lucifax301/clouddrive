package cn.com.liliyun.market.service.impl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.market.mapper.EnrolStatMapper;
import cn.com.liliyun.market.model.AreaEnrolIndex;
import cn.com.liliyun.market.model.EnrolChannelStat;
import cn.com.liliyun.market.model.EnrolChannelStatItem;
import cn.com.liliyun.market.model.EnrolClassStat;
import cn.com.liliyun.market.model.EnrolClassStatItem;
import cn.com.liliyun.market.model.EnrolDetailParam;
import cn.com.liliyun.market.model.SalesChannel;
import cn.com.liliyun.market.service.EnrolIndexService;
import cn.com.liliyun.market.service.EnrolStatService;
import cn.com.liliyun.market.service.SalesChannelService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.model.Classinfo;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.trainorg.service.ClassinfoService;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.User;

@Service
public class EnrolStatServiceImpl implements EnrolStatService {

	@Autowired
	private EnrolStatMapper enrolStatMapper;
	@Autowired
	private ClassinfoService classinfoService;
	@Autowired
	private EnrolIndexService enrolIndexService;
	@Autowired
	private StoreService storeService;
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private SalesChannelService salesService;
	
	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
	
	DecimalFormat df = new DecimalFormat("0.00");
	
	@Override
	public List<EnrolClassStat> statByClass(EnrolDetailParam param) {
		List<EnrolClassStatItem> list= enrolStatMapper.statByClass(param);
		List<EnrolClassStatItem> returnlist= enrolStatMapper.getClassReturnCount(param);
		
		List<Classinfo> clss= classinfoService.selectAllList(null);
		
		List<Area> areas= areaService.selectAllList(null);
		List<EnrolClassStat> result=new ArrayList();
		
		Map<Integer,EnrolClassStat> map=new HashMap();
		
		for(EnrolClassStatItem item:list){
			if(!map.containsKey(item.getAreaid())){
				EnrolClassStat detail=new EnrolClassStat();
				detail.setAreaid(item.getAreaid());
				map.put(item.getAreaid(), detail);
				for(Area area:areas){
					if(item.getAreaid()!=null&&item.getAreaid().intValue()==area.getId().intValue()){
						detail.setArea(area.getName());
					}
				}
				if(detail.getArea()==null) detail.setArea("其他");
			}
			if(item.getClassinfoid()!=null){//班别为空，应该是异常数据
				
				map.get(item.getAreaid()).getClassinfo().put(item.getClassinfoid(), item.getCount());
				map.get(item.getAreaid()).setEnrolsum(map.get(item.getAreaid()).getEnrolsum()+item.getCount());
				
				if(item.getTraintype().toLowerCase().equals("c1")){
					map.get(item.getAreaid()).setC1(map.get(item.getAreaid()).getC1()+item.getCount());
				}else if(item.getTraintype().toLowerCase().equals("c2")){
					map.get(item.getAreaid()).setC2(map.get(item.getAreaid()).getC2()+item.getCount());
				}
			}else{
				
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
			EnrolClassStat detail=map.get(areaid);
			
			
			//计算退费人数，实际报名人数
			for(EnrolClassStatItem r: returnlist){
				if(r.getAreaid().intValue()==detail.getAreaid().intValue()){
					detail.setReturnsum(r.getCount());
					detail.setRealenrolsum(detail.getEnrolsum()-r.getCount());
				}
			}
			
			if(matchindex!=null){
				detail.setEnrolindex(matchindex.getEnrolindex());
				float enrolrate=(float)detail.getRealenrolsum()/matchindex.getEnrolindex()*100;
				detail.setEnrolrate(df.format(enrolrate)+"%");
				detail.setHighindex(matchindex.getHighrate()*matchindex.getEnrolindex());
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
			detail.setHighcount(highcount);
			int avgprice=(detailclass.keySet().size()==0)?0: sumprice/detailclass.keySet().size();
			detail.setAvgprice(avgprice);
			detail.setOutavgprice(outcount==0?0:outsumprice/outcount);
			detail.setHighrate((detailclass.keySet().size()==0?0:highcount/detailclass.keySet().size())+"%");
			detail.setHighindexrate((detail.getHighindex()==0?0:detail.getHighcount()/(detail.getEnrolsum()*detail.getHighindex()))+"%");
			float c2rate= (float)detail.getC2()/(detail.getC1()+detail.getC2())*100;
			detail.setC2rate(df.format(c2rate)+"%");
		}
		
		result.addAll(map.values());
		
		if(param.getShowzero()==null||param.getShowzero()==0){//不显示0
			java.util.Iterator<Integer> zsit= zeroclass.keySet().iterator();
			while(zsit.hasNext()){
				Integer ds=zsit.next();
				if(zeroclass.get(ds)==0){
					for(EnrolClassStat stat:result){
						
						Map<Integer,Integer> detailclass= stat.getClassinfo();
						detailclass.remove(ds);
					}
				}
			}
		}
		
		
		return result;
	}

	
	@Override
	public List<EnrolChannelStat> statByChannel(EnrolDetailParam param) {
		List<EnrolChannelStatItem> list= enrolStatMapper.statByChannel(param);
		List<EnrolChannelStatItem> returnlist= enrolStatMapper.getChannelReturnCount(param);
		
		//List<Classinfo> clss= classinfoService.selectAllList(new Classinfo());
		SalesChannel ps=new SalesChannel();
		
		
		List<SalesChannel> clss=salesService.selectChannels(ps);
		Area pa=new Area();
		
		List<Area> areas= areaService.selectAllList(pa);
		List<EnrolChannelStat> result=new ArrayList();
		
		Map<Integer,EnrolChannelStat> map=new HashMap();
		
		for(EnrolChannelStatItem item:list){
			if(!map.containsKey(item.getAreaid())){
				EnrolChannelStat detail=new EnrolChannelStat();
				detail.setAreaid(item.getAreaid());
				map.put(item.getAreaid(), detail);
				for(Area area:areas){
					if(item.getAreaid()!=null&&item.getAreaid().intValue()==area.getId().intValue()){
						detail.setArea(area.getName());
					}
				}
				if(detail.getArea()==null) detail.setArea("其他");
			}
			if(item.getChannelid()!=null){//班别为空，应该是异常数据
				
				map.get(item.getAreaid()).getChannelinfo().put(item.getChannelid(), item.getCount());
				map.get(item.getAreaid()).setEnrolsum(map.get(item.getAreaid()).getEnrolsum()+item.getCount());
			}
			
		}
		
		Date pdate=param.getBegindate();
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
			EnrolChannelStat detail=map.get(areaid);
			
			
			//计算退费人数，实际报名人数
			for(EnrolChannelStatItem r: returnlist){
				if(r.getAreaid().intValue()==detail.getAreaid().intValue()){
					detail.setReturnsum(r.getCount());
					detail.setRealenrolsum(detail.getEnrolsum()-r.getCount());
				}
			}
			
			if(matchindex!=null){
				detail.setEnrolindex(matchindex.getEnrolindex());
				float enrolrate=(float)detail.getRealenrolsum()/matchindex.getEnrolindex()*100;
				detail.setEnrolrate(df.format(enrolrate)+"%");
				
			}else{
				detail.setEnrolindex(0);
				detail.setEnrolrate(0+"%");
			}
			
			for(SalesChannel cls:clss){
				if(!detail.getChannelinfo().containsKey(cls.getId())){
					detail.getChannelinfo().put(cls.getId(), 0);
				}
			}
			
			param.setBegindate(pdate);
			List<EnrolClassStat> stats=statByClass(param);
			for(EnrolClassStat s:stats){
				
				if(s.getAreaid()!=null&&s.getAreaid().intValue()==detail.getAreaid().intValue()){
					detail.setHighrate(s.getHighrate());
				}
			}
			
			
		}
		
		result.addAll(map.values());
		
		return result;
	}
}
