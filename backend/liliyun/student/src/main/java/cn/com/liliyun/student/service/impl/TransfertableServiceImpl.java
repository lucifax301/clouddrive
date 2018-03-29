package cn.com.liliyun.student.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.ApplyExam;
import cn.com.liliyun.common.util.ConstantUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.student.mapper.StudentMapper;
import cn.com.liliyun.student.mapper.StudentStatusLogMapper;
import cn.com.liliyun.student.mapper.TransfertableItemMapper;
import cn.com.liliyun.student.mapper.TransfertableMapper;
import cn.com.liliyun.student.model.Student;
import cn.com.liliyun.student.model.StudentStatusLog;
import cn.com.liliyun.student.model.Transfertable;
import cn.com.liliyun.student.model.TransfertableItem;
import cn.com.liliyun.student.service.TransfertableService;
import cn.com.liliyun.user.model.User;

@Service
public class TransfertableServiceImpl implements TransfertableService {

	Logger logger = Logger.getLogger(TransfertableServiceImpl.class);

	@Autowired
	private TransfertableMapper transfertableMapper;

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private TransfertableItemMapper transfertableItemMapper;
	
	@Autowired
	private StudentStatusLogMapper studentStatusLogMapper;

	@Override
	public List<Transfertable> list(Transfertable transfertable) {
		PageUtil.startPage(transfertable);
		return transfertableMapper.selectList(transfertable);
	}

	@Override
	public List<TransfertableItem> listItem(TransfertableItem transfertableItem) {
		return transfertableItemMapper.selectList(transfertableItem);
	}

	/**
	 * 门店交表
	 * @param user
	 * @param list
	 * @return
	 */
	@Override
	public ResultBean doStoreTransfer(List<TransfertableItem> list) {
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		String tableId = user.getBatchId();
		Student query = new Student();
		
		Iterator <TransfertableItem> iterator = list.iterator();
		List <Student> photobackUpdateList = new ArrayList<>();
		List <StudentStatusLog> logList = new ArrayList<>();
		while (iterator.hasNext()) {
			TransfertableItem item = iterator.next();
			query.setIdcard(item.getIdcard());
			Student result = studentMapper.selectOne(query);
			if (result != null) {
				item.setTableid(tableId);
				item.setStudentid(result.getId());
				item.setName(result.getName());
				item.setStatus(0);
				
				//照片回执的学员
				Student update = new Student();
				update.setId(result.getId());
				update.setPhotoback(item.getPhotoback());
				photobackUpdateList.add(update);
				
				//办证状态
				StudentStatusLog studentStatusLog = new StudentStatusLog();
				studentStatusLog.setStudentid(result.getId());
				studentStatusLog.setIdcard(result.getIdcard());
				studentStatusLog.setTableid(tableId);
				studentStatusLog.setSubject(ApplyExam.SIGNUP_MATERIAL_STORE.getSubject());
				studentStatusLog.setSubjectname(ApplyExam.SIGNUP_MATERIAL_STORE.getName());
				addList(logList,studentStatusLog);
			} else {
				iterator.remove();
			}
		}

		if (list.size() > 0) {
			Map<String, Object> params = new HashMap<>();
			
			params.put("list", list);
			int count = transfertableItemMapper.insertBatch(params);
			//更新照片回执
			params.put("list", photobackUpdateList);
			studentMapper.updateBatch(params);
			//跟新进度
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
			//新增业务数据
			Transfertable transfertable = new Transfertable();
			
			transfertable.setTableid(tableId);
			transfertable.setItemcount(count);
			transfertable.setCorrectnum(count);
			transfertable.setReturnnum(0);
			transfertable.setTransferareaid(user.getAreaid());
			transfertable.setTransferstoreid(user.getStoreid());
			transfertable.setTransfername(user.getRealname());
			transfertable.setStatus(0); // 门店交表
			transfertableMapper.insertSelective(transfertable);
		}
		return new ResultBean();
	}

