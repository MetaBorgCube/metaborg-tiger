package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings;

import org.metaborg.lang.tiger.ninterpreter.terms.Occ;
import org.metaborg.lang.tiger.ninterpreter.terms.__Id2Occ___1;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types.Type;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;

public final class VarDec_3 extends Dec {
	public final static String CONSTRUCTOR = "VarDec";

	public final static int ARITY = 3;

	private final Occ _1;

	private final Type _2;

	@Child
	Exp _3;

	public VarDec_3(Occ _1, Type _2, Exp _3) {
		this._1 = _1;
		this._2 = _2;
		this._3 = _3;
	}

	@CompilationFinal
	private FrameSlot boundSlot;

	@Override
	public void fillFrameDescriptor(FrameDescriptor fd) {
		boundSlot = fd.addFrameSlot(((__Id2Occ___1) _1).get_1(), FrameSlotKind.Object);
	}

	public FrameSlot slot() {
		assert boundSlot != null;
		return boundSlot;
	}

	@Override
	public void executeDeclaration(VirtualFrame frame) {
		TigerObject value = _3.executeGeneric(frame);
		frame.setObject(slot(), value);
	}

	public static VarDec_3 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new VarDec_3(Occ.create(term.getSubterm(0)), Type.create(term.getSubterm(1)),
				Exp.create(term.getSubterm(2)));
	}
}