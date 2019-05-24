package xin.ss.model;

import java.util.List;

import xin.ss.util.JsonGenerator;

public class Page {
	private int count;
	private int typeid;
	private int userid;
	private int totalPage;
	private int pageSize=10;
	private int currentPage;
	private List<ShareDto> shareList;
	
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<ShareDto> getShareList() {
		return shareList;
	}
	public void setShareList(List<ShareDto> shareList) {
		this.shareList = shareList;
	}

	
	@Override
	public String toString() {
		return "Page [count=" + count + ", typeid=" + typeid + ", userid=" + userid + ", totalPage=" + totalPage
				+ ", pageSize=" + pageSize + ", currentPage=" + currentPage + ", shareList=" + shareList + "]";
	}
	public String toJson() {
		String s="{\"count\":\""+count+"\",\"totalPage\":\""+totalPage+"\",\"pageSize\":\""+pageSize+"\",\"currentPage\":\""+currentPage+"\","
				+ "\"shareList\":"+JsonGenerator.getJson(shareList)+"}";
		return s;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	
	
}




















