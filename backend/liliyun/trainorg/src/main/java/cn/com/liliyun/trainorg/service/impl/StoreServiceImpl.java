package cn.com.liliyun.trainorg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.coach.service.CoachService;
import cn.com.liliyun.common.dto.MapDTO;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.trainorg.mapper.StoreMapper;
import cn.com.liliyun.trainorg.model.Store;
import cn.com.liliyun.trainorg.service.StoreService;
import cn.com.liliyun.user.model.User;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	private StoreMapper storeMapper;
	
	@Autowired
	private CoachService coachService;

	@Override
	public ResultBean insert(Store store, User user) {
		store.setDblink(user.getDblink());
		storeMapper.insertSelective(store);
		return new ResultBean();
	}

	@Override
	public ResultBean selectByPrimaryKey(Store store, User user) {
		store.setDblink(user.getDblink());
		storeMapper.selectByPrimaryKey(store);
		return null;
	}

	@Override
	public ResultBean updateByPrimaryKey(Store store, User user) {
		store.setDblink(user.getDblink());
		storeMapper.updateByPrimaryKeySelective(store);
		return new ResultBean();
	}

	@Override
	public ResultBean deleteById(Store store, User user) {
		String [] ids = store.getIds().split(",");
		for (String id : ids) {
			store.setId(Integer.parseInt(id));
			store.setDblink(user.getDblink());
			storeMapper.deleteByPrimaryKey(store);
		}
		return new ResultBean();
	}

	@Override
	public List <Store> selectList(Store store, User user) {
		store.setDblink(user.getDblink());
		PageUtil.startPage(store);
		List <Store> list = storeMapper.selectList(store);
//		if (store.getPageNo() != null && store.getPageNo() != -1) {
//			List<Integer> storeids = new ArrayList<>();
//			for (Store s : list) {
//				storeids.add(s.getId());
//			}
//			if (storeids.size() > 0) {
//				Map<Integer, Integer> storeCoachMap = coachService.getStoreCoachNumBatch(storeids, user);
//				for (Store s : list) {
//					Integer coachnum = storeCoachMap.get(s.getId());
//					if (coachnum == null)
//						coachnum = 0;
//					s.setCoachnum(coachnum);
//				}
//			}
//		}
		return list;
	}
	
	@Override
	public List <Store> selectList(Store store, User user, Boolean isStorePage) {
		store.setDblink(user.getDblink());
		PageUtil.startPage(store);
		List <Store> list = storeMapper.selectList(store);
		if (isStorePage) {
			List<Integer> storeids = new ArrayList<>();
			for (Store s : list) {
				storeids.add(s.getId());
			}
			if (storeids.size() > 0) {
				Map<Integer, Integer> storeCoachMap = coachService.getStoreCoachNumBatch(storeids, user);
				for (Store s : list) {
					Integer coachnum = storeCoachMap.get(s.getId());
					if (coachnum == null)
						coachnum = 0;
					s.setCoachnum(coachnum);
				}
			} 
		}
		return list;
	}
	
	@Override
	public List<Store> selectAllList(Store store, User user) {
		store.setDblink(user.getDblink());
		return  storeMapper.selectList(store);
	}

	@Override
	public Store selectOne(Store store, User user) {
		store.setDblink(user.getDblink());
		return storeMapper.selectByPrimaryKey(store);
	}

	@Override
	public int getCount(Store store, User user) {
		return storeMapper.getCount(store);
	}

	@Override
	public ResultBean updateByPrimaryKeySelective(Store store, User user) {
		storeMapper.updateByPrimaryKeySelective(store);
		return new ResultBean();
	}

	@Override
	public Map<Integer, MapDTO> getMap(Store store) {
		return storeMapper.getMap(store);
	}

}
