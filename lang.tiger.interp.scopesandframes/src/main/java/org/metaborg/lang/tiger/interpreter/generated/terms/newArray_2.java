package org.metaborg.lang.tiger.interpreter.generated.terms;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.spoofax.interpreter.terms.IStrategoTerm;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.CompilerDirectives.CompilationFinal;
import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;

public final class newArray_2 extends newArray_2_Meta {
	public final static String CONSTRUCTOR = "newArray";

	public final static int ARITY = 2;

	public newArray_2(int _1, Ty _2) {
		this(_1, _2, null);
	}

	private newArray_2(int _1, Ty _2, IStrategoTerm strategoTerm) {
		this._1 = _1;
		this._2 = _2;
		this.strategoTerm = strategoTerm;
	}

	private final int _1;

	private final Ty _2;

	public int get_1() {
		return _1;
	}

	public Ty get_2() {
		return _2;
	}

	@TruffleBoundary
	public static newArray_2 create(IStrategoTerm term) {
		throw new IllegalStateException("Value terms and metafunctions cannot be created from Stratego terms");
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		newArray_2 other = (newArray_2) obj;
		if (_1 != other._1) {
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

	@CompilationFinal
	private boolean hashCodeComputed;

	@CompilationFinal
	private int hashcode;

	@Override
	public int hashCode() {
		if (!hashCodeComputed) {
			CompilerDirectives.transferToInterpreterAndInvalidate();
			hashcode = makeHashCode();
			hashCodeComputed = true;
		}
		return hashcode;
	}

	@TruffleBoundary
	private int makeHashCode() {
		return new HashCodeBuilder().append(_2).append(_1).toHashCode();
	}
}