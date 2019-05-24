package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class Tag extends AbstarctEntity {
	
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toJson() {
		return "{\"id\":\""+getId()+"\",\"name\":\""+name+"\"}";
	}


}
