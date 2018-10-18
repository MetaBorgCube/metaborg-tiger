package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.truffle.EvaluatableTigerTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class Occ extends EvaluatableTigerTerm {
	public static Occ create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__Id2Occ__", 1)) {
			return __Id2Occ___1.create(term);
		}
		try {
			return new __Id2Occ___1(((IStrategoString) term).stringValue());
		} catch (IllegalStateException m_25) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}

	public abstract String getId();
}