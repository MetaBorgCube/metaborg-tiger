package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IWithScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class GetScopeOfTerm extends ScopesAndFramesNode {

	public GetScopeOfTerm(TruffleLanguage<? extends IWithScopesAndFramesContext> language) {
		super(language);
	}

	public abstract ScopeIdentifier execute(VirtualFrame frame, ITerm t);

	@Specialization(limit = "1000", guards = { "t == t_cached" })
	public ScopeIdentifier doCached(ITerm t, @Cached("t") ITerm t_cached,
			@Cached("getScopeIdentifier(t_cached)") ScopeIdentifier sid_cached) {
		return sid_cached;
	}

	@Specialization
	public ScopeIdentifier doUncached(ITerm t) {
		return getScopeIdentifier(t);
	}

	@TruffleBoundary
	protected ScopeIdentifier getScopeIdentifier(ITerm t) {
		IStrategoTerm scopeIdentT = getAstProperty(t.getStrategoTerm(), "bodyScope");
		return ScopeIdentifier.create(scopeIdentT);
	}

}
