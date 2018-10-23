package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg;

import org.spoofax.interpreter.terms.IStrategoTerm;

public final class D extends ALabel {

	public final static D SINGLETON = new D();

	private D() {
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
		return "D()";
	}

	@Override
	public int hashCode() {
		// TODO: heh?
		return 95366934;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj instanceof D);
	}

}
