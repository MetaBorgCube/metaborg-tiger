package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types;

import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings.Dec;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoList;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;

public final class TypeDecs_1 extends Dec {
	public final static String CONSTRUCTOR = "TypeDecs";

	public final static int ARITY = 1;

	private TypeDecs_1(TypeDec[] _1) {
		this.recDecs = _1;
	}

	@Children
	final TypeDec[] recDecs;

	@Override
	@ExplodeLoop
	public void fillFrameDescriptor(FrameDescriptor fd) {
		for (int i = 0; i < recDecs.length; i++) {
			recDecs[i].fillFrameDescriptor(fd);
		}
	}

	@Override
	@ExplodeLoop
	public void executeDeclaration(VirtualFrame frame) {
		for (int i = 0; i < recDecs.length; i++) {
			recDecs[i].executeDeclaration(frame);
		}
	}

	public static TypeDecs_1 create(IStrategoTerm term) {
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoList tsT = Tools.listAt(term, 0);
		TypeDec[] ts = new TypeDec[tsT.size()];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = TypeDec.create(tsT.getSubterm(i));
		}
		return new TypeDecs_1(ts);
	}
}