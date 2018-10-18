package org.metaborg.lang.tiger.ninterpreter.terms;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class NIL_0 extends Ty {
	public final static String CONSTRUCTOR = "NIL";

	public final static int ARITY = 0;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(")");
		return sb.toString();
	}

	public final static NIL_0 SINGLETON = new NIL_0();

	private NIL_0() {
	}

	public static NIL_0 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new NIL_0();
	}
}