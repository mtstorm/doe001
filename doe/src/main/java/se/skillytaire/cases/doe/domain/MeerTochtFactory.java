package se.skillytaire.cases.doe.domain;

import java.time.Duration;

import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.common.TochtType;

public class MeerTochtFactory implements TochtFactory {

	@Override
	public MeerTocht create( Duration verwachteDuur) {
		return new MeerTocht(verwachteDuur);
	}

	@Override
	public boolean isTypeFor( VoertuigType bootType,  TochtType tochtType) {
		return TochtType.MEER.equals(tochtType) && !VoertuigType.FIETS.equals(bootType);
	}

}
