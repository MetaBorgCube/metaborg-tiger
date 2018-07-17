package org.metaborg.lang.tiger.ninterpreter.terms;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class ArrayTy_1 extends Type {
	public final static String CONSTRUCTOR = "ArrayTy";

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

	private ArrayTy_1(TypeId _1) {
		this._1 = _1;
	}

	private final TypeId _1;

	public TypeId get_1() {
		return _1;
	}

	public static ArrayTy_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new ArrayTy_1(TypeId.create(term.getSubterm(0)));
	}
}