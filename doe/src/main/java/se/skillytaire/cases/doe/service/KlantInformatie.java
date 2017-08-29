package se.skillytaire.cases.doe.service;

public class KlantInformatie implements TransferableObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final String naam;
	private final String adres;
	private final String woonplaats;
	private final String telefoonnr;

	public KlantInformatie(final String naam, final String adres, final String woonplaats, final String telefoonnr) {
		this.naam = naam;
		this.adres = adres;
		this.woonplaats = woonplaats;
		this.telefoonnr = telefoonnr;
	}

	public String geefNaam() {
		return naam;
	}

	public String geefAdres() {
		return adres;
	}

	public String geefWoonplaats() {
		return woonplaats;
	}

	public String geefTelefoonnr() {
		return telefoonnr;
	}

}
