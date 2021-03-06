package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.EvaluatableTigerTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class Var extends EvaluatableTigerTerm {
	public static Var create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Var", 1)) {
			return Var_1.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}

	public abstract String getId();
}