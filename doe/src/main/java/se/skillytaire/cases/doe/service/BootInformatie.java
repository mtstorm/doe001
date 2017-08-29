package se.skillytaire.cases.doe.service;

import java.time.LocalDateTime;
import java.util.Comparator;

import se.skillytaire.cases.doe.common.VoertuigType;

public class BootInformatie implements TransferableObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final VoertuigType type;
	private final int aantalPersonen;
	private final LocalDateTime verwachteEindtijd;

	private final String naam;
	private final int nummer;
	private final boolean inspectie;
	private final double prijs;
	public double getPrijs() {
		return prijs;
	}

	// public BootTransferObject(final BootType bootType, final int aantalPersonen,
	// final String verwachteEindtijd) {
	// this.type = bootType;
	// this.aantalPersonen = aantalPersonen;
	// this.verwachteEindtijd = verwachteEindtijd;
	// naam = "onbekend";//VIEW
	// nummer = 0;
	// inspectie = false;
	// }
	// TODO He constructors lijken verdacht opelkaar, chaning???
	public BootInformatie(final VoertuigType bootType, final String naam, final int bootnummer,
			final int aantalPersonen, final boolean inspectie, final LocalDateTime verwachteEindtijd, double prijs) {
		if (bootType == null) {
			throw new IllegalArgumentException("Type is void");
		}
		this.type = bootType;
		this.aantalPersonen = aantalPersonen;
		this.verwachteEindtijd = verwachteEindtijd;
		this.naam = naam;
		this.nummer = bootnummer;
		this.inspectie = inspectie;
		this.prijs = prijs;
	}

	public VoertuigType getType() {
		return type;
	}

	public String getNaam() {
		return naam;
	}

	public int getNummer() {
		return nummer;
	}

	public int getAantalPersonen() {
		return aantalPersonen;
	}

	public LocalDateTime getVerwachteEindtijd() {
		return verwachteEindtijd;
	}

	public boolean getInspectie() {
		return inspectie;
	}

	@Override
	public String toString() {
		return String.format(
				"BootInformatie [type=%s, aantalPersonen=%s, verwachteEindtijd=%s, naam=%s, nummer=%s, inspectie=%s]",
				type, aantalPersonen, verwachteEindtijd, naam, nummer, inspectie);
	}

	public static Comparator<BootInformatie> comparatorByNumber() {
		return (o1, o2) -> {
			return Integer.compare(o1.getNummer(), o2.getNummer());
		};
	}

	public static Comparator<BootInformatie> comparatorByName() {
		return (o1, o2) -> {
			return o1.getNaam().compareTo(o2.getNaam());
		};
	}

	public static Comparator<BootInformatie> comparatorByType() {
		return (o1, o2) -> {
			return o1.getType().compareTo(o2.getType());
		};
	}

	public static Comparator<BootInformatie> comparatorByAantalPersonen() {
		return (o1, o2) -> {
			return Integer.compare(o1.getAantalPersonen(), o2.getAantalPersonen());
		};
	}

	public static Comparator<BootInformatie> comparatorByInspectie() {
		return (o1, o2) -> {
			return Boolean.compare(o1.getInspectie(), o2.getInspectie());
		};
	}
	// TODO hasMethods


}