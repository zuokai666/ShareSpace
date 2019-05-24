package xin.ss.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Transient;

import xin.ss.util.JsonGenerator;
@Entity
public class User extends AbstarctEntity {
	
	private String account;
	private String password;
	private String name;
	private String url;
	private String location;
	private String description;
	private int isauthentication;
	private String title;
	private int follownumber;
	private int fannumber;
	private List<Tag> taglist;
	private String registertime;
	private int rank;
	private int usertype;

	
	public User() {
	}
	public User(int id, int rank) {
		super(id);
		this.rank = rank;
	}
	
	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Transient
	public List<Tag> getTaglist() {
		return taglist;
	}
	public void setTaglist(List<Tag> taglist) {
		this.taglist = taglist;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsauthentication() {
		return isauthentication;
	}
	public void setIsauthentication(int isauthentication) {
		this.isauthentication = isauthentication;
	}
	public int getFollownumber() {
		return follownumber;
	}
	public void setFollownumber(int follownumber) {
		this.follownumber = follownumber;
	}
	public int getFannumber() {
		return fannumber;
	}
	public void setFannumber(int fannumber) {
		this.fannumber = fannumber;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRegistertime() {
		return registertime;
	}
	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "User [account=" + account + ", password=" + password + ", name=" + name + ", url=" + url + ", location="
				+ location + ", description=" + description + ", isauthentication=" + isauthentication + ", title="
				+ title + ", follownumber=" + follownumber + ", fannumber=" + fannumber + ", taglist=" + taglist
				+ ", registertime=" + registertime + ", rank=" + rank + "]";
	}
	@Override
	public String toJson() {
		String s="{\"id\":\""+getId()+"\","
				+ "\"account\":\""+account+"\","
				+ "\"url\":\""+url+"\","
				+ "\"location\":\""+location+"\","
				+ "\"description\":\""+description+"\","
				+ "\"isauthentication\":\""+isauthentication+"\","
				+ "\"title\":\""+title+"\","
				+ "\"follownumber\":\""+follownumber+"\","
				+ "\"fannumber\":\""+fannumber+"\","
				+ "\"taglist\":"+JsonGenerator.getJson(taglist)+","
				+ "\"name\":\""+name+"\","
				+ "\"registertime\":\""+registertime+"\","
				+ "\"rank\":\""+rank+"\"}";
		return s;
	}

}
