package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.Dec_1_Term;
import org.metaborg.lang.tiger.interpreter.generated.terms.IDecTerm;
import org.metaborg.lang.tiger.interpreter.generated.terms.Tuple_IScopeTerm_IScopeTerm;
import org.metaborg.lang.tiger.interpreter.generated.terms.checks.IS_IDecTerm;
import org.metaborg.lang.tiger.interpreter.generated.terms.checks.IS_IDec_1_MetaTerm;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2TermBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused")
@NodeChild(value = "term", type = IS_IDecTerm.class)
public abstract class nabl2_paramsOfIDecTerm_1 extends NaBL2TermBuild {
	public nabl2_paramsOfIDecTerm_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public Tuple_IScopeTerm_IScopeTerm doGet(IDecTerm term) {
		return Tuple_IScopeTerm_IScopeTerm.create(getAstProperty(term.getStrategoTerm(), "Params"));
	}

	public static NativeOpBuild create(SourceSection source, IS_IDecTerm term) {
		return nabl2_paramsOfIDecTerm_1NodeGen.create(source, term);
	}
}