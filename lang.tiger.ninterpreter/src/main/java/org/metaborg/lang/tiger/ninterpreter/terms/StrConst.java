package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.EvaluatableTigerTerm;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class StrConst extends EvaluatableTigerTerm {
	public static StrConst create(IStrategoTerm term) {
		assert term != null;
		throw new IllegalStateException("Unsupported term: " + term);
	}
}