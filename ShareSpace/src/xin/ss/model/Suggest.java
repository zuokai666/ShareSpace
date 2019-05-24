package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class Suggest extends AbstarctEntity {

	private int userid;
	private String content;
	private String time;
	
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Suggest [userid=" + userid + ", content=" + content + ", time=" + time + "]";
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}

}
