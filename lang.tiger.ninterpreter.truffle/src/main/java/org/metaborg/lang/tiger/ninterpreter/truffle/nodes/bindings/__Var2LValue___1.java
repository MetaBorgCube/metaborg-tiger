package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings;

import org.metaborg.lang.tiger.ninterpreter.truffle.objects.TigerMemoryRef;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class __Var2LValue___1 extends LValue {
	public final static String CONSTRUCTOR = "__Var2LValue__";

	public final static int ARITY = 1;

	@Child
	Var _1;

	public __Var2LValue___1(Var _1) {
		this._1 = _1;
	}

	@Specialization
	public TigerMemoryRef evaluateToRef(VirtualFrame frame) {
		return _1.executeToRef(frame);
	}

	public static __Var2LValue___1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return __Var2LValue___1NodeGen.create(Var.create(term.getSubterm(0)));
	}
}