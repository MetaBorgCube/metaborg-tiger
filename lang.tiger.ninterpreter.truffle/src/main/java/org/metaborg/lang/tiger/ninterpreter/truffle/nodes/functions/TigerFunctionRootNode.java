package org.metaborg.lang.tiger.ninterpreter.truffle.nodes.functions;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.Exp;
import org.metaborg.lang.tiger.ninterpreter.truffle.nodes.TigerRootNode;
import org.metaborg.lang.tiger.ninterpreter.truffle.runtime.TigerLanguage;

import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;

public class TigerFunctionRootNode extends TigerRootNode {

	private FrameSlot[] argSlots;
	private MaterializedFrame parentFrame;

	public TigerFunctionRootNode(TigerLanguage language, FrameDescriptor frameDescriptor, Frame parentFrame,
			FrameSlot[] argSlots, Exp bodyNode) {
		super(language, frameDescriptor, bodyNode);
		this.argSlots = argSlots;
		this.parentFrame = parentFrame.materialize();

	}

	@Override
	public TigerObject execute(VirtualFrame frame) {
		Object[] argValues = frame.getArguments();
		for (int i = 0; i < argSlots.length; i++) {
			frame.setObject(argSlots[i], argValues[i]);
		}
		frame.setObject(ctx().parentFrameSlot, parentFrame);
		return super.execute(frame);
	}

}
