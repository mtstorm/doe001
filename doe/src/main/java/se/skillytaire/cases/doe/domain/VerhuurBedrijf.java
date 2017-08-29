package se.skillytaire.cases.doe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class VerhuurBedrijf extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Column(nullable=false,unique=true)
	private String name;

	public String getName() {
		return this.name;
	}

	/**
	 * Deze constructor mag niet worden gebruikt door developers
	 */
	public VerhuurBedrijf() {
	}

	public VerhuurBedrijf(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = super.equals(obj);
		if (!equals && obj instanceof VerhuurBedrijf) {
			 VerhuurBedrijf that = (VerhuurBedrijf) obj;
			 equals = this.getName().equals(that.getName());
		}
		return equals;
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
