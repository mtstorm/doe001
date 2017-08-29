package se.skillytaire.cases.doe.domain;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;

import se.skillytaire.cases.doe.common.VoertuigType;

@Entity
public class ElektrischeBoot extends Boot {

	private static  long serialVersionUID = 1L;

	/**
	 * Deze constructor mag niet gebruikt worden
	 */
	public ElektrischeBoot() {
		
	}
	
	public ElektrischeBoot( int nummer,  String naam,  int aantalPersonen,  double prijs) {
		super(VoertuigType.ELEKTRISCHE_BOOT, nummer, naam, 6, 25);
	}
@Deprecated
	public String geefType() {
		return this.getClass().getSimpleName();
		// simplename geeft de classnaam, dit is het type (ElektrischeBoot in dit
		// geval).
	}

	@Override
	public Tocht reserveerRivierTocht( LocalDateTime beginTijd,  Duration duur) {
		return null;
	}

	@Override
	public Tocht gaRivierTochtMaken( Duration verwachteDuur) throws BootInspectieException {
		return null;
	}

	@Override
	public boolean isInspectieNodig() {
		return false;
	}
}
