package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class FunDecs_1 extends Dec {
	public final static String CONSTRUCTOR = "FunDecs";

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
		for (FunDec fdec : _1) {
			fdec.evaluate(e);
		}

		return e;
	}

	private FunDecs_1(FunDec[] _1) {
		this._1 = _1;
	}

	private final FunDec[] _1;

	public FunDec[] get_1() {
		return _1;
	}

	public static FunDecs_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 0);
		FunDec[] ts = new FunDec[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = FunDec.create(tsT.getSubterm(i));
		}
		return new FunDecs_1(ts);
	}
}