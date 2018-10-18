package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.ArrayV;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Array_3 extends Exp {
	public final static String CONSTRUCTOR = "Array";

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
	public Object evaluate(TigerEnv env) {
		IntV lenV = (IntV) _2.evaluate(env);
		TigerObject defV = (TigerObject) _3.evaluate(env);

		return new ArrayV(lenV.value(), defV, context().heap());
	}

	private Array_3(TypeId _1, Exp _2, Exp _3) {
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
	}

	private final TypeId _1;

	private final Exp _2;

	private final Exp _3;

	public TypeId get_1() {
		return _1;
	}

	public Exp get_2() {
		return _2;
	}

	public Exp get_3() {
		return _3;
	}

	public static Array_3 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Array_3(TypeId.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)),
				Exp.create(term.getSubterm(2)));
	}
}