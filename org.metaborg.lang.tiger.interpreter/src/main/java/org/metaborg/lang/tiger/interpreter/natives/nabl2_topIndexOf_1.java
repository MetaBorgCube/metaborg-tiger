package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2TermBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;
import org.metaborg.meta.nabl2.stratego.TermIndex;
import org.metaborg.org.metaborg.lang.tiger.interpreter.generated.terms.ITermIndexTerm;
import org.metaborg.org.metaborg.lang.tiger.interpreter.generated.terms.TermIndex_2_Term;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused")
@NodeChild(value = "term", type = TermBuild.class)
public abstract class nabl2_topIndexOf_1 extends NaBL2TermBuild {
	public nabl2_topIndexOf_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public ITermIndexTerm doGet(org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm term) {
		TermIndex index = getTermIndex(term.getStrategoTerm());
		return new TermIndex_2_Term(index.getResource(), 0);
	}

	public static TermBuild create(SourceSection source, TermBuild term) {
		return nabl2_topIndexOf_1NodeGen.create(source, term);
	}
}