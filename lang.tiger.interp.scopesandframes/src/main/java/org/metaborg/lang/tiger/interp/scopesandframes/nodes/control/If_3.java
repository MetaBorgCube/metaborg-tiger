package org.metaborg.lang.tiger.interp.scopesandframes.nodes.control;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interpreter.generated.terms.IntV_1;
import org.metaborg.lang.tiger.interpreter.generated.terms.V;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.profiles.ConditionProfile;

public final class If_3 extends Exp {
	public final static String CONSTRUCTOR = "If";

	public final static int ARITY = 3;

	@Child
	private Exp condition;

	@Child
	private Exp thenBranch;

	@Child
	private Exp elseBranch;

	private final ConditionProfile conditionProfile = ConditionProfile.createCountingProfile();

	public If_3(Exp _1, Exp _2, Exp _3) {
		this(_1, _2, _3, null);
	}

	private If_3(Exp _1, Exp _2, Exp _3, IStrategoTerm strategoTerm) {
		this.condition = _1;
		this.thenBranch = _2;
		this.elseBranch = _3;
		this.strategoTerm = strategoTerm;
	}

	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		if (conditionProfile.profile(evaluateCondition(frame, currentFrame))) {
			return thenBranch.executeGeneric(frame, currentFrame);
		} else {
			return elseBranch.executeGeneric(frame, currentFrame);
		}
	}

	private boolean evaluateCondition(VirtualFrame frame, DynamicObject currentFrame) {
		IntV_1 i = TigerTypesGen.asIntV_1(condition.executeGeneric(frame, currentFrame));
		return i.get_1() == 0 ? false : true;
	}

	@TruffleBoundary
	public static If_3 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new If_3(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), Exp.create(term.getSubterm(2)),
				term);
	}

	private final IStrategoTerm strategoTerm;

	@Override
	public int size() {
		return ARITY;
	}

	@Override
	public boolean hasStrategoTerm() {
		return strategoTerm != null;
	}

	@Override
	public IStrategoTerm getStrategoTerm() {
		return strategoTerm;
	}

	@TruffleBoundary
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(condition);
		sb.append(", ");
		sb.append(thenBranch);
		sb.append(", ");
		sb.append(elseBranch);
		sb.append(")");
		return sb.toString();
	}

}