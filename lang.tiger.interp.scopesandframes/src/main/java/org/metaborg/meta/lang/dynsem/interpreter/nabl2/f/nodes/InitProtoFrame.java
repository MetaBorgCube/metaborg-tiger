package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IWithScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeEntryLayoutImpl;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public abstract class InitProtoFrame extends ScopesAndFramesNode {

	@Child private CreateProtoFrame protoFrameFactory;

	public InitProtoFrame(TruffleLanguage<? extends IWithScopesAndFramesContext> language) {
		super(language);
		this.protoFrameFactory = new CreateProtoFrame(language);
	}

	public abstract void execute(VirtualFrame frame, Object scopeEntry);
	
	@Specialization(guards = { "isScopeEntry(scopeEntry)" })
	public void executeScopeEntry(VirtualFrame frame, DynamicObject scopeEntry) {
		DynamicObject protoFrame = protoFrameFactory.execute(frame, scopeEntry);
		assert FrameLayoutImpl.INSTANCE.isFrame(protoFrame);
		assert FrameLayoutImpl.INSTANCE.getScope(protoFrame) == ScopeEntryLayoutImpl.INSTANCE.getIdentifier(scopeEntry);
		context().addProtoFrame(ScopeEntryLayoutImpl.INSTANCE.getIdentifier(scopeEntry), protoFrame);
	}

	protected static boolean isScopeEntry(DynamicObject scopeEntry) {
		return ScopeEntryLayoutImpl.INSTANCE.isScopeEntry(scopeEntry);
	}

}
