package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class Let_2 extends Exp {
	public final static String CONSTRUCTOR = "Let";

	public final static int ARITY = 2;

	@Override
	public Object evaluate(TigerHeap heap, TigerEnv env) {
		for (Dec dec : _1) {
			env = (TigerEnv) dec.evaluate(heap, env);
		}

		Object res = null;

		for (Exp exp : _2) {
			res = exp.evaluate(heap, env);
		}
		return res;
	}

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

	private Let_2(Dec[] _1, Exp[] _2) {
		this._1 = _1;
		this._2 = _2;
	}

	private final Dec[] _1;

	private final Exp[] _2;

	public Dec[] get_1() {
		return _1;
	}

	public Exp[] get_2() {
		return _2;
	}

	public static Let_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList decsT = Tools.listAt(term, 0);
		Dec[] decs = new Dec[decsT.size()];
		for (int i = 0; i < decs.length; i++) {
			decs[i] = Dec.create(decsT.getSubterm(i));
		}
		IStrategoList esT = Tools.listAt(term, 1);
		Exp[] es = new Exp[esT.size()];
		for (int i = 0; i < es.length; i++) {
			es[i] = Exp.create(esT.getSubterm(i));
		}
		return new Let_2(decs, es);
	}
}