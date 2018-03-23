package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "stringbuild", type = TermBuild.class)
public abstract class exitI_1 extends NativeOpBuild {

	public exitI_1(SourceSection source) {
		super(source);
	}

	/**
	 * Exit the program with exit code status.
	 * 
	 * @param i
	 * @return
	 */
	@Specialization
	@TruffleBoundary
	public int doInt(int i) {
		System.exit(i);
		
		return 0;
	}

	public static NativeOpBuild create(SourceSection source, TermBuild stringbuild) {
		return exitI_1NodeGen.create(source, stringbuild);
	}
}
