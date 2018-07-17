package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.IEvaluatableTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class Type implements IEvaluatableTerm {
	public static Type create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "ArrayTy", 1)) {
			return ArrayTy_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "RecordTy", 1)) {
			return RecordTy_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__TypeId2Type__", 1)) {
			return __TypeId2Type___1.create(term);
		}
		try {
			return new __TypeId2Type___1(TypeId.create(term));
		} catch (IllegalStateException n_25) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}
}