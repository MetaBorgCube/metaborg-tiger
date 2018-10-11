package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.EvaluatableTigerTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class Declarations extends EvaluatableTigerTerm {
	public static Declarations create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Declarations", 1)) {
			return Declarations_1.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}
}