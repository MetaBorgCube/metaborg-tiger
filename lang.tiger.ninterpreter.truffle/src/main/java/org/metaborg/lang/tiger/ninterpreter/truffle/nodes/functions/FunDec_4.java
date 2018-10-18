package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.functions;

import org.metaborg.lang.tiger.ninterpreter.terms.FArg;
import org.metaborg.lang.tiger.ninterpreter.terms.FunDec;
import org.metaborg.lang.tiger.ninterpreter.terms.Occ;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types.Type;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.ClosureV;
import org.metaborg.lang.tiger.ninterpreter.truffle.runtime.TigerLanguage;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeUtil;
import com.oracle.truffle.api.nodes.RootNode;

public final class FunDec_4 extends FunDec {
	public final static String CONSTRUCTOR = "FunDec";

	public final static int ARITY = 4;

	private final Occ name;

	private final FArg[] fargs;

	private final Type _3;

	@Child
	private Exp bodyNode;

	private FunDec_4(Occ _1, FArg[] _2, Type _3, Exp _4) {
		this.name = _1;
		this.fargs = _2;
		this._3 = _3;
		this.bodyNode = _4;
	}

	@CompilationFinal
	private FrameSlot boundSlot;

	@Override
	public void fillFrameDescriptor(FrameDescriptor fd) {
		boundSlot = fd.addFrameSlot(name.getId(), FrameSlotKind.Object);
	}

	public FrameSlot slot() {
		assert boundSlot != null;
		return boundSlot;
	}

	@Override
	public void executeDeclaration(VirtualFrame frame) {
		// compute FrameDescriptor for function
		FrameDescriptor functionDescriptor = ctx().baseDescriptor.copy();
		FrameSlot[] argSlots = new FrameSlot[fargs.length];
		for (int i = 0; i < argSlots.length; i++) {
			argSlots[i] = functionDescriptor.addFrameSlot(fargs[i].getId(), FrameSlotKind.Object);
		}
		// create root node
		RootNode fRoot = new TigerFunctionRootNode(this.getRootNode().getLanguage(TigerLanguage.class),
				functionDescriptor, frame, argSlots, NodeUtil.cloneNode(bodyNode));
		// create call target. Should probably be a RootCallTarget
		CallTarget fCallTarget = Truffle.getRuntime().createCallTarget(fRoot);

		frame.setObject(slot(), new ClosureV(fCallTarget));
	}

	public static FunDec_4 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 1);
		FArg[] ts = new FArg[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = FArg.create(tsT.getSubterm(i));
		}
		return new FunDec_4(Occ.create(term.getSubterm(0)), ts, Type.create(term.getSubterm(2)),
				Exp.create(term.getSubterm(3)));
	}

}