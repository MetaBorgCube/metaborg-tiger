package org.metaborg.lang.tiger.ninterpreter.truffle;

import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;

public class TigerContext {

	public final FrameDescriptor baseDescriptor;
	public final FrameSlot parentFrameSlot;

	public TigerContext() {
		baseDescriptor = new FrameDescriptor();
		parentFrameSlot = baseDescriptor.addFrameSlot("$_PARENT_FRAME_$");
	}

}
