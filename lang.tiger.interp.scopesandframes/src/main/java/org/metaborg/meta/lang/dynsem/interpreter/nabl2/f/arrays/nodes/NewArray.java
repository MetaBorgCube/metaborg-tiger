package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.arrays.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.arrays.Array;

import com.oracle.truffle.api.frame.VirtualFrame;

public final class NewArray extends ScopesAndFramesNode {

	public NewArray() {
		super();
	}

	public Array execute(VirtualFrame frame, int length, Object fill) {
		return new Array(length, fill);
	}

}
