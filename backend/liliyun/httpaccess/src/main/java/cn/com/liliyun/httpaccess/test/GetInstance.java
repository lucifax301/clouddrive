package cn.com.liliyun.httpaccess.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import com.sun.tools.attach.AttachNotSupportedException;

import sun.tools.attach.HotSpotVirtualMachine;

public class GetInstance {

	public static void process() throws Exception, IOException{
		RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean();
		String name = bean.getName();
		int index = name.indexOf('@');
		String pid = name.substring(0, index);
		//这里要区分操作系统
		HotSpotVirtualMachine machine = (HotSpotVirtualMachine) new sun.tools.attach.WindowsAttachProvider().attachVirtualMachine(pid);
		InputStream is = machine.heapHisto("-all");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int readed;
		byte[] buff = new byte[1024];
		while((readed = is.read(buff)) > 0){
			baos.write(buff, 0, readed);
		}
		is.close();
		machine.detach();
	}
}
