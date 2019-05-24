package xin.ss.model;

public class RelationDto extends AbstarctEntity {

	private String guestname;
	private int score;
	
	@Override
	public String toJson() {
		String s="{\"guestname\":\""+guestname+"\",\"score\":\""+score+"\"}";
		return s;
	}
	public String getGuestname() {
		return guestname;
	}
	public void setGuestname(String guestname) {
		this.guestname = guestname;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	
}
