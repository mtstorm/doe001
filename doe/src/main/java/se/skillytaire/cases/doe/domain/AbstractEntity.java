package se.skillytaire.cases.doe.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
	@MappedSuperclass
public class AbstractEntity implements Entity<Integer> {
	/*
	 * FIXME Database moet straks dit nummer regelen.
	 */
	@Id @GeneratedValue
	private Integer oid;
	/**
	 * Developers should not use this message.
	 * @param oid
	 */
	void setOid(Integer oid) {
		this.oid = oid;
	}

	@Override
	public Integer getOid() {
		return oid;
	}
	
	@Override
	public  boolean isPersistant() {
		return this.oid != null;
	}
	
	@Override
	public  boolean isIdentical(Object o) {
		boolean isIdentical = false;
		if(o != null && this.getClass()==o.getClass()) {
			AbstractEntity that = (AbstractEntity) o;
			isIdentical = this.getOid().equals(that.getOid());
		}
		return isIdentical;
	}

	@Override
	public String toString() {
		return String.format("{oid=%s} , ", oid);
	}
	
	
}
