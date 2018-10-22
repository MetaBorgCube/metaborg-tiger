package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interpreter.generated.terms.Var;
import org.metaborg.lang.tiger.interpreter.generated.terms.Var_1;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Lookup;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.LookupNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class __Var2LValue___1 extends LValue {
	public final static String CONSTRUCTOR = "__Var2LValue__";

	public final static int ARITY = 1;

	private final Occurrence refOcc;

	@Child
	private Lookup lookupNode;

	public __Var2LValue___1(Var _1) {
		this(_1, null);
	}

	private __Var2LValue___1(Var _1, IStrategoTerm strategoTerm) {
		this.refOcc = ((__Occurrence2Occ___1) ((Var_1) _1).get_1()).get_1();
		this.strategoTerm = strategoTerm;
		this.lookupNode = LookupNodeGen.create();
	}

	@Override
	public Addr execute(VirtualFrame frame, DynamicObject currentFrame) {
		// F F |- __Var2LValue__(Var(x : Occurrence)) --> lookup(F, x)
		return lookupNode.execute(frame, currentFrame, refOcc);
	}

	@TruffleBoundary
	public static __Var2LValue___1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new __Var2LValue___1(Var.create(term.getSubterm(0)), term);
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