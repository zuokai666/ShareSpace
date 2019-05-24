package xin.ss.model;

public class ReportDto extends AbstarctEntity {
	
	private int userid;
	private String userurl;
	private String username;
	private int shareid;
	private String type;
	private String content;
	private String time;
	
	public String getUserurl() {
		return userurl;
	}
	public void setUserurl(String userurl) {
		this.userurl = userurl;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getShareid() {
		return shareid;
	}
	public void setShareid(int shareid) {
		this.shareid = shareid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String toJson() {
		String s="{\"id\":\""+getId()+"\",\"userid\":\""+userid+"\","
				+ "\"userurl\":\""+userurl+"\",\"username\":\""+username+"\","
				+ "\"shareid\":\""+shareid+"\",\"type\":\""+type+"\","
				+ "\"content\":\""+content+"\",\"time\":\""+time+"\"}";
		return s;
	}
}














