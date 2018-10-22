package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;

//@NodeChildren({ @NodeChild(value = "s1", type = TermBuild.class), @NodeChild(value = "s2", type = TermBuild.class) })
public final class ScopesEqual extends ScopesAndFramesNode {

	public ScopesEqual() {
		super();
	}

	public boolean eqCheck(ScopeIdentifier s1, ScopeIdentifier s2) {
		return s1.equals(s2);
	}

}
