package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "term", type = TermBuild.class)
public abstract class _printTerm_1 extends TermBuild {

	public _printTerm_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public Object doInt(Object s) {
		System.out.println(s.toString() + " " + s.getClass() + " (unknown class)");
		return s;
	}

	public static TermBuild create(SourceSection source, TermBuild print) {
		return _printTerm_1NodeGen.create(source, print);
	}

}