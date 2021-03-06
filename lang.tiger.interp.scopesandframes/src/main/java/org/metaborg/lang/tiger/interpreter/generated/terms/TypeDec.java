package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class TypeDec extends TigerTruffleNode implements IApplTerm {

	@TruffleBoundary
	public static TypeDec create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "TypeDec", 2)) {
			return TypeDec_2.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}

	public abstract void execute(VirtualFrame frame, DynamicObject f, DynamicObject f_outer);
}