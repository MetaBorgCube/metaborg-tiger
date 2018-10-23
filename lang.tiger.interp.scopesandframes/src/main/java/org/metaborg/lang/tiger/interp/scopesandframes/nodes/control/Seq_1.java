package org.metaborg.lang.tiger.interp.scopesandframes.nodes.control;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.object.DynamicObject;

public final class Seq_1 extends Exp {
	public final static String CONSTRUCTOR = "Seq";

	public final static int ARITY = 1;

	@Children
	private final Exp[] exps;

	public Seq_1(Exp[] _1) {
		this(_1, null);
	}

	private Seq_1(Exp[] _1, IStrategoTerm strategoTerm) {
		this.exps = _1;
		this.strategoTerm = strategoTerm;
	}

	@Override
	@ExplodeLoop
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		V result = UnitV_0.SINGLETON;
		for (int i = 0; i < exps.length; i++) {
			result = exps[i].executeGeneric(frame, currentFrame);
		}
		return result;
	}

	@TruffleBoundary
	public static Seq_1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoTerm[] expTerms = Tools.listAt(term, 0).getAllSubterms();
		Exp[] exps = new Exp[expTerms.length];
		for (int i = 0; i < exps.length; i++) {
			exps[i] = Exp.create(expTerms[i]);
		}
		return new Seq_1(exps, term);
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
		sb.append(exps);
		sb.append(")");
		return sb.toString();
	}

}