package org.metaborg.lang.tiger.ninterpreter.terms;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class RecordTy_1 extends Type {
	public final static String CONSTRUCTOR = "RecordTy";

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

	private RecordTy_1(Field[] _1) {
		this._1 = _1;
	}

	private final Field[] _1;

	public Field[] get_1() {
		return _1;
	}

	public static RecordTy_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 0);
		Field[] ts = new Field[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = Field.create(tsT.getSubterm(i));
		}
		return new RecordTy_1(ts);
	}
}