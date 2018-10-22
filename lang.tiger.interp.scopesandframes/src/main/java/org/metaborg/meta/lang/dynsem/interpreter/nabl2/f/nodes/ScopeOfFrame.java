package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class ScopeOfFrame extends ScopesAndFramesNode {

	public ScopeOfFrame() {
		super();
	}

	public ScopeIdentifier execute(VirtualFrame frame, DynamicObject frm) {
		return FrameLayoutImpl.INSTANCE.getScope(frm);
	}

}
