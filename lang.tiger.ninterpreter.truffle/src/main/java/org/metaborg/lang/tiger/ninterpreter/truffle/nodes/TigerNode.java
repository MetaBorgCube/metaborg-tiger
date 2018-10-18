package org.metaborg.lang.tiger.ninterpreter.truffle.nodes;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerContext;
import org.metaborg.lang.tiger.ninterpreter.truffle.runtime.TigerLanguage;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.TruffleLanguage.ContextReference;
import com.oracle.truffle.api.nodes.Node;

public abstract class TigerNode extends Node {

	@CompilationFinal
	private ContextReference<TigerContext> contextRef;

	protected final TigerContext ctx() {
		if (contextRef == null) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			contextRef = this.getRootNode().getLanguage(TigerLanguage.class).getContextReference();
		}
		return contextRef.get();
	}
}
