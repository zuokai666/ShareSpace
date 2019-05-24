package xin.ss.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 所有实体类的父类
 * @author zk
 *
 */
@MappedSuperclass
public abstract class AbstarctEntity {
	
	private int id;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	protected AbstarctEntity(int id) {
		super();
		this.id = id;
	}

	protected AbstarctEntity() {
	}
	
	public abstract String toJson();
	
}
