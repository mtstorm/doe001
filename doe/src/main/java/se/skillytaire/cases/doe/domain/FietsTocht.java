package se.skillytaire.cases.doe.domain;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class FietsTocht extends Tocht{

	/**
	 * Deze constructor mag niet gebruikt worden
	 */
	public FietsTocht() {
		
	}
	public FietsTocht(Duration verwachteDuur) {
		super(verwachteDuur);
	}

	public FietsTocht(LocalDateTime verwachteBeginTijd, Duration verwachteDuur) {
		super(verwachteBeginTijd, verwachteDuur);
	}

}
