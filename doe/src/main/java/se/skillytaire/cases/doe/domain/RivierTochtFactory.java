package se.skillytaire.cases.doe.domain;

import java.time.Duration;

import se.skillytaire.cases.doe.common.VoertuigType;
import se.skillytaire.cases.doe.common.TochtType;

public class RivierTochtFactory implements TochtFactory {

	@Override
	public RivierTocht create( Duration verwachteDuur) {
		return new RivierTocht(verwachteDuur);
	}

	@Override
	public boolean isTypeFor( VoertuigType bootType,  TochtType tochtType) {
		boolean typeFor = VoertuigType.ROEI_BOOT.equals(bootType) && TochtType.RIVIER.equals(tochtType);
		return typeFor;
	}

}
