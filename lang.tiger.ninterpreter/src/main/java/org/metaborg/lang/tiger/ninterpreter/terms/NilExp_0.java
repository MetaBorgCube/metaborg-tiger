package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.objects.NilV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class NilExp_0 extends Exp {
	public final static String CONSTRUCTOR = "NilExp";

	public final static int ARITY = 0;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(")");
		return sb.toString();
	}

	public final static NilExp_0 SINGLETON = new NilExp_0();

	@Override
	public Object evaluate(TigerHeap heap, TigerEnv env) {
		return new NilV();
	}

	private NilExp_0() {
	}

	public static NilExp_0 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new NilExp_0();
	}
}