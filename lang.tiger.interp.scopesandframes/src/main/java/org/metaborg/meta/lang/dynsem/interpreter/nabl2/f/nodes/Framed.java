package org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.IWithScopesAndFramesContext;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.ScopesAndFramesNode;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.GetScopeOfTerm;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.GetScopeOfTermNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Framed extends ScopesAndFramesNode {

	@Child private GetScopeOfTerm scopeOfTermNode;
	@Child private NewFrame2 newFrameNode;
	@Child private Links addLinksNode;
	
	public Framed(TruffleLanguage<? extends IWithScopesAndFramesContext> language) {
		super(language);
		this.scopeOfTermNode = GetScopeOfTermNodeGen.create(language);
		this.newFrameNode = NewFrame2NodeGen.create(language);
		this.addLinksNode = new Links(language);
	}
	
	public DynamicObject executeNewFrame(VirtualFrame frame, ITerm t, FLink[] links) {
		ScopeIdentifier sid = scopeOfTermNode.execute(frame, t);
		DynamicObject newFrame = newFrameNode.execute(frame, sid);
		addLinksNode.execute(frame, newFrame, links);
		return newFrame;
	}

	
	
}
