package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class Image extends AbstarctEntity {
	private int shareid;
	private String url;

	public int getShareid() {
		return shareid;
	}
	public void setShareid(int shareid) {
		this.shareid = shareid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toJson() {
		String s="{\"url\":\""+url+"\"}";
		return s;
	}
	@Override
	public String toString() {
		return "Image [shareid=" + shareid + ", url=" + url + "]";
	}
	
}
