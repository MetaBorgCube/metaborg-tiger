package org.metaborg.lang.tiger.ninterpreter.objects;

import org.metaborg.lang.tiger.ninterpreter.TigerObject;

public class IntV implements TigerObject {
	
	private final int v;
	
	public IntV(int v) {
		this.v = v;
	}

	public int value() {
		return v;
	}
	
	@Override
	public String toString() {
		return "IntV(" + v + ")";
	}
}
