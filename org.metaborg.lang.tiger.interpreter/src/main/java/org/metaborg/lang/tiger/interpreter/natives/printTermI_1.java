package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@NodeChild(value = "print", type = TermBuild.class)
public abstract class printTermI_1 extends TermBuild {

	public printTermI_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public Object doInt(Object s) {
		System.out.println(s.toString() + " " + s.getClass() + " (unknown class)");
		return s;
	}

	public static TermBuild create(SourceSection source, TermBuild print) {
		return printTermI_1NodeGen.create(source, print);
	}

}