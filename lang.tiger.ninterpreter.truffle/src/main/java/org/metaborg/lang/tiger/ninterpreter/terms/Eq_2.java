package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.ArrayV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.NilV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.RecordV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.StringV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Eq_2 extends Exp {
	public final static String CONSTRUCTOR = "Eq";

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
		IntV t = new IntV(1);
		IntV f = new IntV(0);
		TigerObject v1 = (TigerObject) _1.evaluate(env);
		TigerObject v2 = (TigerObject) _2.evaluate(env);
		if (v1 instanceof IntV) {
			if (v2 instanceof IntV) {
				return ((IntV) v1).value() == ((IntV) v2).value() ? t : f;
			}
		}
		if (v1 instanceof ArrayV) {
			if (v2 instanceof ArrayV) {
				return ((ArrayV) v1).idx() == ((ArrayV) v2).idx() ? t : f;
			}
		}
		if (v1 instanceof NilV) {
			if (v2 instanceof NilV) {
				return t;
			}
			if (v2 instanceof RecordV) {
				return f;
			}
		}
		if (v1 instanceof RecordV) {
			if (v2 instanceof NilV) {
				return f;
			}
			if (v2 instanceof RecordV) {
				return ((RecordV) v1).idx() == ((RecordV) v2).idx() ? t : f;
			}
		}
		if (v1 instanceof StringV) {
			if (v2 instanceof StringV) {
				return ((StringV) v1).s().equals(((StringV) v2).s()) ? t : f;
			}
		}
		throw new RuntimeException("Type error. Can't compare: " + v1 + " and " + v2);
	}

	public Eq_2(Exp _1, Exp _2) {
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

	public static Eq_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Eq_2(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)));
	}
}