package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class __Var2LValue___1 extends LValue {
	public final static String CONSTRUCTOR = "__Var2LValue__";

	public final static int ARITY = 1;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(_1);
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Integer evaluate(TigerHeap h, TigerEnv e) {
		return e.lookup(_1.getId());
	}

	public __Var2LValue___1(Var _1) {
		this._1 = _1;
	}

	private final Var _1;

	public Var get_1() {
		return _1;
	}

	public static __Var2LValue___1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new __Var2LValue___1(Var.create(term.getSubterm(0)));
	}
}