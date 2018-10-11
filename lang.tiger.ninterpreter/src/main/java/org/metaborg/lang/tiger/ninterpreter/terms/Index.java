package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.EvaluatableTigerTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class Index extends EvaluatableTigerTerm {
	public static Index create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__Exp2Index__", 1)) {
			return __Exp2Index___1.create(term);
		}
		try {
			return new __Exp2Index___1(Exp.create(term));
		} catch (IllegalStateException c_25) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}
}