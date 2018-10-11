package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.TigerUtils;
import org.metaborg.lang.tiger.ninterpreter.objects.ClosureV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Call_2 extends Exp {
	public final static String CONSTRUCTOR = "Call";

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
		ClosureV clos = (ClosureV) TigerUtils.readVar(_1.getId(), context().heap(), env);
		FArg[] fparams = clos.args();
		TigerEnv callEnv = new TigerEnv(clos.env());
		for (int i = 0; i < _2.length; i++) {
			TigerUtils.bindVar(fparams[i].getId(), (TigerObject) _2[i].evaluate(env), context().heap(), callEnv);
		}

		return clos.body().evaluate(callEnv);
	}

	private Call_2(Occ _1, Exp[] _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final Occ _1;

	private final Exp[] _2;

	public Occ get_1() {
		return _1;
	}

	public Exp[] get_2() {
		return _2;
	}

	public static Call_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList esT = Tools.listAt(term, 1);
		Exp[] es = new Exp[esT.size()];
		for (int i = 0; i < es.length; i++) {
			es[i] = Exp.create(esT.getSubterm(i));
		}
		return new Call_2(Occ.create(term.getSubterm(0)), es);
	}
}