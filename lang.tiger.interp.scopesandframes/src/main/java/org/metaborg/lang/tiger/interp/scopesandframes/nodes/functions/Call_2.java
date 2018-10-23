package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.FunV;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLinkNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddrNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Lookup;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrameNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlot;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlotNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
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
	@Child private NewFrame newFrame;
	@Child private AddFrameLink linkFrames;
	@Child private SetFrameSlot setSlot;
	
	public Call_2(Occ _1, Exp[] _2) {
		this(_1, _2, null);
	}

	private Call_2(Occ _1, Exp[] _2, IStrategoTerm strategoTerm) {
		this.refOcc = ((__Occurrence2Occ___1) _1).get_1();
		this.exps = _2;
		this.strategoTerm = strategoTerm;
		this.getNode = GetAtAddrNodeGen.create();
		this.lookupNode = Lookup.create();
		this.newFrame = NewFrameNodeGen.create();
		this.linkFrames = AddFrameLinkNodeGen.create();
		this.setSlot = SetFrameSlotNodeGen.create();
	}

	@Override
	@ExplodeLoop
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		// FIXME: a lot of caching opportunity for what comes out of the closure (FunV): function scope, parent scope, fargs
//		@formatter:off
//		  F F |- Call(f : Occurrence, exps) --> v
//		  where
//		    get(lookup(F, f)) => FunV(F_fun_virt, args, e);
//		    clone(F_fun_virt) => F_call;
//		    Frames1 (F, F_call) |- Zip-Params(exps, args) --> _;
//		    F_call |- e --> v
//		@formatter:on
		// lookup closure
		FunV clos = (FunV) getNode.execute(frame, lookupNode.execute(frame, currentFrame, refOcc));
		// create call frame
		DynamicObject callFrame = newFrame.execute(frame, clos.getFunctionScope());
		// link call frame
		this.linkFrames.execute(frame, callFrame, new FrameEdgeLink(P.SINGLETON, clos.getParentFrame()));
		Occurrence[] fargs = clos.getArguments();
		for(int i = 0; i < exps.length; i++) {
			setSlot.execute(frame, callFrame, fargs[i], exps[i].executeGeneric(frame, currentFrame));
		}
		return (V) clos.getCallTarget().call(callFrame);
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