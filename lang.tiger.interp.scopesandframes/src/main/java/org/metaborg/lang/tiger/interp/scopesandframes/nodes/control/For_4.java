package org.metaborg.lang.tiger.interp.scopesandframes.nodes.control;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.control.For_4NodeGen.ForRepeatingNodeGen;
import org.metaborg.lang.tiger.interp.scopesandframes.values.IntV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.Var;
import org.metaborg.lang.tiger.interpreter.generated.terms.Var_1;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameEdgeLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameEdgeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameUtils;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlot;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlotNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.P;
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
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.LoopNode;
import com.oracle.truffle.api.nodes.RepeatingNode;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class For_4 extends Exp {
	public final static String CONSTRUCTOR = "For";

	public final static int ARITY = 4;

	private final Occurrence boundVar;

	@Child
	private Exp e1;

	@Child
	private Exp e2;

	@Child
	private Framed newFrameNode;

	@Child
	private SetFrameSlot setNode;

	private final FrameDescriptor loopDescriptor;
	private final FrameSlot currentFrameSlot;
	private final FrameSlot lowerBoundSlot;
	private final FrameSlot upperBoundSlot;
	private final FrameSlot currentValueSlot;

	@Child
	private LoopNode loopingNode;

	public For_4(Var _1, Exp _2, Exp _3, Exp _4) {
		this(_1, _2, _3, _4, null);
	}

	public For_4(Var _1, Exp _2, Exp _3, Exp body, IStrategoTerm strategoTerm) {
		this.boundVar = ((__Occurrence2Occ___1) ((Var_1) _1).get_1()).get_1();
		this.e1 = _2;
		this.e2 = _3;
		this.newFrameNode = new Framed();
		this.setNode = SetFrameSlotNodeGen.create();
		this.loopDescriptor = new FrameDescriptor();
		this.currentFrameSlot = this.loopDescriptor.addFrameSlot("__current_frame__", FrameSlotKind.Object);
		this.currentValueSlot = this.loopDescriptor.addFrameSlot("__current_value__", FrameSlotKind.Int);
		this.lowerBoundSlot = this.loopDescriptor.addFrameSlot("__lb__", FrameSlotKind.Int);
		this.upperBoundSlot = this.loopDescriptor.addFrameSlot("__ub__", FrameSlotKind.Int);
		this.loopingNode = Truffle.getRuntime().createLoopNode(ForRepeatingNodeGen.create(body, this.boundVar,
				currentFrameSlot, lowerBoundSlot, upperBoundSlot, currentValueSlot));
		this.strategoTerm = strategoTerm;
	}

	@Specialization
	@ExplodeLoop
	public V executeWithCaching(VirtualFrame frame, DynamicObject currentFrame,
			@Cached(value = "label()") ALabel linkLabel,
			@Cached("getEdgeIdent(linkLabel, currentFrame)") FrameEdgeIdentifier edgeIdent) {
		int lb = TigerTypesGen.asIntV_1(e1.executeGeneric(frame, currentFrame)).get_1();
		int ub = TigerTypesGen.asIntV_1(e2.executeGeneric(frame, currentFrame)).get_1();
		DynamicObject f_for = newFrameNode.execute(frame, this,
				new FLink[] { new FrameEdgeLink(P.SINGLETON, currentFrame, edgeIdent) });

		VirtualFrame loopFrame = Truffle.getRuntime().createVirtualFrame(null, loopDescriptor);
		loopFrame.setInt(lowerBoundSlot, lb);
		loopFrame.setInt(upperBoundSlot, ub);
		loopFrame.setInt(currentValueSlot, lb);
		loopFrame.setObject(currentFrameSlot, f_for);

		setNode.execute(frame, f_for, boundVar, new IntV_1(lb));
		loopingNode.executeLoop(loopFrame);
		return UnitV_0.SINGLETON;
	}

	protected FrameEdgeIdentifier getEdgeIdent(ALabel label, DynamicObject frm) {
		return new FrameEdgeIdentifier(label, FrameLayoutImpl.INSTANCE.getScope(frm));
	}

	protected ALabel label() {
		return P.SINGLETON;
	}

	public static abstract class ForRepeatingNode extends TigerTruffleNode implements RepeatingNode {

		private final FrameSlot currentFrameSlot;
		private final FrameSlot lowerBoundSlot;
		private final FrameSlot upperBoundSlot;
		private final FrameSlot currentValueSlot;

		@Child
		private Exp body;

		private final Occurrence boundVar;

		@Child
		private SetFrameSlot setSlotNode;

		public ForRepeatingNode(Exp body, Occurrence boundVar, FrameSlot currentFrameSlot, FrameSlot lowerBoundSlot,
				FrameSlot upperBoundSlot, FrameSlot currentValueSlot) {
			this.body = body;
			this.boundVar = boundVar;
			this.setSlotNode = SetFrameSlotNodeGen.create();
			this.currentFrameSlot = currentFrameSlot;
			this.lowerBoundSlot = lowerBoundSlot;
			this.upperBoundSlot = upperBoundSlot;
			this.currentValueSlot = currentValueSlot;

		}

		@Specialization
		public boolean doWithCaching(VirtualFrame frame, @Cached("getCurrentFrame(frame)") DynamicObject currentFrame,
				@Cached("getLowerBound(frame)") int lb, @Cached("getUpperBound(frame)") int ub) {
			try {
				// evaluate body
				body.executeGeneric(frame, currentFrame);
				// increment counter
				int currentValue = frame.getInt(currentValueSlot);
				currentValue++;
				if (currentValue <= ub) {
					frame.setInt(currentValueSlot, currentValue);
					setSlotNode.execute(frame, currentFrame, boundVar, new IntV_1(currentValue));
					return true;
				} else {
					return false;
				}
			} catch (BreakException brx) {
				return false;
			} catch (FrameSlotTypeException e) {
				throw new RuntimeException("Type error", e);
			}
		}

		protected int getLowerBound(VirtualFrame frame) {
			try {
				return frame.getInt(lowerBoundSlot);
			} catch (FrameSlotTypeException e) {
				throw new RuntimeException("Type error", e);
			}
		}

		protected int getUpperBound(VirtualFrame frame) {
			try {
				return frame.getInt(upperBoundSlot);
			} catch (FrameSlotTypeException e) {
				throw new RuntimeException("Type error", e);
			}
		}

		protected DynamicObject getCurrentFrame(VirtualFrame frame) {
			return FrameUtils.asFrame(frame.getValue(currentFrameSlot));
		}

	}

	@TruffleBoundary
	public static For_4 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return For_4NodeGen.create(Var.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)),
				Exp.create(term.getSubterm(2)), Exp.create(term.getSubterm(3)), term);
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