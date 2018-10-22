package org.metaborg.lang.tiger.interp.scopesandframes;

import org.metaborg.lang.tiger.interp.scopesandframes.values.V;

import com.oracle.truffle.api.dsl.TypeSystemReference;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

@TypeSystemReference(TigerTypes.class)
public abstract class TigerEvalNode extends TigerTruffleNode {

	public TigerEvalNode() {
		super();
	}

	public abstract V executeGeneric(VirtualFrame frame, DynamicObject currentFrame);

}
