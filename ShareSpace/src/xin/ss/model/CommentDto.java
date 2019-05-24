package xin.ss.model;

public class CommentDto extends AbstarctEntity {

	private int userid;
	private String url;
	private String username;
	private String content;
	private String commenttime;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}
	@Override
	public String toString() {
		return "CommentDto [username=" + username + ", content=" + content + ", commenttime="
				+ commenttime + "]";
	}
	@Override
	public String toJson() {
		String s="{\"id\":\""+getId()+"\",\"userid\":\""+userid+"\",\"url\":\""+url+"\",\"username\":\""+username+"\",\"content\":\""+content+"\","
				+ "\"commenttime\":\""+commenttime+"\"}";
		return s;
	}

	
	

}
