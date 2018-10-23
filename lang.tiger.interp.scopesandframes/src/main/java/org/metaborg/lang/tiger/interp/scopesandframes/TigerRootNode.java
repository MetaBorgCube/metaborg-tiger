package org.metaborg.lang.tiger.interp.scopesandframes;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.layouts.FrameUtils;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public class TigerRootNode extends RootNode {

	private final String name;

	@Child
	private TigerEvalNode programNode;

	public TigerRootNode(TigerLanguage language, FrameDescriptor frameDescriptor, String name,
			TigerEvalNode programNode) {
		super(language, frameDescriptor);
		this.name = name;
		this.programNode = programNode;
	}

	@Override
	public Object execute(VirtualFrame frame) {
		return programNode.executeGeneric(frame, FrameUtils.asFrame(frame.getArguments()[0]));
	}

	@Override
	public String getName() {
		return "<root:" + name + ">";
	}

	@Override
	public String toString() {
		return getName();
	}

}
