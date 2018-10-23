package org.metaborg.lang.tiger.interp.scopesandframes.nodes.numbers;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.IntV_1;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Lt_2 extends Exp {
	public final static String CONSTRUCTOR = "Lt";

	public final static int ARITY = 2;

	public Lt_2(Exp _1, Exp _2) {
		this(_1, _2, null);
	}

	private Lt_2(Exp _1, Exp _2, IStrategoTerm strategoTerm) {
		this.left = _1;
		this.right = _2;
		this.strategoTerm = strategoTerm;
	}

	@Child
	private Exp left;

	@Child
	private Exp right;

	@Override
	public IntV_1 executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		IntV_1 left = TigerTypesGen.asIntV_1(this.left.executeGeneric(frame, currentFrame));
		IntV_1 right = TigerTypesGen.asIntV_1(this.right.executeGeneric(frame, currentFrame));
		if (left.get_1() < right.get_1()) {
			return new IntV_1(1);
		} else {
			return new IntV_1(0);
		}
	}

	@TruffleBoundary
	public static Lt_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Lt_2(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), term);
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
		sb.append(left);
		sb.append(", ");
		sb.append(right);
		sb.append(")");
		return sb.toString();
	}

}