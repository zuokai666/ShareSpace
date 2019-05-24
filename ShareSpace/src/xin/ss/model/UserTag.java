package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class UserTag extends AbstarctEntity {
	
	private int userid;
	private int tagid;

	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getTagid() {
		return tagid;
	}
	public void setTagid(int tagid) {
		this.tagid = tagid;
	}

	@Override
	public String toJson() {
		return null;
	}


}
