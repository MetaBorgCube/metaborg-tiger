package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "string", type = TermBuild.class)
public abstract class chrI_1 extends NativeOpBuild {

	public chrI_1(SourceSection source) {
		super(source);
	}

	/**
	 * Return the one character long string containing the character which code is
	 * code. If code does not belong to the range [0..255], raise a runtime error:
	 * 'chr: character out of range'.
	 * 
	 * @param i
	 * @return
	 */
	@Specialization
	@TruffleBoundary
	public String doInt(int i) {
		if (i < 0 || i > 255) {
			throw new RuntimeException("chr: character out of range");
		}

		return Character.toString((char) i);
	}

	public static NativeOpBuild create(SourceSection source, TermBuild string) {
		return chrI_1NodeGen.create(source, string);
	}
}
