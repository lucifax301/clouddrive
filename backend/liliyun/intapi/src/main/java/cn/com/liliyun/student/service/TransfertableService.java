package cn.com.liliyun.student.service;


import java.util.List;

import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.student.model.Transfertable;
import cn.com.liliyun.student.model.TransfertableItem;

public interface TransfertableService {

	List<Transfertable> list(Transfertable transfertable);
	
	List<TransfertableItem> listItem(TransfertableItem transfertableItem);
	
	List<TransfertableItem> listAreaTransferItem(List <Transfertable> list);
	
	List<TransfertableItem> listLicenseTransferItem(List <Transfertable> list);
	
	ResultBean doAreaTranfer(List <Transfertable> list);
	
	ResultBean doStoreTransfer(List <TransfertableItem> list);
	
	ResultBean doAreaReturn(List <TransfertableItem> list,String rtnreason);
	
	ResultBean doLicenseReturn(List <TransfertableItem> list,String rtnreason);
	
	ResultBean doLicenseReceive(List <Transfertable> list);
}
