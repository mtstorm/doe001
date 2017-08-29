package se.skillytaire.cases.doe.service;

import java.time.Duration;

public class TochtEinde implements TransferableObject {

	private static final long serialVersionUID = 1L;
	private final Duration duur;
	private final boolean inspectieNodig;

	public TochtEinde(final Duration duur, final boolean inspectie) {
		super();
		this.duur = duur;
		inspectieNodig = inspectie;
	}

	public Duration getDuur() {
		return duur;
	}

	public boolean isInspectieNodig() {
		return inspectieNodig;
	}

}
