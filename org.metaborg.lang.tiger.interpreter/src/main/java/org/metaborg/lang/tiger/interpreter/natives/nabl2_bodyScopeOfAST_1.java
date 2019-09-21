package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.Scope;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

@SuppressWarnings("unused")
@NodeChild(value = "term", type = TermBuild.class)
public abstract class nabl2_bodyScopeOfAST_1 extends NativeOpBuild {
	public nabl2_bodyScopeOfAST_1(SourceSection source) {
		super(source);
	}

	@Specialization
	public Scope doGet(org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm term) {
		return Scope.create(getAstProperty(term.getStrategoTerm(), getAstPropertyKey("bodyScope")));
	}

	public static NativeOpBuild create(SourceSection source, TermBuild term) {
		return nabl2_bodyScopeOfAST_1NodeGen.create(source, term);
	}
}