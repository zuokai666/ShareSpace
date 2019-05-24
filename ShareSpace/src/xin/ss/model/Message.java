package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class Message extends AbstarctEntity {
	
	private int hostid;
	private int guestid;
	private String content;
	private String time;
	private int isread;
	private int ishostdel;
	private int isguestdel;
	
	public int getIshostdel() {
		return ishostdel;
	}
	public void setIshostdel(int ishostdel) {
		this.ishostdel = ishostdel;
	}
	public int getIsguestdel() {
		return isguestdel;
	}
	public void setIsguestdel(int isguestdel) {
		this.isguestdel = isguestdel;
	}
	public int getHostid() {
		return hostid;
	}
	public void setHostid(int hostid) {
		this.hostid = hostid;
	}
	public int getGuestid() {
		return guestid;
	}
	public void setGuestid(int guestid) {
		this.guestid = guestid;
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
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}


	@Override
	public String toJson() {
		return null;
	}


}
