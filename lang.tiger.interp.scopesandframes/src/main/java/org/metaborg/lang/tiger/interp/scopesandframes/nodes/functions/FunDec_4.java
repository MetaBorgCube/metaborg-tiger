package org.metaborg.lang.tiger.interp.scopesandframes.nodes.functions;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerLanguage;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.FunV;
import org.metaborg.lang.tiger.interpreter.generated.terms.FArg;
import org.metaborg.lang.tiger.interpreter.generated.terms.FArg_2;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.metaborg.lang.tiger.interpreter.generated.terms.Ty;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.FrameAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetAtAddrNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.ScopeIdentifier;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.GetScopeOfTerm;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.nodes.GetScopeOfTermNodeGen;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeUtil;
import com.oracle.truffle.api.object.DynamicObject;

public final class FunDec_4 extends FunDec {
	public final static String CONSTRUCTOR = "FunDec";

	public final static int ARITY = 4;

	private final Occurrence decOcc;

	private final FArg[] fargs;

	private final Ty ty;

	private final Exp body;

	@Child
	private GetScopeOfTerm scopeOfTermNode;
	@Child
	private SetAtAddr setNode;

	public FunDec_4(Occ _1, FArg[] _2, Ty _3, Exp _4) {
		this(_1, _2, _3, _4, null);
	}

	public FunDec_4(Occ _1, FArg[] _2, Ty _3, Exp _4, IStrategoTerm strategoTerm) {
		this.decOcc = ((__Occurrence2Occ___1) _1).get_1();
		this.fargs = _2;
		this.ty = _3;
		this.body = _4;
		this.strategoTerm = strategoTerm;
		this.scopeOfTermNode = GetScopeOfTermNodeGen.create();
		this.setNode = SetAtAddrNodeGen.create();
	}

	@CompilationFinal
	private CallTarget functionTarget;

	private CallTarget getCallTarget(VirtualFrame frame) {
		if (functionTarget == null) {
			ScopeIdentifier functionScope = scopeOfTermNode.execute(frame, this);
			CompilerDirectives.transferToInterpreterAndInvalidate();
			TigerLanguage language = getRootNode().getLanguage(TigerLanguage.class);
			Occurrence[] fArgOccs = new Occurrence[fargs.length];
			for(int i = 0; i < fArgOccs.length; i++) {
				fArgOccs[i] = ((__Occurrence2Occ___1)((FArg_2)fargs[i]).get_1()).get_1();
			}
			TigerFunctionRoot funRoot = new TigerFunctionRoot(language, functionScope, fArgOccs, NodeUtil.cloneNode(body));
			functionTarget = Truffle.getRuntime().createCallTarget(funRoot);
		}
		return functionTarget;
	}

	@Override
	public void execute(VirtualFrame frame, DynamicObject f, DynamicObject f_outer) {
//		@formatter:off
//		  Frames1 (F, F_outer) |- d@FunDec(f : Occurrence, args, t, e) --> U()
//		  where
//		    framed(d, [L(P(), F)]) --> F_fun; <--- DEFERRED
//		    set(Addr(F, f), FunV(F_fun, unpackArgs(args), e)) => _
//		@formatter:on
		CallTarget target = getCallTarget(frame);
		setNode.execute(frame, new FrameAddr(f, decOcc), new FunV(f, target));
	}

	@TruffleBoundary
	public static FunDec_4 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		IStrategoTerm[] fargTerms = Tools.listAt(term, 1).getAllSubterms();
		FArg[] fargs = new FArg[fargTerms.length];
		for (int i = 0; i < fargTerms.length; i++) {
			fargs[i] = FArg.create(fargTerms[i]);
		}
		return new FunDec_4(Occ.create(term.getSubterm(0)), fargs, Ty.create(term.getSubterm(2)),
				Exp.create(term.getSubterm(3)), term);
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
		sb.append(decOcc);
		sb.append(", ");
		sb.append(fargs);
		sb.append(", ");
		sb.append(ty);
		sb.append(", ");
		sb.append(body);
		sb.append(")");
		return sb.toString();
	}

}