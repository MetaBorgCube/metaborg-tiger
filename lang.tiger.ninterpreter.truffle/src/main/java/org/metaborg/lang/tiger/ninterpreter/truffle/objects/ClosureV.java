package org.metaborg.lang.tiger.ninterpreter.truffle.objects;

import org.metaborg.lang.tiger.ninterpreter.truffle.TigerObject;

import com.oracle.truffle.api.CallTarget;

public class ClosureV implements TigerObject {

	private final CallTarget callTarget;

	public ClosureV(CallTarget callTarget) {
		this.callTarget = callTarget;
	}

	public CallTarget getCallTarget() {
		return callTarget;
	}

}
