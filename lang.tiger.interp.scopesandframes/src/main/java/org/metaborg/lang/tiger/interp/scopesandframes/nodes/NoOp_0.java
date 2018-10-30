package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class NoOp_0 extends Exp {
	public final static String CONSTRUCTOR = "NoOp";

	public final static int ARITY = 0;

	public NoOp_0() {
		this(null);
	}

	private NoOp_0(IStrategoTerm strategoTerm) {
		this.strategoTerm = strategoTerm;
	}
	
	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		return UnitV_0.SINGLETON;
	}

	@TruffleBoundary
	public static NoOp_0 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new NoOp_0(term);
	}

	private final IStrategoTerm strategoTerm;

	@Override
	public int size() {
		return ARITY;
	}

	@Override
	public boolean hasStrategoTerm() {
		return strategoTerm != null;
	}

	@Override
	public IStrategoTerm getStrategoTerm() {
		return strategoTerm;
	}

	@TruffleBoundary
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(")");
		return sb.toString();
	}

}