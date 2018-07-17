package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class TypeDecs_1 extends Dec {
	public final static String CONSTRUCTOR = "TypeDecs";

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
	public Object evaluate(TigerHeap heap, TigerEnv env) {
		return env;
	}

	private TypeDecs_1(TypeDec[] _1) {
		this._1 = _1;
	}

	private final TypeDec[] _1;

	public TypeDec[] get_1() {
		return _1;
	}

	public static TypeDecs_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 0);
		TypeDec[] ts = new TypeDec[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = TypeDec.create(tsT.getSubterm(i));
		}
		return new TypeDecs_1(ts);
	}
}