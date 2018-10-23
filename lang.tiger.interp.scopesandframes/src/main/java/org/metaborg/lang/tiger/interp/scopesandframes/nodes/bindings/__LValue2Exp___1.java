package org.metaborg.lang.tiger.interp.scopesandframes.nodes.bindings;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.LValue;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddrNodeGen;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class __LValue2Exp___1 extends Exp {
	public final static String CONSTRUCTOR = "__LValue2Exp__";

	public final static int ARITY = 1;

	@Child
	private LValue lv;

	@Child
	private GetAtAddr getAtAddrNode;

	public __LValue2Exp___1(LValue _1) {
		this(_1, null);
	}

	private __LValue2Exp___1(LValue _1, IStrategoTerm strategoTerm) {
		this.lv = _1;
		this.strategoTerm = strategoTerm;
		this.getAtAddrNode = GetAtAddrNodeGen.create();
	}

	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		Addr addr = this.lv.execute(frame, currentFrame);
		return (V) this.getAtAddrNode.execute(frame, addr);
	}

	@TruffleBoundary
	public static __LValue2Exp___1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new __LValue2Exp___1(LValue.create(term.getSubterm(0)), term);
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
		sb.append(lv);
		sb.append(")");
		return sb.toString();
	}

}