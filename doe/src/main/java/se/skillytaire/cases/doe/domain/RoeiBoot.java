package se.skillytaire.cases.doe.domain;

import javax.persistence.Entity;

import se.skillytaire.cases.doe.common.VoertuigType;

@Entity
public class RoeiBoot extends Boot {

	/**
	 * Deze constructor mag niet gebruikt worden
	 */
public RoeiBoot() {
	
}
	public RoeiBoot( int nummer,  String naam,  int aantalPersonen,  double prijs) {
		super(VoertuigType.ROEI_BOOT, nummer, naam, 4, 15);
		// this.aantalPersonen = 4;
		// this.prijs = 15;
	}

	public String geefType() {
		return this.getClass().getSimpleName();
		// simplename geeft de classnaam, dit is het type (RoeiBoot in dit geval).
	}

}
