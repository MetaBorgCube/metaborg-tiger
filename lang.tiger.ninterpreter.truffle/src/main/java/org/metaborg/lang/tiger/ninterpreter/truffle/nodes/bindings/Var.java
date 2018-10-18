package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.TigerNode;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.TigerMemoryRef;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class Var extends TigerNode {
	public static Var create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Var", 1)) {
			return Var_1.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}

	public abstract TigerMemoryRef executeToRef(VirtualFrame frame);

	public abstract TigerObject executeToValue(VirtualFrame frame);

}