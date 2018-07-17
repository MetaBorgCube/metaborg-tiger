package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.objects.IntV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class If_3 extends Exp {
	public final static String CONSTRUCTOR = "If";

	public final static int ARITY = 3;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(_1);
		sb.append(", ");
		sb.append(_2);
		sb.append(", ");
		sb.append(_3);
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Object evaluate(TigerHeap heap, TigerEnv env) {
		IntV cv = (IntV) _1.evaluate(heap, env);
		if (cv.value() == 1) {
			return _2.evaluate(heap, env);
		} else {
			return _3.evaluate(heap, env);
		}
	}

	private If_3(Exp _1, Exp _2, Exp _3) {
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
	}

	private final Exp _1;

	private final Exp _2;

	private final Exp _3;

	public Exp get_1() {
		return _1;
	}

	public Exp get_2() {
		return _2;
	}

	public Exp get_3() {
		return _3;
	}

	public static If_3 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new If_3(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), Exp.create(term.getSubterm(2)));
	}
}