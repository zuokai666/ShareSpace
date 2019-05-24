package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class Comment extends AbstarctEntity {
	
	private int shareid;
	private int userid;
	private String content;
	private String commenttime;
	
	public int getShareid() {
		return shareid;
	}
	public void setShareid(int shareid) {
		this.shareid = shareid;
	}
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
	public String getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}
	@Override
	public String toString() {
		return "Comment [shareid=" + shareid + ", userid=" + userid + ", content=" + content + ", commenttime="
				+ commenttime + "]";
	}
	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}


}
