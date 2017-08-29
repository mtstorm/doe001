package se.skillytaire.cases.doe.domain;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Bak <T> extends AbstractEntity {
	
	private  ArrayList<T> verzameling;
	public Bak(int initialCapacity) {
		verzameling = new ArrayList<>(initialCapacity);
	}	
	public Bak() {
		this(10);
	}
	public  boolean add(T e) {
		return verzameling.add(e);
	}
	public int size() {
		return verzameling.size();
	}
	protected T get(int index) {
		return verzameling.get(index);
	}
	
	public void clear() {
		verzameling.clear();
	}
	
	
}
