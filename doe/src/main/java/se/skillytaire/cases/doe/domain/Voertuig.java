package se.skillytaire.cases.doe.domain;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import se.skillytaire.cases.doe.common.VoertuigType;

public abstract class Voertuig extends AbstractEntity  {

	private static  long serialVersionUID = 1L;
	
	private Tocht laatsteTocht;
	
	private  Tochtenbak eenTochtenbak;
	private  Tochtenbak reserveringen;
	
	private static  Duration INSPECTIE_DUUR = Duration.ofSeconds(10);
	
	private  int aantalPersonen;
	private  double prijs;
	private  VoertuigType type;

	/**
	 * Deze constructor mag niet gebruikt worden
	 */
	public Voertuig() {
		
	}
	
	public Voertuig( VoertuigType type) {
		this(type, -1, 0);
	}

	public Voertuig( VoertuigType type,  int aantalPersonen,  double prijs) {
		if (type == null) {
			throw new IllegalArgumentException("Boem");
		}
		this.type = type;

		this.aantalPersonen = aantalPersonen;

		eenTochtenbak = new Tochtenbak();
		reserveringen = new Tochtenbak();
		uitvoerenInspectie();

		this.prijs = prijs;

	}

	/**
	 * Beindigd de tocht
	 *
	 * @return de duur van de gemaakte tocht
	 * @throws BootBeschikbaarException
	 *             als de boot beschikbaar is
	 */
	public Duration beeindigTocht() throws BootBeschikbaarException, BootInspectieException {
		if (isBeschikbaar()) {
			throw new BootBeschikbaarException("Boot  maakt momenteel geen tocht");
		}
		laatsteTocht.beeindig();
		 Duration duur = laatsteTocht.getDuur();
		return duur;
	}

	public void startTocht( Tocht nieuweTocht) {
		checkInspectie();
		laatsteTocht = nieuweTocht;
		eenTochtenbak.bewaar(nieuweTocht);
	}

	public Tocht gaMeerTochtMaken( Duration verwachteDuur) throws BootInspectieException {
		checkInspectie();
		laatsteTocht = new MeerTocht(verwachteDuur);

		eenTochtenbak.bewaar(laatsteTocht);
		return laatsteTocht;
	}

	public Tocht gaRivierTochtMaken( Duration verwachteDuur) throws BootInspectieException {
		checkInspectie();
		laatsteTocht = new RivierTocht(verwachteDuur);
		eenTochtenbak.bewaar(laatsteTocht);
		return laatsteTocht;
	}

	private void checkInspectie() throws BootInspectieException {
		if (isInspectieNodig()) {
			throw new BootInspectieException("Voertuig heeft inspectie nodig");
		}
	}

	public boolean isInspectieNodig() {
		return eenTochtenbak.geefTotaleDuur().getSeconds() > Voertuig.INSPECTIE_DUUR.getSeconds();
	}

	public boolean isBeschikbaar() {
		boolean isBeschikbaar;
		if (isInspectieNodig()) {
			isBeschikbaar = false;
		} else {
			isBeschikbaar = this.isVrij();
		}
		return isBeschikbaar;
	}

	private boolean isVrij() {
		boolean isVrij;
		if (laatsteTocht == null) {
			isVrij = true;
		} else {
			isVrij = laatsteTocht.isBeeindigd();
		}
		return isVrij;
	}

	public boolean isVrij( LocalDateTime begintijd,  Duration duur) {
		boolean isVrij;
		 boolean isReserveringMogelijk = reserveringen.isReserveringMogelijk(begintijd, duur);
		if (laatsteTocht == null || laatsteTocht.isBeeindigd()) {
			isVrij = isReserveringMogelijk;
		} else {
			 LocalDateTime verwachteEindtijd = laatsteTocht.getVerwachteEindTijd();
			isVrij = begintijd.compareTo(verwachteEindtijd) >= 0 && isReserveringMogelijk;
		}
		return isVrij;
	}

	public void uitvoerenInspectie() throws BootInspectieException {
		if (!this.isVrij()) {
			throw new BootInspectieException(
					"De boot kan niet worden geinspecteerd, de boot is nog niet vrij.");
		}
		eenTochtenbak.clear();
	}



	@Override
	public String toString() {
		return String.format("Voertuig [/isVrij=%b, /inspecieNodig=%b]",  isBeschikbaar(),
				isInspectieNodig());
	}



	/*
	 * public void print() { System.out.println("inhoud eenTochtenbak van boot " +
	 * this.bootNummer ); System.out.println("inhoud alleTochtenbak van boot " +
	 * this.bootNummer ); }
	 */
//TODO generieker
	public Tocht reserveerRivierTocht( LocalDateTime beginTijd,  Duration duur) {
		if (reserveringen.isReserveringMogelijk(beginTijd, duur)) {
			 Tocht nieuweTocht = new RivierTocht(beginTijd, duur);
			reserveringen.bewaar(nieuweTocht);
			return nieuweTocht;
		}
		return null;
	}
	//TODO generieker
	public Tocht reserveerMeerTocht( LocalDateTime beginTijd,  Duration duur) {
		if (reserveringen.isReserveringMogelijk(beginTijd, duur)) {
			 Tocht nieuweTocht = new MeerTocht(beginTijd, duur);
			reserveringen.bewaar(nieuweTocht);
			return nieuweTocht;
		}
		return null;
	}
	Tocht getLaatsteTocht() {
		return laatsteTocht;
	}

	void setLaatsteTocht( Tocht laatsteTocht) {
		this.laatsteTocht = laatsteTocht;
	}



	public int getAantalPersonen() {
		return aantalPersonen;
	}

	public double getPrijs() {
		return prijs;
	}

	private boolean hasLaatsteTocht() {
		return laatsteTocht != null;
	}

	public LocalDateTime getVerwachteEindTijd() {
		LocalDateTime verwachteEindTijd = null;
		if (hasLaatsteTocht() && !laatsteTocht.isBeeindigd()) {
			verwachteEindTijd = laatsteTocht.getVerwachteEindTijd();
		}
		return verwachteEindTijd;
	}

	public VoertuigType getType() {
		return type;
	}

}
