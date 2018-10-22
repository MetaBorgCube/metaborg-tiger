package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.NaBL2LayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeEntryLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeGraphLayoutImpl;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.source.SourceSection;

public abstract class LinkedScopesOverLabel extends ScopesAndFramesNode {

	public LinkedScopesOverLabel(SourceSection source) {
	}
	
	public abstract ScopeIdentifier[] execute(VirtualFrame frame, ScopeIdentifier scope, ALabel label);
	
	@Specialization(guards = { "scope.equals(scope_cached)", "label.equals(label_cached)" }, limit = "1000")
	public ScopeIdentifier[] doGetScopesCached(ScopeIdentifier scope, ALabel label, @Cached("scope") ScopeIdentifier scope_cached,
			@Cached("label") ALabel label_cached, @Cached(value="lookupScopes(scope_cached, label_cached)", dimensions=1) ScopeIdentifier[] scopes) {
		return scopes;
	}

	@Specialization(replaces = "doGetScopesCached")
	public ScopeIdentifier[] doGetScopes(ScopeIdentifier scope, ALabel label) {
		return lookupScopes(scope, label);
	}

	protected ScopeIdentifier[] lookupScopes(ScopeIdentifier scope, ALabel label) {
		DynamicObject sg = NaBL2LayoutImpl.INSTANCE.getScopeGraph(context().getNaBL2Solution());

		DynamicObject scopes = ScopeGraphLayoutImpl.INSTANCE.getScopes(sg);
		DynamicObject scopeEntry = (DynamicObject) scopes.get(scope);
		DynamicObject scopeEdges = ScopeEntryLayoutImpl.INSTANCE.getEdges(scopeEntry);
		return (ScopeIdentifier[]) scopeEdges.get(label);
	}

}
