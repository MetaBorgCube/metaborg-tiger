package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings;

import org.metaborg.lang.tiger.ninterpreter.terms.Occ;
import org.metaborg.lang.tiger.ninterpreter.terms.__Id2Occ___1;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.TigerMemoryRef;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public class Var_1 extends Var {
	public final static String CONSTRUCTOR = "Var";

	public final static int ARITY = 1;

	protected final String identifier;

	public Var_1(Occ _1) {
		identifier = ((__Id2Occ___1) _1).get_1();
	}

	private class ResolutionResult {
		final FrameSlot targetSlot;
		final int frameDistance;
		final MaterializedFrame targetFrame;

		public ResolutionResult(FrameSlot slot, int distance, MaterializedFrame f) {
			this.targetSlot = slot;
			this.frameDistance = distance;
			this.targetFrame = f;
		}
	}

	private ResolutionResult resolve(VirtualFrame frame) {
		FrameSlot parentFrameSlot = ctx().parentFrameSlot;
		Frame f = frame;
		FrameSlot targetSlot = null;
		int frameDistance = -1;
		while (targetSlot == null && f != null) {
			frameDistance++;
			targetSlot = f.getFrameDescriptor().findFrameSlot(this.identifier);
			if (targetSlot == null) {
				try {
					f = (Frame) f.getObject(parentFrameSlot);
				} catch (FrameSlotTypeException e) {
					throw new RuntimeException(e);
				}
			}
		}
		if (f == null || targetSlot == null || frameDistance < 0) {
			throw new RuntimeException("Unresolved variable " + identifier);
		}
		return new ResolutionResult(targetSlot, frameDistance, f.materialize());
	}

	@Override
	public TigerMemoryRef executeToRef(VirtualFrame frame) {
		ResolutionResult resolution = resolve(frame);
		return replace(new ResolvedVar(resolution.targetSlot, resolution.frameDistance))
				.executeToRefEvaluated(resolution.targetFrame);
	}

	@Override
	public TigerObject executeToValue(VirtualFrame frame) {
		ResolutionResult resolution = resolve(frame);
		return replace(new ResolvedVar(resolution.targetSlot, resolution.frameDistance))
				.executeToValueEvaluated(resolution.targetFrame);
	}

	public static Var_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Var_1(Occ.create(term.getSubterm(0)));
	}

	public class ResolvedVar extends Var {

		private FrameSlot targetSlot;
		private int frameDistance;

		public ResolvedVar(FrameSlot targetSlot, int frameDistance) {
			this.targetSlot = targetSlot;
			this.frameDistance = frameDistance;
		}

		@CompilationFinal
		private FrameSlot parentFrameSlot;

		private FrameSlot parentFrameSlot() {
			if (parentFrameSlot == null) {
				parentFrameSlot = ctx().parentFrameSlot;
			}
			return parentFrameSlot;
		}

		@ExplodeLoop
		public Frame getTargetFrame(VirtualFrame frame) {
			FrameSlot parentFrameSlot = parentFrameSlot();
			Frame f = frame;
			for (int i = frameDistance; i > 0; i--) {
				try {
					f = (Frame) f.getObject(parentFrameSlot);
				} catch (FrameSlotTypeException e) {
					throw new RuntimeException(e);
				}
			}
			return f;
		}

		@Override
		public TigerMemoryRef executeToRef(VirtualFrame frame) {
			return new TigerMemoryRef(targetSlot, getTargetFrame(frame).materialize());
		}

		public TigerMemoryRef executeToRefEvaluated(Frame targetFrame) {
			return new TigerMemoryRef(targetSlot, targetFrame.materialize());
		}

		@Override
		public TigerObject executeToValue(VirtualFrame frame) {
			try {
				return (TigerObject) getTargetFrame(frame).getObject(targetSlot);
			} catch (FrameSlotTypeException e) {
				throw new RuntimeException(e);
			}
		}

		public TigerObject executeToValueEvaluated(Frame targetFrame) {
			try {
				return (TigerObject) targetFrame.getObject(targetSlot);
			} catch (FrameSlotTypeException e) {
				throw new RuntimeException(e);
			}
		}

	}

}