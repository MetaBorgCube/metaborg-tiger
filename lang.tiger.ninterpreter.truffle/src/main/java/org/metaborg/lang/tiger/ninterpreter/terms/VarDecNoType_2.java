package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerUtils;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.Dec;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class VarDecNoType_2 extends Dec {
	public final static String CONSTRUCTOR = "VarDecNoType";

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
	public TigerEnv evaluate(TigerEnv e) {
		TigerObject res = (TigerObject) _2.evaluate(e);

		return TigerUtils.bindVar(_1.getId(), res, context().heap(), new TigerEnv(e));
	}

	private VarDecNoType_2(Occ _1, Exp _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final Occ _1;

	private final Exp _2;

	public Occ get_1() {
		return _1;
	}

	public Exp get_2() {
		return _2;
	}

	public static VarDecNoType_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new VarDecNoType_2(Occ.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)));
	}
}