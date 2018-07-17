package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class __Exp2Index___1 extends Index {
	public final static String CONSTRUCTOR = "__Exp2Index__";

	public final static int ARITY = 1;

	@Override
	public Object evaluate(TigerHeap heap, TigerEnv env) {
		return _1.evaluate(heap, env);
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(_1);
		sb.append(")");
		return sb.toString();
	}

	public __Exp2Index___1(Exp _1) {
		this._1 = _1;
	}

	private final Exp _1;

	public Exp get_1() {
		return _1;
	}

	public static __Exp2Index___1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new __Exp2Index___1(Exp.create(term.getSubterm(0)));
	}
}