package org.metaborg.lang.tiger.interp.scopesandframes.nodes.arrays;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class __Exp2Index___1 extends Index {
	public final static String CONSTRUCTOR = "__Exp2Index__";

	public final static int ARITY = 1;

	public __Exp2Index___1(Exp _1) {
		this(_1, null);
	}

	private __Exp2Index___1(Exp _1, IStrategoTerm strategoTerm) {
		this._1 = _1;
		this.strategoTerm = strategoTerm;
	}

	@Child
	private Exp _1;

	@Override
	public int execute(VirtualFrame frame, DynamicObject currentFrame) {
		return TigerTypesGen.asIntV_1(_1.executeGeneric(frame, currentFrame)).get_1();
	}

	@TruffleBoundary
	public static __Exp2Index___1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new __Exp2Index___1(Exp.create(term.getSubterm(0)), term);
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
		sb.append(")");
		return sb.toString();
	}

}