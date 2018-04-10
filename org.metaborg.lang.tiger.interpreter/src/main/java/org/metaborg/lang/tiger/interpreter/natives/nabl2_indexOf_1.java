package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.ITermIndexTerm;
import org.metaborg.lang.tiger.interpreter.generated.terms.TermIndex_2_Term;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2TermBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

import mb.nabl2.stratego.TermIndex;

@SuppressWarnings("unused")
@NodeChild(value = "term", type = TermBuild.class)
public abstract class nabl2_indexOf_1 extends NaBL2TermBuild {
	public nabl2_indexOf_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public ITermIndexTerm doGet(org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm term) {
		TermIndex index = getTermIndex(term.getStrategoTerm());
		return new TermIndex_2_Term(index.getResource(), index.getId());
	}

	public static NativeOpBuild create(SourceSection source, TermBuild term) {
		return nabl2_indexOf_1NodeGen.create(source, term);
	}
}