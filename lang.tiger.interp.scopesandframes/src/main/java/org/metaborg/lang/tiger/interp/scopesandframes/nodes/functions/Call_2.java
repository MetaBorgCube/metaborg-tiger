package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.FunV;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.CloneFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.CloneFrameNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddrNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Lookup;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.LookupNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.object.DynamicObject;

public final class Call_2 extends Exp {
	public final static String CONSTRUCTOR = "Call";

	public final static int ARITY = 2;

	private final Occurrence refOcc;
	
	@Children
	private final Exp[] exps;

	@Child private GetAtAddr getNode;
	@Child private Lookup lookupNode;
	@Child private CloneFrame cloneNode;
	
	public Call_2(Occ _1, Exp[] _2) {
		this(_1, _2, null);
	}

	private Call_2(Occ _1, Exp[] _2, IStrategoTerm strategoTerm) {
		this.refOcc = ((__Occurrence2Occ___1) _1).get_1();
		this.exps = _2;
		this.strategoTerm = strategoTerm;
		this.getNode = GetAtAddrNodeGen.create();
		this.lookupNode = LookupNodeGen.create();
		this.cloneNode = CloneFrameNodeGen.create();
	}

	@Override
	@ExplodeLoop
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
//		@formatter:off
//		  F F |- Call(f : Occurrence, exps) --> v
//		  where
//		    get(lookup(F, f)) => FunV(F_fun_virt, args, e);
//		    clone(F_fun_virt) => F_call;
//		    Frames1 (F, F_call) |- Zip-Params(exps, args) --> _;
//		    F_call |- e --> v
//		@formatter:on
		FunV clos = (FunV) getNode.execute(frame, lookupNode.execute(frame, currentFrame, refOcc));
		Object[] params = new Object[exps.length];
		for(int i = 0; i < params.length; i++) {
			params[i] = exps[i].executeGeneric(frame, currentFrame);
		}
		return (V) clos.getCallTarget().call(clos.getParentFrame(), params);
	}
	
	
	
	
	@TruffleBoundary
	public static Call_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoTerm[] expTerms = Tools.listAt(term, 1).getAllSubterms();
		Exp[] exps = new Exp[expTerms.length];
		for (int i = 0; i < exps.length; i++) {
			exps[i] = Exp.create(expTerms[i]);
		}
		return new Call_2(Occ.create(term.getSubterm(0)), exps, term);
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
		sb.append(refOcc);
		sb.append(", ");
		sb.append(exps);
		sb.append(")");
		return sb.toString();
	}

}