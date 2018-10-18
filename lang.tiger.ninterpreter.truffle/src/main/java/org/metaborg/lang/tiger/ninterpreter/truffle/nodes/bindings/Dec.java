package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings;

import org.metaborg.lang.tiger.ninterpreter.terms.VarDecNoType_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.TigerNode;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.functions.FunDecs_1;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types.TypeDecs_1;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class Dec extends TigerNode {
	public static Dec create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "FunDecs", 1)) {
			return FunDecs_1.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "VarDec", 3)) {
			return VarDec_3.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "VarDecNoType", 2)) {
			return VarDecNoType_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "TypeDecs", 1)) {
			return TypeDecs_1.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}

	public abstract void fillFrameDescriptor(FrameDescriptor fd);

	public abstract void executeDeclaration(VirtualFrame frame);
}