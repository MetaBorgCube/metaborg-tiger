package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;

import com.oracle.truffle.api.object.DynamicObject;

public final class FramesEqual extends ScopesAndFramesNode {

	public FramesEqual() {
		super();
	}

	public boolean eqCheck(DynamicObject frm1, DynamicObject frm2) {
		// TODO proper cast-check for frames
		return (frm1 == frm2);
	}

}
