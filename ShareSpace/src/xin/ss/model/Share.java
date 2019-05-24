package xin.ss.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;
@Entity
public class Share extends AbstarctEntity {

	private int userid;
	private String content;
	private int typeid;
	private String publishtime;
	private List<Image> imageList;
	private int goodnumber;
	private int badnumber;
	@Override
	public String toJson() {
		return null;
	}
	
	@Transient
	public List<Image> getImageList() {
		return imageList;
	}
	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public int getGoodnumber() {
		return goodnumber;
	}
	public void setGoodnumber(int goodnumber) {
		this.goodnumber = goodnumber;
	}
	public int getBadnumber() {
		return badnumber;
	}
	public void setBadnumber(int badnumber) {
		this.badnumber = badnumber;
	}

	@Override
	public String toString() {
		return "Share [userid=" + userid + ", content=" + content + ", typeid=" + typeid + ", publishtime="
				+ publishtime + ", imageList=" + imageList + ", goodnumber=" + goodnumber + ", badnumber=" + badnumber
				+ "]";
	}

	
	

}
