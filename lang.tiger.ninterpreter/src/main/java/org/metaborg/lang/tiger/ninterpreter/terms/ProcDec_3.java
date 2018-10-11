package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerUtils;
import org.metaborg.lang.tiger.ninterpreter.objects.ClosureV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class ProcDec_3 extends FunDec {
	public final static String CONSTRUCTOR = "ProcDec";

	public final static int ARITY = 3;

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(_1);
		sb.append(", ");
		sb.append(_2);
		sb.append(", ");
		sb.append(_3);
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Object evaluate(TigerEnv e) {
		ClosureV clos = new ClosureV(_2, _3, e);
		TigerUtils.writeVar(_1.getId(), clos, context().heap(), e);
		return null;
	}

	private ProcDec_3(Occ _1, FArg[] _2, Exp _3) {
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
	}

	private final Occ _1;

	private final FArg[] _2;

	private final Exp _3;

	public Occ get_1() {
		return _1;
	}

	@Override
	public String getId() {
		return _1.getId();
	}

	public FArg[] get_2() {
		return _2;
	}

	public Exp get_3() {
		return _3;
	}

	public static ProcDec_3 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 1);
		FArg[] ts = new FArg[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = FArg.create(tsT.getSubterm(i));
		}
		return new ProcDec_3(Occ.create(term.getSubterm(0)), ts, Exp.create(term.getSubterm(2)));
	}
}