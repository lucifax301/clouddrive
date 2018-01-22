package cn.com.liliyun.school.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.HttpClientUtil;
import cn.com.liliyun.common.util.PageUtil;
import cn.com.liliyun.common.util.StringUtil;
import cn.com.liliyun.common.util.pay.wxpay.PayCommonUtil;
import cn.com.liliyun.common.util.pay.wxpay.PayConfigUtil;
import cn.com.liliyun.common.util.pay.wxpay.XMLUtil;
import cn.com.liliyun.school.mapper.CoachAccountMapper;
import cn.com.liliyun.school.model.AccountBalanceDTO;
import cn.com.liliyun.school.model.CoachAccount;
import cn.com.liliyun.school.model.Recharge;
import cn.com.liliyun.school.service.CoachAccountService;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;

@Service
public class CoachAccountServiceImpl implements CoachAccountService{
	Logger logger = Logger.getLogger(CoachAccountServiceImpl.class);
	@Autowired
	private CoachAccountMapper coachAccountMapper;
	
	@Autowired
	private UserService userService;
	
	@Override
	public ResultBean addCoachAccount(Map map) {
		ResultBean r= new ResultBean();
		try {
			coachAccountMapper.insertAccount(map);
			r.setCode(0);
			r.setMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(0);
			r.setMsg("操作成功！");
		}
		return r;
	}

	@Override
	public ResultBean deleteCoachAccount(CoachAccount coachAccount) {
		return null;
	}

