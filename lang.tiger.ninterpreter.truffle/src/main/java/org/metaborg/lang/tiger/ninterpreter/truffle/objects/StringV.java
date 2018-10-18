package org.metaborg.lang.tiger.ninterpreter.truffle.objects;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;

public class StringV implements TigerObject {

	private final String s;

	public StringV(String s) {
		this.s = s;
	}

	public String s() {
		return s;
	}

}
