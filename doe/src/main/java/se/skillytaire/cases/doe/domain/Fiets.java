package se.skillytaire.cases.doe.domain;

import javax.persistence.Entity;

import se.skillytaire.cases.doe.common.VoertuigType;

@Entity
public class Fiets extends Voertuig {

	/**
	 * Deze constructor mag niet gebruikt worden
	 */
	public Fiets() {
		
	}
	
	public Fiets( int prijs) {
		super(VoertuigType.FIETS, 1, prijs);
	}

}
