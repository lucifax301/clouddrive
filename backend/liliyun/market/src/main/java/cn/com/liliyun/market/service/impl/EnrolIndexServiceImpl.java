package cn.com.liliyun.market.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.log.model.LogCommon;
import cn.com.liliyun.market.mapper.CSEnrolIndexMapper;
import cn.com.liliyun.market.mapper.CoachEnrolIndexMapper;
import cn.com.liliyun.market.mapper.EnrolIndexMapper;
import cn.com.liliyun.market.model.AreaEnrolIndex;
import cn.com.liliyun.market.model.CSEnrolIndex;
import cn.com.liliyun.market.model.CoachEnrolIndex;
import cn.com.liliyun.market.model.StoreEnrolIndex;
import cn.com.liliyun.market.service.EnrolIndexService;
import cn.com.liliyun.trainorg.model.Area;
import cn.com.liliyun.trainorg.service.AreaService;
import cn.com.liliyun.user.model.User;

@Service
public class EnrolIndexServiceImpl implements EnrolIndexService {

	@Autowired
	private EnrolIndexMapper enrolIndexMapper;
	
	@Autowired
	private CSEnrolIndexMapper csenrolIndexMapper;
	
	@Autowired
	private CoachEnrolIndexMapper coachenrolIndexMapper;
	
	@Autowired
	private AreaService areaService;
	
	@Override
	public ResultBean addAreaEnrollIndex(AreaEnrolIndex index, LogCommon log,
			User user) {
		ResultBean rb = new ResultBean();
		
		AreaEnrolIndex areaEnrolIndex= enrolIndexMapper.getAreaEnrolIndexByDate(index);
		
		if(areaEnrolIndex!=null){
			rb.setMsg("片区指标已经存在");
			rb.setCode(1);
			return rb;
		}
		
		enrolIndexMapper.addAreaEnrolIndex(index);
		if(index.getData()!=null&&index.getData().size()>0){
			Map params=new HashMap();
			params.put("list", index.getData());
			params.put("dblink", user.getDblink());
			enrolIndexMapper.batchAddStoreEnrolIndex(params);
		}
		
		return rb;
	}

	@Override
	public ResultBean updateAreaEnrollIndex(AreaEnrolIndex index,
			LogCommon log, User user) {
		ResultBean rb = new ResultBean();
		
		
		AreaEnrolIndex areaEnrolIndex= enrolIndexMapper.getAreaEnrolIndex(index);
		StoreEnrolIndex storeEnrolIndex=new StoreEnrolIndex();
		storeEnrolIndex.setAreaid(areaEnrolIndex.getAreaid());
		storeEnrolIndex.setYear(areaEnrolIndex.getYear());
		storeEnrolIndex.setMonth(areaEnrolIndex.getMonth());
		storeEnrolIndex.setDblink(user.getDblink());
		List<StoreEnrolIndex> news=index.getData();
		List<StoreEnrolIndex> storeEnrolIndexs= enrolIndexMapper.listStoreEnrolIndex(storeEnrolIndex);
		
		List<StoreEnrolIndex> updates=new ArrayList();
		List<StoreEnrolIndex> adds=new ArrayList();
		for(StoreEnrolIndex n:news){
			boolean exist=false;
			for(StoreEnrolIndex s:storeEnrolIndexs){
				if(n.getStoreid().intValue()==s.getStoreid().intValue()){
					if(n.getHighrate().intValue()!=s.getHighrate().intValue()||n.getEnrolindex().intValue()!=s.getEnrolindex().intValue())
						n.setId(s.getId());
						updates.add(n);
					exist=true;
				}
			}
			
			if(!exist){
				adds.add(n);
			}
		}
		
		if(adds.size()>0){
			Map params=new HashMap();
			params.put("list", adds);
			params.put("dblink", user.getDblink());
			enrolIndexMapper.batchAddStoreEnrolIndex(params);
		}
		if(updates.size()>0){
			for(StoreEnrolIndex s:updates){
				s.setDblink(user.getDblink());
				enrolIndexMapper.updateStoreEnrolIndex(s);
			}
		}
		enrolIndexMapper.updateAreaEnrolIndex(index);
		return rb;
	}

