package se.skillytaire.cases.doe.domain;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;

import org.junit.Test;

import se.skillytaire.cases.doe.common.TochtType;
import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.service.BootInformatie;
import se.skillytaire.cases.doe.service.StartTochtInformatie;
import se.skillytaire.cases.doe.service.VerhuurderService;
import se.skillytaire.cases.doe.service.impl.VerhuurderServiceImpl;

public class VerwachteEindTijdBug {

	@Test
	public void test() {
		VerhuurderService verhuurderService = VerhuurderServiceImpl.getInstance(true);
		int aantalBoten = verhuurderService.getAantalBoten();
		assertEquals(0, aantalBoten);
		int expectedBootnummer = 21;
		VoertuigType bootType = VoertuigType.ROEI_BOOT;
		String naam = "Kroket";
		int aantalPersonen = 7;
		boolean inspectie = true;
		LocalDateTime verwachteEindtijd = null;
		double prijs = 40;
		BootInformatie bootinfo = new BootInformatie(bootType, naam, expectedBootnummer, aantalPersonen, inspectie, verwachteEindtijd, prijs);
		boolean succes = verhuurderService.createBoot(bootinfo);
		assertTrue(succes);
		aantalBoten = verhuurderService.getAantalBoten();
		assertEquals(1, aantalBoten);
		Duration verwachteTochtDuur = Duration.ofHours(2);
		TochtType tochtType = TochtType.MEER;
		StartTochtInformatie info = new StartTochtInformatie(expectedBootnummer, tochtType, verwachteTochtDuur);
		int actualBootnummer = verhuurderService.startTocht(info);
		assertEquals("De verkeerde boot heeft een tocht gestart",actualBootnummer,expectedBootnummer);
		BootInformatie bootInfo = verhuurderService.getBootInformatie(expectedBootnummer);
		int bootInfoNummer = bootInfo.getNummer();
		VoertuigType bootInfoType = bootInfo.getType();
		assertEquals(expectedBootnummer,bootInfoNummer);
		assertEquals(bootType,bootInfoType);
		verwachteEindtijd = bootInfo.getVerwachteEindtijd();
	assertNotNull(verwachteEindtijd);
	}
	
}
