package org.metaborg.lang.tiger.ninterpreter.terms;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class FUN_2 extends Ty {
	public final static String CONSTRUCTOR = "FUN";

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

	private FUN_2(Ty[] _1, Ty _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final Ty[] _1;

	private final Ty _2;

	public Ty[] get_1() {
		return _1;
	}

	public Ty get_2() {
		return _2;
	}

	public static FUN_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 0);
		Ty[] ts = new Ty[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = Ty.create(tsT.getSubterm(i));
		}
		return new FUN_2(ts, Ty.create(term.getSubterm(1)));
	}
}