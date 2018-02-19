package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2TermBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;
import org.metaborg.org.metaborg.lang.tiger.interpreter.generated.terms.IModuleTerm;
import org.metaborg.org.metaborg.lang.tiger.interpreter.generated.terms.IScopeTerm;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused")
@NodeChild(value = "term", type = TermBuild.class)
public abstract class nabl2_paramsOfIModuleTerm_1 extends NaBL2TermBuild {
	public nabl2_paramsOfIModuleTerm_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public IScopeTerm doGet(IModuleTerm term) {
		return IScopeTerm.create(getAstProperty(term.getStrategoTerm(), "Params"));
	}

	public static TermBuild create(SourceSection source, TermBuild term) {
		return nabl2_paramsOfIModuleTerm_1NodeGen.create(source, term);
	}
}