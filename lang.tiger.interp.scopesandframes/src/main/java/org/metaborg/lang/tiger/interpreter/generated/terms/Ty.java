package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.TypeId;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.records.RECORD_1;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class Ty implements IApplTerm {

	@TruffleBoundary
	public static Ty create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "ArrayTy", 1)) {
			return ArrayTy_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "RecordTy", 1)) {
			return RecordTy_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__TypeId2Type__", 1)) {
			return __TypeId2Type___1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "INT", 0)) {
			return INT_0.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "STRING", 0)) {
			return STRING_0.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "NIL", 0)) {
			return NIL_0.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "UNIT", 0)) {
			return UNIT_0.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "NAME", 1)) {
			return NAME_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "RECORD", 1)) {
			return RECORD_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "ARRAY", 2)) {
			return ARRAY_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "FUN", 2)) {
			return FUN_2.create(term);
		}
		try {
			return new __TypeId2Type___1(TypeId.create(term));
		} catch (IllegalStateException m_96) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}
}