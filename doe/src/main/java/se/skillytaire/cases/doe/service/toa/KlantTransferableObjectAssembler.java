package se.skillytaire.cases.doe.service.toa;

import java.time.LocalDateTime;

import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.domain.Boot;
import se.skillytaire.cases.doe.domain.ElektrischeBoot;
import se.skillytaire.cases.doe.domain.Klant;
import se.skillytaire.cases.doe.domain.RoeiBoot;
import se.skillytaire.cases.doe.service.BootInformatie;
import se.skillytaire.cases.doe.service.KlantInformatie;

public class KlantTransferableObjectAssembler implements TransferableObjectAssembler<KlantInformatie, Klant> {

	private static final KlantTransferableObjectAssembler INSTANCE = new KlantTransferableObjectAssembler();

	private KlantTransferableObjectAssembler() {

	}

	@Override
	public Klant assemble(KlantInformatie klantInfo) {
		final String eenNaam=klantInfo.geefNaam();
		final String eenAdres=klantInfo.geefAdres();
		final String eenWoonplaats=klantInfo.geefWoonplaats();
		final String eenTelefoonnr=klantInfo.geefTelefoonnr();
		final Klant klant = new Klant(eenNaam, eenAdres, eenWoonplaats, eenTelefoonnr);

		
		return klant;
	}

	public static KlantTransferableObjectAssembler getInstance() {
		return KlantTransferableObjectAssembler.INSTANCE;
	}

	@Override
	public KlantInformatie assemble(final Klant eenKlant) {
		final String klantNaam = eenKlant.getNaam();
		final String woonplaats = eenKlant.getWoonplaats();
		final String telefoonnr = eenKlant.getTelefoonnr();
		final String adres = eenKlant.getAdres();
		final KlantInformatie klantTO = new KlantInformatie(klantNaam, adres, woonplaats, telefoonnr);
		return klantTO;
	}

}
/*
*/