package org.metaborg.lang.tiger.interp.scopesandframes.nodes.control;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameUtils;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;
import org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.LoopNode;
import com.oracle.truffle.api.nodes.RepeatingNode;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.profiles.BranchProfile;

public final class While_2 extends Exp {
	public final static String CONSTRUCTOR = "While";

	public final static int ARITY = 2;

	private final FrameDescriptor loopDescriptor;
	private final FrameSlot currentFrameSlot;

	@Child
	private LoopNode loopingNode;

	public While_2(Exp _1, Exp _2) {
		this(_1, _2, null);
	}

	private While_2(Exp _1, Exp _2, IStrategoTerm strategoTerm) {
		this.loopDescriptor = new FrameDescriptor();
		this.currentFrameSlot = this.loopDescriptor.addFrameSlot("__current_frame__", FrameSlotKind.Object);
		this.loopingNode = Truffle.getRuntime().createLoopNode(new WhileHelper(_1, _2, currentFrameSlot, strategoTerm));
		this.strategoTerm = strategoTerm;
	}

	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		VirtualFrame loopFrame = Truffle.getRuntime().createVirtualFrame(null, loopDescriptor);
		loopFrame.setObject(currentFrameSlot, currentFrame);
		loopingNode.executeLoop(loopFrame);
		return UnitV_0.SINGLETON;
	}

	public static class WhileHelper extends TigerTruffleNode implements RepeatingNode, ITerm {

		@Child
		private Exp condition;
		@Child
		private Exp body;

		@Child
		private Framed newFramedNode;

		private final FrameSlot currentFrameSlot;
		private final IStrategoTerm strategoTerm;

		public WhileHelper(Exp condition, Exp body, FrameSlot currentFrameSlot, IStrategoTerm term) {
			this.condition = condition;
			this.body = body;
			this.currentFrameSlot = currentFrameSlot;
			this.newFramedNode = new Framed();
			this.strategoTerm = term;
		}

		private final BranchProfile breakTaken = BranchProfile.create();

		@Override
		public boolean executeRepeating(VirtualFrame frame) {
			DynamicObject currentFrame = FrameUtils.asFrame(frame.getValue(currentFrameSlot));
			if (!evaluateCondition(frame, currentFrame)) {
				return false;
			}
			try {
				// F |- __Exp2Evaluatable__(w@While(_, e2)) --> v
				// where
				// framed(w, [L(P(), F)]) --> F';
				// F' |- e2 --> v
				DynamicObject loopFrame = newFramedNode.execute(frame, this,
						new FLink[] { new FrameEdgeLink(P.SINGLETON, currentFrame) });
				body.executeGeneric(frame, loopFrame);
				return true;
			} catch (BreakException brx) {
				breakTaken.enter();
				return false;
			}
		}

		protected boolean evaluateCondition(VirtualFrame frame, DynamicObject currentFrame) {
			int c = TigerTypesGen.asIntV_1(condition.executeGeneric(frame, currentFrame)).get_1();
			return c != 0;
		}

		@Override
		public int size() {
			return 0;
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

	@TruffleBoundary
	public static While_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new While_2(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), term);
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