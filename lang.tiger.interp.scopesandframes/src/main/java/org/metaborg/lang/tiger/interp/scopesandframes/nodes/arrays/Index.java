package org.metaborg.lang.tiger.interp.scopesandframes.nodes.arrays;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class Index extends TigerTruffleNode implements IApplTerm {

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

	public abstract int execute(VirtualFrame frame, DynamicObject currentFrame);
}