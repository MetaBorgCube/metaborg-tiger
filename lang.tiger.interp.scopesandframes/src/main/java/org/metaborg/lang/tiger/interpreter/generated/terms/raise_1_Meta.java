package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class raise_1_Meta implements IApplTerm {
	@Override
	public Class<? extends IApplTerm> getSortClass() {
		return raise_1_Meta.class;
	}

	@TruffleBoundary
	public static raise_1_Meta create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "raise", 1)) {
			return raise_1.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}
}