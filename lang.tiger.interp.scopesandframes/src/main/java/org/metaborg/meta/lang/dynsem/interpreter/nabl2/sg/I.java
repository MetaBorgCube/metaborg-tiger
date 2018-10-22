package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg;

import org.spoofax.interpreter.terms.IStrategoTerm;

public final class I extends ALabel {

	public final static I SINGLETON = new I();

	private I() {
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean hasStrategoTerm() {
		return false;
	}

	@Override
	public IStrategoTerm getStrategoTerm() {
		return null;
	}

	@Override
	public String toString() {
		return "I()";
	}

	@Override
	public int hashCode() {
		// TODO: heh?
		return 144865439;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj instanceof I);
	}

}
