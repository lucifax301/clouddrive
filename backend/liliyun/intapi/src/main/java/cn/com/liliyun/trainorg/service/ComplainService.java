package cn.com.liliyun.trainorg.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.trainorg.model.Complain;
import cn.com.liliyun.trainorg.model.ComplainReturn;

/**
 * 客服管理-投诉管理
 * @author lilixc
 *
 */
public interface ComplainService {
	
	void addComplain(Complain complain);
	
	void updateComplain(Complain complain);
	
	PageInfo<Complain> getComplainList(Complain complain);
	
	void deleteById(Complain complain);
	
	Complain getComplainById(Complain complain);
	
	//int getCount(Complain complain);

	public void importComplain(Complain complain, List<Complain> list);
	
	PageInfo<ComplainReturn> getReturnList(ComplainReturn complainReturn);
	void addComplainReturn(ComplainReturn complainReturn);
}
