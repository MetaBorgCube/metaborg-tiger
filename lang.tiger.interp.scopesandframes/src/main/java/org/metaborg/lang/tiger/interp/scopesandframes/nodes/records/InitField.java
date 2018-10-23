package org.metaborg.lang.tiger.interp.scopesandframes.nodes.records;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTruffleNode;
import org.metaborg.meta.lang.dynsem.interpreter.terms.IApplTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class InitField extends TigerTruffleNode implements IApplTerm {

	@TruffleBoundary
	public static InitField create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		if (Tools.isTermAppl(term) && Tools.hasConstructor((IStrategoAppl) term, "InitField", 2)) {
			return InitField_2.create(term);
		}
		throw new IllegalStateException("Unsupported term: " + term);
	}
	
	public abstract void execute(VirtualFrame frame, DynamicObject record_frame, DynamicObject surrounding_frame);
	
}