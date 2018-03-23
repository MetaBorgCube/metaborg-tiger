package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "stringbuild", type = TermBuild.class)
public abstract class flushS_1 extends NativeOpBuild {

	public flushS_1(SourceSection source) {
		super(source);
	}

	/**
	 * Flush the output buffer.
	 * 
	 * @param s Dummy parameter; I don't know how to hook up DynSem otherwise
	 * @return
	 */
	@Specialization
	@TruffleBoundary
	public int doString(String s) {
		System.out.flush();
		
		// TODO: I don't know how to return void in DynSem
		return 0;
	}

	public static NativeOpBuild create(SourceSection source, TermBuild stringbuild) {
		return flushS_1NodeGen.create(source, stringbuild);
	}
}
