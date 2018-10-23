package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.values.IntV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Neq_2 extends Exp {
	public final static String CONSTRUCTOR = "Neq";

	public final static int ARITY = 2;

	@Child private Eq_2 eqNode;

	public Neq_2(Exp _1, Exp _2) {
		this(_1, _2, null);
	}

	private Neq_2(Exp _1, Exp _2, IStrategoTerm strategoTerm) {
		this.eqNode = new Eq_2(_1, _2, strategoTerm);
		this.strategoTerm = strategoTerm;
	}

	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		int b = TigerTypesGen.asIntV_1(eqNode.executeGeneric(frame, currentFrame)).get_1();
		return new IntV_1(b == 0 ? 1 : 0);
	}
	
	
	@TruffleBoundary
	public static Neq_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Neq_2(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), term);
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


}