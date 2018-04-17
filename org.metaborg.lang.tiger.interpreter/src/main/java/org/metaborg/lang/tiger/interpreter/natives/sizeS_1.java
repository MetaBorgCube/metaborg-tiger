package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "stringbuild", type = TermBuild.class)
public abstract class sizeS_1 extends NativeOpBuild {

	public sizeS_1(SourceSection source) {
		super(source);
	}

	/**
	 * Return the size in characters of the string.
	 * 
	 * @param s
	 * @return
	 */
	@Specialization
	@TruffleBoundary
	public int doString(String s) {
		return s.length();
	}

	public static NativeOpBuild create(SourceSection source, TermBuild stringbuild) {
		return sizeS_1NodeGen.create(source, stringbuild);
	}

}
