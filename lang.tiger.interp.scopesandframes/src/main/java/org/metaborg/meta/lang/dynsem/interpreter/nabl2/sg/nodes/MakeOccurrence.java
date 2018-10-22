package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.TermIndex;

public final class MakeOccurrence extends ScopesAndFramesNode {

	public MakeOccurrence() {
		super();
	}

	public Occurrence execute(String namespace, String name, TermIndex index) {
		return new Occurrence(namespace, name, index);
	}

}
