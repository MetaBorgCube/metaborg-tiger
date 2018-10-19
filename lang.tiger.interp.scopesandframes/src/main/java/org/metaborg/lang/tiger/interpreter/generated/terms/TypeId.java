package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class TypeId implements IApplTerm {
	@Override
	public Class<? extends IApplTerm> getSortClass() {
		return TypeId.class;
	}

	@TruffleBoundary
	public static TypeId create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Tid", 1)) {
			return Tid_1.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}
}