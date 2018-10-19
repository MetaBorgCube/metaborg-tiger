package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class Catching implements IApplTerm {
	@Override
	public Class<? extends IApplTerm> getSortClass() {
		return Catching.class;
	}

	@TruffleBoundary
	public static Catching create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		throw new IllegalStateException("Unsupported term: " + term);
	}
}