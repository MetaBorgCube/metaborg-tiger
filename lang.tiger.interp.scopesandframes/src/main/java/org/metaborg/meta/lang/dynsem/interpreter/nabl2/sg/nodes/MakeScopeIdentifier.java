package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;

public final class MakeScopeIdentifier extends ScopesAndFramesNode {

	public MakeScopeIdentifier() {
		super();
	}

	public ScopeIdentifier executeSpecial(String resource, String name) {
		return new ScopeIdentifier(resource, name);
	}

}
