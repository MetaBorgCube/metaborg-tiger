package org.metaborg.lang.tiger.interp.scopesandframes;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Module;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.InitProtoFrames;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.InitNaBL2Node;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public class TigerRootNode extends RootNode {

	@Child
	private Module programNode;
	
	@Child private InitNaBL2Node initNabl2;
	@Child private InitProtoFrames initProtoFrames;

	public TigerRootNode(TigerLanguage language, FrameDescriptor frameDescriptor, Module programNode) {
		super(language, frameDescriptor);
		this.programNode = programNode;
		this.initNabl2 = new InitNaBL2Node(language);
		this.initProtoFrames = new InitProtoFrames(language);
	}

	@Override
	public Object execute(VirtualFrame frame) {
		initNabl2.execute(frame);
		initProtoFrames.execute(frame);
		// FIXME: built-ins?
		return programNode.executeGeneric(frame);
	}

}
