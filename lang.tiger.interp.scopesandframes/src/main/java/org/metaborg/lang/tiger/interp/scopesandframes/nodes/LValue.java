package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings.__Var2LValue___1;
import org.metaborg.lang.tiger.interpreter.generated.terms.FieldVar_2;
import org.metaborg.lang.tiger.interpreter.generated.terms.Subscript_2;
import org.metaborg.lang.tiger.interpreter.generated.terms.Var;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class LValue extends TigerTruffleNode implements IApplTerm {

	@TruffleBoundary
	public static LValue create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Subscript", 2)) {
			return Subscript_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "FieldVar", 2)) {
			return FieldVar_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__Var2LValue__", 1)) {
			return __Var2LValue___1.create(term);
		}
		try {
			return new __Var2LValue___1(Var.create(term));
		} catch (IllegalStateException h_96) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}
	
	public abstract Addr execute(VirtualFrame frame, DynamicObject currentFrame);
}