package se.skillytaire.cases.doe.service.toa;

import java.time.LocalDateTime;

import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.domain.Boot;
import se.skillytaire.cases.doe.domain.ElektrischeBoot;
import se.skillytaire.cases.doe.domain.RoeiBoot;
import se.skillytaire.cases.doe.service.BootInformatie;

public class BootTransferableObjectAssembler implements TransferableObjectAssembler<BootInformatie, Boot> {

	private static final BootTransferableObjectAssembler INSTANCE = new BootTransferableObjectAssembler();

	private BootTransferableObjectAssembler() {

	}

	@Override
	public Boot assemble(BootInformatie obj) {
		Boot boot;
		VoertuigType bootType = obj.getType();
		int nummer = obj.getNummer();
		String naam = obj.getNaam();
		int aantalPersonen = obj.getAantalPersonen();
		double prijs = obj.getPrijs();
		switch (bootType) {
		case ELEKTRISCHE_BOOT:
			boot = new ElektrischeBoot(nummer, naam, aantalPersonen, prijs);
			break;
		case ROEI_BOOT:
			boot = new RoeiBoot(nummer, naam, aantalPersonen, prijs);
			break;
		default:
			throw new AssertionError("Echt Niet!");
		}
		return boot;
	}

	public static BootTransferableObjectAssembler getInstance() {
		return BootTransferableObjectAssembler.INSTANCE;
	}

	@Override
	public BootInformatie assemble(final Boot eenBoot) {
		final String naam = eenBoot.getNaam();
		final int bootNummer = eenBoot.getNummer();
		final int aantalPersonen = eenBoot.getAantalPersonen();
		final boolean inspectie = eenBoot.isInspectieNodig();
		final LocalDateTime verwachteEindTijd = eenBoot.getVerwachteEindTijd();
		final VoertuigType bootType = eenBoot.getType();
		final double prijs = eenBoot.getPrijs();
		final BootInformatie bootInformatie = new BootInformatie(bootType, naam, bootNummer, aantalPersonen, inspectie,
				verwachteEindTijd, prijs);
		return bootInformatie;
	}

}
