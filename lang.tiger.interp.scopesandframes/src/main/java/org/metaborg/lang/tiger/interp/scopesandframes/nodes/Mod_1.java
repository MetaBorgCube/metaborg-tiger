package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;

public final class Mod_1 extends Module {
	public final static String CONSTRUCTOR = "Mod";

	public final static int ARITY = 1;

	public Mod_1(Exp _1) {
		this(_1, null);
	}

	private Mod_1(Exp _1, IStrategoTerm strategoTerm) {
		this._1 = _1;
		this.strategoTerm = strategoTerm;
		this.framedCreationNode = new Framed
	}

	@Child
	private Exp _1;
	
	@Child
	private Framed framedCreationNode;

	@Override
	public Object executeGeneric(VirtualFrame frame) {
		// framed -->		frame(scopeOfTerm(t), links)
		// @formatter:off
		/*
		  m@Mod(e) -init-> vv
		  where
		    framed(m, []) --> F;
		    F |- stdLib(m) --> _;
		    F |- e --> vv
		*/
		// @formatter:on
		return _1.executeGeneric(frame);
	}
	
	@TruffleBoundary
	public static Mod_1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Mod_1(Exp.create(term.getSubterm(0)), term);
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
		sb.append(_1);
		sb.append(")");
		return sb.toString();
	}

}