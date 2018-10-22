package org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;

public final class Fresh extends Node {

	private static int next = 0;

	public Fresh() {
		super();
	}

	public int execute(VirtualFrame frame) {
		return next++;
	}

}
