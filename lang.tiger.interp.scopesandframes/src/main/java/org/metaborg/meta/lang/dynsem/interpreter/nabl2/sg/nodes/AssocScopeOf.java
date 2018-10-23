package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ALabel;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.DeclEntryLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.DeclarationsLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.NaBL2LayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeGraphLayoutImpl;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class AssocScopeOf extends ScopesAndFramesNode {

	public AssocScopeOf() {
		super();
	}

	public abstract ScopeIdentifier execute(VirtualFrame frame, Occurrence occurrence, ALabel label);

	@Specialization(guards = { "occurrence == occurrence_cached", "label_cached.equals(label)" }, limit = "20")
	public ScopeIdentifier doGetCached(Occurrence occurrence, ALabel label,
			@Cached("occurrence") Occurrence occurrence_cached, @Cached("label") ALabel label_cached,
			@Cached("doGet(occurrence_cached, label_cached)") ScopeIdentifier scope) {
		return scope;
	}

	@Specialization(replaces = "doGetCached")
	public ScopeIdentifier doGet(Occurrence occurrence, ALabel label) {
		DynamicObject nabl2 = context().getNaBL2Solution();

		DynamicObject sg = NaBL2LayoutImpl.INSTANCE.getScopeGraph(nabl2);
		DynamicObject declarations = ScopeGraphLayoutImpl.INSTANCE.getDeclarations(sg);
		assert DeclarationsLayoutImpl.INSTANCE.isDeclarations(declarations);
		DynamicObject declEntry = (DynamicObject) declarations.get(occurrence);
		DynamicObject assocs = DeclEntryLayoutImpl.INSTANCE.getAssociatedScopes(declEntry);
		ScopeIdentifier[] scopes = (ScopeIdentifier[]) assocs.get(label);
		assert scopes.length == 1;
		return scopes[0];
	}

}
