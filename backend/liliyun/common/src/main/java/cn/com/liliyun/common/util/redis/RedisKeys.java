package cn.com.liliyun.common.util.redis;


public class RedisKeys {
	public class EXPIRE {
		/**
		 * 过期时间：天
		 */
		public static final int HOUR = 3600;
		/**
		 * 过期时间：天
		 */
		public static final int DAY = 3600 * 24;
		/**
		 * 过期时间：周
		 */
		public static final int WEEK = 3600 * 24 * 7;
		/**
		 * 过期时间：月
		 */
		public static final int MONTH = 3600 * 24 * 30;
	}
	
    public class REDISKEY {
    	
        /**
         * USER_AUTHCODE+mobile----->authcode
         */
        public static final String USER_AUTHCODE="user.u.authcode.";
        /**
         * USER_TOKEN+id----->token
         */
        public static final String USER_TOKEN="user.u.token.";
        
        /**
         * 分布式锁  e.g.	lock.enroll.id
         */
        public static final String LOCK_PRE ="lock.";
        
        
    }
}
