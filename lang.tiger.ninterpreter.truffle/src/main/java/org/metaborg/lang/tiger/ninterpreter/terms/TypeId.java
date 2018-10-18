package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.truffle.EvaluatableTigerTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class TypeId extends EvaluatableTigerTerm {
	public static TypeId create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Tid", 1)) {
			return Tid_1.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}
}