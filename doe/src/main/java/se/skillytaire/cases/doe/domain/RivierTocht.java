package se.skillytaire.cases.doe.domain;

import java.time.Duration;
import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public  class RivierTocht extends Tocht {
	private static  long serialVersionUID = 1L;
	private static  Duration TOESLAG_DUUR = Duration.ofSeconds(10 * 60);

	/**
	 * deze constructor mag niet gebruikt worden
	 */
	public RivierTocht() {
		
	}
	public RivierTocht( LocalDateTime verwachteBeginTijd,  Duration verwachteDuur) {
		super(verwachteBeginTijd, verwachteDuur);
	}

	public RivierTocht( Duration verwachteDuur) {
		super(verwachteDuur);
	}

	@Override
	public Duration getDuur() {
		 Duration werkelijkeDuur = super.getDuur();
		 Duration duur = werkelijkeDuur.plus(RivierTocht.TOESLAG_DUUR);
		return duur;
	}

	@Override
	public LocalDateTime getVerwachteEindTijd() {
		LocalDateTime verwachteEindtijd = super.getVerwachteEindTijd();
		verwachteEindtijd = verwachteEindtijd.plus(RivierTocht.TOESLAG_DUUR);
		return verwachteEindtijd;
	}
}