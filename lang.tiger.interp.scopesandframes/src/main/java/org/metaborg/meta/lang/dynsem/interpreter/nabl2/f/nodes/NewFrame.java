package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class NewFrame extends ScopesAndFramesNode {

	@Child
	private CloneFrame frameCloner;

	public NewFrame() {
		super();
		this.frameCloner = CloneFrameNodeGen.create();
	}

	public abstract DynamicObject execute(VirtualFrame frame, ScopeIdentifier sid);

	@Specialization(guards = { "scopeId_cached.equals(scopeId)" }, limit = "20")
	public DynamicObject doNewFrameCached(ScopeIdentifier scopeId, @Cached("scopeId") ScopeIdentifier scopeId_cached,
			@Cached("context().getProtoFrame(scopeId_cached)") DynamicObject protoFrame_cached) {
		return frameCloner.execute(protoFrame_cached);
	}

	@Specialization
	public DynamicObject doNewFrame(ScopeIdentifier scopeId) {
		return frameCloner.execute(context().getProtoFrame(scopeId));
	}

}
