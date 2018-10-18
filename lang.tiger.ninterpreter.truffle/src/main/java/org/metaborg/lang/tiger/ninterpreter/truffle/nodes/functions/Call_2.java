package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.functions;

import org.metaborg.lang.tiger.ninterpreter.terms.Occ;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerTypesGen;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.Var;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.Var_1;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public final class Call_2 extends Exp {
	public final static String CONSTRUCTOR = "Call";

	public final static int ARITY = 2;

	private Call_2(Occ _1, Exp[] _2) {
		this.lookupNode = new Var_1(_1);
		this.args = _2;
	}

	@Child
	Var lookupNode;

	@Children
	final Exp[] args;

	@Override
	@ExplodeLoop
	public TigerObject executeGeneric(VirtualFrame frame) {
		CallTarget ct = TigerTypesGen.asClosureV(lookupNode.executeToValue(frame)).getCallTarget();
		Object[] params = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			params[i] = args[i].executeGeneric(frame);
		}
		return (TigerObject) ct.call(params);
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