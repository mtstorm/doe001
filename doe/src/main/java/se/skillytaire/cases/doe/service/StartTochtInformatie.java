package se.skillytaire.cases.doe.service;

import java.time.Duration;

import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.common.TochtType;

public class StartTochtInformatie implements TransferableObject {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final VoertuigType bootType;
	private final TochtType tochtType;
	private final Duration verwachteDuur;
	private final int bootNummer;


	private final static int NO_BOOT_NR = -1;

	private StartTochtInformatie(final int bootNummer, final VoertuigType bootType, final TochtType tochtType, final Duration verwachteDuur) {
		this.bootNummer = bootNummer;
		this.bootType = bootType;
		this.tochtType = tochtType;
		this.verwachteDuur = verwachteDuur;
	}
	// FIXME null checks
	public StartTochtInformatie(final VoertuigType bootType, final TochtType tochtType, final Duration verwachteDuur) {
		this(NO_BOOT_NR, bootType, tochtType, verwachteDuur);
	}
	public StartTochtInformatie(final int bootNummer,final TochtType tochtType,final Duration verwachteDuur) {
		this(bootNummer, null,tochtType,verwachteDuur);
	}
			
	public VoertuigType getBootType() {
		return bootType;
	}

	public TochtType getTochtType() {
		return tochtType;
	}

	public Duration getVerwachteDuur() {
		return verwachteDuur;
	}
	
	public boolean isByNumber() {
		return this.bootNummer != NO_BOOT_NR;
	}
	public int getBootNummer() {
		return bootNummer;
	}
}