	/**
	 * 片区人员所能看到的学员
	 * @param user
	 * @param list
	 * @return
	 */
	@Override
	public List<TransfertableItem> listAreaTransferItem(List<Transfertable> list) {
		List<TransfertableItem> dataList = new ArrayList<>();
		for (Transfertable t : list) {
			TransfertableItem item = new TransfertableItem();
			
			item.setStatus(0); // 只查询正常的学员
			item.setTableid(t.getTableid());
			List<TransfertableItem> itemList = transfertableItemMapper.selectList(item);
			dataList.addAll(itemList);
		}
		return dataList;
	}
	
	/**
	 * 牌证人员所能看到的学员
	 * @param user
	 * @param list
	 * @return
	 */
	@Override
	public List<TransfertableItem> listLicenseTransferItem(List<Transfertable> list) {
		List<TransfertableItem> dataList = new ArrayList<>();
		for (Transfertable t : list) {
			TransfertableItem item = new TransfertableItem();
			
			item.setStatus(0); // 只查询正常的学员
			item.setAreatableid(t.getTableid()); //只看片区提交上来的数据
			List<TransfertableItem> itemList = transfertableItemMapper.selectList(item);
			dataList.addAll(itemList);
		}
		return dataList;
	}

	@Override
	public ResultBean doAreaTranfer(List<Transfertable> list) {
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		String tableId = user.getBatchId();
		TransfertableItem item = new TransfertableItem();
		
		Map<String, Object> params = new HashMap<>();
		
		int count = 0;
		List <StudentStatusLog> logList = new ArrayList<>();
		for (Transfertable t : list) {
			item.setTableid(t.getTableid());
			item.setStatus(0); // 只查询正常的学员
			List<TransfertableItem> itemList = transfertableItemMapper.selectList(item);
			if (itemList != null && itemList.size() > 0) {
				for (TransfertableItem ti : itemList) {
					ti.setAreatableid(tableId); //记录新批次号
					//办证状态
					StudentStatusLog studentStatusLog = new StudentStatusLog();
					studentStatusLog.setStudentid(ti.getStudentid());
					studentStatusLog.setIdcard(ti.getIdcard());
					studentStatusLog.setTableid(tableId);
					studentStatusLog.setSubject(ApplyExam.SIGNUP_MATERIAL_AREA.getSubject());
					studentStatusLog.setSubjectname(ApplyExam.SIGNUP_MATERIAL_AREA.getName());
					addList(logList,studentStatusLog);
				}
				params.put("list", itemList);
				int i = transfertableItemMapper.updateTableIdBatch(params);
				count += i;
			}
		}
		if (count > 0) {
			//跟新进度
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
			
			Transfertable transfertable = new Transfertable(); //生成新的批次信息
			
			transfertable.setTableid(tableId);
			transfertable.setItemcount(count);
			transfertable.setCorrectnum(count);
			transfertable.setReturnnum(0);
			transfertable.setTransferareaid(user.getAreaid());
			transfertable.setTransferstoreid(user.getStoreid());
			transfertable.setTransfername(user.getRealname());
			transfertable.setStatus(2); // 片区交表
			transfertableMapper.insertSelective(transfertable);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean doAreaReturn(List<TransfertableItem> list, String rtnreason) {
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		Transfertable transfertable = new Transfertable();
		
		Map<String, Object> params = new HashMap<>();
		
		TransfertableItem transfertableItem = new TransfertableItem();
		
		List <StudentStatusLog> logList = new ArrayList<>();
		for (TransfertableItem item : list) {
			transfertableItem.setTableid(item.getTableid());
			transfertableItem.setIdcard(item.getIdcard());
			transfertableItem.setRtnreason(rtnreason);
			transfertableItem.setStatus(1);
			transfertableItemMapper.updateStoreReturn(transfertableItem); //更新退表明细
			
			transfertable.setTableid(item.getTableid());
			transfertableMapper.updateStoreReturnCount(transfertable);

			StudentStatusLog studentStatusLog = new StudentStatusLog();
			studentStatusLog.setStudentid(item.getStudentid());
			studentStatusLog.setIdcard(item.getIdcard());
			studentStatusLog.setTableid(item.getTableid());
			studentStatusLog.setSubject(ApplyExam.SIGNUP_MATERIAL_RETURN.getSubject());
			studentStatusLog.setSubjectname(ApplyExam.SIGNUP_MATERIAL_RETURN.getName());
			addList(logList,studentStatusLog);
		}
		
		if (logList.size() > 0) {
			//跟新进度
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean doLicenseReturn(List<TransfertableItem> list, String rtnreason) {
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		Transfertable transfertable = new Transfertable();
		
		TransfertableItem transfertableItem = new TransfertableItem();
		
		List <StudentStatusLog> logList = new ArrayList<>();
		for (TransfertableItem item : list) {
			transfertableItem.setAreatableid(item.getAreatableid());
			transfertableItem.setIdcard(item.getIdcard());
			transfertableItem.setRtnreason(rtnreason);
			transfertableItem.setStatus(1);
			transfertableItemMapper.updateAreaReturn(transfertableItem); //更新退表明细
			
			transfertable.setTableid(item.getTableid());
			transfertableMapper.updateStoreReturnCount(transfertable);
			transfertable.setTableid(item.getAreatableid());
			transfertableMapper.updateAreaReturnCount(transfertable);

			StudentStatusLog studentStatusLog = new StudentStatusLog();
			studentStatusLog.setStudentid(item.getStudentid());
			studentStatusLog.setIdcard(item.getIdcard());
			studentStatusLog.setTableid(item.getTableid());
			studentStatusLog.setSubject(ApplyExam.SIGNUP_MATERIAL_RETURN.getSubject());
			studentStatusLog.setSubjectname(ApplyExam.SIGNUP_MATERIAL_RETURN.getName());
			addList(logList,studentStatusLog);
		}
		if (logList.size() > 0) {
			//跟新进度
			Map <String, Object> params = new HashMap<>();
			
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}

	@Override
	public ResultBean doLicenseReceive(List <Transfertable> list) {
		
		Map<String, Object> params = new HashMap<>();
		
		TransfertableItem item = new TransfertableItem();
		
		List <StudentStatusLog> logList = new ArrayList<>();
		for (Transfertable t : list) {
			item.setTableid(t.getTableid());
			item.setStatus(0); // 只查询正常的学员
			List <TransfertableItem> itemList = transfertableItemMapper.selectList(item);
			if (itemList != null && itemList.size() > 0) {
				for (TransfertableItem ti : itemList) {
					//办证状态
					StudentStatusLog studentStatusLog = new StudentStatusLog();
					studentStatusLog.setStudentid(item.getStudentid());
					studentStatusLog.setIdcard(item.getIdcard());
					studentStatusLog.setTableid(item.getTableid());
					studentStatusLog.setSubject(ApplyExam.SIGNUP_MATERIAL_SHCOOL.getSubject());
					studentStatusLog.setSubjectname(ApplyExam.SIGNUP_MATERIAL_SHCOOL.getName());
					addList(logList,studentStatusLog);
				}
			}
			Transfertable update = new Transfertable();
			
			update.setTableid(t.getTableid());
			update.setStatus(4);
			transfertableMapper.updateByPrimaryKeySelective(update);
		}
		if (logList.size() > 0) {
			//跟新进度
			params.put("list", logList);
			studentStatusLogMapper.insertBatch(params);
		}
		return new ResultBean();
	}

	private void addList(List <StudentStatusLog> list,StudentStatusLog studentStatusLog) {
		User user = RequestContext.getValue(ConstantUtil.USER_SESSION);
		studentStatusLog.setCuid(user.getId());
		studentStatusLog.setCname(user.getRealname());
		studentStatusLog.setCtime(new Date());
		studentStatusLog.setStoreid(user.getStoreid());
		studentStatusLog.setAreaid(user.getAreaid());
		list.add(studentStatusLog);
	}

}