	@Override
	public ResultBean delAreaEnrollIndex(AreaEnrolIndex index, LogCommon log,
			User user) {
		ResultBean rb = new ResultBean();
		AreaEnrolIndex areaEnrolIndex= enrolIndexMapper.getAreaEnrolIndex(index);
		enrolIndexMapper.delAreaEnrolIndex(index);
		
		StoreEnrolIndex i=new StoreEnrolIndex();
		i.setAreaid(areaEnrolIndex.getAreaid());
		i.setDblink(user.getDblink());
		enrolIndexMapper.delStoreEnrolIndex(i);
		return rb;
	}

	@Override
	public AreaEnrolIndex getAreaEnrollIndex(AreaEnrolIndex index) {
		AreaEnrolIndex areaEnrolIndex= enrolIndexMapper.getAreaEnrolIndex(index);
		StoreEnrolIndex storeEnrolIndex=new StoreEnrolIndex();
		storeEnrolIndex.setAreaid(areaEnrolIndex.getAreaid());
		storeEnrolIndex.setYear(areaEnrolIndex.getYear());
		storeEnrolIndex.setMonth(areaEnrolIndex.getMonth());
		storeEnrolIndex.setDblink(index.getDblink());
		List<StoreEnrolIndex> storeEnrolIndexs= enrolIndexMapper.listStoreEnrolIndex(storeEnrolIndex);
		areaEnrolIndex.setData(storeEnrolIndexs);
		return areaEnrolIndex;
	}

	@Override
	public List<AreaEnrolIndex> listAreaEnrollIndex(AreaEnrolIndex index) {
		PageUtil.startPage(index);
		return enrolIndexMapper.listAreaEnrolIndex(index);
	}
	
	@Override
	public List<AreaEnrolIndex> listAllAreaEnrollIndex(AreaEnrolIndex index) {
		
		List<AreaEnrolIndex> list= enrolIndexMapper.listAreaEnrolIndex(index);
		Area pa=new Area();
		pa.setDblink(index.getDblink());
		List<Area> areas= areaService.selectAllList(pa);
		for(AreaEnrolIndex i:list){
			for(Area area:areas){
				if(i.getAreaid().intValue()==area.getId()){
					i.setArea(area.getName());
				}
			}
			if(i.getArea()==null)i.setArea("其他");
			i.setHighratestr(i.getHighrate()+"%");
			i.setDatestr(i.getYear()+"年"+i.getMonth()+"月");
		}
		return list;
	}

	@Override
	public ResultBean editCSEnrolIndex(CSEnrolIndex index) {
		Date now=new Date();
		java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy-MM");
		String time[]= format.format(now).split("-");
		String year=time[0];
		String month=time[1];
		index.setYear(Integer.parseInt(year));
		CSEnrolIndex param=new CSEnrolIndex();
		param.setDblink(index.getDblink());
		param.setYear(Integer.parseInt(year));
		param.setMonth(Integer.parseInt(month));
		CSEnrolIndex exist= csenrolIndexMapper.getCSEnrolIndex(param);
		if(exist!=null){
			exist.setEnrolindex(index.getEnrolindex());
			exist.setDblink(index.getDblink());
			csenrolIndexMapper.updateCSEnrolIndex(exist);
		}else{
			index.setYear(Integer.parseInt(year));
			index.setMonth(Integer.parseInt(month));
			csenrolIndexMapper.addCSEnrolIndex(index);
		}
		ResultBean rb=new ResultBean();
		
		return rb;
	}

	@Override
	public CSEnrolIndex getLastCSEnrolIndex(User user){
		Date now=new Date();
		java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy");
		String year= format.format(now);
		CSEnrolIndex index=new CSEnrolIndex();
		index.setYear(Integer.parseInt(year));
		index.setDblink(user.getDblink());
		List<CSEnrolIndex> cslist= csenrolIndexMapper.listCSEnrolIndex(index);
		CSEnrolIndex last=null;
		if(cslist!=null&&cslist.size()>0){
			int maxmonth=0;
			
			for(CSEnrolIndex i:cslist){
				if(i.getMonth()>maxmonth){
					maxmonth=i.getMonth();
					last=i;
				}
			}
			
		}
		
		return last;
	}
	
