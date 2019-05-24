package xin.ss.model;

import java.util.List;

import xin.ss.util.JsonGenerator;

public class ShareDto extends AbstarctEntity {
	
	private int userid;
	private String url;
	private String username;
	private String content;
	private String typename;
	private String publishtime;
	private int goodnumber;
	private int badnumber;
	private List<CommentDto> commentList;
	private List<Image> imageList;
	
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
	public List<Image> getImageList() {
		return imageList;
	}
	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}
	public List<CommentDto> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentDto> commentList) {
		this.commentList = commentList;
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
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public int getGoodnumber() {
		return goodnumber;
	}
	public void setGoodnumber(int goodnumber) {
		this.goodnumber = goodnumber;
	}
	public int getBadnumber() {
		return badnumber;
	}
	public void setBadnumber(int badnumber) {
		this.badnumber = badnumber;
	}
	@Override
	public String toString() {
		return "ShareDto [username=" + username + ", content=" + content + ", typename=" + typename + ", publishtime="
				+ publishtime + ", goodnumber=" + goodnumber + ", badnumber=" + badnumber + ", commentList=" + commentList + "]";
	}
	@Override
	public String toJson() {
		String s="{\"id\":\""+getId()+"\",\"userid\":\""+userid+"\",\"url\":\""+url+"\",\"username\":\""+username+"\",\"content\":\""+content+"\","
				+ "\"typename\":\""+typename+"\",\"publishtime\":\""+publishtime+"\",\"goodnumber\":\""+goodnumber+"\","
				+ "\"badnumber\":\""+badnumber+"\",\"commentList\":"+JsonGenerator.getJson(commentList)+""
				+ ",\"imageList\":"+JsonGenerator.getJson(imageList)+"}";
		return s;
	}
	

}
























