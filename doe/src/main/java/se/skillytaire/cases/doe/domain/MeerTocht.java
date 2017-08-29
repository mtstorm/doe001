package se.skillytaire.cases.doe.domain;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public  class MeerTocht extends Tocht {
	private static  long serialVersionUID = 1L;

	/**
	 * deze constructor mag niet gebruikt worden
	 */
	public MeerTocht() {
		
	}
	
	public MeerTocht( LocalDateTime verwachteBeginTijd,  Duration verwachteDuur) {
		super(verwachteBeginTijd, verwachteDuur);
	}

	public MeerTocht( Duration verwachteDuur) {
		super(verwachteDuur);
	}
}
