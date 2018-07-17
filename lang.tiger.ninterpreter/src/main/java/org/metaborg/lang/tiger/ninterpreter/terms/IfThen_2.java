package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.objects.IntV;
import org.metaborg.lang.tiger.ninterpreter.objects.UnitV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class IfThen_2 extends Exp {
	public final static String CONSTRUCTOR = "IfThen";

	public final static int ARITY = 2;

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

	@Override
	public Object evaluate(TigerHeap heap, TigerEnv env) {
		IntV cv = (IntV) _1.evaluate(heap, env);
		if (cv.value() == 1) {
			return _2.evaluate(heap, env);
		} else {
			return new UnitV();
		}
	}

	private IfThen_2(Exp _1, Exp _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final Exp _1;

	private final Exp _2;

	public Exp get_1() {
		return _1;
	}

	public Exp get_2() {
		return _2;
	}

	public static IfThen_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new IfThen_2(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)));
	}
}