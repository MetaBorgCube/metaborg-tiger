package org.metaborg.lang.tiger.interp.scopesandframes.nodes.arrays;

import org.metaborg.lang.tiger.interp.scopesandframes.TigerTypesGen;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.Exp;
import org.metaborg.lang.tiger.interp.scopesandframes.nodes.TypeId;
import org.metaborg.lang.tiger.interp.scopesandframes.values.ArrayV_1;
import org.metaborg.lang.tiger.interp.scopesandframes.values.V;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.arrays.Array;
import org.metaborg.meta.lang.dynsem.interpreter.nabl2.f.arrays.nodes.NewArray;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoAppl;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.object.DynamicObject;

public final class Array_3 extends Exp {
	public final static String CONSTRUCTOR = "Array";

	public final static int ARITY = 3;

	private final TypeId _1;
	
	@Child private Exp lengthExp;
	
	@Child private Exp fillValueExp;
	
	@Child private NewArray newArrayNode;

	public Array_3(TypeId _1, Exp _2, Exp _3) {
		this(_1, _2, _3, null);
	}

	private Array_3(TypeId _1, Exp _2, Exp _3, IStrategoTerm strategoTerm) {
		this._1 = _1;
		this.lengthExp = _2;
		this.fillValueExp = _3;
		this.newArrayNode = new NewArray();
		this.strategoTerm = strategoTerm;
	}

	
	@Override
	public V executeGeneric(VirtualFrame frame, DynamicObject currentFrame) {
		int length = TigerTypesGen.asIntV_1(lengthExp.executeGeneric(frame, currentFrame)).get_1();
		V fillValue = fillValueExp.executeGeneric(frame, currentFrame);
		Array arr = newArrayNode.execute(frame, length, fillValue);
		return new ArrayV_1(arr);
	}

	@TruffleBoundary
	public static Array_3 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermAppl(term);
		assert Tools.hasConstructor((IStrategoAppl) term, CONSTRUCTOR, ARITY);
		return new Array_3(TypeId.create(term.getSubterm(0)), Exp.create(term.getSubterm(1)),
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
		sb.append(_1);
		sb.append(", ");
		sb.append(lengthExp);
		sb.append(", ");
		sb.append(fillValueExp);
		sb.append(")");
		return sb.toString();
	}

}