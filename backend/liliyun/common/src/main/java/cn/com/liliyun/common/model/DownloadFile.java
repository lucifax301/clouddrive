package cn.com.liliyun.common.model;

import java.io.InputStream;

public class DownloadFile {

	private int length;
	private InputStream in;
	private String fileName;
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public InputStream getIn() {
		return in;
	}
	public void setIn(InputStream in) {
		this.in = in;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public DownloadFile(int length,InputStream in,String fileName){
		this.length=length;
		this.in=in;
		this.fileName=fileName;
	}
}
