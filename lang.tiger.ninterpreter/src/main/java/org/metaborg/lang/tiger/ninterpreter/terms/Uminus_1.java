package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.objects.IntV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Uminus_1 extends Exp {
	public final static String CONSTRUCTOR = "Uminus";

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
	public Object evaluate(TigerEnv env) {
		IntV v = (IntV) _1.evaluate(env);
		return new IntV(v.value() * -1);
	}

	private Uminus_1(Exp _1) {
		this._1 = _1;
	}

	private final Exp _1;

	public Exp get_1() {
		return _1;
	}

	public static Uminus_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Uminus_1(Exp.create(term.getSubterm(0)));
	}
}