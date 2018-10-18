package org.metaborg.lang.tiger.ninterpreter.truffle.objects;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;

import com.oracle.truffle.api.frame.MaterializedFrame;

public class RecordV implements TigerObject {

	private MaterializedFrame recordFrame;

	public RecordV(MaterializedFrame recordFrame) {
		this.recordFrame = recordFrame;
	}

}
