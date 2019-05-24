package xin.ss.model;

public class FanDto extends AbstarctEntity {
	
	private int hostid;
	private String hosturl;
	private String hostname;
	private int guestid;
	private String guesturl;
	private String guestname;
	
	public String getHosturl() {
		return hosturl;
	}
	public void setHosturl(String hosturl) {
		this.hosturl = hosturl;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getGuesturl() {
		return guesturl;
	}
	public void setGuesturl(String guesturl) {
		this.guesturl = guesturl;
	}
	public String getGuestname() {
		return guestname;
	}
	public void setGuestname(String guestname) {
		this.guestname = guestname;
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

	@Override
	public String toString() {
		return "FanDto [hostid=" + hostid + ", hosturl=" + hosturl + ", hostname=" + hostname + ", guestid=" + guestid
				+ ", guesturl=" + guesturl + ", guestname=" + guestname + "]";
	}
	@Override
	public String toJson() {
		String string="";
		if(hostid==0){
			string="{\"guestid\":\""+guestid+"\",\"guesturl\":\""+guesturl+"\",\"guestname\":\""+guestname+"\"}";
		}else {
			string="{\"hostid\":\""+hostid+"\",\"hosturl\":\""+hosturl+"\",\"hostname\":\""+hostname+"\"}";
		}
		return string;
	}


}
