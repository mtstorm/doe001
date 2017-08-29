package se.skillytaire.cases.doe.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import se.skillytaire.cases.doe.common.VoertuigType;

// FIXME meerdere verhuurbedrijven
public abstract class Boot extends Voertuig {
	@NotNull
	@Column(unique=true,nullable=false)
	private  Integer nummer;
	@NotNull
	@Column(unique=true,length=50,nullable=false)
	@Min(value=1)
	@Max(value=50)
	private String naam;
	
	/**
	 * Deze constructor mag niet gebruikt worden
	 */
	public Boot() {
		
	}
	
	public Boot(VoertuigType type, int nummer, String naam, int aantalPersonen, double prijs) {
		super(type, aantalPersonen, prijs);
		this.nummer = nummer;
		this.naam = naam;
	}

	public boolean hasNummer( int nummer) {
		return this.nummer == nummer;
	}
	
	@Override
	public int hashCode() {
		return nummer;
	}

	@Override
	public boolean equals( Object obj) {
		boolean equals = super.equals(obj);
		if (!equals && obj instanceof Boot) {
			@SuppressWarnings("unchecked")
			 Boot that = (Boot) obj;
			 int otherNumber = that.getNummer();
			equals = hasNummer(otherNumber);
		}
		return equals;
	}
	
	// -- Getters en setters
	public int getNummer() {
		return nummer;
	}
	public String getNaam() {
		return naam;
	}
}
