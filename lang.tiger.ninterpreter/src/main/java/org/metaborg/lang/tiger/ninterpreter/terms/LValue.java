package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.EvaluatableTigerTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class LValue extends EvaluatableTigerTerm {
	public static LValue create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Subscript", 2)) {
			return Subscript_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "FieldVar", 2)) {
			return FieldVar_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__Var2LValue__", 1)) {
			return __Var2LValue___1.create(term);
		}
		try {
			return new __Var2LValue___1(Var.create(term));
		} catch (IllegalStateException j_25) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}
}