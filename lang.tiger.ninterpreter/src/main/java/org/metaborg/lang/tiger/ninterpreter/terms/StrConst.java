package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.IEvaluatableTerm;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class StrConst implements IEvaluatableTerm {
	public static StrConst create(IStrategoTerm term) {
		assert term != null;
		throw new IllegalStateException("Unsupported term: " + term);
	}
}