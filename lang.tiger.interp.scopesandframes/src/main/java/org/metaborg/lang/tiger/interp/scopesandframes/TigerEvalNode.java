package org.metaborg.lang.tiger.interp.scopesandframes;

import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class TigerEvalNode extends TigerTruffleNode {

	public TigerEvalNode() {
		super();
	}

	public abstract Object executeGeneric(VirtualFrame frame);

}
