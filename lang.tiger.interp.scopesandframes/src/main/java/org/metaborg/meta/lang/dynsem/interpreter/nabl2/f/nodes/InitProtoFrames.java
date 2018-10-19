package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IWithScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.NaBL2LayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopeGraphLayoutImpl;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.layouts.ScopesLayoutImpl;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;
import com.oracle.truffle.api.object.Property;

public class InitProtoFrames extends ScopesAndFramesNode {

	@Child
	private InitProtoFrame initProtoFrame;

	public InitProtoFrames(TruffleLanguage<? extends IWithScopesAndFramesContext> language) {
		super(language);
		this.initProtoFrame = FrameNodeFactories.createInitProtoFrame(language);
	}

	public void execute(VirtualFrame frame) {
		DynamicObject scopes = ScopeGraphLayoutImpl.INSTANCE
				.getScopes(NaBL2LayoutImpl.INSTANCE.getScopeGraph(context().getNaBL2Solution()));
		assert ScopesLayoutImpl.INSTANCE.isScopes(scopes);
		for (Property scopeIdentProp : scopes.getShape().getProperties()) {
			initProtoFrame.execute(frame, scopeIdentProp.get(scopes, true));
		}
	}

}
