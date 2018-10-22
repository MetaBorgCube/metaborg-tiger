package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Links extends ScopesAndFramesNode {

	@Child
	private AddFrameLink linkNode;

	public Links() {
		super();
		this.linkNode = AddFrameLinkNodeGen.create();
	}

	public void execute(VirtualFrame frame, DynamicObject f, FLink[] links) {
		// TODO: optimization opportunity
		for (FLink link : links) {
			linkNode.execute(frame, f, link);
		}
	}

}
