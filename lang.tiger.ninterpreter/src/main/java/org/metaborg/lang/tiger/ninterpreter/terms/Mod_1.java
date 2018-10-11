package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.StdLib;
import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Mod_1 extends Module {
	public final static String CONSTRUCTOR = "Mod";

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
	public Object evaluate(TigerEnv e) {
		new StdLib().evaluate(e);
		return _1.evaluate(e);
	}

	private Mod_1(Exp _1) {
		this._1 = _1;
	}

	private final Exp _1;

	public Exp get_1() {
		return _1;
	}

	public static Mod_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Mod_1(Exp.create(term.getSubterm(0)));
	}
}