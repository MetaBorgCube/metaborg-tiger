package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class Index implements IApplTerm {
	@Override
	public Class<? extends IApplTerm> getSortClass() {
		return Index.class;
	}

	@TruffleBoundary
	public static Index create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__Exp2Index__", 1)) {
			return __Exp2Index___1.create(term);
		}
		try {
			return new __Exp2Index___1(Exp.create(term));
		} catch (IllegalStateException b_96) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}
}