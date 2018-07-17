package org.metaborg.lang.tiger.ninterpreter.terms;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class FArg_2 extends FArg {
	public final static String CONSTRUCTOR = "FArg";

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

	private FArg_2(Occ _1, Type _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final Occ _1;

	private final Type _2;

	public Occ get_1() {
		return _1;
	}

	public Type get_2() {
		return _2;
	}

	@Override
	public String getId() {
		return _1.getId();
	}

	public static FArg_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new FArg_2(Occ.create(term.getSubterm(0)), Type.create(term.getSubterm(1)));
	}
}