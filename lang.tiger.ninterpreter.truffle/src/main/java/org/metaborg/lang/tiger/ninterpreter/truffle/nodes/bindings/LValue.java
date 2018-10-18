package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.bindings;

import org.metaborg.lang.tiger.ninterpreter.terms.FieldVar_2;
import org.metaborg.lang.tiger.ninterpreter.terms.Subscript_2;
import org.metaborg.lang.tiger.ninterpreter.truffle.objects.TigerMemoryRef;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public abstract class LValue extends Node {
	public static LValue create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Subscript", 2)) {
			return Subscript_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "FieldVar", 2)) {
			return FieldVar_2.create(term);
		}
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "__Var2LValue__", 1)) {
			return __Var2LValue___1.create(term);
		}
		try {
			return __Var2LValue___1NodeGen.create(Var.create(term));
		} catch (IllegalStateException j_25) {
			throw new IllegalStateException("Unsupported term: " + term);
		}
	}

	public abstract TigerMemoryRef executeToRef(VirtualFrame frame);
}