package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

public abstract class _startTiming_0 extends TermBuild {

	public static int startTime = -1;
	
	public _startTiming_0(SourceSection source) {
		super(source);
	}

	@Specialization
	public int doStart() {
		startTime = (int) (System.currentTimeMillis() % 100000);
		return startTime;
	}

	public static TermBuild create(SourceSection source) {
		return _startTiming_0NodeGen.create(source);
	}

}
