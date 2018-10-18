package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerUtils;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.Var;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.BreakException;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.UnitV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class For_4 extends Exp {
	public final static String CONSTRUCTOR = "For";

	public final static int ARITY = 4;

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
		sb.append(", ");
		sb.append(_4);
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Object evaluate(TigerEnv env) {
		IntV firstV = (IntV) _2.evaluate(env);
		IntV lastV = (IntV) _3.evaluate(env);
		TigerEnv forEnv = new TigerEnv(env);
		TigerUtils.bindVar(_1.getId(), new UnitV(), context().heap(), forEnv);
		for (int i = firstV.value(); i <= lastV.value(); i++) {
			TigerUtils.writeVar(_1.getId(), new IntV(i), context().heap(), forEnv);
			try {
				_4.evaluate(forEnv);
			} catch (BreakException brex) {
				break;
			}
		}

		return new UnitV();
	}

	private For_4(Var _1, Exp _2, Exp _3, Exp _4) {
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
		this._4 = _4;
	}

	private final Var _1;

	private final Exp _2;

	private final Exp _3;

	private final Exp _4;

	public Var get_1() {
		return _1;
	}

	public Exp get_2() {
		return _2;
	}

	public Exp get_3() {
		return _3;
	}

	public Exp get_4() {
		return _4;
	}

	public static For_4 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new For_4(Var.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), Exp.create(term.getSubterm(2)),
				Exp.create(term.getSubterm(3)));
	}
}