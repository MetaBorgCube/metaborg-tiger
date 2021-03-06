package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.types;

import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

public abstract class Field {
	public static Field create(IStrategoTerm term) {
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "Field", 2)) {
			return Field_2.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}

	public abstract String getId();
}