package org.metaborg.lang.tiger.ninterpreter.terms;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class ARRAY_2 extends Ty {
	public final static String CONSTRUCTOR = "ARRAY";

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

	private ARRAY_2(Ty _1, Scope _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final Ty _1;

	private final Scope _2;

	public Ty get_1() {
		return _1;
	}

	public Scope get_2() {
		return _2;
	}

	public static ARRAY_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new ARRAY_2(Ty.create(term.getSubterm(0)), Scope.create(term.getSubterm(1)));
	}
}