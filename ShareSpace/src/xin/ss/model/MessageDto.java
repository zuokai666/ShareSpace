package xin.ss.model;

public class MessageDto extends AbstarctEntity {
	
	private int hostid;
	private String hostname;
	private String hosturl;
	private int guestid;
	private String guestname;
	private String guesturl;
	private String content;
	private String time;
	private int isread;
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getHosturl() {
		return hosturl;
	}
	public void setHosturl(String hosturl) {
		this.hosturl = hosturl;
	}
	public String getGuestname() {
		return guestname;
	}
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}
	public String getGuesturl() {
		return guesturl;
	}
	public void setGuesturl(String guesturl) {
		this.guesturl = guesturl;
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
		String string="";
		if(hostid==0){
			string="{\"id\":\""+getId()+"\",\"guestid\":\""+guestid+"\",\"guesturl\":\""+guesturl+"\",\"guestname\":\""+guestname+"\","
					+ "\"content\":\""+content+"\",\"time\":\""+time+"\",\"isread\":\""+isread+"\"}";
		}else {
			string="{\"id\":\""+getId()+"\",\"hostid\":\""+hostid+"\",\"hosturl\":\""+hosturl+"\",\"hostname\":\""+hostname+"\","
					+ "\"content\":\""+content+"\",\"time\":\""+time+"\"}";
		}
		return string;
	}


}
