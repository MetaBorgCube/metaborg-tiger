package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class __LValue2Exp___1 extends Exp {
	public final static String CONSTRUCTOR = "__LValue2Exp__";

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
	public TigerObject evaluate(TigerHeap h, TigerEnv e) {
		int a = (int) _1.evaluate(h, e);
		return h.read(a);
	}

	public __LValue2Exp___1(LValue _1) {
		this._1 = _1;
	}

	private final LValue _1;

	public LValue get_1() {
		return _1;
	}

	public static __LValue2Exp___1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new __LValue2Exp___1(LValue.create(term.getSubterm(0)));
	}
}