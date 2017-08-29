package se.skillytaire.cases.doe.domain;

import java.io.Serializable;
/*
 * unique -> Comparable ook voor Entity? Mag en de rest
 */
/**
 * 
 * @author prolector
 *
 * @param <E> De entity class
 * @param <OID> Het type van de niet zeggende primaire sleutel in de database
 */
public interface Entity<OID extends Comparable<OID> & Serializable> extends Serializable {
	OID getOid();
	boolean isPersistant();
	boolean isIdentical(Object that);
}
