package xin.ss.model;

import javax.persistence.Entity;

@Entity
public class Relation extends AbstarctEntity {

	private int shareid;
	private int guestid;
	private int score;
	
	public int getShareid() {
		return shareid;
	}

	public void setShareid(int shareid) {
		this.shareid = shareid;
	}

	public int getGuestid() {
		return guestid;
	}

	public void setGuestid(int guestid) {
		this.guestid = guestid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toJson() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
