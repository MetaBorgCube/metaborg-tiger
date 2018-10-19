package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoString;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class Occ implements IApplTerm {
	@Override
	public Class<? extends IApplTerm> getSortClass() {
		return Occ.class;
	}

	@TruffleBoundary
	public static Occ create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__Occurrence2Occ__", 1)) {
			return __Occurrence2Occ___1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__Id2Occ__", 1)) {
			return __Id2Occ___1.create(term);
		}
		try {
			return new __Occurrence2Occ___1(org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence.create(term));
		} catch (IllegalStateException l_96) {
			try {
				return new __Id2Occ___1(((IStrategoString) term).stringValue());
			} catch (IllegalStateException k_96) {
				throw new IllegalStateException("Unsupported term: " + term);
			}
		}
	}
}