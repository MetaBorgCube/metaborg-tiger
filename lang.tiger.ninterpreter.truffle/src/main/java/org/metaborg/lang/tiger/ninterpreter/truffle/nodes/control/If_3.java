package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.control;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerTypesGen;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.IntV;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.profiles.ConditionProfile;

public final class If_3 extends Exp {
	public final static String CONSTRUCTOR = "If";

	public final static int ARITY = 3;

	@Child
	Exp conditionNode;

	@Child
	Exp thenNode;

	@Child
	Exp elseNode;

	public If_3(Exp _1, Exp _2, Exp _3) {
		this.conditionNode = _1;
		this.thenNode = _2;
		this.elseNode = _3;
	}

	private final ConditionProfile condition = ConditionProfile.createCountingProfile();

	@Override
	public TigerObject executeGeneric(VirtualFrame frame) {
		if (condition.profile(evaluateCondition(frame))) {
			return thenNode.executeGeneric(frame);
		} else {
			return elseNode.executeGeneric(frame);
		}
	}

	public boolean evaluateCondition(VirtualFrame frame) {
		try {
			IntV conditionV = TigerTypesGen.expectIntV(conditionNode.executeGeneric(frame));
			return conditionV.value() == 0 ? false : true;
		} catch (UnexpectedResultException e) {
			throw new RuntimeException("Type error", e);
		}
	}

	public static If_3 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new If_3(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), Exp.create(term.getSubterm(2)));
	}
}