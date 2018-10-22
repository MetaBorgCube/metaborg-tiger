package org.metaborg.lang.tiger.interp.scopesandframes.nodes.arrays;

import org.metaborg.lang.tiger.interp.scopesandframes.nodes.LValue;
import org.metaborg.lang.tiger.interp.scopesandframes.values.ArrayV_1;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.Addr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.arrays.Array;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddr;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.nodes.GetAtAddrNodeGen;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Subscript_2 extends LValue {
	public final static String CONSTRUCTOR = "Subscript";

	public final static int ARITY = 2;

	@Child
	private LValue addressNode;

	@Child
	private Index indexNode;

	@Child
	private GetAtAddr getNode;

	public Subscript_2(LValue _1, Index _2) {
		this(_1, _2, null);
	}

	private Subscript_2(LValue _1, Index _2, IStrategoTerm strategoTerm) {
		this.addressNode = _1;
		this.indexNode = _2;
		this.getNode = GetAtAddrNodeGen.create();
		this.strategoTerm = strategoTerm;
	}

	@Override
	public Addr execute(VirtualFrame frame, DynamicObject currentFrame) {
		ArrayV_1 arrv = (ArrayV_1) getNode.execute(frame, addressNode.execute(frame, currentFrame));
		Array arr = arrv.get_1();
		return arr.lookup(indexNode.execute(frame, currentFrame));
	}

	@TruffleBoundary
	public static Subscript_2 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Subscript_2(LValue.create(term.getSubterm(0)), Index.create(term.getSubterm(1)), term);
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
		sb.append(addressNode);
		sb.append(", ");
		sb.append(indexNode);
		sb.append(")");
		return sb.toString();
	}

}