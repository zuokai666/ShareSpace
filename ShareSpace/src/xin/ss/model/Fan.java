package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class Fan extends AbstarctEntity {
	
	private int hostid;
	private int guestid;

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

	@Override
	public String toJson() {
		return null;
	}


}
