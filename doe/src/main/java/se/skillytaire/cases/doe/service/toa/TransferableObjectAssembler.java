package se.skillytaire.cases.doe.service.toa;

import se.skillytaire.cases.doe.domain.Entity;
import se.skillytaire.cases.doe.service.TransferableObject;

public interface TransferableObjectAssembler<T extends TransferableObject, E extends Entity> {

	default T assemble(final E entity) {
		throw new UnsupportedOperationException("assemble form entity to transferable object is not yet implemented");
	}

	default E assemble(final T obj) {
		throw new UnsupportedOperationException("assemble form transferable to entity object is not yet implemented");
	}
}
