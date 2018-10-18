package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types;

import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.TigerNode;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class TypeDec extends TigerNode {
	public static TypeDec create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "TypeDec", 2)) {
			return TypeDec_2.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}

	public abstract void fillFrameDescriptor(FrameDescriptor fd);

	public abstract void executeDeclaration(VirtualFrame frame);
}