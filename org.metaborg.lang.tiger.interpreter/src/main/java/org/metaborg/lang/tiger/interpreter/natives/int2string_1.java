package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "t", type = TermBuild.class)
public abstract class int2string_1 extends NativeOpBuild {

	public int2string_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public String doInt(int i) {
		return i + "";
	}

	public static NativeOpBuild create(SourceSection source, TermBuild t) {
		return int2string_1NodeGen.create(source, t);
	}

}
