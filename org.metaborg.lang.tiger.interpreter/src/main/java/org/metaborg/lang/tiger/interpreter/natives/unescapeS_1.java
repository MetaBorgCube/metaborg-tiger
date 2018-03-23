package org.metaborg.lang.tiger.interpreter.natives;

import org.apache.commons.lang3.StringEscapeUtils;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "string", type = TermBuild.class)
public abstract class unescapeS_1 extends NativeOpBuild {

	public unescapeS_1(SourceSection source) {
		super(source);
	}
	
	/**
	 * After parsing, a string literal contains a leading and ending quote. In
	 * addition, it may contain escape sequences. This function makes a string
	 * literal a "real" string.
	 * 
	 * This uses Apache Commons Lang's unescape function, but we should write
	 * a custom function for Tiger: https://www.lrde.epita.fr/~tiger/tiger.html
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	@Specialization
	@TruffleBoundary
	public String doEscape(String string) {
		
		String unescaped = StringEscapeUtils.unescapeJava(string);
		String unquoted = unescaped.substring(1, unescaped.length()-1);
		
		return unquoted;
	}

	public static NativeOpBuild create(SourceSection source, TermBuild string) {
		return unescapeS_1NodeGen.create(source, string);
	}

}
