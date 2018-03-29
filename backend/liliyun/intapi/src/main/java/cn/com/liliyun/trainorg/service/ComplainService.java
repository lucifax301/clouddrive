package cn.com.liliyun.trainorg.service;

import java.util.List;

import cn.com.liliyun.trainorg.model.Complain;
import cn.com.liliyun.trainorg.model.ComplainReturn;

import com.github.pagehelper.PageInfo;

/**
 * 客服管理-投诉管理
 * @author lilixc
 *
 */
public interface ComplainService {
	
	public void addComplain(Complain complain);
	
	public void updateComplain(Complain complain);
	
	public PageInfo<Complain> getComplainList(Complain complain);
	
	void deleteById(Complain complain);
	
	public Complain getComplainById(Complain complain);
	
	//int getCount(Complain complain);

	public void importComplain(Complain complain, List<Complain> list);
	
	public PageInfo<ComplainReturn> getReturnList(ComplainReturn complainReturn);
	
	public void addComplainReturn(ComplainReturn complainReturn);
}
