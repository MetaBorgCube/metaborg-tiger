package org.metaborg.lang.tiger.interp.scopesandframes.nodes;

import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.lang.tiger.interpreter.generated.terms.V;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FLink;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.Framed;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.object.DynamicObject;

public final class Let_2 extends Exp {
	public final static String CONSTRUCTOR = "Let";

	public final static int ARITY = 2;

	@Child
	private LetDecs decs;
	@Children
	private final Exp[] bodyExps;

	@Child
	private Framed createFrameNode;

	public Let_2(Dec[] decs, Exp[] bodyExps) {
		this(decs, bodyExps, null);
	}

	private Let_2(Dec[] decs, Exp[] bodyExps, IStrategoTerm strategoTerm) {
		this.decs = LetDecs.create(decs);
		this.bodyExps = bodyExps;
		this.strategoTerm = strategoTerm;
		this.createFrameNode = new Framed();
	}

	@Override
	@ExplodeLoop
	public V executeGeneric(VirtualFrame frame, DynamicObject f) {
//		@formatter:off
//		  F F |- l@Let(blocks, exps) --> vv
//		  where
//		    framed(l, []) --> F_body;
//		    Frames1 (F, F_body) |- Decs(blocks) --> _;
//		    F F_body |- Seq(exps) --> vv
//		@formatter:on
		DynamicObject f_body = this.createFrameNode.executeNewFrame(frame, this, new FLink[] {});
		decs.execute(frame, f, f_body);
		V result = UnitV_0.SINGLETON;
		for(int i = 0; i < bodyExps.length; i++) {
			result = bodyExps[i].executeGeneric(frame, f_body);
		}
		return result;
	}

	@TruffleBoundary
	public static Let_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoTerm[] decTerms = Tools.listAt(term, 0).getAllSubterms();
		Dec[] decs = new Dec[decTerms.length];
		for (int i = 0; i < decs.length; i++) {
			decs[i] = Dec.create(decTerms[i]);
		}
		IStrategoTerm[] expTerms = Tools.listAt(term, 1).getAllSubterms();
		Exp[] exps = new Exp[expTerms.length];
		for (int i = 0; i < exps.length; i++) {
			exps[i] = Exp.create(expTerms[i]);
		}
		return new Let_2(decs, exps, term);
	}

	private final IStrategoTerm strategoTerm;

	@Override
	public int size() {
		return ARITY;
	}

	@Override
	public boolean hasStrategoTerm() {
		return strategoTerm != null;
	}

	@Override
	public IStrategoTerm getStrategoTerm() {
		return strategoTerm;
	}

	@TruffleBoundary
	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append(CONSTRUCTOR);
		sb.append("(");
		sb.append(decs);
		sb.append(", ");
		sb.append(bodyExps);
		sb.append(")");
		return sb.toString();
	}

}