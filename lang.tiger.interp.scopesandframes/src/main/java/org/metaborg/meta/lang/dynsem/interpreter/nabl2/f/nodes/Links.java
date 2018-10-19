package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IWithScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Links extends ScopesAndFramesNode {

	@Child
	private AddFrameLink linkNode;

	public Links(TruffleLanguage<? extends IWithScopesAndFramesContext> language) {
		super(language);
		this.linkNode = AddFrameLinkNodeGen.create(language);
	}

	public void execute(VirtualFrame frame, DynamicObject f, FLink[] links) {
		for (FLink link : links) {
			linkNode.execute(frame, f, link);
		}
	}

}
