package org.metaborg.lang.tiger.ninterpreter.terms;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Field_2 extends Field {
	public final static String CONSTRUCTOR = "Field";

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

	private Field_2(Occ _1, TypeId _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final Occ _1;

	private final TypeId _2;

	public Occ get_1() {
		return _1;
	}

	public TypeId get_2() {
		return _2;
	}

	public static Field_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Field_2(Occ.create(term.getSubterm(0)), TypeId.create(term.getSubterm(1)));
	}
}