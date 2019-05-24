package xin.ss.model;


public class UserDto extends AbstarctEntity {
	
	private String name;
	private int rank;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}


	@Override
	public String toString() {
		return "UserDto [name=" + name + ", rank=" + rank + "]";
	}

	@Override
	public String toJson() {
		String s="{\"id\":\""+getId()+"\",\"name\":\""+name+"\",\"rank\":\""+rank+"\"}";
		return s;
	}

}
