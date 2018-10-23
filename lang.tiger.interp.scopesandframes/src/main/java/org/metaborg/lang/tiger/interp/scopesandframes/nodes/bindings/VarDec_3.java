package org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.Occ;
import org.metaborg.lang.tiger.interpreter.generated.terms.Ty;
import org.metaborg.lang.tiger.interpreter.generated.terms.__Occurrence2Occ___1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlot;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetFrameSlotNodeGen;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.sg.Occurrence;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class VarDec_3 extends Dec {
	public final static String CONSTRUCTOR = "VarDec";

	public final static int ARITY = 3;

	private final Occurrence decOcc;

	private final Ty _2;

	@Child
	private Exp exp;

	@Child
	private SetFrameSlot setNode;
	
	public VarDec_3(Occ _1, Ty _2, Exp _3) {
		this(_1, _2, _3, null);
	}

	public VarDec_3(Occ _1, Ty _2, Exp _3, IStrategoTerm strategoTerm) {
		this.decOcc = ((__Occurrence2Occ___1) _1).get_1();
		this._2 = _2;
		this.exp = _3;
		this.strategoTerm = strategoTerm;
		this.setNode = SetFrameSlotNodeGen.create();
	}

	@Override
	public void execute(VirtualFrame frame, DynamicObject f, DynamicObject f_outer) {
//		@formatter:off
//		  Frames1 (F, F_outer) |- Dec(VarDec(x : Occurrence, t, e)) --> U()
//		  where
//		    F F_outer |- e --> v2;
//		    set(Addr(F, x), v2) => _
//		@formatter:on
		V v2 = exp.executeGeneric(frame, f_outer);
		setNode.execute(frame, f, decOcc, v2);
	}

	@TruffleBoundary
	public static VarDec_3 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new VarDec_3(Occ.create(term.getSubterm(0)), Ty.create(term.getSubterm(1)),
				Exp.create(term.getSubterm(2)), term);
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
		sb.append(_2);
		sb.append(", ");
		sb.append(exp);
		sb.append(")");
		return sb.toString();
	}

}