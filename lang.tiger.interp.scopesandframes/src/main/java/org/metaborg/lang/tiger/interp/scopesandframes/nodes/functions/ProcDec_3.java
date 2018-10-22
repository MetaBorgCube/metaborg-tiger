package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interpreter.generated.terms.FArg;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public abstract class ProcDec_3 extends FunDec {
	public final static String CONSTRUCTOR = "ProcDec";

	public final static int ARITY = 3;

	@TruffleBoundary
	public static FunDec create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoTerm[] fargTerms = Tools.listAt(term, 1).getAllSubterms();
		FArg[] fargs = new FArg[fargTerms.length];
		for (int i = 0; i < fargTerms.length; i++) {
			fargs[i] = FArg.create(fargTerms[i]);
		}
		return FunDec_4NodeGen.create(Occ.create(term.getSubterm(0)), fargs, null, Exp.create(term.getSubterm(2)),
				term);
	}

}