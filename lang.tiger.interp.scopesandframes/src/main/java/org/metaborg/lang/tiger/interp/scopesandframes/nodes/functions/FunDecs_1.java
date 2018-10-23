package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings.Dec;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.object.DynamicObject;

public final class FunDecs_1 extends Dec {
	public final static String CONSTRUCTOR = "FunDecs";

	public final static int ARITY = 1;

	@Children
	private final FunDec[] funDecs;

	public FunDecs_1(FunDec[] _1) {
		this(_1, null);
	}

	private FunDecs_1(FunDec[] _1, IStrategoTerm strategoTerm) {
		this.funDecs = _1;
		this.strategoTerm = strategoTerm;
	}

	@Override
	@ExplodeLoop
	public void execute(VirtualFrame frame, DynamicObject f, DynamicObject f_outer) {
		// @formatter:off
//		  Frames1 (F, F') |- Map2-FunDecs([]) --> U()
//				  
//		  Frames1 (F, F') |- Map2-FunDecs([x|xs]) --> U()
//		  where
//		    Frames1 (F, F') |- x --> _;
//		    Frames1 (F, F') |- Map2-FunDecs(xs) --> _
		// @formatter:on
		for (int i = 0; i < funDecs.length; i++) {
			funDecs[i].execute(frame, f, f_outer);
		}
	}

	@TruffleBoundary
	public static FunDecs_1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoTerm[] expTerms = Tools.listAt(term, 0).getAllSubterms();
		FunDec[] fdecs = new FunDec[expTerms.length];
		for (int i = 0; i < fdecs.length; i++) {
			fdecs[i] = FunDec.create(expTerms[i]);
		}
		return new FunDecs_1(fdecs, term);
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
		sb.append(funDecs);
		sb.append(")");
		return sb.toString();
	}

}