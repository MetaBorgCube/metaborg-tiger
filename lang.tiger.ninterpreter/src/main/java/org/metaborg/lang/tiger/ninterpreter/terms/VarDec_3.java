package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.TigerUtils;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class VarDec_3 extends Dec {
	public final static String CONSTRUCTOR = "VarDec";

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
	public TigerEnv evaluate(TigerEnv e) {
		TigerObject res = (TigerObject) _3.evaluate(e);
		return TigerUtils.bindVar(_1.getId(), res, context().heap(), new TigerEnv(e));
	}

	private VarDec_3(Occ _1, Type _2, Exp _3) {
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
	}

	private final Occ _1;

	private final Type _2;

	private final Exp _3;

	public Occ get_1() {
		return _1;
	}

	public Type get_2() {
		return _2;
	}

	public Exp get_3() {
		return _3;
	}

	public static VarDec_3 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new VarDec_3(Occ.create(term.getSubterm(0)), Type.create(term.getSubterm(1)),
				Exp.create(term.getSubterm(2)));
	}
}