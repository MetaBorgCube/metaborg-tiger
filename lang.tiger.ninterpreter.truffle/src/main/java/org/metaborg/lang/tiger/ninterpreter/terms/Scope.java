package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.truffle.EvaluatableTigerTerm;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class Scope extends EvaluatableTigerTerm {
	public static Scope create(IStrategoTerm term) {
		assert term != null;
		throw new IllegalStateException("Unsupported term: " + term);
	}
}