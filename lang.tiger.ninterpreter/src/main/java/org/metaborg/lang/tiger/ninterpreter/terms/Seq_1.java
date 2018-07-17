package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.objects.UnitV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Seq_1 extends Exp {
	public final static String CONSTRUCTOR = "Seq";

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
		TigerObject res = new UnitV();
		for (Exp exp : _1) {
			res = (TigerObject) exp.evaluate(heap, env);
		}
		return res;
	}

	private Seq_1(Exp[] _1) {
		this._1 = _1;
	}

	private final Exp[] _1;

	public Exp[] get_1() {
		return _1;
	}

	public static Seq_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 0);
		Exp[] ts = new Exp[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = Exp.create(tsT.getSubterm(i));
		}
		return new Seq_1(ts);
	}
}