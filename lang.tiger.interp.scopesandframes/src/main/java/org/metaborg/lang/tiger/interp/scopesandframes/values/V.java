package org.metaborg.lang.tiger.interp.scopesandframes.values;

import org.metaborg.lang.tiger.interpreter.generated.terms.NilV_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.RecordV_1;
import org.metaborg.lang.tiger.interpreter.generated.terms.StringV_1;
import org.metaborg.lang.tiger.interpreter.generated.terms.UndefV_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class V implements IApplTerm {

	@TruffleBoundary
	public static V create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "ArrayV", 1)) {
			return ArrayV_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "NilV", 0)) {
			return NilV_0.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "RecordV", 1)) {
			return RecordV_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "StringV", 1)) {
			return StringV_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "IntV", 1)) {
			return IntV_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "UnitV", 0)) {
			return UnitV_0.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "UndefV", 0)) {
			return UndefV_0.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}
}