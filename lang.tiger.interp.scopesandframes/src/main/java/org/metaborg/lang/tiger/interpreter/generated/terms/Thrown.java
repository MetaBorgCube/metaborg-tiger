package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class Thrown implements IApplTerm {
	@Override
	public Class<? extends IApplTerm> getSortClass() {
		return Thrown.class;
	}

	@TruffleBoundary
	public static Thrown create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		throw new IllegalStateException("Unsupported term: " + term);
	}
}