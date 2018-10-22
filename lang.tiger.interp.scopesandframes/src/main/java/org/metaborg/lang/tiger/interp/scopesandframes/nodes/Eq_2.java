package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interp.scopesandframes.values.IntV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Eq_2 extends Exp {
	public final static String CONSTRUCTOR = "Eq";

	public final static int ARITY = 2;

	public Eq_2(Exp _1, Exp _2) {
		this(_1, _2, null);
	}

	private Eq_2(Exp _1, Exp _2, IStrategoTerm strategoTerm) {
		this._1 = _1;
		this._2 = _2;
		this.helper = EqHelperNodeGen.create();
		this.strategoTerm = strategoTerm;
	}

	@Child
	private Exp _1;

	@Child
	private Exp _2;

	@Child
	private EqHelper helper;

	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		V v1 = _1.executeGeneric(frame, currentFrame);
		V v2 = _2.executeGeneric(frame, currentFrame);
		return helper.executeGeneric(v1, v2) ? new IntV_1(1) : new IntV_1(0);
	}

	@TruffleBoundary
	public static Eq_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Eq_2(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), term);
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
		sb.append(_1);
		sb.append(", ");
		sb.append(_2);
		sb.append(")");
		return sb.toString();
	}

}