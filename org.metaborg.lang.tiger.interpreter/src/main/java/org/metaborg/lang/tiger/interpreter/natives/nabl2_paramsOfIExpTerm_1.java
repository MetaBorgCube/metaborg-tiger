package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.IExpTerm;
import org.metaborg.lang.tiger.interpreter.generated.terms.IScopeTerm;
import org.metaborg.lang.tiger.interpreter.generated.terms.checks.IS_IExpTerm;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2TermBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused")
@NodeChild(value = "term", type = IS_IExpTerm.class)
public abstract class nabl2_paramsOfIExpTerm_1 extends NaBL2TermBuild {
	public nabl2_paramsOfIExpTerm_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public IScopeTerm doGet(IExpTerm term) {
		return IScopeTerm.create(getAstProperty(term.getStrategoTerm(), "Params"));
	}

	public static NativeOpBuild create(SourceSection source, IS_IExpTerm term) {
		return nabl2_paramsOfIExpTerm_1NodeGen.create(source, term);
	}
}