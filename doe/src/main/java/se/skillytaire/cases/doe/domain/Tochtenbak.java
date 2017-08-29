package se.skillytaire.cases.doe.domain;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Entity;

@Entity
public class Tochtenbak extends Bak<Tocht> {

	private static  long serialVersionUID = 1L;

	//@Deprecated
	public int bewaar( Tocht eenTocht) {
		add(eenTocht);
		int tellertje = this.size();
		eenTocht.setOid(tellertje);
		return tellertje;
	}

	/*
	 * public Tocht geefTocht( int nummer) { int index = nummer - 1; return
	 * this.tochten[index]; }
	 */
//verh
	public int geefAantalBeeindigdeTochten() {
		int counter = 0;
		for (int i = 0; i < this.size(); i++) {
			 Tocht tocht = get(i);
			if (tocht.isBeeindigd()) {
				counter++;
			}
		}
		return counter;
	}
//verh
	public boolean zijnAlleTochtenBeindigd() {
		boolean allen = true;
		for (int i = 0; i < size(); i++) {
			if (get(i).isBeeindigd()) {
				allen = false;
				break;
			}
		}
		return allen;
	}
//verh
	public Duration geefGemiddeldeDuur() {
		Duration gemiddeldeDuur = null;
		Duration totaleDuur = Duration.ofSeconds(0);
		int aantal = 0;
		for (int i = 0; i < size(); i++) {
			 Tocht tocht = get(i);
			if (tocht.isBeeindigd()) {
				 Duration duur = tocht.getDuur();
				aantal++;
				totaleDuur = totaleDuur.plus(duur);
			}
		}
		if (aantal > 0) {
			gemiddeldeDuur = totaleDuur.dividedBy(aantal);
		} else {
			gemiddeldeDuur = Duration.ofSeconds(0);
		}
		return gemiddeldeDuur;
	}
//v
	public Duration geefTotaleDuur() {
		Duration totaleDuur = Duration.ofSeconds(0);
		for (int i = 0; i < size(); i++) {
			 Tocht tocht = get(i);
			if (tocht.isBeeindigd()) {
				 Duration duur = tocht.getDuur();
				totaleDuur = totaleDuur.plus(duur);
			}
		}
		return totaleDuur;
	}


//v
//Tocht reserveren
	public boolean isReserveringMogelijk( LocalDateTime beginTijd,  Duration duur) {
		 LocalDateTime eindTijd = beginTijd.plus(duur);
		for (int index = 0; index < size(); index++) {
			 Tocht eenReservering = get(index);
			 LocalDateTime reserveringBeginTijd = eenReservering.getVerwachteBeginTijd();
			 LocalDateTime reserveringEindTijd = eenReservering.getVerwachteEindTijd();
			if (beginTijd.compareTo(reserveringBeginTijd) > 0 && beginTijd.compareTo(reserveringEindTijd) < 0) {
				return false;
			}
			if (eindTijd.compareTo(reserveringBeginTijd) > 0 && eindTijd.compareTo(reserveringEindTijd) < 0) {
				return false;
			}
		}
		return true;
	}

}
