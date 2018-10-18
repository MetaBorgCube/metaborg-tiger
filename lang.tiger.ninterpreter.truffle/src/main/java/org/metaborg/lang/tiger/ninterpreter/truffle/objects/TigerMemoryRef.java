package org.metaborg.lang.tiger.ninterpreter.truffle.objects;

import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.MaterializedFrame;

public class TigerMemoryRef {

	private final FrameSlot slot;
	private final MaterializedFrame frame;

	public TigerMemoryRef(FrameSlot slot, MaterializedFrame frame) {
		this.slot = slot;
		this.frame = frame;
	}

	public FrameSlot slot() {
		return slot;
	}

	public MaterializedFrame frame() {
		return frame;
	}

}
