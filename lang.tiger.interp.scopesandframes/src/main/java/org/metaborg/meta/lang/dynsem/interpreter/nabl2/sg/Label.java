package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg;

import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class Label extends ALabel {

	private String label;

	public Label(String label) {
		this.label = label;
	}

	@Override
	public int size() {
		return 1;
	}

	public String get_1() {
		return label;
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
		return label;
	}

	@Override
	@TruffleBoundary
	public int hashCode() {
		return 37 * label.hashCode() + 9534;
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj instanceof Label && ((Label) obj).label.equals(this.label));
	}

}
