package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IDefaultValueProvider;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IWithScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;

public abstract class DefaultValue extends ScopesAndFramesNode {
//	private static final String DEFAULT_CTR_NAME = "default";
//	private static final int DEFAULT_CTR_ARITY = 1;

//	@Child private DispatchNode defaultValueDispatchNode;

	public DefaultValue(TruffleLanguage<? extends IWithScopesAndFramesContext> language) {
		super(language);
//		this.defaultValueDispatchNode = DispatchNode.create(source, "");
	}

	public abstract Object execute(VirtualFrame frame, Object type);

	@Specialization(guards = { "type == null" })
	public Object executeNull(VirtualFrame frame, Object type) {
		return null;
	}

	@Specialization(guards = { "type != null" })
	public Object executeDefault(VirtualFrame frame, Object type, @Cached("context().getDefaultValues()") IDefaultValueProvider defaultProvider) {
		return defaultProvider.defaultValue(type);
	}
	
//	public static DefaultValue create(SourceSection source) {
//		return FrameNodeFactories.createDefaultValue(source);
//	}
}
