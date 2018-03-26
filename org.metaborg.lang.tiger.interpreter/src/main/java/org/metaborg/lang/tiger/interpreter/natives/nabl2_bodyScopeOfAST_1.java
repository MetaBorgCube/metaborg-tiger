package org.metaborg.lang.tiger.interpreter.natives;

import org.metaborg.lang.tiger.interpreter.generated.terms.IScopeTerm;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.NaBL2TermBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.NativeOpBuild;
import org.metaborg.meta.lang.dynsem.interpreter.nodes.building.TermBuild;
import org.metaborg.meta.lang.dynsem.interpreter.terms.ITerm;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.source.SourceSection;

import mb.nabl2.constraints.ast.AstProperties;

@NodeChild(value = "term", type = TermBuild.class)
public abstract class nabl2_bodyScopeOfAST_1 extends NaBL2TermBuild {
	public nabl2_bodyScopeOfAST_1(SourceSection source) {
		super(source);
	}

	@Specialization(limit = "1000", guards = { "term == cachedTerm" })
	public IScopeTerm doGet(ITerm term, @Cached("term") ITerm cachedTerm,
			@Cached("uncachedGet(cachedTerm)") IScopeTerm cachedScope) {
		return cachedScope;
	}

	@Specialization(replaces = "doGet")
	@TruffleBoundary
	protected IScopeTerm uncachedGet(ITerm term) {
		return IScopeTerm.create(getAstProperty(term.getStrategoTerm(), AstProperties.key("bodyScope")));
	}

	public static NativeOpBuild create(SourceSection source, TermBuild term) {
		return nabl2_bodyScopeOfAST_1NodeGen.create(source, term);
	}
}