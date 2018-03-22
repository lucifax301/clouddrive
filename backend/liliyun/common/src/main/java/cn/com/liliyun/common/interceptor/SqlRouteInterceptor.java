package cn.com.liliyun.common.interceptor;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import cn.com.liliyun.common.model.BaseModel;
import cn.com.liliyun.common.model.RequestContext;
import cn.com.liliyun.common.util.ConstantUtil;


/**
 * sql路由拦截器
 * @author lilixc
 *
 */
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class}))
public class SqlRouteInterceptor implements Interceptor{
	
	public static final String SQLKEY = "delegate.boundSql.sql";
	public static final String PREFIX = "/*!mycat: schema = %s */ %s";
	
	@SuppressWarnings("rawtypes")
	@Override
	public Object intercept(final Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget(); 
		Object paramObject = statementHandler.getParameterHandler().getParameterObject();
		String schema = null;
		boolean mgrdb = false;
//		if (paramObject instanceof BaseModel) {
//			BaseModel baseModel = (BaseModel) paramObject;
//			schema = baseModel.getDblink();
//			mgrdb = baseModel.getMgrdb();
//			//分页插件中封装了原参数 详情可见源码
//		} else if (paramObject instanceof Map) {
//			Map params = (Map) paramObject;
//			schema  = (String)params.get("dblink");
//			if (params.get("mgrdb") != null) {
//				mgrdb = (boolean)params.get("mgrdb");
//			} else {
//				mgrdb = false;
//			}
//		}
		
		BaseModel user = RequestContext.<BaseModel>get(ConstantUtil.USER_SESSION);
		if(user!=null){
			schema = RequestContext.<BaseModel>get(ConstantUtil.USER_SESSION).getDblink();
			mgrdb = RequestContext.<BaseModel>get(ConstantUtil.USER_SESSION).getMgrdb();
		}else{
			mgrdb = true;
		}
//		if (paramObject instanceof User) {
//			
//		}
		//如果设置mgrdb=true时，连接走管理数据库时，不会做数据路由，直接操作管理库
		if (StringUtils.isNotEmpty(schema) && mgrdb == false) {
			MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);  
			String sql = (String) metaStatementHandler.getValue(SQLKEY);  
			sql = String.format(PREFIX, schema, sql); 
			metaStatementHandler.setValue(SQLKEY, sql);  
		}
		return invocation.proceed();  
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties arg0) {
	}

}
