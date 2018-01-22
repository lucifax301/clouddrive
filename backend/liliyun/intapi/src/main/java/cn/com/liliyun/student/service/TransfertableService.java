package cn.com.liliyun.student.service;


import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.Transfertable;
import cn.com.liliyun.student.model.TransfertableItem;
import cn.com.liliyun.user.model.User;

public interface TransfertableService {

	List<Transfertable> list(User user,Transfertable transfertable);
	
	List<TransfertableItem> listItem(User user,TransfertableItem transfertableItem);
	
	List<TransfertableItem> listAreaTransferItem(User user,List <Transfertable> list);
	
	List<TransfertableItem> listLicenseTransferItem(User user,List <Transfertable> list);
	
	ResultBean doAreaTranfer(User user,List <Transfertable> list);
	
	ResultBean doStoreTransfer(User user,List <TransfertableItem> list);
	
	ResultBean doAreaReturn(User user,List <TransfertableItem> list,String rtnreason);
	
	ResultBean doLicenseReturn(User user,List <TransfertableItem> list,String rtnreason);
	
	ResultBean doLicenseReceive(User user,List <Transfertable> list);
}
