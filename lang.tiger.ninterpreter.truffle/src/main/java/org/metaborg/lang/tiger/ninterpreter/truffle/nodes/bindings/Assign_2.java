package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.TigerMemoryRef;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.VirtualFrame;

public final class Assign_2 extends Exp {
	public final static String CONSTRUCTOR = "Assign";

	public final static int ARITY = 2;

	@Child
	LValue lhs;

	@Child
	Exp rhs;

	private Assign_2(LValue _1, Exp _2) {
		this.lhs = _1;
		this.rhs = _2;
	}

	@Override
	public TigerObject executeGeneric(VirtualFrame frame) {
		TigerMemoryRef ref = lhs.executeToRef(frame);
		TigerObject v = rhs.executeGeneric(frame);
		ref.frame().setObject(ref.slot(), v);
		return null;
	}

	public static Assign_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Assign_2(LValue.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)));
	}
}