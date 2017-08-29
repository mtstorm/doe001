package se.skillytaire.cases.doe.domain;

import javax.persistence.Entity;

@Entity
public class Klantenbak extends Bak<Klant> {

	private static  long serialVersionUID = 1L;

	public int bewaar( Klant eenKlant) {
		add(eenKlant);
		int tellertje = this.size();
		eenKlant.setOid(tellertje);
		return tellertje;
	}
	
	public Klant geefKlant( int klantNummer) {
		Klant eenKlant = null;
		for (int i = 0; i < this.size(); i++) {
			 Klant klant = this.get(i);
			if (klant.hasKlantNummer(klantNummer)) {
				eenKlant = klant;
				break;
			}
		}
		return eenKlant;
	}
}
