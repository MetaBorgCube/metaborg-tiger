package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChildren({ @NodeChild(value = "left", type = TermBuild.class),
		@NodeChild(value = "right", type = TermBuild.class) })
public abstract class eqS_2 extends NativeOpBuild {

	public eqS_2(SourceSection source) {
		super(source);
	}

	@Specialization
	@TruffleBoundary
	public int doInt(String left, String right) {
		return left.equals(right) ? 1 : 0;
	}

	public static NativeOpBuild create(SourceSection source, TermBuild left, TermBuild right) {
		return eqS_2NodeGen.create(source, left, right);
	}

}
