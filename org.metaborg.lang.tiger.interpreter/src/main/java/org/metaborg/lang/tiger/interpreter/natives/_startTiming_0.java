package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

public abstract class _startTiming_0 extends NativeOpBuild {

	public static long startTime = -1;
	
	public _startTiming_0(SourceSection source) {
		super(source);
	}

	@Specialization
	public int doStart() {
		startTime = System.currentTimeMillis();
		return (int) startTime;
	}

	public static NativeOpBuild create(SourceSection source) {
		return _startTiming_0NodeGen.create(source);
	}

}
