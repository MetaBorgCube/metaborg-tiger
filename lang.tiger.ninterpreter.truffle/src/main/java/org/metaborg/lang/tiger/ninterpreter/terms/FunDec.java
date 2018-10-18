package org.metaborg.lang.tiger.ninterpreter.terms;

import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.TigerNode;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.functions.FunDec_4;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class FunDec extends TigerNode {
	public static FunDec create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "ProcDec", 3)) {
			return ProcDec_3.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "FunDec", 4)) {
			return FunDec_4.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}

	public abstract void fillFrameDescriptor(FrameDescriptor fd);

	public abstract void executeDeclaration(VirtualFrame frame);
}