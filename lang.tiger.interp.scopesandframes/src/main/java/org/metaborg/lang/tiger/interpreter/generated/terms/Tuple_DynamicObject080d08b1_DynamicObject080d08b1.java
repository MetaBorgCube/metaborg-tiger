package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.metaborg.meta.lang.dynsem.interpreter.terms.ITupleTerm;
import org.spoofax.interpreter.core.Tools;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class Tuple_DynamicObject080d08b1_DynamicObject080d08b1 implements ITupleTerm {
	public final static int ARITY = 2;

	public Tuple_DynamicObject080d08b1_DynamicObject080d08b1(com.oracle.truffle.api.object.DynamicObject _1,
			com.oracle.truffle.api.object.DynamicObject _2) {
		this(_1, _2, null);
	}

	private Tuple_DynamicObject080d08b1_DynamicObject080d08b1(com.oracle.truffle.api.object.DynamicObject _1,
			com.oracle.truffle.api.object.DynamicObject _2, IStrategoTerm strategoTerm) {
		this._1 = _1;
		this._2 = _2;
		this.strategoTerm = strategoTerm;
	}

	private final com.oracle.truffle.api.object.DynamicObject _1;

	private final com.oracle.truffle.api.object.DynamicObject _2;

	public com.oracle.truffle.api.object.DynamicObject get_1() {
		return _1;
	}

	public com.oracle.truffle.api.object.DynamicObject get_2() {
		return _2;
	}

	@TruffleBoundary
	public static Tuple_DynamicObject080d08b1_DynamicObject080d08b1 create(IStrategoTerm term) {
		CompilerAsserts.neverPartOfCompilation();
		assert term != null;
		assert Tools.isTermTuple(term);
		assert term.getSubtermCount() == ARITY;
		return new Tuple_DynamicObject080d08b1_DynamicObject080d08b1(null, null, term);
	}

	private final IStrategoTerm strategoTerm;

	@Override
	public int size() {
		return ARITY;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tuple_DynamicObject080d08b1_DynamicObject080d08b1 other = (Tuple_DynamicObject080d08b1_DynamicObject080d08b1) obj;
		if (_1 == null) {
			if (other._1 != null) {
				return false;
			}
		} else if (!_1.equals(other._1)) {
			return false;
		}
		if (_2 == null) {
			if (other._2 != null) {
				return false;
			}
		} else if (!_2.equals(other._2)) {
			return false;
		}
		return true;
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
		sb.append("(");
		sb.append(_1);
		sb.append(", ");
		sb.append(_2);
		sb.append(")");
		return sb.toString();
	}

	@Override
	@TruffleBoundary
	public int hashCode() {
		return new HashCodeBuilder().append(_2).append(_1).toHashCode();
	}
}