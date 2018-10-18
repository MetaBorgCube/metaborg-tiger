package org.metaborg.lang.tiger.ninterpreter.truffle.nodes;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerContext;
import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.runtime.TigerLanguage;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.TruffleLanguage.ContextReference;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.nodes.RootNode;

@NodeInfo(language = "Tiger")
public class TigerRootNode extends RootNode {

	@Child
	Exp bodyNode;

	public TigerRootNode(TigerLanguage language, FrameDescriptor frameDescriptor, Exp bodyNode) {
		super(language, frameDescriptor);
		this.bodyNode = bodyNode;
	}

	@Override
	public TigerObject execute(VirtualFrame frame) {
		return bodyNode.executeGeneric(frame);
	}

	@CompilationFinal
	private ContextReference<TigerContext> contextRef;

	protected final TigerContext ctx() {
		if (contextRef == null) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			contextRef = this.getRootNode().getLanguage(TigerLanguage.class).getContextReference();
		}
		return contextRef.get();
	}

}
