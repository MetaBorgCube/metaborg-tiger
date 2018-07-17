package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.TigerEnv;
import org.metaborg.lang.tiger.ninterpreter.TigerHeap;
import org.metaborg.lang.tiger.ninterpreter.TigerUtils;
import org.metaborg.lang.tiger.ninterpreter.objects.ClosureV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

public final class FunDec_4 extends FunDec {
	public final static String CONSTRUCTOR = "FunDec";

	public final static int ARITY = 4;

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
		sb.append(", ");
		sb.append(_4);
		sb.append(")");
		return sb.toString();
	}

	@Override
	public Object evaluate(TigerHeap h, TigerEnv e) {
		ClosureV clos = new ClosureV(_2, _4, e);
		TigerUtils.writeVar(_1.getId(), clos, h, e);
		return null;
	}

	private FunDec_4(Occ _1, FArg[] _2, Type _3, Exp _4) {
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
		this._4 = _4;
	}

	private final Occ _1;

	private final FArg[] _2;

	private final Type _3;

	private final Exp _4;

	public Occ get_1() {
		return _1;
	}

	public FArg[] get_2() {
		return _2;
	}

	public Type get_3() {
		return _3;
	}

	public Exp get_4() {
		return _4;
	}

	@Override
	public String getId() {
		return _1.getId();
	}

	public static FunDec_4 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 1);
		FArg[] ts = new FArg[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = FArg.create(tsT.getSubterm(i));
		}
		return new FunDec_4(Occ.create(term.getSubterm(0)), ts, Type.create(term.getSubterm(2)),
				Exp.create(term.getSubterm(3)));
	}
}