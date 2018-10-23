package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions.Call_2NodeGen.CallHelperNodeGen;
import org.metaborg.lang.tiger.interp.scopesandframes.values.FunV;
import org.metaborg.lang.tiger.interp.scopesandframes.values.FunV.CacheableFunV;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameEdgeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLinkNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddrNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Lookup;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrameNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlot;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlotNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class Call_2 extends Exp {
	public final static String CONSTRUCTOR = "Call";

	public final static int ARITY = 2;

	private final Occurrence refOcc;
	@Child
	private Lookup lookupNode;

	@Child
	private GetAtAddr getNode;

	@Child
	private CallHelper actualCallNode;

	public Call_2(Occ _1, Exp[] _2) {
		this(_1, _2, null);
	}

	public Call_2(Occ _1, Exp[] _2, IStrategoTerm strategoTerm) {
		this.refOcc = ((__Occurrence2Occ___1) _1).get_1();
		this.strategoTerm = strategoTerm;
		this.lookupNode = Lookup.create();
		this.getNode = GetAtAddrNodeGen.create();
		this.actualCallNode = CallHelperNodeGen.create(_2);
	}

	protected FunV getFunction(VirtualFrame frame, DynamicObject currentFrame) {
		return (FunV) getNode.execute(frame, lookupNode.execute(frame, currentFrame, refOcc));
	}

	@Specialization
	@ExplodeLoop
	public V executeWithCaching(VirtualFrame frame, DynamicObject currentFrame) {
		// lookup closure
		FunV clos = (FunV) getNode.execute(frame, lookupNode.execute(frame, currentFrame, refOcc));
		return actualCallNode.execute(frame, currentFrame, clos);
	}

	public static abstract class CallHelper extends TigerTruffleNode {
		@Children
		private final Exp[] exps;

		@Child
		private NewFrame newFrame;

		@Child
		private AddFrameLink linkFrames;

		@Child
		private SetFrameSlot setSlot;

		public CallHelper(Exp[] exps) {
			this.exps = exps;
			this.newFrame = NewFrameNodeGen.create();
			this.linkFrames = AddFrameLinkNodeGen.create();
			this.setSlot = SetFrameSlotNodeGen.create();
		}

		public abstract V execute(VirtualFrame frame, DynamicObject currentFrame, FunV funv);

		@Specialization(guards = { "funv.getCacheablePart() == stablePart" }, limit = "1")
		@ExplodeLoop
		public V doCached(VirtualFrame frame, DynamicObject currentFrame, FunV funv,
				@Cached("funv.getCacheablePart()") CacheableFunV stablePart,
				@Cached("stablePart.getFunctionScope()") ScopeIdentifier functionScope,
				@Cached("label()") ALabel linkLabel,
				@Cached("getEdgeIdent(linkLabel, funv.getParentFrame())") FrameEdgeIdentifier edgeIdent,
				@Cached(value = "stablePart.getArguments()", dimensions = 1) Occurrence[] fArgs,
				@Cached("create(stablePart.getCallTarget())") DirectCallNode callNode) {
			// create call frame
			DynamicObject callFrame = newFrame.execute(frame, functionScope);
			// link call frame
			linkFrames.execute(frame, callFrame, new FrameEdgeLink(linkLabel, funv.getParentFrame(), edgeIdent));
			// evaluate and bind parameters
			for (int i = 0; i < exps.length; i++) {
				setSlot.execute(frame, callFrame, fArgs[i], exps[i].executeGeneric(frame, currentFrame));
			}
			// actual call
			return (V) callNode.call(new Object[] { callFrame });
		}

		protected FrameEdgeIdentifier getEdgeIdent(ALabel label, DynamicObject frm) {
			return new FrameEdgeIdentifier(label, FrameLayoutImpl.INSTANCE.getScope(frm));
		}

		protected ALabel label() {
			return P.SINGLETON;
		}
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
		return Call_2NodeGen.create(Occ.create(term.getSubterm(0)), exps, term);
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