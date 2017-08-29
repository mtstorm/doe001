package se.skillytaire.cases.doe.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

import se.skillytaire.cases.doe.domain.BootBestaatNietException;
//Transaction Boundary
public interface VerhuurderService {
	/**
	 * 
	 * @param bootNummer
	 * @return
	 * @throws BootBestaatNietException
	 */
	TochtEinde beeindigTocht(int bootNummer);

	int geefAantalBeeindigdeTochten();

	Duration geefGemiddeldeDuur();

	// int startMeerTocht() throws GeenBootVrijException;
	int startTocht(StartTochtInformatie info);

	// /**
	// *
	// * @param verwachteDuur
	// * @return
	// * @throws GeenBootVrijException
	// */
	// int startMeerTochtRoeiBoot(Duration verwachteDuur) ;
	/// **
	// *
	// * @param verwachteDuur
	// * @return
	// * @throws GeenBootVrijException
	// */
	// int startMeerTochtElektrischeBoot(Duration verwachteDuur) ;
	/// **
	// *
	// * @param verwachteDuur
	// * @return
	// * @throws GeenBootVrijException
	// */
	// int startRivierTocht(Duration verwachteDuur);
	/**
	 * 
	 * @param nummer
	 * @throws BootBestaatNietException
	 */
	void uitvoerenInspectie(int nummer);

	/**
	 * 
	 * @param bootnr
	 * @return
	 * @throws BootBestaatNietException
	 */
	BootInformatie getBootInformatie(int bootnr);

	/**
	 * 
	 * @param klantNummer
	 * @return
	 */
	KlantInformatie getKlantInformatie(int klantNummer);

	boolean eindeDag();

	int registrerenKlant(KlantInformatie klantInfo);

	// FIXME reserverings to
	boolean reserveren(String typeTocht, String typeBoot, LocalDateTime datumtijd, Duration duur);
	/**
	 * Maakt een overzicht van vrije boten voor een gegeven tijdstip en gegeven duur rekening houdende met de reserveringen.
	 * @param tijdstip het gewenste moment van vrije boten overzicht
	 * @param duur verwachte tocht duur.
	 * @return Het overzicht van de vrije boten.
	 */
	
	// FIXME periode to
	ArrayList<BootInformatie> geefVrijeBoten(LocalDateTime tijdstip, Duration duur);

	boolean createBoot(BootInformatie bootinfo);

	int getAantalBoten();
}