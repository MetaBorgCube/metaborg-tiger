package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.NaBL2LayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeEntryLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeGraphLayoutImpl;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class DeclsOfScope extends ScopesAndFramesNode {

	public DeclsOfScope() {
		super();
	}

	public abstract Occurrence[] execute(VirtualFrame frame, ScopeIdentifier scope);
	
	@Specialization(guards = { "scope == scope_cached" }, limit = "20")
	public Occurrence[] executeCached(ScopeIdentifier scope, @Cached("scope") ScopeIdentifier scope_cached,
			@Cached(value = "lookupScopeDecls(scope)", dimensions = 1) Occurrence[] decs_cached) {
		return decs_cached;
	}

	@Specialization(replaces = "executeCached")
	public Occurrence[] executeUncached(ScopeIdentifier scope) {
		return lookupScopeDecls(scope);
	}

	protected Occurrence[] lookupScopeDecls(ScopeIdentifier scope) {
		DynamicObject sg = NaBL2LayoutImpl.INSTANCE.getScopeGraph(context().getNaBL2Solution());

		DynamicObject scopes = ScopeGraphLayoutImpl.INSTANCE.getScopes(sg);
		// FIXME eliminate this cast
		return ScopeEntryLayoutImpl.INSTANCE.getDeclarations((DynamicObject) scopes.get(scope));
	}

}
