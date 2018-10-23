package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IDefaultValueProvider;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;

import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class DefaultValue extends ScopesAndFramesNode {
	public DefaultValue() {
		super();
	}

	public abstract Object execute(VirtualFrame frame, Object type);

	@Specialization(guards = { "type == null" })
	public Object executeNull(VirtualFrame frame, Object type) {
		return null;
	}

	@Specialization(guards = { "type != null" })
	public Object executeDefault(VirtualFrame frame, Object type,
			@Cached("context().getDefaultValues()") IDefaultValueProvider defaultProvider) {
		return defaultProvider.defaultValue(type);
	}

}
