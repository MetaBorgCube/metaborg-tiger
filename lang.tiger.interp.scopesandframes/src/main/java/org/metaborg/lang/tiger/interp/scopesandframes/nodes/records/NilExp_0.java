package org.metaborg.lang.tiger.interp.scopesandframes.nodes.records;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.NilV_0;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class NilExp_0 extends Exp {
	public final static String CONSTRUCTOR = "NilExp";

	public final static int ARITY = 0;

	public final static NilExp_0 SINGLETON = new NilExp_0();

	private NilExp_0() {
		this(null);
	}

	private NilExp_0(IStrategoTerm strategoTerm) {
		this.strategoTerm = strategoTerm;
	}
	
	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		return NilV_0.SINGLETON;
	}

	@TruffleBoundary
	public static NilExp_0 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new NilExp_0(term);
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