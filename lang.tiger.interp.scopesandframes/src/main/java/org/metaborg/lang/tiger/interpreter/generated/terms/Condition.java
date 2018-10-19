package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class Condition implements IApplTerm {
	@Override
	public Class<? extends IApplTerm> getSortClass() {
		return Condition.class;
	}

	@TruffleBoundary
	public static Condition create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "IsInt1", 1)) {
			return IsInt1_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "AddrLte", 2)) {
			return AddrLte_2.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}
}