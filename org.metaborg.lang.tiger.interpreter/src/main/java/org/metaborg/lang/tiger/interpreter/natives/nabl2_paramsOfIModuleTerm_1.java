package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.IModuleTerm;
import org.metaborg.lang.tiger.interpreter.generated.terms.IScopeTerm;
import org.metaborg.lang.tiger.interpreter.generated.terms.Mod_1_Term;
import org.metaborg.lang.tiger.interpreter.generated.terms.checks.IS_IModuleTerm;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2TermBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused")
@NodeChild(value = "term", type = IS_IModuleTerm.class)
public abstract class nabl2_paramsOfIModuleTerm_1 extends NaBL2TermBuild {
	public nabl2_paramsOfIModuleTerm_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public IScopeTerm doGet(Mod_1_Term term) {
		return IScopeTerm.create(getAstProperty(term.getStrategoTerm(), "Params"));
	}

	public static NativeOpBuild create(SourceSection source, IS_IModuleTerm term) {
		return nabl2_paramsOfIModuleTerm_1NodeGen.create(source, term);
	}
}