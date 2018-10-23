package org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions.FunDecs_1;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class Dec extends TigerTruffleNode implements IApplTerm {

	@TruffleBoundary
	public static Dec create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "FunDecs", 1)) {
			return FunDecs_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "VarDec", 3)) {
			return VarDec_3.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "VarDecNoType", 2)) {
			return VarDecNoType_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "TypeDecs", 1)) {
			return TypeDecs_1.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}
	
	public abstract void execute(VirtualFrame frame, DynamicObject f, DynamicObject f_outer);
}