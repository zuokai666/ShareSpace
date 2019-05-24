package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class Authentication extends AbstarctEntity {
	
	private int userid;
	private String url;
	private String title;
	private String content;
	private String time;
	private int status;
	private String result;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Authentication [userid=" + userid + ", url=" + url + ", title=" + title + ", content=" + content
				+ ", time=" + time + ", status=" + status + ", result=" + result + ", getId()=" + getId() + "]";
	}

	@Override
	public String toJson() {
		String s="{\"id\":\""+getId()+"\",\"userid\":\""+userid+"\",\"url\":\""+url+"\",\"title\":\""+title+"\",\"content\":\""+content+"\","
				+ "\"time\":\""+time+"\",\"status\":\""+status+"\",\"result\":\""+result+"\"}";
		return s;
	}


}
