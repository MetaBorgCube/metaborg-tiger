package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.objects.RecordV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class FieldVar_2 extends LValue {
	public final static String CONSTRUCTOR = "FieldVar";

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

	@Override
	public Object evaluate(TigerEnv env) {
		int a = (int) _1.evaluate(env);
		RecordV rec = (RecordV) context().heap().read(a);
		return rec.get(_2.getId());
	}

	private FieldVar_2(LValue _1, Occ _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final LValue _1;

	private final Occ _2;

	public LValue get_1() {
		return _1;
	}

	public Occ get_2() {
		return _2;
	}

	public static FieldVar_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new FieldVar_2(LValue.create(term.getSubterm(0)), Occ.create(term.getSubterm(1)));
	}
}