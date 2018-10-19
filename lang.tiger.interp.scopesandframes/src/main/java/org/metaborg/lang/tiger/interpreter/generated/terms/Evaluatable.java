package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class Evaluatable implements IApplTerm {
	@Override
	public Class<? extends IApplTerm> getSortClass() {
		return Evaluatable.class;
	}

	@TruffleBoundary
	public static Evaluatable create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "handle", 2)) {
			return handle_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "handle", 3)) {
			return handle_3.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "whileLoop", 3)) {
			return whileLoop_3.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "WhileBody", 1)) {
			return WhileBody_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "IncAddr", 1)) {
			return IncAddr_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__Exp2Evaluatable__", 1)) {
			return __Exp2Evaluatable___1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "EvSeq", 2)) {
			return EvSeq_2.create(term);
		}
		try {
			return new __Exp2Evaluatable___1(Exp.create(term));
		} catch (IllegalStateException y_95) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}
}