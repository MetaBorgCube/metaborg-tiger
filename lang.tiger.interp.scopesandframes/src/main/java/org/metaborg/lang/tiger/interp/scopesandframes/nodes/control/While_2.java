package org.metaborg.lang.tiger.interp.scopesandframes.nodes.control;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.control.While_2NodeGen.WhileHelperNodeGen;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameEdgeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameUtils;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.AddFrameLinkNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrame;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.NewFrameNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.GetScopeOfTerm;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.GetScopeOfTermNodeGen;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.LoopNode;
import com.oracle.truffle.api.nodes.RepeatingNode;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.profiles.BranchProfile;

public abstract class While_2 extends Exp {
	public final static String CONSTRUCTOR = "While";

	public final static int ARITY = 2;

	private final FrameDescriptor loopDescriptor;
	private final FrameSlot whileScopeSlot;
	private final FrameSlot currentScopeSlot;
	private final FrameSlot currentFrameSlot;

	@Child
	private LoopNode loopingNode;

	@Child
	private GetScopeOfTerm scopeOf;

	public While_2(Exp _1, Exp _2) {
		this(_1, _2, null);
	}

	public While_2(Exp _1, Exp _2, IStrategoTerm strategoTerm) {
		this.loopDescriptor = new FrameDescriptor();
		this.currentFrameSlot = this.loopDescriptor.addFrameSlot("__current_frame__", FrameSlotKind.Object);
		this.currentScopeSlot = this.loopDescriptor.addFrameSlot("__current_scope__", FrameSlotKind.Object);
		this.whileScopeSlot = this.loopDescriptor.addFrameSlot("__while_scope__", FrameSlotKind.Object);
		this.loopingNode = Truffle.getRuntime()
				.createLoopNode(WhileHelperNodeGen.create(_1, _2, currentFrameSlot, currentScopeSlot, whileScopeSlot));
		this.scopeOf = GetScopeOfTermNodeGen.create();
		this.strategoTerm = strategoTerm;
	}

	@Specialization
	public V doCaching(VirtualFrame frame, DynamicObject currentFrame,
			@Cached("getScopeOfWhile(frame)") ScopeIdentifier s_while) {
		VirtualFrame loopFrame = Truffle.getRuntime().createVirtualFrame(null, loopDescriptor);
		loopFrame.setObject(currentFrameSlot, currentFrame);
		loopFrame.setObject(currentScopeSlot, FrameLayoutImpl.INSTANCE.getScope(currentFrame));
		loopFrame.setObject(whileScopeSlot, s_while);
		loopingNode.executeLoop(loopFrame);
		return UnitV_0.SINGLETON;
	}

	protected ScopeIdentifier getScopeOfWhile(VirtualFrame frame) {
		return scopeOf.execute(frame, this);
	}

	public static abstract class WhileHelper extends TigerTruffleNode implements RepeatingNode {

		@Child
		private Exp condition;
		@Child
		private Exp body;

		@Child
		private NewFrame newFramedNode;

		@Child
		private AddFrameLink linkFrame;

		private final FrameSlot currentFrameSlot;
		private final FrameSlot currentScopeSlot;
		private final FrameSlot whileScopeSlot;

		public WhileHelper(Exp condition, Exp body, FrameSlot currentFrameSlot, FrameSlot currentScopeSlot,
				FrameSlot whileScopeSlot) {
			this.condition = condition;
			this.body = body;
			this.currentFrameSlot = currentFrameSlot;
			this.currentScopeSlot = currentScopeSlot;
			this.whileScopeSlot = whileScopeSlot;
			this.newFramedNode = NewFrameNodeGen.create();
			this.linkFrame = AddFrameLinkNodeGen.create();
		}

		private final BranchProfile breakTaken = BranchProfile.create();

		@Specialization
		public boolean doCaching(VirtualFrame frame, @Cached("parentFrameScope(frame)") ScopeIdentifier s_parent,
				@Cached("getWhileScope(frame)") ScopeIdentifier s_while, @Cached("label()") ALabel linkLabel,
				@Cached("getEdgeIdent(linkLabel, s_parent)") FrameEdgeIdentifier edgeIdent) {
			DynamicObject currentFrame = FrameUtils.asFrame(frame.getValue(currentFrameSlot));
			if (!evaluateCondition(frame, currentFrame)) {
				return false;
			}
			try {
				DynamicObject loopFrame = newFramedNode.execute(frame, s_while);
				linkFrame.execute(frame, loopFrame,
						new FrameEdgeLink(linkLabel, FrameUtils.asFrame(frame.getValue(currentFrameSlot)), edgeIdent));
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

		protected ScopeIdentifier getWhileScope(VirtualFrame frame) {
			return (ScopeIdentifier) frame.getValue(whileScopeSlot);
		}

		protected ScopeIdentifier parentFrameScope(VirtualFrame frame) {
			return (ScopeIdentifier) frame.getValue(currentScopeSlot);
		}

		protected FrameEdgeIdentifier getEdgeIdent(ALabel label, ScopeIdentifier scope) {
			return new FrameEdgeIdentifier(label, scope);
		}

		protected ALabel label() {
			return P.SINGLETON;
		}

	}

	@TruffleBoundary
	public static While_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return While_2NodeGen.create(Exp.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), term);
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