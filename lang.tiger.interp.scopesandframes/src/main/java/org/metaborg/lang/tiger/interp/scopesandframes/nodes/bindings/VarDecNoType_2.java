package org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class VarDecNoType_2 extends Dec {
	public final static String CONSTRUCTOR = "VarDecNoType";

	public final static int ARITY = 2;

	public VarDecNoType_2(Occ _1, Exp _2) {
		this(_1, _2, null);
	}

	private VarDecNoType_2(Occ _1, Exp _2, IStrategoTerm strategoTerm) {
		this.wrappedVarDec = new VarDec_3(_1, null, _2, strategoTerm);
		this.strategoTerm = strategoTerm;
	}

	@Child
	private VarDec_3 wrappedVarDec;

	@Override
	public void execute(VirtualFrame frame, DynamicObject f, DynamicObject f_outer) {
		wrappedVarDec.execute(frame, f, f_outer);
	}

	@TruffleBoundary
	public static VarDecNoType_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new VarDecNoType_2(Occ.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), term);
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