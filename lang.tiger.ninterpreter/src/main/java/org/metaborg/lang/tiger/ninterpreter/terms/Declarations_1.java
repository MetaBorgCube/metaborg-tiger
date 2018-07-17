package org.metaborg.lang.tiger.ninterpreter.terms;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Declarations_1 extends Declarations {
	public final static String CONSTRUCTOR = "Declarations";

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

	private Declarations_1(Dec[] _1) {
		this._1 = _1;
	}

	private final Dec[] _1;

	public Dec[] get_1() {
		return _1;
	}

	public static Declarations_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList decsT = Tools.listAt(term, 0);
		Dec[] decs = new Dec[decsT.size()];
		for (int i = 0; i < decs.length; i++) {
			decs[i] = Dec.create(decsT.getSubterm(i));
		}
		return new Declarations_1(decs);
	}
}