	@Override
	public ResultBean getCSEnrolIndex(CSEnrolIndex index) {
		Date now=new Date();
		java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy");
		String year= format.format(now);
		index.setYear(Integer.parseInt(year));
		List<CSEnrolIndex> cslist= csenrolIndexMapper.listCSEnrolIndex(index);
		CSEnrolIndex last=null;
		if(cslist!=null&&cslist.size()>0){
			int maxmonth=0;
			
			for(CSEnrolIndex i:cslist){
				if(i.getMonth()>maxmonth){
					maxmonth=i.getMonth();
					last=i;
				}
			}
			
		}
		ResultBean rb=new ResultBean();
		
		if(last==null){
			last=new CSEnrolIndex();
			last.setEnrolindex(1);
			last.setYear(Integer.parseInt(year));
			rb.setResult(last);
		}else{
			CSEnrolIndex one=new CSEnrolIndex();
			one.setId(last.getId());
			one.setEnrolindex(last.getEnrolindex());
			one.setYear(last.getYear());
			one.setMonth(last.getMonth());
			one.setData(cslist);
			rb.setResult(one);
		}
		
		
		return rb;
	}

	@Override
	public ResultBean editCoachEnrolIndex(CoachEnrolIndex index) {
		Date now=new Date();
		java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy-MM");
		String time[]= format.format(now).split("-");
		String year=time[0];
		String month=time[1];
		index.setYear(Integer.parseInt(year));
		CoachEnrolIndex param=new CoachEnrolIndex();
		param.setYear(Integer.parseInt(year));
		param.setMonth(Integer.parseInt(month));
		param.setDblink(index.getDblink());
		CoachEnrolIndex exist= coachenrolIndexMapper.getCoachEnrolIndex(param);
		if(exist!=null){
			exist.setEnrolindex(index.getEnrolindex());
			exist.setDblink(index.getDblink());
			coachenrolIndexMapper.updateCoachEnrolIndex(exist);
		}else{
			index.setYear(Integer.parseInt(year));
			index.setMonth(Integer.parseInt(month));
			coachenrolIndexMapper.addCoachEnrolIndex(index);
		}
		ResultBean rb=new ResultBean();
		
		return rb;
	}

	@Override
	public CoachEnrolIndex getLastCoachEnrolIndex(User user) {
		Date now=new Date();
		java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy");
		String year= format.format(now);
		CoachEnrolIndex index=new CoachEnrolIndex();
		index.setYear(Integer.parseInt(year));
		index.setDblink(user.getDblink());
		List<CoachEnrolIndex> cslist= coachenrolIndexMapper.listCoachEnrolIndex(index);
		CoachEnrolIndex last=null;
		if(cslist!=null&&cslist.size()>0){
			int maxmonth=0;
			
			for(CoachEnrolIndex i:cslist){
				if(i.getMonth()>maxmonth){
					maxmonth=i.getMonth();
					last=i;
				}
			}
			
		}
		
		return last;
	}

	@Override
	public ResultBean getCoachEnrolIndex(CoachEnrolIndex index) {
		Date now=new Date();
		java.text.SimpleDateFormat format=new java.text.SimpleDateFormat("yyyy");
		String year= format.format(now);
		index.setYear(Integer.parseInt(year));
		List<CoachEnrolIndex> cslist= coachenrolIndexMapper.listCoachEnrolIndex(index);
		CoachEnrolIndex last=null;
		if(cslist!=null&&cslist.size()>0){
			int maxmonth=0;
			
			for(CoachEnrolIndex i:cslist){
				if(i.getMonth()>maxmonth){
					maxmonth=i.getMonth();
					last=i;
				}
			}
			
		}
		ResultBean rb=new ResultBean();
		
		if(last==null){
			last=new CoachEnrolIndex();
			last.setEnrolindex(1);
			last.setYear(Integer.parseInt(year));
			rb.setResult(last);
		}else{
			CoachEnrolIndex one=new CoachEnrolIndex();
			one.setId(last.getId());
			one.setEnrolindex(last.getEnrolindex());
			one.setYear(last.getYear());
			one.setMonth(last.getMonth());
			one.setData(cslist);
			rb.setResult(one);
		}
		
		
		return rb;
	}

	
	
}
