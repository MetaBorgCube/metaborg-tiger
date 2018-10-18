package org.metaborg.lang.tiger.ninterpreter.terms;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class __Id2Occ___1 extends Occ {
	public final static String CONSTRUCTOR = "__Id2Occ__";

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

	public __Id2Occ___1(String _1) {
		this._1 = _1;
	}

	private final String _1;

	public String get_1() {
		return _1;
	}

	@Override
	public String getId() {
		return get_1();
	}

	public static __Id2Occ___1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new __Id2Occ___1(Tools.asJavaString(term.getSubterm(0)));
	}
}