package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.objects.UnitV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Assign_2 extends Exp {
	public final static String CONSTRUCTOR = "Assign";

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
	public Object evaluate(TigerEnv env) {
		int a = (int) _1.evaluate(env);
		TigerObject v = (TigerObject) _2.evaluate(env);
		context().heap().write(a, v);
		return new UnitV();
	}

	private Assign_2(LValue _1, Exp _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final LValue _1;

	private final Exp _2;

	public LValue get_1() {
		return _1;
	}

	public Exp get_2() {
		return _2;
	}

	public static Assign_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Assign_2(LValue.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)));
	}
}