package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.TermIndex;

import com.oracle.truffle.api.frame.VirtualFrame;

public final class MakeFreshOccurrence extends ScopesAndFramesNode {

	@Child
	private Fresh freshGen;

	public MakeFreshOccurrence() {
		super();
		this.freshGen = new Fresh();
	}

	public Occurrence execute(VirtualFrame frame, String namespace, String name) {
		return new Occurrence(namespace, name, new TermIndex("<phantom>", freshGen.execute(frame)));
	}

}
