package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class Notice extends AbstarctEntity {
	
	private String title;
	private String content;
	private String time;
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	

	@Override
	public String toJson() {
		String s="{\"id\":\""+getId()+"\",\"title\":\""+title+"\",\"content\":\""+content+"\",\"time\":\""+time+"\"}";
		return s;
	}
	


}










