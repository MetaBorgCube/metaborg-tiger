package org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.LValue;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.lang.tiger.interpreter.generated.terms.UnitV_0;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.SetAtAddrNodeGen;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Assign_2 extends Exp {
	public final static String CONSTRUCTOR = "Assign";

	public final static int ARITY = 2;

	@Child
	private LValue _1;

	@Child
	private Exp _2;

	@Child
	private SetAtAddr setNode;

	public Assign_2(LValue _1, Exp _2) {
		this(_1, _2, null);
	}

	private Assign_2(LValue _1, Exp _2, IStrategoTerm strategoTerm) {
		this._1 = _1;
		this._2 = _2;
		this.setNode = SetAtAddrNodeGen.create();
		this.strategoTerm = strategoTerm;
	}

	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		Addr addr = _1.execute(frame, currentFrame);
		V v = _2.executeGeneric(frame, currentFrame);
		setNode.execute(frame, addr, v);
		return UnitV_0.SINGLETON;
	}

	@TruffleBoundary
	public static Assign_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Assign_2(LValue.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)), term);
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
		sb.append(_1);
		sb.append(", ");
		sb.append(_2);
		sb.append(")");
		return sb.toString();
	}

}