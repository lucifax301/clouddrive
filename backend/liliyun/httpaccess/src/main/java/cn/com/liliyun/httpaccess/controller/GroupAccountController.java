package cn.com.liliyun.httpaccess.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import cn.com.liliyun.coach.model.Coach;
import cn.com.liliyun.common.model.ResultBean;
import cn.com.liliyun.common.util.HttpClientUtil;
import cn.com.liliyun.common.util.StringUtil;
import cn.com.liliyun.common.util.pay.alipay.util.AlipaySubmit;
import cn.com.liliyun.common.util.pay.wxpay.PayCommonUtil;
import cn.com.liliyun.common.util.pay.wxpay.PayConfigUtil;
import cn.com.liliyun.common.util.pay.wxpay.Weixin_pay;
import cn.com.liliyun.common.util.pay.wxpay.XMLUtil;
import cn.com.liliyun.school.model.AccountBalanceDTO;
import cn.com.liliyun.school.model.CoachAccount;
import cn.com.liliyun.school.model.Recharge;
import cn.com.liliyun.school.service.CoachAccountService;
import cn.com.liliyun.user.model.User;
import cn.com.liliyun.user.service.UserService;

@Controller
@ResponseBody
public class GroupAccountController extends BaseController {
	Logger logger = Logger.getLogger(GroupAccountController.class);
	@Autowired
	private CoachAccountService coachAccountService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/groupAccount/list")
	public ResultBean getList(CoachAccount coachAccount) {
		return coachAccountService.getList(coachAccount);
	}
	
	@RequestMapping(value="/groupAccount/notAccountCoachList")
	public ResultBean getNotAccountCoachList(Coach coach) {
		return coachAccountService.selectNotAccountCoach(coach);
	}
	
	@RequestMapping(value="/groupAccount/add")
	public ResultBean addAccount(HttpServletRequest request,CoachAccount coachAccount) {
		String[] str = coachAccount.getCoachids().split(",");
		int array[] = new int[str.length];  
		for(int i=0;i<str.length;i++){  
		    array[i]=Integer.parseInt(str[i]);
		}
		logger.info("==array[]==="+array.length);
		List<CoachAccount> list = new ArrayList<>();
		for (int i : array) {
			CoachAccount ca = new CoachAccount();
			ca.setCoachid(i);
			ca.setIsLimit(coachAccount.getIsLimit());
			ca.setLimitQuantity(coachAccount.getLimitQuantity());
			ca.setStatus(coachAccount.getStatus());
			list.add(ca);
		}
		User user = getUser(request);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("list",list);
		map.put("dblink", user.getDblink());
		map.put("mgrdb", user.getMgrdb());
		return coachAccountService.addCoachAccount(map);
	}
	
	@RequestMapping(value="/groupAccount/update")
	public ResultBean updateAccount(CoachAccount coachAccount) {
		return coachAccountService.updateCoachAccount(coachAccount);
	}
	
	@RequestMapping(value="/groupAccount/rechargeList") ///groupAccount/rechargeList
	public ResultBean getRechargeList(Recharge recharge) {
		return coachAccountService.selectAllRecord(recharge);
	}
	
	//测试微信支付二维码
    @RequestMapping(value = "/groupAccount/getqrcode_wx")  
    public ResultBean geteqcode(HttpServletRequest request,HttpServletResponse response,Recharge recharge ) throws IOException { 
    	ResultBean rb = new ResultBean();
    	String ip = request.getRemoteAddr();
    	PayConfigUtil.spbill_create_ip = ip;
    	Weixin_pay wp = new Weixin_pay();
    	logger.info("======二维码参数=====："+recharge.getAttach());
    	String qrcode = wp.getUrl(recharge.getMessage(),recharge.getAttach(),recharge.getAmount(),recharge.getNotifyUrl());
    	rb.setResult(qrcode);
    	return rb;
    }
    
    //测试微信支付完成回调
    @RequestMapping(value = "/open/notify_wx")    
    public void notify(HttpServletRequest request,HttpServletResponse response) throws IOException { 
    	ResultBean rb = new ResultBean();
    	//读取参数  
        InputStream inputStream ;  
        StringBuffer sb = new StringBuffer();  
        inputStream = request.getInputStream();  
        String s ;  
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
        while ((s = in.readLine()) != null){  
            sb.append(s);  
        }  
        in.close();  
        inputStream.close();  
        //解析xml成map  
        Map<String, String> m = new HashMap<String, String>();  
        m = XMLUtil.doXMLParse(sb.toString());  
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
		            Date date;
					try {
						date = sdf.parse(time_end);
						 sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			             recharge.setRtime(sdf.format(date)); 
					} catch (ParseException e) {
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
						ResultBean resultBean = getAccountBalance(ab);
						String balance = new BigDecimal((String)resultBean.getResult()).divide(new BigDecimal(100)).setScale(2).toString();//分转换元
			    		if(Double.valueOf(balance)>1){
			    			balance = myformat.format(Double.valueOf(balance));
			    		}
			    		recharge.setBalance(balance);
					} catch (Exception e) {
						e.printStackTrace();
					}
		            coachAccountService.addRecharge(recharge);
                } 
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
                  
            } else {  
                logger.info("支付失败,错误信息：" + packageParams.get("err_code"));  
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
            }  
            //处理业务完毕  
            BufferedOutputStream out = new BufferedOutputStream(  
            response.getOutputStream());  
            out.write(resXml.getBytes());  
            out.flush();  
            out.close();  
        } else{  
            logger.info("通知签名验证失败");  
        } 
    	
    }
    
    
    //测试支付宝支付二维码
    @RequestMapping(value = "/groupAccount/getqrcode_alipay")  
    public ResultBean geteqcode_alipay(HttpServletRequest request,HttpServletResponse response,Recharge recharge ) throws IOException { 
    	ResultBean rb = new ResultBean();
    	AlipaySubmit as = new AlipaySubmit();
    	logger.info("======二维码参数=====："+recharge.getAttach());
    	String parametes = as.getUrl(recharge.getMessage(),recharge.getAttach(),recharge.getAmount(),recharge.getNotifyUrl());
    	rb.setResult(parametes);
    	return rb;
    }
    
    //支付宝支付完成回调
    @RequestMapping(value = "/open/notify_alipay")    
    public void notify_alipay(HttpServletRequest request,HttpServletResponse response){
    	ResultBean rb = new ResultBean();
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
				ResultBean resultBean = getAccountBalance(ab);
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
    		
            rb = coachAccountService.addRecharge(recharge);
            try {
            	PrintWriter  pw = response.getWriter();
            	pw.write("success");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    
    //查询app驾校集团账号余额
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/groupAccount/getAccountBalance") 
    public ResultBean getAccountBalance(AccountBalanceDTO accountBalanceDTO){
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
}
