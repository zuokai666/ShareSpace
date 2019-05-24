package xin.ss.model;

public class Statistics {
	private int share;
	private int comment;
	private int pageview;
	private int register;
	public int getShare() {
		return share;
	}
	public void setShare(int share) {
		this.share = share;
	}
	public int getComment() {
		return comment;
	}
	public void setComment(int comment) {
		this.comment = comment;
	}
	public int getPageview() {
		return pageview;
	}
	public void setPageview(int pageview) {
		this.pageview = pageview;
	}
	public int getRegister() {
		return register;
	}
	public void setRegister(int register) {
		this.register = register;
	}
	
	public String toJson() {
		String s="{\"share\":\""+share+"\",\"comment\":\""+comment+"\",\"pageview\":\""+pageview+"\",\"register\":\""+register+"\"}";
		return s;
	}
}








