package se.skillytaire.cases.doe.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Klant extends AbstractEntity {

	/**
	 *
	 */
	private static  long serialVersionUID = 1L;
	@Column (nullable=false,length=255)
	@Min (value=3)
	@Max (value=255)
	@NotNull
	private  String naam;
	@Column (nullable=false,length=255)
	@Min (value=3)
	@Max (value=255)
	@NotNull
	private  String adres;
	@Column (nullable=false,length=255)
	@Min (value=3)
	@Max (value=255)
	@NotNull
	private  String woonplaats;
	
	//FIXME telefoonnummer format fixen
	//zie website https://stackoverflow.com/questions/17949757/regular-expression-for-dutch-phone-number
	@Pattern (regexp="\\+[0-9]{2}|^\\+[0-9]{2}\\(0\\)|^\\(\\+[0-9]{2}\\)\\(0\\)|^00[0-9]{2}|^0)([0-9]{9}$|[0-9\\-\\s]{10}")
	private  String telefoonnr;

	public String getNaam() {
		return naam;
	}

	public String getAdres() {
		return adres;
	}

	public String getWoonplaats() {
		return woonplaats;
	}

	public String getTelefoonnr() {
		return telefoonnr;
	}




	public Klant( String eenNaam,  String eenAdres,  String eenWoonplaats,  String eenTelefoonnr) {
		naam = eenNaam;
		adres = eenAdres;
		woonplaats = eenWoonplaats;
		telefoonnr = eenTelefoonnr;
	}
	
	/** 
	 * developers may not use this
	 */
	public Klant () {
		
	}
	

	/**
	 * Controleert of deze klant het nummer heeft wat is opgegeven.
	 *
	 * @param klantNummer
	 *            Het opgegeven klantnummer
	 * @return true als het opgegeven klantnummer overeenkomt met het nummer van
	 *         deze klant.
	 */
	public boolean hasKlantNummer( int klantNummer) {
		return this.getKlantNummer() == klantNummer;
	}

	public int getKlantNummer() {
		return this.getOid();
	}

}
