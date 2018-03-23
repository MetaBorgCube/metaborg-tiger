package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "stringbuild", type = TermBuild.class)
public abstract class printI_1 extends NativeOpBuild {

	public printI_1(SourceSection source) {
		super(source);
	}

	@Specialization
	@TruffleBoundary
	public String doString(int i) {
		final String s = i + "";
		System.out.println(s);
		return s;
	}

	public static NativeOpBuild create(SourceSection source, TermBuild stringbuild) {
		return printI_1NodeGen.create(source, stringbuild);
	}
}
