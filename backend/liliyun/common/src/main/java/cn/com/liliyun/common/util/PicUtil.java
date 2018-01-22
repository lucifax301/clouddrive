package cn.com.liliyun.common.util;

import java.io.File;

import org.json.JSONException;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import com.qiniu.util.Auth;

/**
 * 从七牛上获取图片
 * @author Hughes
 *
 */
public class PicUtil {

//	private final static String BUCKET = "lili";
	private final static String BUCKET_PUBLIC = "liliyun";
	private final static String AK = "wTZfpMov09_Pvgpzt01kVbTGoFKMcMf2CUmYs5n2";
	private final static String SK = "CzQMFHoGeNOVuF_0sG96oFzrQtVdRx25-aQrudp1";	
	private final static String DOMAIN = "http://7xnvu2.com1.z0.glb.clouddn.com/";
	
	static Auth auth = Auth.create(AK, SK);
	
	/**
	 * 获取七牛上存储的图片路径
	 * @param headIconName
	 * @return
	 */
	public static String getPicFromQN(String headIconName){

		String resoureName ="FnjqWFunVDKhLb3fDO0OeDCFzuWB";//默认图片
		if(StringUtil.isNullString(headIconName)){
			return auth.privateDownloadUrl(DOMAIN + resoureName);
		}else {
			long expires = System.currentTimeMillis()/1000L + 600; //十分钟
			return auth.privateDownloadUrl(DOMAIN + headIconName, expires);
		}
	}
	
	/**
	 * 获取七牛图片上传public token
	 * @return
	 */
	public static String getPublicToken(){
		return auth.uploadToken(BUCKET_PUBLIC);
	}
	
	
    //通过File上传
    public static PutRet uploadFile(File file) throws AuthException, JSONException {
        String uptoken = getUpToken();

        // 可选的上传选项，具体说明请参见使用手册。
        PutExtra extra = new PutExtra();

        // 上传文件
        PutRet ret = IoApi.putFile(uptoken, file.getName(), file.getAbsolutePath(), extra);

        return ret;

    }
    
    public static PutRet uploadFileWithRandomName(File file) throws AuthException, JSONException {
        String uptoken = getUpToken();

        // 可选的上传选项，具体说明请参见使用手册。
        PutExtra extra = new PutExtra();

        // 上传文件
        PutRet ret = IoApi.putFile(uptoken, null, file.getAbsolutePath(), extra);

        return ret;

    }
    
    //获取凭证
    private static String getUpToken() throws AuthException, JSONException {
        Mac mac = getMac();
        PutPolicy putPolicy = new PutPolicy(BUCKET_PUBLIC);
        String uptoken = putPolicy.token(mac);
        return uptoken;
    }

    private static Mac getMac() {
        Mac mac = new Mac(AK, SK);
        return mac;
    }
	
}
