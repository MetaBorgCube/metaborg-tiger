package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg;

import org.spoofax.interpreter.terms.IStrategoTerm;

public final class P extends ALabel {

	public final static P SINGLETON = new P();

	private P() {
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
		return "P()";
	}

	@Override
	public int hashCode() {
		// TODO: heh?
		return 85523795;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj instanceof P);
	}

}