	@Override
	public ResultBean updateCoachAccount(CoachAccount coachAccount) {
		ResultBean r= new ResultBean();
		try {
			coachAccountMapper.updateAccount(coachAccount);
			r.setCode(0);
			r.setMsg("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			r.setCode(1);
			r.setMsg("操作失败！");
		}
		return r;
	}

	@Override
	public ResultBean getList(CoachAccount coachAccount) {
		ResultBean resultBean = new ResultBean();
		PageUtil.startPage(coachAccount);
		List<CoachAccount> list = coachAccountMapper.selectAllAccount(coachAccount);
		PageInfo<CoachAccount> pagedResult = new PageInfo<>(list);
		resultBean.setResult(pagedResult);
		resultBean.setCode(0);
		return resultBean;
	}

	@Override
	public ResultBean getCoachAccountByid(CoachAccount coachAccount) {
		return null;
	}

	@Override
	public ResultBean addRecharge(Recharge recharge) {
		ResultBean resultBean = new ResultBean();
		coachAccountMapper.insertRecharge(recharge);
		return resultBean; 
	}

	@Override
	public ResultBean selectAllRecord(Recharge recharge) {
		ResultBean resultBean = new ResultBean();
		PageUtil.startPage(recharge);
		List<Recharge> list = coachAccountMapper.selectAllRecord(recharge);
		resultBean.setResult(new PageInfo<>(list));
		resultBean.setCode(0);
		return resultBean;
		
	}

	@Override
	public ResultBean selectNotAccountCoach(Coach coach) {
		ResultBean resultBean = new ResultBean();
		PageUtil.startPage(coach);
		List<Coach> list = coachAccountMapper.selectNotAccountCoach(coach);
		PageInfo<Coach> pagedResult = new PageInfo<>(list);
		resultBean.setResult(pagedResult);
		resultBean.setCode(0);
		return resultBean;
	}

	@Override
	public String wxNotify(Map<String, String> m){
        //过滤空 设置 TreeMap  
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();        
        Iterator it = m.keySet().iterator();  
        while (it.hasNext()) {  
            String parameter = (String) it.next();  
            String parameterValue = m.get(parameter);  
            String v = "";  
            if(null != parameterValue) {  
                v = parameterValue.trim();  
            }  
            packageParams.put(parameter, v);  
        }  
        // 账号信息  
        String key = PayConfigUtil.API_KEY; // key  
        logger.info("=========微信回调1：=========="+packageParams);  
        //判断签名是否正确  
        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams,key)) {  
            //处理业务开始  
            String resXml = "";  
            // return_code 通信成功与否   result_code 支付是否成功
            if("SUCCESS".equals((String)packageParams.get("return_code"))){  
                //////////执行自己的业务逻辑////////////////  
                String mch_id = (String)packageParams.get("mch_id");  
                String out_trade_no = (String)packageParams.get("out_trade_no");  
                String total_fee = (String)packageParams.get("total_fee");  
                logger.info("=========微信回调2：=========="+"mch_id:"+mch_id+",out_trade_no:"+out_trade_no+",out_trade_no:"+out_trade_no);  
                //////////执行自己的业务逻辑////////////////  
                String attach = (String) packageParams.get("attach"); //充值的驾校
                if(!StringUtil.isNull(attach)){
            		String str = attach.trim();
            		String username = str.substring(0, str.indexOf("&"));
		            User user = new User();
		            user.setUsername(username);
		            User u = userService.getUser(user);
		            Recharge recharge = new Recharge();
		            recharge.setDblink(u.getDblink());
		            recharge.setSerialNumber((String)packageParams.get("transaction_id"));
		            DecimalFormat myformat = new DecimalFormat();
		    		myformat.applyPattern("##,###.00");
		    		String money = new BigDecimal(total_fee).divide(new BigDecimal(100)).setScale(2).toString();//分转换元
		    		if(Double.valueOf(money)>1){
		    			money = myformat.format(Double.valueOf(money));
		    		}
		    		recharge.setAmount(money);
		            String time_end =  (String)packageParams.get("time_end");
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		            try {
		            	 Date date = sdf.parse(time_end);
						 sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			             recharge.setRtime(sdf.format(date)); 
					} catch (Exception e) {
						e.printStackTrace();
					}
		           
					recharge.setRtype("1");//
					if("SUCCESS".equals((String)packageParams.get("result_code") ) ){
						recharge.setRstatus("1"); //成功
					}else {
						recharge.setRstatus("2"); //失败
					}
					String message = str.substring( str.indexOf("&")+1); //充值信息
					recharge.setMessage(message);
					//调用app接口查询余额
					try {
						AccountBalanceDTO ab = new AccountBalanceDTO();
						ab.setUserId(String.valueOf(u.getSchoolid()));
						ab.setUserType("5");
						ab.setPayWay("1"); //微信支付
						ab.setRemark(message);
						ab.setOrderId((String)packageParams.get("transaction_id"));
						ab.setPrice(total_fee);//分
						ResultBean resultBean = getBalance(ab);
						String balance = new BigDecimal((String)resultBean.getResult()).divide(new BigDecimal(100)).setScale(2).toString();//分转换元
			    		if(Double.valueOf(balance)>1){
			    			balance = myformat.format(Double.valueOf(balance));
			    		}
			    		recharge.setBalance(balance);
					} catch (Exception e) {
						e.printStackTrace();
					}
		            addRecharge(recharge);
                } 
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
                  
            } else {  
                logger.info("支付失败,错误信息：" + packageParams.get("err_code"));  
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
            }
		
            return resXml;
        }else{
        	logger.info("通知签名验证失败"); 
        }
        	return null;
	}
	


	@SuppressWarnings("unchecked")
	@Override
	public ResultBean getBalance(AccountBalanceDTO accountBalanceDTO) {
		ResultBean rb = new ResultBean();
    	Map map = new HashMap<>();
    	map.put("userId", accountBalanceDTO.getUserId());
    	map.put("userType", accountBalanceDTO.getUserType());
    	map.put("payWay", accountBalanceDTO.getPayWay());
    	map.put("remark", accountBalanceDTO.getRemark());
    	map.put("orderId", accountBalanceDTO.getOrderId());
    	map.put("timestamp", accountBalanceDTO.getTimestamp());
    	map.put("price", accountBalanceDTO.getPrice());
    	String secret = "6b1019d9561c548037b37023d7a0451b";
    	String sign = getSignature(map,secret);
		map.put("sign", sign);
		HttpClientUtil hc = new HttpClientUtil();
		try {
			String str = hc.httpPostRequest("http://192.168.1.94:9026/httpaccess/v1/yun/recharge", map);
			logger.info("========app余额接口返回：========：："+str);
			Gson gson = new Gson();
			AccountBalanceDTO a = gson.fromJson(str, AccountBalanceDTO.class);
			rb.setCode(0);
			rb.setResult(a.getData());
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return rb;
	}
	
	protected String getSignature(Map<String, String> params, String secret) {
		// 先将参数以其参数名的字典序升序进行排序
		Map<String,String> sortedParams = new TreeMap<String,String>(params);
		Set<Entry<String, String>> entrys = sortedParams.entrySet();
		// 遍历排序后的字典，将所有参数按"key=value"格式拼接在一起
	    StringBuilder basestring = new StringBuilder();
	    for (Entry<String, String> param : entrys) {
	        basestring.append(param.getKey()).append("=").append(param.getValue());
	    }
	    // 添加secret值到末尾
	    basestring.append(secret);
	    // 使用MD5对待签名串求签
	    byte[] bytes = null;
	    try {
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        bytes = md5.digest(basestring.toString().getBytes("UTF-8"));
	    } catch (Exception ex) {
	    }
	    // 将MD5输出的二进制结果转换为小写的十六进制
	    StringBuilder sign = new StringBuilder();
	    for (int i = 0; i < bytes.length; i++) {
	        String hex = Integer.toHexString(bytes[i] & 0xFF);
	        if (hex.length() == 1) {
	            sign.append("0");
	        }
	        sign.append(hex);
	    }
	    return sign.toString();
	}

	@Override
	public void alipayNotify(HttpServletRequest request) {
		String body = request.getParameter("body");
    	if(!StringUtil.isNull(body)){
    		logger.info("=========支付宝回调：=========="+body);
    		String str = body.trim();
    		String username = str.substring(0, str.indexOf("|"));
            User user = new User();
            user.setUsername(username);
            User u = userService.getUser(user);
            Recharge recharge = new Recharge();
            recharge.setDblink(u.getDblink());
            recharge.setSerialNumber(request.getParameter("trade_no")); // 支付宝交易号
            recharge.setRtype("2");
            recharge.setRtime(request.getParameter("gmt_payment"));//付款时间
            recharge.setMessage( str.substring( str.indexOf("|")+1));
            if("TRADE_SUCCESS".equals(request.getParameter("trade_status"))){
            	recharge.setRstatus("1");
            }
            DecimalFormat myformat = new DecimalFormat();
    		myformat.applyPattern("##,###.00");
    		String money = request.getParameter("price");
    		if(Double.valueOf(money)>1 || Double.valueOf(money)==1){
    			recharge.setAmount(myformat.format(Double.valueOf(money)));
    		}else{
    			recharge.setAmount(money);
    		}
    		
    		//调用app接口查询余额
			try {
				AccountBalanceDTO ab = new AccountBalanceDTO();
				ab.setUserId(String.valueOf(u.getSchoolid()));
				ab.setUserType("5");
				ab.setPayWay("2"); //支付宝支付
				ab.setRemark( str.substring( str.indexOf("|")+1));
				ab.setOrderId(request.getParameter("trade_no"));
				ab.setPrice( String.valueOf(Integer.valueOf((int) (Double.valueOf(recharge.getAmount())*100))) );//元转分
				ResultBean resultBean = getBalance(ab);
				String balance = (String)resultBean.getResult();
				balance = new BigDecimal(balance).divide(new BigDecimal(100)).setScale(2).toString();//分转换元
				logger.info("========app查询余额：========：："+balance);
	    		if(Double.valueOf(balance)>1 ){
	    			balance = myformat.format(Double.valueOf(balance));
	    		}
	    			recharge.setBalance(balance);
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
			addRecharge(recharge);
    	}
	}
}