package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class FunDec implements IApplTerm {
	@Override
	public Class<? extends IApplTerm> getSortClass() {
		return FunDec.class;
	}

	@TruffleBoundary
	public static FunDec create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "ProcDec", 3)) {
			return ProcDec_3.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "FunDec", 4)) {
			return FunDec_4.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}
}