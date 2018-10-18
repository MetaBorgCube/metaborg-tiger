package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types;

import org.metaborg.lang.tiger.ninterpreter.terms.Occ;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;

public final class TypeDec_2 extends TypeDec {
	public final static String CONSTRUCTOR = "TypeDec";

	public final static int ARITY = 2;

	private TypeDec_2(Occ _1, Type _2) {
		this.name = _1;
		this.typeDec = _2;
	}

	private final Occ name;

	private final Type typeDec;

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
		frame.setObject(slot(), typeDec.executeTypeDec(frame));
	}

	public static TypeDec_2 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new TypeDec_2(Occ.create(term.getSubterm(0)), Type.create(term.getSubterm(1)));
	}
}