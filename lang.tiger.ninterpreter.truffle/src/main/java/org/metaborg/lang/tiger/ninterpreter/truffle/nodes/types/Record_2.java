package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types;

import org.metaborg.lang.tiger.ninterpreter.terms.InitField;
import org.metaborg.lang.tiger.ninterpreter.terms.TypeId;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Record_2 extends Exp {
	public final static String CONSTRUCTOR = "Record";

	public final static int ARITY = 2;

	private Record_2(TypeId _1, InitField[] _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final TypeId _1;

	private final InitField[] _2;

	// @Override
	// public Object evaluate(TigerEnv env) {
	// TigerEnv fieldEnv = new TigerEnv(null);
	// for (InitField initField : _2) {
	// InitField_2 init = (InitField_2) initField;
	// TigerUtils.bindVar(init.get_1().getId(), (TigerObject)
	// init.get_2().evaluate(env), context().heap(),
	// fieldEnv);
	// }
	// return new RecordV(fieldEnv);
	// }

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