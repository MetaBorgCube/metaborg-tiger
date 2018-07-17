package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.TigerUtils;
import org.metaborg.lang.tiger.ninterpreter.objects.RecordV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Record_2 extends Exp {
	public final static String CONSTRUCTOR = "Record";

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
	public Object evaluate(TigerHeap heap, TigerEnv env) {
		TigerEnv fieldEnv = new TigerEnv(null);
		for (InitField initField : _2) {
			InitField_2 init = (InitField_2) initField;
			TigerUtils.bindVar(init.get_1().getId(), (TigerObject) init.get_2().evaluate(heap, env), heap, fieldEnv);
		}
		return new RecordV(fieldEnv);
	}

	private Record_2(TypeId _1, InitField[] _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final TypeId _1;

	private final InitField[] _2;

	public TypeId get_1() {
		return _1;
	}

	public InitField[] get_2() {
		return _2;
	}

	public static Record_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 1);
		InitField[] ts = new InitField[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = InitField.create(tsT.getSubterm(i));
		}
		return new Record_2(TypeId.create(term.getSubterm(0)), ts);
	}
}