package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.control;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.VirtualFrame;

public final class Seq_1 extends Exp {
	public final static String CONSTRUCTOR = "Seq";

	public final static int ARITY = 1;

	@Children
	final Exp[] exps;

	private Seq_1(Exp[] _1) {
		this.exps = _1;
	}

	@Override
	public TigerObject executeGeneric(VirtualFrame frame) {
		TigerObject result = null;
		for (int i = 0; i < exps.length; i++) {
			result = exps[i].executeGeneric(frame);
		}
		return result;
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