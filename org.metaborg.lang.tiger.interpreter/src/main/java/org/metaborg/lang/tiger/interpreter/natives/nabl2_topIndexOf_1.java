package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.TermIndex_2;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

import mb.nabl2.terms.stratego.TermIndex;

@SuppressWarnings("unused")
@NodeChild(value = "term", type = TermBuild.class)
public abstract class nabl2_topIndexOf_1 extends NativeOpBuild {
	public nabl2_topIndexOf_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public org.metaborg.lang.tiger.interpreter.generated.terms.TermIndex doGet(org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm term) {
		TermIndex index = getTermIndex(term.getStrategoTerm());
		return new TermIndex_2(index.getResource(), 0);
	}

	public static NativeOpBuild create(SourceSection source, TermBuild term) {
		return nabl2_topIndexOf_1NodeGen.create(source, term);
	}
}