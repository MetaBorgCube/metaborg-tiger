package org.metaborg.meta.lang.dynsem.interpreter.nabl2;

import java.util.Optional;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerLanguage;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.TruffleLanguage.ContextReference;
import com.oracle.truffle.api.nodes.Node;

import mb.nabl2.constraints.ast.AstProperties;
import mb.nabl2.stratego.ConstraintTerms;
import mb.nabl2.stratego.StrategoTermIndices;
import mb.nabl2.stratego.TermIndex;
import mb.nabl2.terms.ITerm;

public abstract class ScopesAndFramesNode extends Node {

	public ScopesAndFramesNode() {
	}


	@CompilationFinal
	private TigerLanguage language;

	public final TigerLanguage language() {
		if (language == null) {
			language = this.getRootNode().getLanguage(TigerLanguage.class);
		}

		return language;
	}

	@CompilationFinal
	private ContextReference<? extends IWithScopesAndFramesContext> ctxRef;

	public final ScopesAndFramesContext context() {
		if (ctxRef == null) {
			ctxRef = language().getContextReference();
		}
		return ctxRef.get().getScopesAndFramesContext();
	}

	protected IStrategoTerm getAstProperty(IStrategoTerm sterm, String key) {
		return getAstProperty(sterm, getAstPropertyKey(key));
	}

	protected IStrategoTerm getAstProperty(IStrategoTerm sterm, ITerm key) {
		TermIndex index = getTermIndex(sterm);
		NaBL2Context nabl2ctx = context().getNaBL2Context();
		Optional<ITerm> val = internal_getPropertyValue(nabl2ctx, index, key);
		if (!val.isPresent()) {
			throw new IllegalArgumentException("Node has no " + key + " parameter");
		}
		return safeToStratego(nabl2ctx, val.get());
	}

	@TruffleBoundary
	private static Optional<ITerm> internal_getPropertyValue(NaBL2Context nabl2ctx, TermIndex index, ITerm key) {
		Optional<ITerm> prop = nabl2ctx.getSolution().astProperties().getValue(index, key);
		return prop;
	}

	@TruffleBoundary
	protected static TermIndex getTermIndex(IStrategoTerm sterm) {
		if (sterm == null) {
			throw new IllegalArgumentException("Primitive must be called on an AST node.");
		}
		return StrategoTermIndices.get(sterm).orElseThrow(() -> new IllegalArgumentException("Node has no index."));
	}

	@TruffleBoundary
	private static IStrategoTerm safeToStratego(NaBL2Context nabl2ctx, ITerm term) {
		term = ConstraintTerms.explicate(term);
		return nabl2ctx.getStrategoTerms().toStratego(term);
	}

	@TruffleBoundary
	protected static ITerm getAstPropertyKey(String propName) {
		return AstProperties.key(propName);
	}

}
