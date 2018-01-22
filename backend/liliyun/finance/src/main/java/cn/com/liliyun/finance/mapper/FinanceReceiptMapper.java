package cn.com.liliyun.finance.mapper;

import java.util.List;
import java.util.Map;

import cn.com.liliyun.finance.model.FinanceIncome;
import cn.com.liliyun.finance.model.FinanceInvoiceDTO;
import cn.com.liliyun.finance.model.FinanceReceipt;

public interface FinanceReceiptMapper {
    int deleteByPrimaryKey(FinanceReceipt financeReceipt);

    int insert(FinanceReceipt financeReceipt);

    int insertSelective(FinanceReceipt financeReceipt);

    FinanceReceipt selectByPrimaryKey(FinanceReceipt financeReceipt);

    int updateByPrimaryKeySelective(FinanceReceipt financeReceipt);

    int updateByPrimaryKey(FinanceReceipt financeReceipt);

    List<FinanceReceipt> selectList(FinanceReceipt financeReceipt);

    List<FinanceInvoiceDTO> selectInvoiceExport(FinanceReceipt financeReceipt);

    String selectMaxBatchnum(FinanceReceipt financeReceipt);

    int updateBatchnum(FinanceReceipt financeReceipt);

    int updateByReceiptnumBatch(Map<String, Object> map);

    int updateConfirmByIds(FinanceReceipt financeReceipt);
    
    List <FinanceIncome> selectIncome(FinanceIncome financeIncome);
}