package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.objects.StringV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class String_1 extends Exp {
	public final static String CONSTRUCTOR = "String";

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

	@Override
	public Object evaluate(TigerEnv env) {
		return new StringV(_1.substring(1, _1.length() - 1));
	}

	private String_1(String _1) {
		this._1 = _1;
	}

	private final String _1;

	public String get_1() {
		return _1;
	}

	public static String_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new String_1(Tools.asJavaString(term.getSubterm(0)));
	}
}