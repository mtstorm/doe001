package se.skillytaire.cases.doe.domain;

import java.time.Duration;

import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.common.TochtType;

public interface TochtFactory {

	Tocht create(Duration verwachteDuur);

	boolean isTypeFor(VoertuigType bootType, TochtType tochtType);

}
