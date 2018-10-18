package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.truffle.EvaluatableTigerTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class FArg extends EvaluatableTigerTerm {
	public static FArg create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "FArg", 2)) {
			return FArg_2.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}

	public abstract String getId();
}