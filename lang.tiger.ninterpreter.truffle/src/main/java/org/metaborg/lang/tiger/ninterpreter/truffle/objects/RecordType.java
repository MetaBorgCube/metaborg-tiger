package org.metaborg.lang.tiger.ninterpreter.truffle.objects;

import com.oracle.truffle.api.frame.FrameDescriptor;

public class RecordType extends TypeV {

	private final FrameDescriptor recordDescriptor;

	public RecordType(FrameDescriptor recordDescriptor) {
		this.recordDescriptor = recordDescriptor;
	}

}
